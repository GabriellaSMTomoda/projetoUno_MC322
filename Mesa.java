import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private List<Cartas> monteDeCompra;   
    private Cartas ultimaCartaJogada;
    private int jogadorAtual;


    public void primeiraCarta(Baralho baralho) {
        if (!baralho.getBaralho().isEmpty()) {
            List<Cartas> cartas = baralho.getBaralho();
            for (int i = 0; i < cartas.size(); i++) {
                Cartas carta = cartas.get(i);
                if (carta.getTipo() == TipoDeCarta.NUMERO) { // verifica se a carta é do tipo NUMERO
                    cartas.remove(i); // remove a carta do baralho
                    setUltimaCartaJogada(carta); // define a última carta jogada
                    monteDeCompra = new ArrayList<>(cartas); // aualiza o monte de compra com as cartas restantes
                    return; // sai do loop após encontrar a primeira carta de número
                }
            }
        } else {
            System.out.println("O baralho está vazio!");
        }
    }

    public Jogador proximoTurno(List<Jogador> jogadores) {
        int tamanho = jogadores.size();
        jogadorAtual = (jogadorAtual + 1) % tamanho; //atualiza o jogador atual para o próximo turno
        return jogadores.get(jogadorAtual);
    }


    public void imprimeUltimaCarta() {
        System.out.println("última carta: " + ultimaCartaJogada);
    }
    
    public Mesa() {
        monteDeCompra = new ArrayList<>();
        this.ultimaCartaJogada = null;
        jogadorAtual = 0;
    }

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


    @Override
    public String toString() {
        return "Mesa{" +
                "monteDeCompra=" + monteDeCompra +
                ", ultimaCartaJogada=" + ultimaCartaJogada +
                '}';
    }
}
