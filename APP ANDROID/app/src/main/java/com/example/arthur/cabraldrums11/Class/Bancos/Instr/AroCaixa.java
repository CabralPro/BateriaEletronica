package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class AroCaixa {

    public static Pad aroCai = new Pad("aroCai");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public AroCaixa() {

        CriarInst.criar(aroCai);

    }

}
