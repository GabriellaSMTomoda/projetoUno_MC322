import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindx ao nosso...");
        System.out.println(" .----------------.  .-----------------. .----------------.  .----------------. \n" + //
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" + //
                "| | _____  _____ | || | ____  _____  | || |     ____     | || |              | |\n" + //
                "| ||_   _||_   _|| || ||_   \\|_   _| | || |   .'    `.   | || |      _       | |\n" + //
                "| |  | |    | |  | || |  |   \\ | |   | || |  /  .--.  \\  | || |     | |      | |\n" + //
                "| |  | '    ' |  | || |  | |\\ \\| |   | || |  | |    | |  | || |     | |      | |\n" + //
                "| |  \\  `--'  /  | || | _| |_\\   |_  | || |  \\  `--'  /  | || |     | |      | |\n" + //
                "| |   `. __ .'   | || ||_____|\\____| | || |   `.____.'   | || |     |_|      | |\n" + //
                "| |              | || |              | || |              | || |     (_)      | |\n" + //
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" + //
                " '----------------'  '----------------'  '----------------'  '----------------' \n" + //
                "\n" + //
                "");

        Baralho baralho = new Baralho();
        Jogador.listaDeJogadores();
        Jogador.distribuirCartas(baralho);
        
        for (Jogador j : Jogador.getJogadores()) {
            j.imprimirMao();
        }

        Mesa mesa = new Mesa();
        mesa.primeiraCarta(baralho);
        mesa.imprimeUltimaCarta();

        List<Jogador> jogadores = Jogador.getJogadores();
        do {
            Jogador jogadorAtual = mesa.proximoTurno(jogadores);
            System.out.println("É a vez de: " + jogadorAtual.getNome());

            //logica das cartas

            if (Jogador.Resultado(jogadorAtual)) {
                System.out.println(jogadorAtual.getNome() + " venceu o jogo!");
                break;
            }

        } while (true);
    }
}
