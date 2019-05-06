package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class PratoAtk {

    public static Pad pAtk = new Pad("pAtk");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public PratoAtk() {

        CriarInst.criar(pAtk);

    }

}
