import java.util.List;
import java.util.Scanner;

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

        Scanner scanner = new Scanner(System.in);
        Baralho baralho = new Baralho();
        OperacaoJogador.listaDeJogadores(scanner);
        Jogador.distribuirCartas(baralho);

        Mesa mesa = Mesa.getInstance();

        mesa.primeiraCarta(baralho);

        OperacaoCarta operacaoCarta = new OperacaoCarta();

        List<Jogador> jogadores = Jogador.getJogadores();
        do { //loop que acaba apenas quando algum jogador ganhar o jogo
            OperacaoJogador jogadorAtual = (OperacaoJogador) mesa.proximoTurno(jogadores, operacaoCarta, mesa); //atualiza o turno
            System.out.println("É a vez de: " + jogadorAtual.getNome());
            jogadorAtual.imprimirMao();
            jogadorAtual.menuJogador(baralho, mesa, jogadorAtual, operacaoCarta, scanner);

            //confere o jogador ganhou
            if (Jogador.Resultado(jogadorAtual)) {
                System.out.println(jogadorAtual.getNome() + " venceu o jogo!");
                break;
            }
        
            mesa.embaralharMonteDeCompra(); //embaralha o monte de compra
            
        } while (true);
        scanner.close();
    }
}
