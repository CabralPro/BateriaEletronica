package BancosDados.Pad;

public class Pad {

    public static final int numeroDePads = 100;

    String nome;
    int num[] = new int[numeroDePads];
    int volPad[] = new int[numeroDePads];
    //boolean numEmUso[] = new boolean[11] ;

    public Pad(String nom) {
        nome = nom;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNum(int posicao) {
        return num[posicao];
    }

    public void setNum(int posicao, int num) {
        this.num[posicao] = num;
    }

    public int getVolPad(int posicao) {
        return volPad[posicao];
    }

    public void setVolPad(int posicao, int volPad) {

        this.volPad[posicao] = volPad;

    }

    /*  public boolean[] getNumEmUso() {
        return numEmUso;
    }

    public void setNumEmUso(int posicao ,boolean numEmUso) {
        this.numEmUso[posicao] = numEmUso;
    }
     */
}
