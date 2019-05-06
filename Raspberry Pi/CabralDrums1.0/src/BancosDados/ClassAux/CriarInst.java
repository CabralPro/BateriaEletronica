package BancosDados.ClassAux;

import BancosDados.Pad.Pad;

public class CriarInst {

   public static void criar(Pad pad) {
        for (int i = 1; i < Pad.numeroDePads; i++) {
            pad.setNum(i, i);
            pad.setVolPad(i, 34);
            pad.setNum(i, i);
        }
    }
}
