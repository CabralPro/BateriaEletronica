package BancosDados;

import BancosDados.Pad.Pad;

public class Banco {

    //a posição 0 não esta sendo usada
    public static final int numeroDeInst = 11;

    final String NOME;
    int numBanco;
    String instr[] = new String[numeroDeInst];
    int somInstr[] = new int[numeroDeInst];
    //vol do pad e nao do instrumento
    int volInstr[] = new int[Pad.numeroDePads];

    boolean bancoEmUso;

    public Banco(final String nom) {
        NOME = nom;
    }

    public String getNome() {
        return NOME;
    }

    /* public void setNome(String nome) {
        this.NOME = nome;
    }*/
    public String getInstr(int posicao) {
        return instr[posicao];
    }

    public void setInstr(int posicao, String instr) {
        this.instr[posicao] = instr;
    }

    public int getSomInstr(int posicao) {
        return somInstr[posicao];
    }

    public void setSomInstr(int posicao, int somInstr) {
        this.somInstr[posicao] = somInstr;
    }

    public int getVolInstr(int posicao) {
        return volInstr[posicao];
    }

    public void setVolInstr(int posicao, int volInstr) {
        this.volInstr[posicao] = volInstr;
    }

    public boolean isBancoEmUso() {
        return bancoEmUso;
    }

    public void setBancoEmUso(boolean bancoEmUso) {
        this.bancoEmUso = bancoEmUso;
    }

    public int getNumBanco() {
        return numBanco;
    }

    public void setNumBanco(int numBanco) {
        this.numBanco = numBanco;
    }

}
