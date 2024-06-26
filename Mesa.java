import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L;

    private static Mesa instance = null;

    private List<Carta> monteDeCompra;
    private Carta ultimaCartaJogada;
    private int jogadorAtual = 0;

    private boolean ordemNormal = true;
    private boolean bloqueado = false;
    private boolean compraDuas = false;
    private boolean compraQuatro = false;

    private boolean primeiroTurno = true;
    private Cor corAtual;

    // -------------------CONSTRUTOR --------------------------
    private Mesa() {
        monteDeCompra = new ArrayList<>();
        this.ultimaCartaJogada = null;
        jogadorAtual = 0;
        ordemNormal = true;
    }

    public static Mesa getInstance() {
        if (instance == null) {
            instance = new Mesa();
        }
        return instance;
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

    // Método para passar para o próximo turno(atualiza o jogador)
    public Jogador proximoTurno(List<Jogador> jogadores, OperacaoCarta operacaoCarta, Mesa mesa) {
        if (bloqueado) { //se a carta da mesa for um bloqueio
            jogadorAtual = (jogadorAtual + (ordemNormal ? 2 : -2) + jogadores.size()) % jogadores.size(); //pula 2 índices da lista de jogadores
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
    public Carta comprarCarta() {
        if (monteDeCompra.isEmpty()) {
            //adiciona um novo baralho se o monte de compra estiver vazio
            Baralho novoBaralho = new Baralho();
            monteDeCompra.addAll(novoBaralho.getBaralho());
        }

        if (monteDeCompra.isEmpty()) {
            return null; //retorna null se ainda estiver vazio após adicionar um novo baralho
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

    // Método para salvar o estado atual da mesa no arquivo
    public void salvarEstado(String nomeArquivo) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            outputStream.writeObject(this); // Grava o estado atual da mesa no arquivo
            System.out.println("Estado da mesa salvo com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar estado da mesa: " + e.getMessage());
        }
    }

    // Método estático para carregar o estado da mesa a partir de um arquivo
    public static Mesa carregarEstado(String nomeArquivo) {
        Mesa mesa = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            mesa = (Mesa) inputStream.readObject(); //lê o estado da mesa do arquivo
            System.out.println("Estado da mesa carregado com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar estado da mesa: " + e.getMessage());
        }
        return mesa;
    }

    @Override
    public String toString() {
        return "Mesa [monteDeCompra=" + monteDeCompra + ", ultimaCartaJogada=" + ultimaCartaJogada + ", jogadorAtual="
                + jogadorAtual + ", ordemNormal=" + ordemNormal + ", bloqueado=" + bloqueado + ", compraDuas="
                + compraDuas + ", compraQuatro=" + compraQuatro + ", primeiroTurno=" + primeiroTurno + ", corAtual="
                + corAtual + "]";
    }
}
