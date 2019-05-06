package AutoClic;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoClic {

    public AutoClic() throws AWTException, InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        clic();
                    } catch (AWTException ex) {
                        Logger.getLogger(AutoClic.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AutoClic.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }).start();

    }

    public void clic() throws AWTException, InterruptedException {
        Robot r;
        r = new Robot();
        r.mouseMove(402, 495);
        r.mousePress(InputEvent.BUTTON1_MASK);
        r.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(4000);
    }
}
