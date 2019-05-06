package com.example.arthur.cabraldrums11.Class.Bancos.Instr;

import com.example.arthur.cabraldrums11.Class.Bancos.ClassAux.CriarInst;
import com.example.arthur.cabraldrums11.Class.Bancos.Pad.Pad;

public class Bumbo {

    public static Pad bumbo = new Pad("bumbo");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public Bumbo() {

        CriarInst.criar(bumbo);

    }

}
