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
        OperacaoJogador.listaDeJogadores();
        Jogador.distribuirCartas(baralho);

        Mesa mesa = new Mesa();
        mesa.primeiraCarta(baralho);

        List<Jogador> jogadores = Jogador.getJogadores();
        do {
            OperacaoJogador jogadorAtual = (OperacaoJogador) mesa.proximoTurno(jogadores);
            System.out.println("É a vez de: " + jogadorAtual.getNome());
            jogadorAtual.imprimirMao();
            jogadorAtual.menuJogador(baralho, mesa, jogadorAtual);


            if (Jogador.Resultado(jogadorAtual)) {
                System.out.println(jogadorAtual.getNome() + " venceu o jogo!");
                break;
            }

        } while (true);
    }
}
