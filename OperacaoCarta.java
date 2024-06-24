import java.util.List;
import java.util.Scanner;

public class OperacaoCarta {

    public void realizarOperacaoCarta(Carta carta, Mesa mesa) {
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
                mudaCor(carta, mesa);
                mesa.setCompraQuatro(true);
                break;
            case ALTERACOR:
                mudaCor(carta, mesa);
                break;
            case NUMERO:
                // No specific action needed for number cards
                break;
            default:
                throw new IllegalArgumentException("Tipo de carta desconhecido: " + tipo);
        }
    }

    public void compra(Jogador jogador, Mesa mesa) {
        Carta cartaComprada = mesa.comprarCarta();
        if (cartaComprada != null) {
            jogador.getMao().add(cartaComprada);
            jogador.setNumeroDeCartas(jogador.getNumeroDeCartas() + 1);
        }
    }

    public void inverterOrdem(Mesa mesa) {
        mesa.setOrdemNormal(!mesa.isOrdemNormal());
    }

    public void bloquearProximoJogador(Mesa mesa) {
        mesa.setBloqueado(true);
    }

    public void compraDuas(Jogador jogador, Mesa mesa) {
        for (int i = 0; i < 2; i++) {
            compra(jogador, mesa);
        }
    }

    public void compraQuatro(Jogador jogador, Mesa mesa) {
        for (int i = 0; i < 4; i++) {
            compra(jogador, mesa);
        }
    }

    public void mudaCor(Carta carta, Mesa mesa) {
        Scanner scanner = new Scanner(System.in);

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
