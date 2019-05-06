package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class ChimbalAberto {

    public static Pad chAbe = new Pad("chAbe");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public ChimbalAberto() {

        CriarInst.criar(chAbe);

    }

}
