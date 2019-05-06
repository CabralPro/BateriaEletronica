package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class ChimbalFechado {

    public static Pad chFec = new Pad("chFec");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public ChimbalFechado() {

        CriarInst.criar(chFec);

    }

}
