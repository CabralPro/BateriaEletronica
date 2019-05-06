package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class Ton1 {

    public static Pad ton1 = new Pad("ton1");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public Ton1() {

        CriarInst.criar(ton1);

    }
}
