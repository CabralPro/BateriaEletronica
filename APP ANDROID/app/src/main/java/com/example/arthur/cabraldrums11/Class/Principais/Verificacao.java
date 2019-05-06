package com.example.arthur.cabraldrums11.Class.Principais;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arthur.cabraldrums11.R;

public class Verificacao extends AppCompatActivity {

    static TextView bluet, mac, pareamento, nomeMod, txCom;
    Button atu, sair, cont;
    ProgressDialog progressDialog;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        verificaPermissao();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificacao);

        bluet = findViewById(R.id.txBluet);
        mac = findViewById(R.id.txMac);
        pareamento = findViewById(R.id.txPar);
        nomeMod = findViewById(R.id.txNome);
        atu = findViewById(R.id.btAtu);
        sair = findViewById(R.id.btSair);
        cont = findViewById(R.id.btCont);
        txCom = findViewById(R.id.txCom);

        atu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), Verificacao.class));
                finish();
            }
        });

        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });

        final Bluetooth bt = new Bluetooth(this, true);

        progressDialog = new ProgressDialog(Verificacao.this);
        progressDialog.setTitle("AGUARDE");
        progressDialog.setMessage("Tentando estabelecer conexão bluetooth ...");
        progressDialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        bt.confBluet();
                        bt.enviarMsg("ver", "teste");
                        progressDialog.dismiss();

                    }
                });


            }
        }).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_verific, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.alterarMac:
        }
        return super.onOptionsItemSelected(item);
    }

    public void verificaPermissao() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            int permissao = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissao2 = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH);
            int permissao3 = ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN);

            if (permissao != 0) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
                return;
            }
            if (permissao2 != 0) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, 0);
                return;
            }
            if (permissao3 != 0) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, 0);
                return;
            }

            while (permissao != 0){
                try {
                    permissao = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
