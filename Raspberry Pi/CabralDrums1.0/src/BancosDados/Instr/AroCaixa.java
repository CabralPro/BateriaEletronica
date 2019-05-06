package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class AroCaixa {

    public static Pad aroCai = new Pad("aroCai");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public AroCaixa() {

        CriarInst.criar(aroCai);

    }

}
