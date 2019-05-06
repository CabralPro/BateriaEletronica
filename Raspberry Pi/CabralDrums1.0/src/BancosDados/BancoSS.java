package BancosDados;

import BancosDados.Instr.*;
import MAIN_CLASS.CabralDrums;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BancoSS {

    public static final int numeroDeBancos = 10;

    public static int bancoEmUso = 1;
    static URL url = null;
    static AudioInputStream audioInputStream = null;
             AudioClip clip;


    static Banco bk[] = {new Banco("Banco 0"), new Banco("Banco 1"),
        new Banco("Banco 2"), new Banco("Banco 3"), new Banco("Banco 4"),
        new Banco("Banco 5"), new Banco("Banco 6"), new Banco("Banco 7"),
        new Banco("Banco 8"), new Banco("Banco 9"), new Banco("Banco 10"),};

    public BancoSS() {

        new Caixa();
        new Ton1();
        new Ton2();
        new Surdo();
        new Bumbo();
        new PratoAtk();
        new PratoCond();
        new ChimbalAberto();
        new ChimbalFechado();
        new AroCaixa();

        adicionaInstrumentosBancos();
        adicionaSonsParaBancos();
        //criarBancosPersonalizados(4, 3, 80, 4, 95, 5, 100, 2, 87, 10, 90, 6, 102, 1, 98, 8, 130, 9, 79, 5, 98, true);
        lerAdicionarBancosPErsonalizados();
        definirParaUso(lerUltimoBancoEmUso());
        //definirParaUso("Banco 10");
      

    }

    void adicionaSonsParaBancos() {

        //seta sons para os bancos
        for (int i = 1; i < Banco.numeroDeInst; i++) {

            //set CAIXA
            bk[i].setSomInstr(1, Caixa.caixa.getNum(i));
            bk[i].setVolInstr(1, Caixa.caixa.getVolPad(i));

            //set TON1             
            bk[i].setSomInstr(2, Ton1.ton1.getNum(i));
            bk[i].setVolInstr(2, Ton1.ton1.getVolPad(i));

            //set TON2
            bk[i].setSomInstr(3, Ton2.ton2.getNum(i));
            bk[i].setVolInstr(3, Ton2.ton2.getVolPad(i));

            //set SURDO
            bk[i].setSomInstr(4, Surdo.surdo.getNum(i));
            bk[i].setVolInstr(4, Surdo.surdo.getVolPad(i));

            //set BUMDO
            bk[i].setSomInstr(5, Bumbo.bumbo.getNum(i));
            bk[i].setVolInstr(5, Bumbo.bumbo.getVolPad(i));

            //set PRATO DE ATK
            bk[i].setSomInstr(6, PratoAtk.pAtk.getNum(i));
            bk[i].setVolInstr(6, PratoAtk.pAtk.getVolPad(i));

            //set PRATO DE CONDUçãO
            bk[i].setSomInstr(7, PratoCond.pCond.getNum(i));
            bk[i].setVolInstr(7, PratoCond.pCond.getVolPad(i));

            //set CHIMBAL ABERTO
            bk[i].setSomInstr(8, ChimbalAberto.chAbe.getNum(i));
            bk[i].setVolInstr(8, ChimbalAberto.chAbe.getVolPad(i));

            //set CHIMBAL FECHADO
            bk[i].setSomInstr(9, ChimbalFechado.chFec.getNum(i));
            bk[i].setVolInstr(9, ChimbalFechado.chFec.getVolPad(i));

            //set ARO
            bk[i].setSomInstr(10, AroCaixa.aroCai.getNum(i));
            bk[i].setVolInstr(10, AroCaixa.aroCai.getVolPad(i));

        }
    }

    public static void criarBancosPersonalizados(int numeroBanco,
            int numeroCaixa, int volumeCaixa,
            int numeroTon1, int volumeTon1,
            int numeroTon2, int volumeTon2,
            int numeroSurdo, int volumeSurdo,
            int numeroBumbo, int volumeBumbo,
            int numeroPatk, int volumePatk,
            int numeroPcond, int volumePcond,
            int numeroChAbe, int volumeChAbe,
            int numeroChFec, int volumeChFec,
            int numeroAro, int volumeAro,
            boolean gravar) {

        //CAIXA
        bk[numeroBanco].setSomInstr(1, Caixa.caixa.getNum(numeroCaixa));
        bk[numeroBanco].setVolInstr(1, volumeCaixa);

        //TON1
        bk[numeroBanco].setSomInstr(2, Ton1.ton1.getNum(numeroTon1));
        bk[numeroBanco].setVolInstr(2, volumeTon1);

        //TON2
        bk[numeroBanco].setSomInstr(3, Ton2.ton2.getNum(numeroTon2));
        bk[numeroBanco].setVolInstr(3, volumeTon2);

        //SURDO
        bk[numeroBanco].setSomInstr(4, Surdo.surdo.getNum(numeroSurdo));
        bk[numeroBanco].setVolInstr(4, volumeSurdo);

        //BUMBO
        bk[numeroBanco].setSomInstr(5, Bumbo.bumbo.getNum(numeroBumbo));
        bk[numeroBanco].setVolInstr(5, volumeBumbo);

        //PRATO ATK
        bk[numeroBanco].setSomInstr(6, PratoAtk.pAtk.getNum(numeroPatk));
        bk[numeroBanco].setVolInstr(6, volumePatk);

        //PRATO COND
        bk[numeroBanco].setSomInstr(7, PratoCond.pCond.getNum(numeroPcond));
        bk[numeroBanco].setVolInstr(7, volumePcond);

        //CHIMBAL ABE
        bk[numeroBanco].setSomInstr(8, ChimbalAberto.chAbe.getNum(numeroChAbe));
        bk[numeroBanco].setVolInstr(8, volumeChAbe);

        //CHIMBAL FEC
        bk[numeroBanco].setSomInstr(9, ChimbalFechado.chFec.getNum(numeroChFec));
        bk[numeroBanco].setVolInstr(9, volumeChFec);

        //ARO
        bk[numeroBanco].setSomInstr(10, AroCaixa.aroCai.getNum(numeroAro));
        bk[numeroBanco].setVolInstr(10, volumeAro);

        if (gravar) {
            Gravar_Ler_TXT.gravarTxt(
                    bk[numeroBanco].getNome(), "BANCO\n" + numeroBanco
                    + "\ncaixa\n" + numeroCaixa + "\nvolume caixa\n" + volumeCaixa
                    + "\nton1\n" + numeroTon1 + "\nvolume ton1\n" + volumeTon1
                    + "\nton2\n" + numeroTon2 + "\nvolume ton2\n" + volumeTon2
                    + "\nsurdo\n" + numeroSurdo + "\nvolume\n" + volumeSurdo
                    + "\nbumbo\n" + numeroBumbo + "\nvolume bumbo\n" + volumeBumbo
                    + "\nprato atk\n" + numeroPatk + "\nvolume prato atk\n" + volumePatk
                    + "\nprato cond\n" + numeroPcond + "\nvolume prato cond\n" + volumePcond
                    + "\nchimbal abe\n" + numeroChAbe + "\nvolume chimbal abe\n" + volumeChAbe
                    + "\nchimbal fec\n" + numeroChFec + "\nvolume chimbal fec\n" + volumeChFec
                    + "\naro\n" + numeroAro + "\nvolume aro\n" + volumeAro
            );
        }

    }

    public void lerAdicionarBancosPErsonalizados() {
        File arq = new File("/home/pi/Documents/CabralDrums1.0/src/BancosDados/ArquivosTxt");
        File[] file = arq.listFiles();

        for (int i = 0; i < file.length; i++) {
            File f = file[i];

            if (!file[i].getName().equals("UltimoBancoEmUso")) {
                Gravar_Ler_TXT.lerTxt(f.getName());
            }
        }

    }

    public void adicionaInstrumentosBancos() {
        for (int i = 0; i < Banco.numeroDeInst; i++) {
            bk[i].setInstr(1, Caixa.caixa.getNome());
            bk[i].setInstr(2, Ton1.ton1.getNome());
            bk[i].setInstr(3, Ton2.ton2.getNome());
            bk[i].setInstr(4, Surdo.surdo.getNome());
            bk[i].setInstr(5, Bumbo.bumbo.getNome());
            bk[i].setInstr(6, PratoAtk.pAtk.getNome());
            bk[i].setInstr(7, PratoCond.pCond.getNome());
            bk[i].setInstr(8, ChimbalAberto.chAbe.getNome());
            bk[i].setInstr(9, ChimbalFechado.chFec.getNome());
            bk[i].setInstr(10, AroCaixa.aroCai.getNome());

            bk[i].setNumBanco(i);

        }
    }

    public static void definirParaUso(String nomeBanco) {

        for (int i = 0; i < 11; i++) {
            bk[i].setBancoEmUso(false);
        }
        
        CabralDrums.mm.close();

        for (int i = 0; i < 11; i++) {

            if (nomeBanco.equals(bk[i].getNome())) {
                try {
                    bk[i].setBancoEmUso(true);
                    bancoEmUso = bk[i].getNumBanco();
                    
                    Caixa.numEmUso = bk[i].getSomInstr(1);
                    Caixa.volEmUso = bk[i].getVolInstr(1);
                    url = CabralDrums.class.getResource("/Audios/CAIXA/" + Caixa.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[1].open(audioInputStream);
                    
                    Ton1.numEmUso = bk[i].getSomInstr(2);
                    Ton1.volEmUso = bk[i].getVolInstr(2);
                    url = CabralDrums.class.getResource("/Audios/TON_1/" + Ton1.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[2].open(audioInputStream);
                    
                    Ton2.numEmUso = bk[i].getSomInstr(3);
                    Ton2.volEmUso = bk[i].getVolInstr(3);
                    url = CabralDrums.class.getResource("/Audios/TON_2/" + Ton2.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[3].open(audioInputStream);
                    
                    Surdo.numEmUso = bk[i].getSomInstr(4);
                    Surdo.volEmUso = bk[i].getVolInstr(4);
                    url = CabralDrums.class.getResource("/Audios/SURDO/" + Surdo.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[4].open(audioInputStream);
                    
                    Bumbo.numEmUso = bk[i].getSomInstr(5);
                    Bumbo.volEmUso = bk[i].getVolInstr(5);
                    url = CabralDrums.class.getResource("/Audios/BUMBO/" + Bumbo.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[5].open(audioInputStream);
                    
                    
                    PratoAtk.numEmUso = bk[i].getSomInstr(6);
                    PratoAtk.volEmUso = bk[i].getVolInstr(6);
                    url = CabralDrums.class.getResource("/Audios/PRATO_ATK/" + PratoAtk.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[6].open(audioInputStream);
                    
                    PratoCond.numEmUso = bk[i].getSomInstr(7);
                    PratoCond.volEmUso = bk[i].getVolInstr(7);
                    url = CabralDrums.class.getResource("/Audios/PRATO_COND/" + PratoCond.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[7].open(audioInputStream);
                    
                    ChimbalAberto.numEmUso = bk[i].getSomInstr(8);
                    ChimbalAberto.volEmUso = bk[i].getVolInstr(8);
                    url = CabralDrums.class.getResource("/Audios/CHIMBAL_ABE/" + ChimbalAberto.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[8].open(audioInputStream);
                    
                    ChimbalFechado.numEmUso = bk[i].getSomInstr(9);
                    ChimbalFechado.volEmUso = bk[i].getVolInstr(9);
                    url = CabralDrums.class.getResource("/Audios/CHIMBAL_FEC/" + ChimbalFechado.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[9].open(audioInputStream);
                    
                    
                    AroCaixa.numEmUso = bk[i].getSomInstr(10);
                    AroCaixa.volEmUso = bk[i].getVolInstr(10);
                    url = CabralDrums.class.getResource("/Audios/ARO/" + AroCaixa.numEmUso + ".wav");
                    audioInputStream = AudioSystem.getAudioInputStream(url);
                    CabralDrums.clipp[10].open(audioInputStream);
                                        
                    System.out.println("============================================================");
                    System.out.println("Banco em USO : " + bk[i].getNome());
                    System.out.println("============================================================");
                    
                    Gravar_Ler_TXT.gravarUltimoBancoEmUSo(bk[i].getNome());
                    
                    return;
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(BancoSS.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(BancoSS.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(BancoSS.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }

    public String lerUltimoBancoEmUso() {
        return Gravar_Ler_TXT.lerUltimoBancoEmuso();
    }

}
