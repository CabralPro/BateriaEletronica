package com.example.arthur.cabraldrums11.Class.Principais;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class Bluetooth extends Activity {
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothAdapter btAdapter = null;
    private BluetoothDevice device = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outStream = null;
    MainActivity main = new MainActivity();
    Context context;
    boolean testVerf;

    public Bluetooth(Context cont, Boolean verificacao) {
        testVerf = verificacao;
        context = cont;
    }


    // Well known SPP UUID
    private static final UUID MY_UUID =
            UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    // Insert your server's MAC address
    private static String address = "B8:27:EB:6D:28:CE";
    //BluetoothDevice device = btAdapter.getRemoteDevice(address);


    public void confBluet() {

        // Verifica se o dispositivo tem Bluetooth
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        btAdapter.enable();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        if (btAdapter == null) {
            main.alertBox(context, "Erro", "Seu dispositivo não possui conexão Bluetooth.");
        }

        if (btAdapter.isEnabled() == false) {
            Verificacao.bluet.append("DESATIVADO");
            Verificacao.bluet.setTextColor(Color.RED);

        }else
        {
            Verificacao.bluet.append("OK");
            Verificacao.bluet.setTextColor(Color.BLUE);
        }


        if (BluetoothAdapter.checkBluetoothAddress(address) == false){
            Verificacao.mac.append("INVALIDO");
            Verificacao.mac.setTextColor(Color.RED);

        }else {
            Verificacao.mac.append("VALIDO");
            Verificacao.mac.setTextColor(Color.BLUE);

        }

        device = btAdapter.getRemoteDevice(address);

        switch (device.getBondState()){
            //PAREADO
            case 12:
                Verificacao.pareamento.append("OK!");
                Verificacao.pareamento.setTextColor(Color.BLUE);
                break;
            //EM LIGAÇÃO
            case 11:
                Verificacao.pareamento.append("Tentando conexão");
                Verificacao.pareamento.setTextColor(Color.YELLOW);
                break;
            //NÃO PAREADO
            case 10:
                Verificacao.pareamento.append("DESATIVADO");
                Verificacao.pareamento.setTextColor(Color.RED);
                break;
                }

        if (device.getName() == null){
            Verificacao.nomeMod.append("---");
            Verificacao.nomeMod.setTextColor(Color.RED);
        }else{
            Verificacao.nomeMod.append(device.getName());
            Verificacao.nomeMod.setTextColor(Color.BLUE);
        }

    }




    public void enviarMsg( String comando , String menssagem) {

        btAdapter = BluetoothAdapter.getDefaultAdapter();

        if (btAdapter.isEnabled() == false) {
            btAdapter.enable();
            }

        device = btAdapter.getRemoteDevice(address);

        if (device.getName() == null) {
            if(testVerf == false){
                if (context == PadActivity.context){
                    new PadActivity().alertBox(context, "FALHA", "Verifique se a bateria esta ligada e se a distância está razoavel!");
                }else{
                    if (context == MainActivity.context){
                        main.alertBox(context, "FALHA", "Verifique se a bateria esta ligada e se a distância está razoavel!");
                    }
                }
                return;
            }
        }


        try {
            btSocket = device.createRfcommSocketToServiceRecord(MY_UUID);
            //btSocket = device.createInsecureRfcommSocketToServiceRecord(MY_UUID);

        } catch (IOException e) {
            if(testVerf == false){
                if (context == PadActivity.context){
                    new PadActivity().alertBox(context, "FALHA", "Verifique se a bateria esta ligada e se a distância está razoavel!");
                }else{
                    if (context == MainActivity.context){
                        main.alertBox(context, "FALHA", "Verifique se a bateria esta ligada e se a distância está razoavel!");
                    }
                }
                return;}
        }

        // Discovery is resource intensive.  Make sure it isn't going on
        // when you attempt to connect and pass your message.
        //btAdapter.cancelDiscovery();
        // Establish the connection.  This will block until it connects.
        try {


            btSocket.connect();

            if (testVerf == true) {
                Verificacao.txCom.setText("ATIVADA");
                Verificacao.txCom.setTextColor(Color.BLUE);
                btSocket.close();
                return;
            }

        } catch (IOException e) {
            /*try {
                //btSocket = (BluetoothSocket) device.getClass().getMethod("createRfcommSocket", new Class[]{int.class}).invoke(device, 1);
               // btSocket.connect();

                MainActivity.out.append("Connected!");
            } catch (Exception e2) {  */
                if (testVerf == true) {
                    Verificacao.txCom.setText("DESATIVADA");
                    Verificacao.txCom.setTextColor(Color.RED);
                    return;
                }
            if(testVerf == false){

                if (context == PadActivity.context){
                    new PadActivity().alertBox(context, "FALHA", "Verifique se a bateria esta ligada e se a distância está razoavel!");
                }else{
                    if (context == MainActivity.context){
                        main.alertBox(context, "FALHA", "Verifique se a bateria esta ligada e se a distância está razoavel!");
                    }
                }
                return;}
            }
                //}

            // Create a data stream so we can talk to server.

            try {
                outStream = btSocket.getOutputStream();

                // out.append("\noutStream = " + outStream);

            } catch (IOException e3) {
                if (context == PadActivity.context){
                    new PadActivity().alertBox(context, "FALHA", e3.getMessage());
                }else{
                    if (context == MainActivity.context){
                        main.alertBox(context, "FALHA", e3.getMessage());
                    }
                }
            main.alertBox(context, "FATAL ERRO" , e3.getMessage());
            //MainActivity.out.append("Fatal Error : In onResume() and output stream creation failed:" + e3.getMessage() + ".");
                return;
            }

            String message = comando +"-"+ menssagem ;
           // String message = "hellow";
        byte[] msgBuffer = message.getBytes();;

        try {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            outStream.write(msgBuffer);

                outStream.flush();
                outStream.close();
                btSocket.close();

            } catch (IOException e4) {
                main.alertBox(context, "ERRO", "In onResume() and an exception occurred during write: " + e4.getMessage());
                return;
            }



    }



    public void alertBox( Context context, String title, String message ){
        new AlertDialog.Builder(context)
                .setTitle( title )
                .setMessage( message )
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                }).show();
    }

}