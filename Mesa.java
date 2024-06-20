import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private List<Cartas> monteDeCompra;   
    private Cartas ultimaCartaJogada;
    private int jogadorAtual;

    // ----------------------------------- CONSTRUTOR -----------------------------------
    public Mesa() {
        monteDeCompra = new ArrayList<>();
        this.ultimaCartaJogada = null;
        jogadorAtual = 0;
    }

    // ----------------------------------- GETTERS E SETTERS ---------------------------
    public List<Cartas> getMonteDeCompra() {
        return monteDeCompra;
    }

    public void setMonteDeCompra(List<Cartas> monteDeCompra) {
        this.monteDeCompra = monteDeCompra;
    }

    public Cartas getUltimaCartaJogada() {
        return ultimaCartaJogada;
    }

    public void setUltimaCartaJogada(Cartas ultimaCartaJogada) {
        this.ultimaCartaJogada = ultimaCartaJogada;
    }

    public int getJogadorAtual() {
        return jogadorAtual;
    }

    public void setJogadorAtual(int jogadorAtual) {
        this.jogadorAtual = jogadorAtual;
    }

    // Método que define a primeira carta da mesa
    public void primeiraCarta(Baralho baralho) {
        if (!baralho.getBaralho().isEmpty()) {
            List<Cartas> cartas = baralho.getBaralho();
            for (int i = 0; i < cartas.size(); i++) {
                Cartas carta = cartas.get(i);
                if (carta.getTipo() == TipoDeCarta.NUMERO) {
                    cartas.remove(i);
                    setUltimaCartaJogada(carta);
                    monteDeCompra = new ArrayList<>(cartas);
                    return;
                }
            }
        } else {
            System.out.println("O baralho está vazio!");
        }
    }

    // Método para passar para o próximo turno
    public Jogador proximoTurno(List<Jogador> jogadores) {
        int tamanho = jogadores.size();
        jogadorAtual = (jogadorAtual + 1) % tamanho;
        return jogadores.get(jogadorAtual);
    }

    // Método para imprimir a última carta jogada
    public void imprimeUltimaCarta() {
        System.out.println("Última carta jogada: " + ultimaCartaJogada);
    }

    // Método toString para representação textual da Mesa
    @Override
    public String toString() {
        return "Mesa{" +
                "monteDeCompra=" + monteDeCompra +
                ", ultimaCartaJogada=" + ultimaCartaJogada +
                '}';
    }
}
