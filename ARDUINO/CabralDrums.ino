
  // 0:cHIMBAL_FEC  / 1:SURDO / 2:ton_1 / 3: ton_3 / 4:PRATO_ATK / 5:CAIXA/ 6:BUMBO / 7: Prato_Cond
byte numPad[8] = {         0,    1,    2,    3,    4,    5,    6,   7};
int sensibilidadeMin[8] = {100,  100,  320,  320,  275,  110,  30,  325}; //quanto maior o numero, menor a sensibilidade
int sensibilidadeMax[8] = {1023, 1023, 1600, 1600, 1023, 1023, 200, 1023};
int toqueDuploMin[8] = {   10,   10,   10,   10,   35,   10,   15,  20}; //quanto maior o numero, menor a possibilidade de toque duplo
int toqueDuploMax[8] = {   90,   125,  90,   90,   130,  110,  110, 120};
byte tipoSensor[8] = {144, 144, 144, 144, 144, 144, 144, 144};

#define READPIN 1
#define READPIN 2
#define READPIN 3
#define READPIN 4
#define READPIN 5
#define READPIN 6
#define READPIN 7
#define READPIN 8

//int led = 13;
int lerSensor = 0;
unsigned long miliSegAnt[8];
unsigned long segAtual[8];
unsigned int leituraSensor[10];
int lerNum[8] = {0, 0, 0, 0, 0, 0, 0, 0};
boolean tocarPad[8] = {true, true, true, true, true, true, true, true};

int pin = 0;

int normalizarSensor(int sensor, int pin) {
  if (sensor < sensibilidadeMin[pin])
  {
    sensor = sensibilidadeMin[pin];
  } else if (sensor > sensibilidadeMax[pin])
  {
    sensor = sensibilidadeMax[pin];
  }

  if (tipoSensor[pin] == 176) {
    sensor = map(sensor, sensibilidadeMax[pin], sensibilidadeMin[pin], 1, 127);
  }
  else
  {
    sensor = map(sensor, 0, sensibilidadeMax[pin], 0, 127);
  }

  return sensor;
}

void MIDI_TX(int message, byte tipoPad, byte forca) {
  
  Serial.print(tipoPad);
  Serial.print(",");
  Serial.println(forca);
  delay(5);
}



void setup() {

  Serial.begin(115200);


}

void loop() {
  for (pin = 0; pin < READPIN; pin++)
  {
    lerSensor = analogRead(pin);

    if ((tipoSensor[pin] != 128) && (lerSensor > sensibilidadeMin[pin]) && ((segAtual[pin] - miliSegAnt[pin]) <= toqueDuploMax[pin]))
    {
      segAtual[pin] = millis();

      if (lerNum[pin] < toqueDuploMin[pin])
      {
        if (lerSensor > leituraSensor[pin])
        {
          leituraSensor[pin] = lerSensor;

        }
        lerNum[pin]++;
      } else if (tocarPad[pin] == true)
      {

        tocarPad[pin] = false;

        int forca = normalizarSensor(leituraSensor[pin], pin);
        MIDI_TX(tipoSensor[pin], numPad[pin], forca);
        //digitalWrite(led, HIGH);
        //digitalWrite(led, LOW);
      }

    } else if ((segAtual[pin] - miliSegAnt[pin]) > toqueDuploMax[pin])
    {
      tocarPad[pin] = true;
      lerNum[pin] = 0;
      leituraSensor[pin] = 0;
      miliSegAnt[pin] = segAtual[pin];
    }
  }
}
