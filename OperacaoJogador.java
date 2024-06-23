import java.util.List;
import java.util.Scanner;

class OperacaoJogador extends Jogador {
    
    private int ultimaJogada;
    
    public OperacaoJogador(String nome, int numeroDeCartas, int ultimaJogada) {
        super(nome, numeroDeCartas);
        this.ultimaJogada = ultimaJogada;
    }
    
    public void menuJogador(Baralho baralho, Mesa mesa, Jogador jogador) {
        mesa.imprimeUltimaCarta();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do que deseja fazer: ");
        System.out.println("0 - Jogar carta\n" + 
                            "1 - Comprar carta\n" +
                            "2 - UNO!!!!\n" +
                            "3 - Passar vez\n");
        int operacao = scanner.nextInt();
        switch (operacao) {
            case 0:
                if (ultimaJogada == 4) {
                    System.out.println("Você já jogou uma carta neste turno!+1 carta pelo mal-caratismo. PŔOXIMO JOGADOR");
                    ultimaJogada = -1;
                    break;
                } else if (getNumeroDeCartas() == 1 && ultimaJogada != 2) {
                    System.out.println("Você esqueceu de gritar UNO na última rodada!+1 carta e perdeu sua vez");
                    ultimaJogada = -1;
                    compra(jogador, mesa);
                    break;
                } else {
                    jogarCarta(mesa);
                }
                menuJogador(baralho, mesa, jogador);

                break;
            case 1:
                if(ultimaJogada == 4) {
                    compra(jogador, mesa);
                    System.out.println("Você já jogou!+1 carta e perdeu seu turno");
                } else {
                    compra(jogador, mesa);
                    ultimaJogada = 1;
                    menuJogador(baralho, mesa, jogador);
                }
                break;
            case 2:
                if (getNumeroDeCartas() != 1) {
                    System.out.println("Você só pode gritar UNO quando tiver uma carta!+1 carta pra você");
                    compra(jogador, mesa);
                    break;
                } else {
                    ultimaJogada = 2;
                }
                menuJogador(baralho, mesa, jogador);
                break;
            case 3:
                if(ultimaJogada == 1) {
                    System.out.println("Você passou a vez.");
                } else {
                    System.out.println("Você passou a vez sem jogar...+1 carta e perdeu a vez");
                    compra(jogador, mesa);
                }
                ultimaJogada = 3;
                break;
            default:
                System.out.println("Opção inválida, tente novamente.");
                menuJogador(baralho, mesa, jogador);
                break;
        }
    }

    // Compra uma carta
    public void compra(Jogador jogador, Mesa mesa) {
        Cartas cartaComprada = mesa.comprarCarta();
        if (cartaComprada != null) {
            jogador.getMao().add(cartaComprada);
            jogador.setNumeroDeCartas(jogador.getNumeroDeCartas() + 1);
        }
        System.out.println("Nova mão:");
        imprimirMao();
    }

    public void jogarCarta(Mesa mesa) {
        Scanner scanner = new Scanner(System.in);
        List<Cartas> mao = getMao(); // retorna a mão do jogador
        System.out.println("Escolha o número da carta que deseja jogar:");
        for (int i = 0; i < mao.size(); i++) {
            System.out.println((i + 1) + ": " + mao.get(i));
        }
        int cartaIndex = scanner.nextInt();
        if (cartaIndex >= 1 && cartaIndex <= mao.size()) {
            Cartas cartaJogada = mao.get(cartaIndex - 1);
            Cartas ultimaCarta = mesa.getUltimaCartaJogada();

            boolean podeJogar = false;

            // Verifica se a carta pode ser jogada
            if (cartaJogada.getCor() == ultimaCarta.getCor() ||
                cartaJogada.getNumero() == ultimaCarta.getNumero() ||
                mesa.getCorAtual() == cartaJogada.getCor() ||
                cartaJogada.getCor() == Cor.PRETA) { // Cartas coringa
                podeJogar = true;
            }

            if (podeJogar) {
                mao.remove(cartaIndex - 1);
                //mesa.jogarCarta(cartaJogada); 
                setNumeroDeCartas(mao.size()); // atualiza o número de cartas na mão
                System.out.println("Você jogou a carta: " + cartaJogada);

                // Se a carta jogada for uma carta coringa, perguntar a cor escolhida
                if (cartaJogada.getCor() == Cor.PRETA) {
                    System.out.println("Escolha a cor para continuar (AZUL, AMARELA, VERDE, VERMELHA):");
                    String novaCor = scanner.next().toUpperCase();
                    Cor corEscolhida = Cor.valueOf(novaCor);
                    mesa.setCorAtual(corEscolhida); // define a cor atual da mesa
                }
                mesa.setUltimaCartaJogada(cartaJogada);
                System.out.println("Nova mão:");
                imprimirMao(); // imprime a mão do jogador
                ultimaJogada = 4;
            } else {
                System.out.println("Você não pode jogar essa carta. Tente novamente");
                ultimaJogada = -1;
            }
        } else {
            System.out.println("Carta inválida.");
        }
    }
                        
    public int getUltimaJogada() {
        return ultimaJogada;
    }
                        
    public void setUltimaJogada(int ultimaJogada) {
        this.ultimaJogada = ultimaJogada;
    }
                        
}