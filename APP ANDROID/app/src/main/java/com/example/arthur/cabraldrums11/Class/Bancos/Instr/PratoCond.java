package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class PratoCond {

    public static Pad pCond = new Pad("pCond");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public PratoCond() {

        CriarInst.criar(pCond);

    }

}
