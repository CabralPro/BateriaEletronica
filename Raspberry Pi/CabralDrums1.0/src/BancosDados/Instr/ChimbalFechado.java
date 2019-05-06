package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class ChimbalFechado {

    public static Pad chFec = new Pad("chFec");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public ChimbalFechado() {

        CriarInst.criar(chFec);

    }

}
