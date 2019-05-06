package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class PratoAtk {

    public static Pad pAtk = new Pad("pAtk");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public PratoAtk() {

        CriarInst.criar(pAtk);

    }

}
