import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private int numeroDeCartas;
    private List<Carta> mao;
    private static List<Jogador> jogadores = new ArrayList<>();

    /**
     * Função em que o usuário determina o número de jogadores
     * @return quantidade de jogadores
     */
    public static int numeroDeJogadores(Scanner scanner) {
        while (true) {
            System.out.println("Digite a quantidade de jogadores. (de 2 a 6)");
            int nJogadores = scanner.nextInt();
            scanner.nextLine();
            if (nJogadores >= 2 && nJogadores <= 6) {
                return nJogadores;
            } else {
                System.out.println("O número de jogadores é inválido.");
            }
        }
    }

    /**
     * Função que coleta a lista de jogadores
     */
    public static void listaDeJogadores(Scanner scanner) {

        int nJogadores = numeroDeJogadores(scanner);

        for (int i = 0; i < nJogadores; i++) {
            System.out.print("Digite o nome do jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            jogadores.add(new OperacaoJogador(nome, 0, -1)); // iniciando o número de cartas com 0
        }

        System.out.println("________________________\n");
        System.out.println("Os jogadores são:");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome());
        }
        System.out.println("________________________");
    }

    //distribui as cartas iniciais para os jogadores
    public static void distribuirCartas(Baralho baralho) {
        final int CARTAS_INICIAIS = 7;
        for (Jogador jogador : jogadores) {
            jogador.mao = new ArrayList<>();
            for (int i = 0; i < CARTAS_INICIAIS; i++) {
                jogador.mao.add(baralho.getBaralho().remove(baralho.getBaralho().size() - 1));
            }
            jogador.setNumeroDeCartas(CARTAS_INICIAIS);
        }
    }

    //confere seu o jogador ganhou o jogo (não possui mais cartas na mão)
    public static boolean Resultado(Jogador jogador) {
        return jogador.getMao().isEmpty();
    }

    /**
     * Função para imprimir a mão do jogador
     */
    public void imprimirMao() {
        System.out.println("Mão do jogador " + this.nome + ":");
        for (Carta carta : this.mao) {
            System.out.println("Cor: " + carta.getCor() + ", Tipo: " + carta.getTipo() + ", Numero: " + carta.getNumero());
        }
        System.out.println("________________________");
    }

    // ----------------------------------- CONSTRUTOR -----------------------------------
    public Jogador(String nome, int numeroDeCartas) {
        this.nome = nome;
        this.numeroDeCartas = numeroDeCartas;
        this.mao = new ArrayList<>();
    }

    // ----------------------------------- GETTERS E SETTERS ---------------------------
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroDeCartas() {
        return numeroDeCartas;
    }

    public void setNumeroDeCartas(int numeroDeCartas) {
        this.numeroDeCartas = numeroDeCartas;
    }

    public static List<Jogador> getJogadores() {
        return jogadores;
    }

    public List<Carta> getMao() {
        return mao;
    }

}
