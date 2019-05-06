package Arduino;

import MAIN_CLASS.CabralDrums;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;

public class Protocolo {

    private String tipoPad;
    private String forca;
    private String leituraComando;

    private void interpretaComando() {

        String aux[] = leituraComando.split(",");
        if (aux.length == 2) {
            tipoPad = aux[0];
            forca = aux[1];

                    try { 
                        CabralDrums.acionaPadArd(tipoPad, forca);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(Protocolo.class.getName()).log(Level.SEVERE, null, ex);
                    }
        }
    }

    public void setLeituraComando(String leituraComando) {
        this.leituraComando = leituraComando;//temos a string de dados
        this.interpretaComando();//interpretamos a string
    }

}
