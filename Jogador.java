import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nome;
    private int numeroDeCartas;
    private List<Cartas> mao;

    /**
     * Função em que o usuário determina o número de jogadores
     * @return quantidade de jogadores
     */
    public static int numeroDeJogadores() {
        Scanner scanner = new Scanner(System.in);
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
    public static void listaDeJogadores() {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int nJogadores = numeroDeJogadores();

        for (int i = 0; i < nJogadores; i++) {
            System.out.print("Digite o nome do jogador " + (i + 1) + ": ");
            String nome = scanner.nextLine();
            jogadores.add(new Jogador(nome, 0)); // iniciando o número de cartas com 0
        }

        System.out.println("________________________\n");
        System.out.println("Os jogadores são:");
        for (Jogador jogador : jogadores) {
            System.out.println(jogador.getNome());
        }
        System.out.println("________________________");
        scanner.close();
    }


    // --------------- CONSTRUTOR-----------------------------------
    public Jogador(String nome, int numeroDeCartas) {
        this.nome = nome;
        this.numeroDeCartas = numeroDeCartas;
    }

    // --------------- GETTERS E SETTERS ---------------------------
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

}
