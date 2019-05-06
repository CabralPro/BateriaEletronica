package Arduino;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;

public class Serial implements SerialPortEventListener {

    SerialPort serialPort = null;

    private Protocolo protocolo = new Protocolo();
    private String appName = "ard";//nome da aplicação
    private BufferedReader input; // objeto para leitura do serial
    private OutputStream output; //objeto de escrita para serial

    private static final int TIME_OUT = 1;
    private static int DATA_RATE = 115200;

    private String serialPortName = "";

    public boolean iniciaSerial() {
        boolean status = false;
        try {
            //obtem portas seriais do sistema
            CommPortIdentifier portId = null;
            Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();


            while (portEnum.hasMoreElements()) {
                CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();

               // if (currPortId.getName().equals(serialPortName) || currPortId.getName().startsWith(serialPortName)) {
                    serialPortName = currPortId.getName();
                    serialPort = (SerialPort) currPortId.open(appName, TIME_OUT);
                    portId = currPortId;
                    System.out.println("ARDUINO CONECTADO EM: " + currPortId.getName());
                    break;
               // }
            }
            if (portId == null || serialPort == null) {
                System.out.println("!!!FALHA NA CONEXAO COM O ARDUINO!!!");
                return false;
            } // depois q retorna falso a aplicação encerra aqui

            serialPort.setSerialPortParams(DATA_RATE, serialPort.DATABITS_8, serialPort.STOPBITS_1, serialPort.PARITY_NONE);

            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
            status = true;

            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
            //se deu certo retorne true
            return true;

        } catch (Exception e) {
            status = false;
        }
        //retorna falso
        return status;
    }

    //fecha a porta serial
    public synchronized void closeSerial() {
        if (serialPort != null) {
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent spe) {
        //chegada de dados
        try {
            switch (spe.getEventType()) {
                case SerialPortEvent.DATA_AVAILABLE:
                    if (input == null) {
                        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
                    }

                    if (input.ready()) {
                        protocolo.setLeituraComando(input.readLine());
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Protocolo getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(Protocolo protocolo) {
        this.protocolo = protocolo;
    }

    public String getSerialPortName() {
        return serialPortName;
    }

    public void setSerialPortName(String serialPortName) {
        this.serialPortName = serialPortName;
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

}
