package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class Bumbo {

    public static Pad bumbo = new Pad("bumbo");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public Bumbo() {

        CriarInst.criar(bumbo);

    }

}
