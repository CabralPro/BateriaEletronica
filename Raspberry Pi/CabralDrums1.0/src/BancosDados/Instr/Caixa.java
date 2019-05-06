package BancosDados.Instr;

import BancosDados.ClassAux.CriarInst;
import BancosDados.Pad.Pad;

public class Caixa {
    
    public static Pad caixa = new Pad("caixa");

    public static int numEmUso = 1;
    public static int volEmUso = 100;

    public Caixa() {
        
        CriarInst.criar(caixa);
        
    }
}
