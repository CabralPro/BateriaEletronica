package com.example.arthur.cabraldrums11.Class.Principais;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arthur.cabraldrums11.Class.Bancos.Banc.BancoSS;
import com.example.arthur.cabraldrums11.R;

import java.lang.reflect.Array;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Button btEditBancos;
    Bluetooth bluet = null;
    Spinner sp[] = new Spinner[6];
    Button[] usarBcos = new Button[6];
    Button usarVolGeral = null;
    public static Context context;
    Handler handler = new Handler();
    SeekBar volGeral = null;
    TextView txvolGeral = null;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //verificaPermissao();

        bluet = new Bluetooth(this, false);

        //
        btEditBancos= findViewById(R.id.btEditBanco);
        sp[1] = findViewById(R.id.spinner);
        sp[2] = findViewById(R.id.spinner2);
        sp[3] = findViewById(R.id.spinner3);
        sp[4] = findViewById(R.id.spinner4);
        sp[5] = findViewById(R.id.spinner5);

        usarBcos[1] = findViewById(R.id.btUsarBc1);
        usarBcos[2] = findViewById(R.id.btUsarBc2);
        usarBcos[3] = findViewById(R.id.btUsarBc3);
        usarBcos[4] = findViewById(R.id.btUsarBc4);
        usarBcos[5] = findViewById(R.id.btUsarBc5);

        usarVolGeral = findViewById(R.id.btUsarVolGeral);

        volGeral = findViewById(R.id.seekBarVolGeral);
        txvolGeral = findViewById(R.id.txVolGeral);

        volGeral.setMax(50);
        volGeral.setProgress(40);
        txvolGeral.setText("" + volGeral.getProgress());

        new BancoSS();
        preencherSpiners();
        evtBtUsar();
        seekbarProgress();

        context = this;


    }

       public void evtBtUsar(){
//           for(int i = 1; i <= usarBcos.length; i ++) {
//               final int iAux = i;
//               usarBcos[i].setOnClickListener(new View.OnClickListener() {
////                   @Override
////                   public void onClick(View v) {
////                       progressDialog();
////                       procUsar(iAux);
//                   }
//               });
//           }


            usarBcos[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog();
                    procUsar(1);
                }
            });
            usarBcos[2].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog();
                    procUsar(2);
                }
            });
        usarBcos[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog();
                procUsar(3);
            }
        });

        usarBcos[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog();
                procUsar(4);
            }
        });
        usarBcos[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog();
                procUsar(5);
                }
        });

        usarVolGeral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               progressDialog();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                bluet.enviarMsg("alterarVolume" ,String.valueOf(volGeral.getProgress()));
                                progressDialog.dismiss();
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private void procUsar(final int k){
        for (int i = 1 ; i <= 5; i++) {
            usarBcos[i].setBackgroundColor(Color.parseColor("#C9C9C9"));
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        bluet.enviarMsg("definirParaUso" ,sp[k].getSelectedItem().toString());
                        usarBcos[k].setBackgroundColor(Color.parseColor("#ff00ddff"));
                        progressDialog.dismiss();
                    }
                });
            }
        }).start();
    }


    public void preencherSpiners(){
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(this, R.array.bancos, android.R.layout.simple_spinner_item);
        sp[1].setAdapter(arrayAdapter);
        sp[2].setAdapter(arrayAdapter);
        sp[2].setSelection(1);
        sp[3].setAdapter(arrayAdapter);
        sp[3].setSelection(2);
        sp[4].setAdapter(arrayAdapter);
        sp[4].setSelection(3);
        sp[5].setAdapter(arrayAdapter);
        sp[5].setSelection(4);
    }

    public void telaBancos(View view){
        startActivity(new Intent(getApplicationContext(), BancoSSActivity.class));
    }

    private void seekbarProgress() {
        volGeral.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                txvolGeral.setText("" + volGeral.getProgress());
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

                    }
                }).show();
    }

    public  void progressDialog() {

        new Thread(new Runnable() {
            @Override
            public void run() {
            int i = 1;
            i = 10+5;
            }
        }).start();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Aguarde");
        progressDialog.setMessage("Enviando ...");
        progressDialog.show();
            }
    public void verificaPermissao() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int permissao = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permissao == 0) {
                return;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return;
            }
        } else {
            return;
        }
    }








   /* public void alert( Context context, String title, String message ){
        new AlertDialog.Builder(contexttt)
                .setTitle( title )
                .setMessage( message  )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                                          }
                }).show();
    }

*/

}

