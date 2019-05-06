package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class Caixa {
    
    public static Pad caixa = new Pad("caixa");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public Caixa() {
        
        CriarInst.criar(caixa);
        
    }

}

/*
   public void definirParaUSo(int n, int vol) {
    
        CriarInst.definirParaUso(caixa, numEmUso, volEmUso, n, vol);
        
        caixa.setNumEmUso(numEmUso, false);
        
        caixa.setNumEmUso(n, true);
        caixa.setVolPad(n, vol);
        numEmUso = n;
        volEmUso = vol;
        
    }
*/

/*public Caixa() {
        
        CriarInst.criar(caixa);
        
        for (int i = 0; i < 10; i++) {
            caixa.setVolPad(i, 100);
            caixa.setNumEmUso(i, false);
           // caixa.setNum(i, i);
        }
        
    }
*/