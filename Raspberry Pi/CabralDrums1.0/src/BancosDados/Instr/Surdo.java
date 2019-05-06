package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class Surdo {

    public static Pad surdo = new Pad("surdo");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public Surdo() {

        CriarInst.criar(surdo);

    }

}
