package MAIN_CLASS;

import Arduino.Serial;
import BancosDados.Instr.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.Line;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import BancosDados.*;
import Bluetooth.ConBluetooth;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.sound.sampled.LineUnavailableException;

/**
 *
 * @author Arthur Cabral
 */
public class CabralDrums extends JFrame implements KeyListener {

    static Line[] linhasAbertas;
    static Mixer.Info[] mixer = AudioSystem.getMixerInfo();
    public static Mixer mm = AudioSystem.getMixer(mixer[0]);

    static int volume;
    public static int volumeGeral = 0; // -70 minimo / 0 maximo
    public static Clip clipp[] = new Clip[Banco.numeroDeInst];

    static int kt1 = 0;

    CabralDrums() {

        for (int j = 0; j < clipp.length; j++) {
            try {
                clipp[j] = (Clip) AudioSystem.getClip(mixer[0]);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(CabralDrums.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        addKeyListener(this);
        setSize(547, 191);
        setVisible(true);
        setLocationRelativeTo(null);

        mix();
        connArduino();
        new BancoSS();

    }

    void mix() {
        System.out.println("============================================================");
        for (int i = 0; i < mixer.length; i++) {
            System.out.println(mixer[i]);
        }
        System.out.println("============================================================");

        //System.out.println("max line: " + mm.getMaxLines(mm.getSourceLineInfo()[0]));

        /*System.out.println(mm.getLineInfo());
        for (int i = 0; i < mm.getSourceLineInfo().length; i++) {
            System.out.println(mm.getSourceLineInfo()[i]);
            System.out.println(mm.getMaxLines(mm.getSourceLineInfo()[i]));
        }*/
    }

    void connArduino() {
        Serial serial = new Serial();
        serial.iniciaSerial();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {

        try {
            acionaPad(e, "100");
        } catch (LineUnavailableException ex) {
            Logger.getLogger(CabralDrums.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public static void startClipp(int numClipp, int batida , int volPad) {
        
        clipp[numClipp].setFramePosition(0);
        
        
        volPad -= 34;
        float sensi = (float) (batida/7 - 18.2);
        
        //FloatControl volume = (FloatControl) clipp[numClipp].getControl(FloatControl.Type.MASTER_GAIN);
        FloatControl volume = (FloatControl) clipp[numClipp].getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(volPad + volumeGeral);
        
        
      /*  try {
            Thread.sleep(15);
        } catch (InterruptedException ex) {
            Logger.getLogger(CabralDrums.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        clipp[numClipp].start();
    }

    public static void acionaPad(KeyEvent tipoPad, String forca) throws LineUnavailableException {
        
        
        switch (tipoPad.getKeyCode()) {
            case KeyEvent.VK_A:
                startClipp(1, 127, Caixa.volEmUso);
                break;
            case KeyEvent.VK_S:
                startClipp(2, 127, Ton1.volEmUso );
                break;
            case KeyEvent.VK_D:
                startClipp(3, 127, Ton2.volEmUso );
                break;
            case KeyEvent.VK_F:
                startClipp(4, 127,Surdo.volEmUso );
                break;
            case KeyEvent.VK_V:
                startClipp(5, 127, Bumbo.volEmUso);
                break;
            case KeyEvent.VK_E:
                startClipp(6, 127, PratoAtk.volEmUso);
                break;
            case KeyEvent.VK_K:
                startClipp(7, 127,PratoCond.volEmUso );
                break;
            case KeyEvent.VK_J:
                startClipp(8, 127,ChimbalAberto.volEmUso );
                break;
            case KeyEvent.VK_L:
                startClipp(9, 127, ChimbalFechado.volEmUso);
                break;
            case KeyEvent.VK_Q:
                startClipp(10, 127, AroCaixa.volEmUso);
                break;
        }
    }

    public static void acionaPadArd(String tipoPad, String forca) throws LineUnavailableException {

        int batida = Integer.parseInt(forca); 

        switch (tipoPad) {
            case "5":
                startClipp(1, batida,Caixa.volEmUso );
                break;
            case "0":
                startClipp(9, 127, ChimbalFechado.volEmUso );
                break;
            case "0003":
                startClipp(3, batida, ChimbalAberto.volEmUso);
                break;
            case "1":
                startClipp(4, batida, Surdo.volEmUso );
                break;
            case "2":
                startClipp(2, batida,Ton1.volEmUso );
                break;
            case "3":
                startClipp(3, batida, Ton2.volEmUso);
                break;
            case "4":
                startClipp(6, batida,PratoAtk.volEmUso );
                break;
            case "7":
                startClipp(7, batida, PratoCond.volEmUso);
                break;
            case "6":
                startClipp(5, batida, Bumbo.volEmUso);
                break;
            case "dfdf":
                startClipp(10, batida,AroCaixa.volEmUso );
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) throws InterruptedException, IOException {

        new CabralDrums();
        new ConBluetooth();

    }
}

//linhasAbertas = mm.getSourceLines();  

