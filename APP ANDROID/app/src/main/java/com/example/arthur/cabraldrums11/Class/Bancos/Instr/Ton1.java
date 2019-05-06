package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class Ton1 {

    public static Pad ton1 = new Pad("ton1");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public Ton1() {

        CriarInst.criar(ton1);

    }

 /*   public void definirParaUSo(int n, int vol) {

        CriarInst.definirParaUso(ton1, numEmUso, volEmUso, n, vol);

    }*/

}
