package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class PratoCond {

    public static Pad pCond = new Pad("pCond");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public PratoCond() {

        CriarInst.criar(pCond);

    }

}
