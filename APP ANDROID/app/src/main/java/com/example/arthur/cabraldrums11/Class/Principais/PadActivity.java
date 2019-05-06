package com.example.arthur.cabraldrums11.Class.Principais;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.arthur.cabraldrums11.Class.Bancos.Banc.BancoSS;
import com.example.arthur.cabraldrums11.R;

public class PadActivity extends AppCompatActivity {

    public static Spinner spinnerNum[] = new Spinner[11];
    public static SeekBar vol[] = new SeekBar[11];
    public static TextView txVol[] = new TextView[11];
    public static TextView nomeBanco;
    public static Context context;
    private Button salvar;
    private Button salvarEusar;
    ProgressDialog progressDialog;
    Handler handler = new Handler();
    Bluetooth bluet = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pad);
        context = this;
        bluet = new Bluetooth(this, false);

        spinnerNum[1] = findViewById(R.id.numAro);
        spinnerNum[2] = findViewById(R.id.numBumbo);
        spinnerNum[3] = findViewById(R.id.numCaixa);
        spinnerNum[4] = findViewById(R.id.numChAbe);
        spinnerNum[5] = findViewById(R.id.numChFec);
        spinnerNum[6] = findViewById(R.id.numPatk);
        spinnerNum[7] = findViewById(R.id.numPcond);
        spinnerNum[8] = findViewById(R.id.numSurdo);
        spinnerNum[9] = findViewById(R.id.numTon1);
        spinnerNum[10] = findViewById(R.id.numTon2);

        vol[1] = findViewById(R.id.volAro);
        vol[2] = findViewById(R.id.volBumbo);
        vol[3] = findViewById(R.id.volCaixa);
        vol[4] = findViewById(R.id.volChAbe);
        vol[5] = findViewById(R.id.volChFec);
        vol[6] = findViewById(R.id.volPatk);
        vol[7] = findViewById(R.id.volPcond);
        vol[8] = findViewById(R.id.volSurdo);
        vol[9] = findViewById(R.id.volTon1);
        vol[10] = findViewById(R.id.volTon2);

        txVol[1] = findViewById(R.id.txVolAro);
        txVol[2] = findViewById(R.id.txVolBumbo);
        txVol[3] = findViewById(R.id.txVolCaixa);
        txVol[4] = findViewById(R.id.txVolChAbe);
        txVol[5] = findViewById(R.id.txVolChFec);
        txVol[6] = findViewById(R.id.txVolPatk);
        txVol[7] = findViewById(R.id.txVolPcond);
        txVol[8] = findViewById(R.id.txVolSurdo);
        txVol[9] = findViewById(R.id.txVolTon1);
        txVol[10] = findViewById(R.id.txVolTon2);

        nomeBanco = findViewById(R.id.nomeBancoX);
        salvar = findViewById(R.id.btSalvar);
        salvarEusar = findViewById(R.id.btSalvarEusar);

        ArrayAdapter arrayAdapter[] = new ArrayAdapter[11];

        arrayAdapter[1] = ArrayAdapter.createFromResource(this, R.array.numSonsAro, android.R.layout.simple_spinner_item);
        arrayAdapter[2] = ArrayAdapter.createFromResource(this, R.array.numSonsBumbo, android.R.layout.simple_spinner_item);
        arrayAdapter[3] = ArrayAdapter.createFromResource(this, R.array.numSonsCaixa, android.R.layout.simple_spinner_item);
        arrayAdapter[4] = ArrayAdapter.createFromResource(this, R.array.numSonsChimbal_Abe, android.R.layout.simple_spinner_item);
        arrayAdapter[5] = ArrayAdapter.createFromResource(this, R.array.numSonsChimbal_Fec, android.R.layout.simple_spinner_item);
        arrayAdapter[6] = ArrayAdapter.createFromResource(this, R.array.numSonsPrato_Atk, android.R.layout.simple_spinner_item);
        arrayAdapter[7] = ArrayAdapter.createFromResource(this, R.array.numSonsPrato_Cond, android.R.layout.simple_spinner_item);
        arrayAdapter[8] = ArrayAdapter.createFromResource(this, R.array.numSonsSurdo , android.R.layout.simple_spinner_item);
        arrayAdapter[9] = ArrayAdapter.createFromResource(this, R.array.numSonsTon_1,android.R.layout.simple_spinner_item);
        arrayAdapter[10] = ArrayAdapter.createFromResource(this, R.array.numSonsTon_2, android.R.layout.simple_spinner_item);

        for (int i = 1 ; i < spinnerNum.length ; i++){
            spinnerNum[i].setAdapter(arrayAdapter[i]);
            vol[i].setMax(40);
        }

        BancoSS.pegarDadosBanco(BancoSSActivity.clicBanco);


        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(PadActivity.this);
                progressDialog.setTitle("AGUARDE");
                progressDialog.setMessage("Enviando ...");
                progressDialog.show();

                context = PadActivity.this;

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                gravarEenviar();
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).start();
            }
        });

        salvarEusar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(PadActivity.this);
                progressDialog.setTitle("AGUARDE");
                progressDialog.setMessage("Enviando ...");
                progressDialog.show();

                context = PadActivity.this;

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                gravarEenviar();
                                bluet.enviarMsg("definirParaUso" ,nomeBanco.getText().toString());
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).start();
            }
        });

        seekbarProgress();
    }

    public static void gravarEenviar(){
       String auxiliar[]  = nomeBanco.getText().toString().split(" ");

        int numeroBanco = Integer.parseInt(auxiliar[1]);

        int numeroCaixa = spinnerNum[3].getSelectedItemPosition() +1;
        int volumeCaixa = vol[3].getProgress();

        int numeroTon1 = spinnerNum[9].getSelectedItemPosition() +1;
        int volumeTon1 = vol[9].getProgress();

        int numeroTon2 = spinnerNum[10].getSelectedItemPosition() +1;
        int volumeTon2 = vol[10].getProgress();

        int numeroSurdo = spinnerNum[8].getSelectedItemPosition() +1;
        int volumeSurdo = vol[8].getProgress();

        int numeroBumbo = spinnerNum[2].getSelectedItemPosition() +1;
        int volumeBumbo = vol[2].getProgress();

        int numeroPatk = spinnerNum[6].getSelectedItemPosition() +1;
        int volumePatk = vol[6].getProgress();

        int numeroPcond = spinnerNum[7].getSelectedItemPosition() +1;
        int volumePcond = vol[7].getProgress();

        int numeroChAbe = spinnerNum[4].getSelectedItemPosition() +1;
        int volumeChAbe = vol[4].getProgress();

        int numeroChFec = spinnerNum[5].getSelectedItemPosition() +1;
        int volumeChFec = vol[5].getProgress();

        int numeroAro = spinnerNum[1].getSelectedItemPosition() +1;
        int volumeAro = vol[1].getProgress();

        BancoSS.criarBancosPersonalizados(numeroBanco,
                numeroCaixa, volumeCaixa, numeroTon1, volumeTon1,
                numeroTon2, volumeTon2, numeroSurdo, volumeSurdo,
                numeroBumbo, volumeBumbo, numeroPatk, volumePatk,
                numeroPcond, volumePcond, numeroChAbe, volumeChAbe,
                numeroChFec, volumeChFec, numeroAro, volumeAro, true);
    }

    private void seekbarProgress(){
        vol[1].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[1].setText("VOLUME - " + vol[1].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[2].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[2].setText("VOLUME - " + vol[2].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[3].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[3].setText("VOLUME - " + vol[3].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[4].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[4].setText("VOLUME - " + vol[4].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[5].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[5].setText("VOLUME - " + vol[5].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[6].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[6].setText("VOLUME - " + vol[6].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[7].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[7].setText("VOLUME - " + vol[7].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[8].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[8].setText("VOLUME - " + vol[8].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[9].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[9].setText("VOLUME - " + vol[9].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        vol[10].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txVol[10].setText("VOLUME - " + vol[10].getProgress());
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });



    }

    public void alertBox( Context context, String title, String message ){
        new AlertDialog.Builder(context)
                .setTitle( title )
                .setMessage( message  )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                       // finish();
                    }
                }).show();
    }


}
