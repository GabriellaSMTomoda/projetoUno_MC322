import java.util.List;
import java.util.Scanner;

class OperacaoJogador extends Jogador {

    private int ultimaJogada;
    private boolean uno = false;
    public boolean jogouCarta = false; // Variável para controlar se o jogador já jogou uma carta


    public OperacaoJogador(String nome, int numeroDeCartas, int ultimaJogada) {
        super(nome, numeroDeCartas);
        this.ultimaJogada = ultimaJogada;
    }


    public void menuJogador(Baralho baralho, Mesa mesa, Jogador jogador, OperacaoCarta operacaoCarta, Scanner scanner) {
        boolean turnoAtivo = true;
        // boolean jogouCarta = false; // Variável para controlar se o jogador já jogou uma carta
    
        while (turnoAtivo) {
            mesa.imprimeUltimaCarta();
            if (mesa.getUltimaCartaJogada().getCor() == Cor.PRETA) {
                System.out.println("A cor da rodada é: " + mesa.getCorAtual());
            }
    
            System.out.println("Digite o número do que deseja fazer: ");
            System.out.println("0 - Jogar carta\n" + 
                                "1 - Comprar carta\n" +
                                "2 - UNO!!!!\n" +
                                "3 - Passar vez\n");
            int operacao = scanner.nextInt();
    
            switch (operacao) {
                case 0: // Jogar carta
                    if (jogouCarta) { // Jogador já jogou
                        System.out.println("Você já jogou uma carta neste turno! +1 carta pelo mal-caratismo. PRÓXIMO JOGADOR");
                        ultimaJogada = -1;
                        turnoAtivo = false;
                        jogouCarta = false;
                    } else if (getNumeroDeCartas() == 1 && !uno) { // Jogador não "disse" UNO na última rodada
                        System.out.println("Você esqueceu de gritar UNO na última rodada! +1 carta e perdeu sua vez");
                        ultimaJogada = -1;
                        compra(jogador, mesa);
                        turnoAtivo = false;
                        jogouCarta = false;
                    } else {
                        jogarCarta(mesa, operacaoCarta, jogador, scanner);
                        ultimaJogada = 4;
                    }
                    break;
                case 1: // Comprar 1 carta
                    if (jogouCarta) { // Jogador tentou comprar carta mas já jogou
                        compra(jogador, mesa);
                        System.out.println("Você já jogou! +1 carta e perdeu seu turno");
                        turnoAtivo = false;
                    } else {
                        compra(jogador, mesa);
                        ultimaJogada = 1;
                    }
                    break;
                case 2: // "Grita" UNO
                    if (getNumeroDeCartas() != 1) { // Jogador tem mais de 1 carta
                        System.out.println("Você só pode gritar UNO quando tiver uma carta! +1 carta pra você");
                        compra(jogador, mesa);
                    } else {
                        uno = true; // Define que o jogador gritou UNO
                        System.out.println("UNO!!!"); // Mostra que o jogador gritou UNO
                        ultimaJogada = 5;
                    }
                    break;
                case 3: // Passa o turno
                    if (jogouCarta) { // Jogador jogou uma carta na mesa
                        System.out.println("Você passou a vez.");
                        ultimaJogada = 3;
                        turnoAtivo = false;
                    } else {
                        if (!uno) { // Jogador não gritou UNO na última rodada
                            System.out.println("Você está tentando passar a vez sem jogar, +1 carta");
                            compra(jogador, mesa);
                        } else {
                            uno = false; // Reseta o estado de UNO após passar a vez corretamente
                            turnoAtivo = false;
                        }
                    }
                    jogouCarta = false;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
        scanner.nextLine(); // Limpa o buffer do scanner
    }
    

    //compra uma carta
    public void compra(Jogador jogador, Mesa mesa) {
        Carta cartaComprada = mesa.comprarCarta();
        if (cartaComprada != null) {
            jogador.getMao().add(cartaComprada);
            jogador.setNumeroDeCartas(jogador.getNumeroDeCartas() + 1);
        }
        System.out.println("Nova mão:");
        imprimirMao();
    }

    //joga uma carta na mesa
    public void jogarCarta(Mesa mesa, OperacaoCarta operacaoCarta, Jogador jogador, Scanner scanner) {
        List<Carta> mao = getMao(); // retorna a mão do jogador
        System.out.println("Escolha o número da carta que deseja jogar:");
        for (int i = 0; i < mao.size(); i++) {
            System.out.println((i + 1) + ": " + mao.get(i));
        }
        int cartaIndex = scanner.nextInt();
        if (cartaIndex >= 1 && cartaIndex <= mao.size()) { // carta está no range do numero de cartas da mão do jogador
            Carta cartaJogada = mao.get(cartaIndex - 1);
            Carta ultimaCarta = mesa.getUltimaCartaJogada();

            boolean podeJogar = false;

            // verifica se a carta pode ser jogada a partir das condições do uno
            if (cartaJogada.getCor() == ultimaCarta.getCor() ||  //mesma cor
                    cartaJogada.getNumero() == ultimaCarta.getNumero() || //mesmo número
                    mesa.getCorAtual() == cartaJogada.getCor() || //cor atual da mesa(caso a carta da mesa seja preta)
                    cartaJogada.getCor() == Cor.PRETA) { // Cartas coringa
                podeJogar = true;
            }

            if (podeJogar) {
                mao.remove(cartaIndex - 1);
                setNumeroDeCartas(mao.size()); // atualiza o número de cartas na mão
                System.out.println("Você jogou a carta: " + cartaJogada);
                operacaoCarta.realizarOperacaoCarta(cartaJogada, mesa, scanner); //realiza operação da carta
                mesa.getMonteDeCompra().add(mesa.getUltimaCartaJogada()); //adicona a antiga carta da mesa na pilha de compra
                mesa.setUltimaCartaJogada(cartaJogada);
                System.out.println("Nova mão:");
                imprimirMao(); // imprime a mão do jogador
                jogouCarta = true;
            } else {
                System.out.println("Você não pode jogar essa carta. Tente novamente");
                jogouCarta = false;
                
            }
        } else {
            System.out.println("Carta inválida.");
            jogouCarta = false;
            
        }
    }

    public int getUltimaJogada() {
        return ultimaJogada;
    }

    public void setUltimaJogada(int ultimaJogada) {
        this.ultimaJogada = ultimaJogada;
    }

}
