package com.example.arthur.cabraldrums11.Class.Bancos.ClassAux;

import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad ;

public class CriarInst {

   public static void criar(Pad pad) {
        for (int i = 1; i < Pad.numeroDePads; i++) {
            pad.setNum(i, i);
            pad.setVolPad(i, 34);
            //pad.setNumEmUso(i, false);
            pad.setNum(i, i);
        }
    }

}
