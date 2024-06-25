import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mesa {

    private static Mesa instance = null; // Passo 1: Campo estático para a instância única

    private List<Carta> monteDeCompra;
    private Carta ultimaCartaJogada;
    private int jogadorAtual = 0;

    private boolean ordemNormal = true;
    private boolean bloqueado = false;
    private boolean compraDuas = false;
    private boolean compraQuatro = false;

    private boolean primeiroTurno = true;
    private Cor corAtual;

    // Passo 2: Construtor privado
    private Mesa() {
        monteDeCompra = new ArrayList<>();
        this.ultimaCartaJogada = null;
        jogadorAtual = 0;
        ordemNormal = true;
    }

    // Passo 3: Método estático para acessar a instância única
    public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
    }

    // Métodos da classe Mesa continuam aqui...

    // ----------------------------------- GETTERS E SETTERS ---------------------------
    public boolean isCompraDuas() {
        return compraDuas;
    }

    public void setCompraDuas(boolean compraDuas) {
        this.compraDuas = compraDuas;
    }

    public boolean isCompraQuatro() {
        return compraQuatro;
    }

    public void setCompraQuatro(boolean compraQuatro) {
        this.compraQuatro = compraQuatro;
    }

    public boolean isOrdemNormal() {
        return ordemNormal;
    }

    public void setOrdemNormal(boolean ordemNormal) {
        this.ordemNormal = ordemNormal;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public boolean isPrimeiroTurno() {
        return primeiroTurno;
    }

    public void setPrimeiroTurno(boolean primeiroTurno) {
        this.primeiroTurno = primeiroTurno;
    }

    public void setCorAtual(Cor cor) {
        this.corAtual = cor;
    }

    public Cor getCorAtual() {
        return corAtual;
    }

    public List<Carta> getMonteDeCompra() {
        return monteDeCompra;
    }

    public void setMonteDeCompra(List<Carta> monteDeCompra) {
        this.monteDeCompra = monteDeCompra;
    }

    public Carta getUltimaCartaJogada() {
        return ultimaCartaJogada;
    }

    public void setUltimaCartaJogada(Carta ultimaCartaJogada) {
        this.ultimaCartaJogada = ultimaCartaJogada;
    }

    // Método que define a primeira carta da mesa
    public void primeiraCarta(Baralho baralho) {
        if (!baralho.getBaralho().isEmpty()) {
            List<Carta> cartas = baralho.getBaralho();
            for (int i = 0; i < cartas.size(); i++) {
                Carta carta = cartas.get(i);
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
    public Jogador proximoTurno(List<Jogador> jogadores, OperacaoCarta operacaoCarta, Mesa mesa) {
        if (bloqueado) {
            jogadorAtual = (jogadorAtual + (ordemNormal ? 2 : -2) + jogadores.size()) % jogadores.size();
            bloqueado = false;
            return jogadores.get(jogadorAtual);
        } 
        if (primeiroTurno) {
            jogadorAtual = 0;
            primeiroTurno = !primeiroTurno;
        } else {
            jogadorAtual = (jogadorAtual + (ordemNormal ? 1 : -1) + jogadores.size()) % jogadores.size();
            comprarCarta(operacaoCarta, jogadores, mesa);
        }
        return jogadores.get(jogadorAtual);
    }

    public void comprarCarta(OperacaoCarta operacaoCarta, List<Jogador> jogadores, Mesa mesa) {
        if (compraDuas) {
            operacaoCarta.compraDuas(jogadores.get(jogadorAtual), mesa);
            System.out.println("O jogador anterior jogou um +2 para você");
            compraDuas = false;
        }
        if (compraQuatro) {
            operacaoCarta.compraQuatro(jogadores.get(jogadorAtual), mesa);
            System.out.println("O jogador anterior jogou um +4 para você");
            compraQuatro = false;
        }
    }

    // Método para comprar cartas quando o jogador precisar
    // Se o baralho estiver vazio, ele atualiza o baralho com uma pilha de descarte embaralhada 
    public Carta comprarCarta() {
        if (monteDeCompra.isEmpty()) {
            // Adiciona um novo baralho se o monte de compra estiver vazio
            Baralho novoBaralho = new Baralho();
            monteDeCompra.addAll(novoBaralho.getBaralho());
        }

        if (monteDeCompra.isEmpty()) {
            return null; // Retorna null se ainda estiver vazio após adicionar um novo baralho
        }

        return monteDeCompra.remove(monteDeCompra.size() - 1);
    }

    public void embaralharMonteDeCompra() {
        Collections.shuffle(monteDeCompra);
    }

    // Método para imprimir a última carta jogada
    public void imprimeUltimaCarta() {
        System.out.println("Última carta jogada: " + ultimaCartaJogada);
    }

    // Método toString para representação textual da Mesa
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monte de Compra:\n");
        for (Carta carta : monteDeCompra) {
            sb.append(carta).append("\n");
        }
        sb.append("Última Carta Jogada: ").append(ultimaCartaJogada).append("\n");
        return sb.toString();
    }
}
