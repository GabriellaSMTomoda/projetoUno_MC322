import java.util.Scanner;

public class OperacaoCarta {

    //Vê o tipo da carta e realiza a "operação" dela
    public void realizarOperacaoCarta(Carta carta, Mesa mesa, Scanner scanner) {
        TipoDeCarta tipo = carta.getTipo();
        switch (tipo) {
            case INVERTE:
                inverterOrdem(mesa);
                break;
            case BLOQUEIO:
                bloquearProximoJogador(mesa);
                break;
            case COMPRA_MAIS_2:
                mesa.setCompraDuas(true);
                break;
            case COMPRA_MAIS_4:
                mudaCor(carta, mesa, scanner);
                mesa.setCompraQuatro(true);
                break;
            case ALTERACOR:
                mudaCor(carta, mesa, scanner);
                break;
            case NUMERO:
                break;
            default:
                throw new IllegalArgumentException("Tipo de carta desconhecido: " + tipo);
        }
    }

    private void compra(Jogador jogador, Mesa mesa) {
        Carta cartaComprada = mesa.comprarCarta();
        if (cartaComprada != null) {
            jogador.getMao().add(cartaComprada);
            jogador.setNumeroDeCartas(jogador.getNumeroDeCartas() + 1);
        }
    }

    //inverte a ordem que está a rodada
    public void inverterOrdem(Mesa mesa) {
        mesa.setOrdemNormal(!mesa.isOrdemNormal());
    }

    //bloqueia
    public void bloquearProximoJogador(Mesa mesa) {
        mesa.setBloqueado(true);
    }

    //compra 2
    public void compraDuas(Jogador jogador, Mesa mesa) {
        for (int i = 0; i < 2; i++) {
            compra(jogador, mesa);
        }
    }

    //compra 4
    public void compraQuatro(Jogador jogador, Mesa mesa) {
        for (int i = 0; i < 4; i++) {
            compra(jogador, mesa);
        }
    }

    //muda a cor da carta da mesa de acordo com oque o jogador selecionou
    public void mudaCor(Carta carta, Mesa mesa, Scanner scanner) {

        if (carta.getCor() == Cor.PRETA) {
            while (true) {
                System.out.println("Escolha a cor para continuar (AZUL, AMARELA, VERDE, VERMELHA):");
                String novaCor = scanner.next().toUpperCase();

                try {
                    Cor corEscolhida = Cor.valueOf(novaCor);
                    mesa.setCorAtual(corEscolhida);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Cor inválida. Por favor, escolha uma cor válida (AZUL, AMARELA, VERDE, VERMELHA).");
                }
            }
        }
    }
}
