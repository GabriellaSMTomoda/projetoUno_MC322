import java.util.ArrayList;
import java.util.List;

public class Mesa {
    private List<Cartas> monteDeCompra;   
    private Cartas ultimaCartaJogada;
    private int jogadorAtual = 0;
    
    private boolean ordemNormal = true;
    private boolean bloqueado = false;
    private boolean compraDuas = false;
    private boolean compraQuatro = false;
    
    private boolean primeiroTurno = true;
    private Cor corAtual;
    
    
    // ----------------------------------- CONSTRUTOR -----------------------------------
    public Mesa() {
        monteDeCompra = new ArrayList<>();
        this.ultimaCartaJogada = null;
        jogadorAtual = 0;
        ordemNormal = true;
    }
    
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
        if(compraDuas) {
            operacaoCarta.compraDuas(jogadores.get(jogadorAtual), mesa);
            System.out.println("O jogador anterior jogou um +2 para você");
            compraDuas = false;
        }
        if(compraQuatro) {
            operacaoCarta.compraQuatro(jogadores.get(jogadorAtual), mesa);
            System.out.println("O jogador anterior jogou um +4 para você");
            compraQuatro = false;
        }
    }

    // Método para comprar cartas quando o jogador precisar
    // Se o baralho estiver vazio, ele atualiza o baralho com uma pilha de descarte embaralhada 
    public Cartas comprarCarta() {
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
