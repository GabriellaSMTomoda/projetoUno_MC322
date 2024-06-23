import java.util.List;
import java.util.Scanner;

public class OperacaoCarta {
    // private int numeroDeCartas;

    // public OperacaoCarta(Cor cor, int numero, int numeroDeCartas) {
    //     super(cor, numero);
    //     this.numeroDeCartas = numeroDeCartas;
    // }

    // public OperacaoCarta(Cor cor, TipoDeCarta tipo, int numeroDeCartas) {
    //     super(cor, tipo);
    //     this.numeroDeCartas = numeroDeCartas;
    // }

    // public OperacaoCarta(TipoDeCarta tipo, int numeroDeCartas) {
    //     super(tipo);
    //     this.numeroDeCartas = numeroDeCartas;
    // }

    public void realizarOperacaoCarta(Cartas carta, Mesa mesa) {
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
                
                break;
        
            default:
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
    }

    // Método para inverter a ordem de jogo
    public void inverterOrdem(Mesa mesa) {
        mesa.setOrdemNormal(false);
    }
        
    // Método para bloquear o próximo jogador
    public void bloquearProximoJogador(Mesa mesa) {
        mesa.setBloqueado(true);
    }


    // Compra duas cartas
    public void compraDuas(Jogador jogador, Mesa mesa) {
        for (int i = 0; i < 2; i++) {
            compra(jogador, mesa);
        }
    }

    // Compra quatro cartas
    public void compraQuatro(Jogador jogador, Mesa mesa) {
        for (int i = 0; i < 4; i++) {
            compra(jogador, mesa);
        }
    }

    // Muda a cor
    public void mudaCor(Cartas carta, Mesa mesa) {
        Scanner scanner = new Scanner(System.in);
    
        if (carta.getCor() == Cor.PRETA) {
            while (true) {
                System.out.println("Escolha a cor para continuar (AZUL, AMARELA, VERDE, VERMELHA):");
                String novaCor = scanner.next().toUpperCase();
    
                try {
                    Cor corEscolhida = Cor.valueOf(novaCor);
                    mesa.setCorAtual(corEscolhida); // define a cor atual da mesa
                    break; // sai do loop se a cor for válida
                } catch (IllegalArgumentException e) {
                    System.out.println("Cor inválida. Por favor, escolha uma cor válida (AZUL, AMARELA, VERDE, VERMELHA).");
                }
            }
        }
    }
}
