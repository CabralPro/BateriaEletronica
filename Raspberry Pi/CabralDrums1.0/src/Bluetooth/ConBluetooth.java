package Bluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class ConBluetooth {

    private static final String UUID_STRING = "0000110100001000800000805f9b34fb"; // 32 hex digits
    private static final String SERVICE_NAME = "echoserver";

    private StreamConnectionNotifier server;
    private InputStream in;


    // when program is terminated, shutdown all the client threads
    public ConBluetooth() throws IOException {
        
        //permissão para o arquivo
         Runtime run = Runtime.getRuntime();
         run.exec("sudo chmod 777 /var/run/sdp");
        
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                closeDown();
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {

                initDevice();
                createRFCOMMConnection();
                processClients();

            }
        }).start();

    }

    private void initDevice() {
        try { // make the server's bluetooth device discoverable
            LocalDevice local = LocalDevice.getLocalDevice();

            System.out.println("Device name: " + local.getFriendlyName());
            System.out.println("Bluetooth Address: " + local.getBluetoothAddress());

            boolean res = local.setDiscoverable(DiscoveryAgent.GIAC);

            //System.out.println("Discoverability set: " + res);
        } catch (BluetoothStateException e) {
            System.out.println(e);
            System.exit(1);
        }
    } // end of initDevice()

    private void createRFCOMMConnection() {
        try {
            //System.out.println("Start advertising " + SERVICE_NAME + "...");

            server = (StreamConnectionNotifier) Connector.open(
                    "btspp://localhost:" + UUID_STRING
                    + ";name=" + SERVICE_NAME + ";authenticate=false");

            //System.out.println("server = " + server);
            //System.out.println("UUID = " + UUID_STRING);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(1);
        }
    } // end of createRFCOMMConnection()

    // globals
    private ArrayList<ThreadedEchoHandler> handlers;
    private volatile boolean isRunning = false;

    private void processClients() {
        isRunning = true;
        try {
            while (isRunning) {
                System.out.println("Esperando dados Bluetooth...");
                StreamConnection conn = server.acceptAndOpen();
                    // wait for a client connection

                ThreadedEchoHandler handler = new ThreadedEchoHandler(conn);
//                handlers.add(handler);

                handler.start();
                
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    } // end of processClients	

    private void closeDown() {
        System.out.println("closing down server");
        if (isRunning) {
            isRunning = false;
            try {
                server.close();
            } catch (IOException e) {
                System.err.println(e);
            }

            // close all the handlers
            for (ThreadedEchoHandler hand : handlers) {
                hand.closeDown();
            }
            handlers.clear();
        }
    } // end of closeDown()

}
