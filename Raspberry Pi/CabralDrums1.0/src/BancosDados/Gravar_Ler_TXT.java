package BancosDados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Gravar_Ler_TXT {

    public static void gravarTxt(String nomeBanco, String msg) {

        try {

            // FileWriter arq = new FileWriter(getClass().getResource("BackupBancos.txt").getFile());
            FileWriter arq = new FileWriter(new File("/home/pi/Documents/CabralDrums1.0/src/BancosDados/ArquivosTxt/" + nomeBanco));
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(msg);
            gravarArq.close();

        } catch (IOException ex) {

            System.out.println("ERRO: " + ex.getMessage() + " VERIFIQUE SE O NOME DE USUARIO DA MAQUINA OU O NOME DAS PASTAS DO CABRAL DRUMS FOI MODIFICADO.");
        }

    }

    public static void lerTxt(String nomeBanco) {

        String conteudo = "";

        try {
            FileReader arq = new FileReader("/home/pi/Documents/CabralDrums1.0/src/BancosDados/ArquivosTxt/" + nomeBanco);
            BufferedReader lerArq = new BufferedReader(arq);

            String linha = "";

            linha = lerArq.readLine();
            while (linha != null) {
                conteudo += linha + "\n";
                linha = lerArq.readLine();
            }

            lerArq.close();

            interpretaConteudo(conteudo, false);

        } catch (IOException ex) {
            System.out.println("ERRO: " + ex.getMessage());

        }

    }

    public static void interpretaConteudo(String conteudo, boolean gravar) {

        String cont[] = conteudo.split("\n");

        int numeroBanco = Integer.parseInt(cont[1]);

        int numeroCaixa = Integer.parseInt(cont[3]);
        int volumeCaixa = Integer.parseInt(cont[5]);

        int numeroTon1 = Integer.parseInt(cont[7]);
        int volumeTon1 = Integer.parseInt(cont[9]);

        int numeroTon2 = Integer.parseInt(cont[11]);
        int volumeTon2 = Integer.parseInt(cont[13]);

        int numeroSurdo = Integer.parseInt(cont[15]);
        int volumeSurdo = Integer.parseInt(cont[17]);

        int numeroBumbo = Integer.parseInt(cont[19]);
        int volumeBumbo = Integer.parseInt(cont[21]);

        int numeroPatk = Integer.parseInt(cont[23]);
        int volumePatk = Integer.parseInt(cont[25]);

        int numeroPcond = Integer.parseInt(cont[27]);
        int volumePcond = Integer.parseInt(cont[29]);

        int numeroChAbe = Integer.parseInt(cont[31]);
        int volumeChAbe = Integer.parseInt(cont[33]);

        int numeroChFec = Integer.parseInt(cont[35]);
        int volumeChFec = Integer.parseInt(cont[37]);
        
        int numeroAro = Integer.parseInt(cont[39]);
        int volumeAro = Integer.parseInt(cont[41]);

        BancoSS.criarBancosPersonalizados(numeroBanco,
                numeroCaixa, volumeCaixa, numeroTon1, volumeTon1,
                numeroTon2, volumeTon2, numeroSurdo, volumeSurdo,
                numeroBumbo, volumeBumbo, numeroPatk, volumePatk,
                numeroPcond, volumePcond, numeroChAbe, volumeChAbe,
                numeroChFec, volumeChFec, numeroAro, volumeAro, gravar);

    }

    public static void gravarUltimoBancoEmUSo(String nomeBanco) {

        try {

            FileWriter arq = new FileWriter(new File("/home/pi/Documents/CabralDrums1.0/src/BancosDados/ArquivosTxt/UltimoBancoEmUso"));
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.write(nomeBanco);
            gravarArq.close();

        } catch (IOException ex) {
            System.out.println("ERRO: " + ex.getMessage() + " VERIFIQUE SE O NOME DE USUARIO DA MAQUINA OU O NOME DAS PASTAS DO CABRAL DRUMS FOI MODIFICADO.");
        }
    }

    public static String lerUltimoBancoEmuso() {

        String nome = "";

        try {
            FileReader arq = new FileReader("/home/pi/Documents/CabralDrums1.0/src/BancosDados/ArquivosTxt/UltimoBancoEmUso");
            BufferedReader lerArq = new BufferedReader(arq);
            nome = lerArq.readLine();
            lerArq.close();

        } catch (IOException ex) {
            System.out.println("ERRO: " + ex.getMessage());

        }
        return nome;
    }

}
