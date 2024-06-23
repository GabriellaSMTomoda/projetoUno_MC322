import java.util.List;

public class OperacaoCarta extends Baralho {
    private int numeroDeCartas;

    public OperacaoCarta() {
        super();
        this.numeroDeCartas = 0;
    }

    public OperacaoCarta(List<Cartas> baralho, int numeroDeCartas) {
        super(baralho);
        this.numeroDeCartas = numeroDeCartas;
    }

    // Compra uma carta
    public void compra(Jogador jogador, Mesa mesa) {
        Cartas cartaComprada = mesa.comprarCarta();
        if (cartaComprada != null) {
            jogador.getMao().add(cartaComprada);
            jogador.setNumeroDeCartas(jogador.getNumeroDeCartas() + 1);
        }
    }

    // Inverte a ordem de jogo
    public void inverte(Mesa mesa) {
        mesa.inverterOrdem();
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

    // Bloqueia o próximo jogador
    public void bloqueio(Mesa mesa) {
        mesa.bloquearProximoJogador();
    }

    // Muda a cor
    public void mudaCor(Cartas carta, Cor novaCor) {
        carta.setCor(novaCor);
    }
}
