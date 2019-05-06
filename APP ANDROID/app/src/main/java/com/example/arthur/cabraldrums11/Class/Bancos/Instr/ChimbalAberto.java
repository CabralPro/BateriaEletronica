package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class ChimbalAberto {

    public static Pad chAbe = new Pad("chAbe");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public ChimbalAberto() {

        CriarInst.criar(chAbe);

    }

}
