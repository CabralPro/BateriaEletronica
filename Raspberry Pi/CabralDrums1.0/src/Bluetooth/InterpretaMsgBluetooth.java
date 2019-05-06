package Bluetooth;

import BancosDados.Gravar_Ler_TXT;
import MAIN_CLASS.CabralDrums;

public class InterpretaMsgBluetooth {

    public static void interpretarMsg(String msg) {
        
        if (msg == null) {
            System.out.println("!!!!!A menssagem recebebida esta vazia!!!!!!");
            return;
        }
        
        String cont[] = msg.split("-");
        String comando = cont[0];
        String conteudoSemComando = "";

        for (int i = 1; i < cont.length; i++) {
            conteudoSemComando += cont[i] + "\n";
        }

        switch (comando) {
            case "definirParaUso":
                BancosDados.BancoSS.definirParaUso(cont[1]);
                break;
            case "criarBancosPersonalizados":
                Gravar_Ler_TXT.interpretaConteudo(conteudoSemComando, true);
                break;
            case "alterarVolume":
                CabralDrums.volumeGeral = Integer.parseInt(cont[1]) - 50;
                break;
        }

    }

}
