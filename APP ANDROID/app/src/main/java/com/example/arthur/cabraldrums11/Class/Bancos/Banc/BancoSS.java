package com.example.arthur.cabraldrums11.Class.Bancos.Banc;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.widget.Toast;

import com.example.arthur.cabraldrums11.Class.Bancos.GravarLerTXT.Gravar_Ler_TXT;

import com.example.arthur.cabraldrums11.Class.Bancos.Instr.*;
import com.example.arthur.cabraldrums11.Class.Principais.BancoSSActivity;
import com.example.arthur.cabraldrums11.Class.Principais.Bluetooth;
import com.example.arthur.cabraldrums11.Class.Principais.MainActivity;
import com.example.arthur.cabraldrums11.Class.Principais.PadActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class BancoSS {

    //AssetManager assetManager;
    //Context context;
    public static final int numeroDeBancos = 10;
    public static int bancoEmUso = 1;

    public static Banco bk[] = {new Banco("Banco 0"), new Banco("Banco 1"),
        new Banco("Banco 2"), new Banco("Banco 3"), new Banco("Banco 4"),
        new Banco("Banco 5"), new Banco("Banco 6"), new Banco("Banco 7"),
        new Banco("Banco 8"), new Banco("Banco 9"), new Banco("Banco 10"),};

    public BancoSS() {
        //  /storage/sdcard0/CabralDrums_Backup
        criarPastaBackup();

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

        //para listar arquivos no assets
        //context = contexttt;
        //assetManager = contexttt.getAssets();


        lerAdicionarBancosPErsonalizados();
        definirParaUso(lerUltimoBancoEmUso());

        //definirParaUso("Banco 4");

        //bk[10].getVolInstr(1)


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

        if (gravar){

            String msg = "BANCO-" + numeroBanco
                    + "-caixa-" + numeroCaixa + "-volume caixa-" + volumeCaixa
                    + "-ton1-" + numeroTon1 + "-volume ton1-" + volumeTon1
                    + "-ton2-" + numeroTon2 + "-volume ton2-" + volumeTon2
                    + "-surdo-" + numeroSurdo + "-volume-" + volumeSurdo
                    + "-bumbo-" + numeroBumbo + "-volume bumbo-" + volumeBumbo
                    + "-prato atk-" + numeroPatk + "-volume prato atk-" + volumePatk
                    + "-prato cond-" + numeroPcond + "-volume prato cond-" + volumePcond
                    + "-chimbal abe-" + numeroChAbe + "-volume chimbal abe-" + volumeChAbe
                    + "-chimbal fec-" + numeroChFec + "-volume chimbal fec-" + volumeChFec
                    + "-aro-" + numeroAro + "-volume aro-" + volumeAro;

            Bluetooth bt = new Bluetooth(PadActivity.context,false);
            bt.enviarMsg("criarBancosPersonalizados", msg);
        }
    }

    public void lerAdicionarBancosPErsonalizados() {

        File arq = new File(Environment.getExternalStorageDirectory() + "/CabralDrums_Backup");
        File[] file = arq.listFiles();

        if (file.length == 0) {
         Gravar_Ler_TXT.gravarTxt("UltimoBancoEmUso" , "Banco 1");
         //arq = new File(Environment.getExternalStorageDirectory() + "/CabralDrums_Backup");
         //file = arq.listFiles();
         return;
        }

        for (int i = 0; i < file.length; i++) {
            File f = file[i];

            if (!file[i].getName().equals("UltimoBancoEmUso")) {
                Gravar_Ler_TXT.lerTxt(f.getName());
            }

        }



        /*
        String[] listaDeArquivos = {};
        listaDeArquivos = assetManager.list("");

        File pasta = new File("/storage/sdcard0/CabralDrums_Backup");
        File[] file = pasta.listFiles();
        File arq = new File(String.valueOf(context.getClass().getResource("assets")));
       // File[] file = arq.listFiles();
        for (int i = 0; i < listaDeArquivos.length; i++) {
           // File f = file[i];

            if (!listaDeArquivos[i].equals("UltimoBancoEmUso") && !listaDeArquivos[i].equals("images") &&
                    !listaDeArquivos[i].equals("sounds") && !listaDeArquivos[i].equals("webkit")) {
                Gravar_Ler_TXT.lerTxt(context, listaDeArquivos[i]);
            }

        }*/

    }

    public void adicionaInstrumentosBancos() {
        for (int i = 0; i < Banco.numeroDeInst ; i++) {
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

          //  bk[i].setBancoEmUso(false);
            bk[i].setNumBanco(i);

        }
    }

    public void definirParaUso(String nomeBanco) {

        for (int i = 0; i < 11; i++) {
            bk[i].setBancoEmUso(false);
        }

        for (int i = 0; i < 11; i++) {

            if (nomeBanco.equals(bk[i].getNome())) {
                bk[i].setBancoEmUso(true);
                bancoEmUso = bk[i].getNumBanco();

                Caixa.numEmUso = bk[i].getSomInstr(1);
                Caixa.volEmUso = bk[i].getVolInstr(1);

                Ton1.numEmUso = bk[i].getSomInstr(2);
                Ton1.volEmUso = bk[i].getVolInstr(2);

                Ton2.numEmUso = bk[i].getSomInstr(3);
                Ton2.volEmUso = bk[i].getVolInstr(3);

                Surdo.numEmUso = bk[i].getSomInstr(4);
                Surdo.volEmUso = bk[i].getVolInstr(4);

                Bumbo.numEmUso = bk[i].getSomInstr(5);
                Bumbo.volEmUso = bk[i].getVolInstr(5);

                PratoAtk.numEmUso = bk[i].getSomInstr(6);
                PratoAtk.volEmUso = bk[i].getVolInstr(6);

                PratoCond.numEmUso = bk[i].getSomInstr(7);
                PratoCond.volEmUso = bk[i].getVolInstr(7);

                ChimbalAberto.numEmUso = bk[i].getSomInstr(8);
                ChimbalAberto.volEmUso = bk[i].getVolInstr(8);

                ChimbalFechado.numEmUso = bk[i].getSomInstr(9);
                ChimbalFechado.volEmUso = bk[i].getVolInstr(9);

                AroCaixa.numEmUso = bk[i].getSomInstr(10);
                AroCaixa.volEmUso = bk[i].getVolInstr(10);

                System.out.println("Banco em USO : " + bk[i].getNome());

                Gravar_Ler_TXT.gravarUltimoBancoEmUSo(bk[i].getNome());

                return;
            }

        }

    }

    public String lerUltimoBancoEmUso() {
        return Gravar_Ler_TXT.lerUltimoBancoEmuso();
    }

    public void criarPastaBackup(){
        File pasta = new File(Environment.getExternalStorageDirectory(), "CabralDrums_Backup");
        if(pasta.exists() == false){
            pasta.mkdirs();}
    }

    public static void pegarDadosBanco(CharSequence charSequence){

        for (int i = 0; i < 11; i++) {

            if (charSequence.equals(bk[i].getNome())) {
                //bk[i].setBancoEmUso(true);
                //bancoEmUso = bk[i].getNumBanco();

                PadActivity.nomeBanco.setText(BancoSSActivity.clicBanco);

                PadActivity.spinnerNum[3].setSelection(bk[i].getSomInstr(1) - 1);
                PadActivity.vol[3].setProgress(bk[i].getVolInstr(1));
                PadActivity.txVol[3].setText("VOLUME - " + (bk[i].getVolInstr(1)));
                //Caixa.numEmUso = bk[i].getSomInstr(1);
                //Caixa.volEmUso = bk[i].getVolInstr(1);

                PadActivity.spinnerNum[9].setSelection(bk[i].getSomInstr(2)-1);
                PadActivity.vol[9].setProgress(bk[i].getVolInstr(2));
                PadActivity.txVol[9].setText("VOLUME - " + (bk[i].getVolInstr(2) ));
                // Ton1.numEmUso = bk[i].getSomInstr(2);
                //Ton1.volEmUso = bk[i].getVolInstr(2);

                PadActivity.spinnerNum[10].setSelection(bk[i].getSomInstr(3)-1);
                PadActivity.vol[10].setProgress(bk[i].getVolInstr(3));
                PadActivity.txVol[10].setText("VOLUME - " + (bk[i].getVolInstr(3)));

                // Ton2.numEmUso = bk[i].getSomInstr(3);
                //Ton2.volEmUso = bk[i].getVolInstr(3);

                PadActivity.spinnerNum[8].setSelection(bk[i].getSomInstr(4)-1);
                PadActivity.vol[8].setProgress(bk[i].getVolInstr(4));
                PadActivity.txVol[8].setText("VOLUME - " + (bk[i].getVolInstr(4) ));
                //Surdo.numEmUso = bk[i].getSomInstr(4);
                //Surdo.volEmUso = bk[i].getVolInstr(4);

                PadActivity.spinnerNum[2].setSelection(bk[i].getSomInstr(5)-1);
                PadActivity.vol[2].setProgress(bk[i].getVolInstr(5));
                PadActivity.txVol[2].setText("VOLUME - " + (bk[i].getVolInstr(5)));
                //Bumbo.numEmUso = bk[i].getSomInstr(5);
                //Bumbo.volEmUso = bk[i].getVolInstr(5);

                PadActivity.spinnerNum[6].setSelection(bk[i].getSomInstr(6)-1);
                PadActivity.vol[6].setProgress(bk[i].getVolInstr(6));
                PadActivity.txVol[6].setText("VOLUME - " + (bk[i].getVolInstr(6)));
                //PratoAtk.numEmUso = bk[i].getSomInstr(6);
                //PratoAtk.volEmUso = bk[i].getVolInstr(6);

                PadActivity.spinnerNum[7].setSelection(bk[i].getSomInstr(7)-1);
                PadActivity.vol[7].setProgress(bk[i].getVolInstr(7));
                PadActivity.txVol[7].setText("VOLUME - " + (bk[i].getVolInstr(7)));
                // PratoCond.numEmUso = bk[i].getSomInstr(7);
                //PratoCond.volEmUso = bk[i].getVolInstr(7);

                PadActivity.spinnerNum[4].setSelection(bk[i].getSomInstr(8)-1);
                PadActivity.vol[4].setProgress(bk[i].getVolInstr(8));
                PadActivity.txVol[4].setText("VOLUME - " + (bk[i].getVolInstr(8)));
                //ChimbalAberto.numEmUso = bk[i].getSomInstr(8);
                //ChimbalAberto.volEmUso = bk[i].getVolInstr(8);

                PadActivity.spinnerNum[5].setSelection(bk[i].getSomInstr(9)-1);
                PadActivity.vol[5].setProgress(bk[i].getVolInstr(9));
                PadActivity.txVol[5].setText("VOLUME - " + (bk[i].getVolInstr(9)));
                // ChimbalFechado.numEmUso = bk[i].getSomInstr(9);
                //ChimbalFechado.volEmUso = bk[i].getVolInstr(9);

                PadActivity.spinnerNum[1].setSelection(bk[i].getSomInstr(10)-1);
                PadActivity.vol[1].setProgress(bk[i].getVolInstr(10));
                PadActivity.txVol[1].setText("VOLUME - " + (bk[i].getVolInstr(10)));
                //AroCaixa.numEmUso = bk[i].getSomInstr(10);
                //AroCaixa.volEmUso = bk[i].getVolInstr(10);

                //System.out.println("Banco em USO : " + bk[i].getNome());

                //Gravar_Ler_TXT.gravarUltimoBancoEmUSo(bk[i].getNome());

                return;
            }
        }
    }

}
