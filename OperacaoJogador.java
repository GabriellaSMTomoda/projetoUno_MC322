import java.util.List;
import java.util.Scanner;

class OperacaoJogador extends Jogador {

    private int ultimaJogada;
    private boolean uno = false;

    public OperacaoJogador(String nome, int numeroDeCartas, int ultimaJogada) {
        super(nome, numeroDeCartas);
        this.ultimaJogada = ultimaJogada;
    }

    //menu impresso para o jogador escolher o que vai fazer
    public void menuJogador(Baralho baralho, Mesa mesa, Jogador jogador, OperacaoCarta operacaoCarta, Scanner scanner) {
        boolean turnoAtivo = true;
    
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
                case 0: //jogar carta
                    if (ultimaJogada == 4) { //jogador já jogou
                        System.out.println("Você já jogou uma carta neste turno! +1 carta pelo mal-caratismo. PRÓXIMO JOGADOR");
                        ultimaJogada = -1;
                        turnoAtivo = false;
                    } else if (getNumeroDeCartas() == 1 && ultimaJogada != 2) { //jogador não "disse" UNO na última rodada
                        System.out.println("Você esqueceu de gritar UNO na última rodada! +1 carta e perdeu sua vez");
                        ultimaJogada = -1;
                        compra(jogador, mesa);
                        turnoAtivo = false;
                    } else {
                        jogarCarta(mesa, operacaoCarta, jogador);
                        ultimaJogada = 4;
                    }
                    break;
                case 1: //comprar 1 carta
                    if (ultimaJogada == 4) { //jogador tentou comprar carta mas já jogou
                        compra(jogador, mesa);
                        System.out.println("Você já jogou! +1 carta e perdeu seu turno");
                        turnoAtivo = false;
                    } else {
                        compra(jogador, mesa);
                        ultimaJogada = 1;
                    }
                    break;
                case 2: //"grita" UNO
                    if (getNumeroDeCartas() != 1) { //jogador tem mais de 1 carta
                        System.out.println("Você só pode gritar UNO quando tiver uma carta! +1 carta pra você");
                        compra(jogador, mesa);
                    } else {
                        uno = true; // define que o jogador gritou UNO
                        System.out.println("UNO!!!"); //mostra que o jogador gritou UNO
                        ultimaJogada = 2;
                    }
                    break;
                case 3: //passa o turno
                    if (ultimaJogada == 4) { //jogador jogou uma carta na mesa
                        System.out.println("Você passou a vez.");
                        ultimaJogada = 3;
                        turnoAtivo = false;
                    } else {
                        if (!uno) { //jogador não gritou UNO na última rodada
                            System.out.println("Você está tentando passar a vez sem jogar, +1 carta");
                            compra(jogador, mesa);
                        } else {
                            uno = false; //reseta o estado de UNO após passar a vez corretamente
                            turnoAtivo = false;
                        }
                    }
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
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
    public void jogarCarta(Mesa mesa, OperacaoCarta operacaoCarta, Jogador jogador) {
        Scanner scanner = new Scanner(System.in);
        List<Carta> mao = getMao(); // retorna a mão do jogador
        System.out.println("Escolha o número da carta que deseja jogar:");
        for (int i = 0; i < mao.size(); i++) {
            System.out.println((i + 1) + ": " + mao.get(i));
        }
        int cartaIndex = scanner.nextInt();
        if (cartaIndex >= 1 && cartaIndex <= mao.size()) { // carta existe na mão do jogador
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
