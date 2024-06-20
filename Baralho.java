import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Cartas> baralho;

    public Baralho() {
        this.baralho = new ArrayList<>();
        formarBaralho(); // Função que cria o baralho a partir das cartas que podem existir
        embaralhar(); // Função que embaralha o baralho todas as vezes necessárias
    }

    public void formarBaralho() {
        // Adiciona cartas de número de cada cor
        for (Cor cor : Cor.values()) {
            if (cor != Cor.PRETA) {
                // Adiciona a carta 0 apenas uma vez por cor
                baralho.add(new Cartas(cor, 0));
                
                // Adiciona cartas de número 1 a 9 duas vezes cada
                for (int i = 1; i <= 9; i++) {
                    baralho.add(new Cartas(cor, i));
                    baralho.add(new Cartas(cor, i));
                }

                // Adiciona cartas especiais de cada cor (duas vezes cada)
                for (int i = 0; i < 2; i++) {
                    baralho.add(new Cartas(cor, TipoDeCarta.COMPRA_MAIS_2));
                    baralho.add(new Cartas(cor, TipoDeCarta.BLOQUEIO));
                    baralho.add(new Cartas(cor, TipoDeCarta.INVERTE));
                }
            }
        }

        // Adicionar cartas pretas (especiais)
        for (int i = 0; i < 4; i++) {
            baralho.add(new Cartas(TipoDeCarta.COMPRA_MAIS_4));
            baralho.add(new Cartas(TipoDeCarta.ALTERACOR));
        }
    }

    // Embaralha o baralho
    public void embaralhar() {
        Collections.shuffle(baralho);
    }

    // Método para comprar cartas quando o jogador precisar
    // Se o baralho estiver vazio, ele atualiza o baralho com uma pilha de descarte embaralhada 
    public Cartas comprarCarta(List<Cartas> pilhaDeDescarte) {
        if (baralho.isEmpty()) {
            if (pilhaDeDescarte == null || pilhaDeDescarte.isEmpty()) {
                return null;
            }
            reabastecerBaralho(pilhaDeDescarte);
            embaralhar();
        }
        return baralho.remove(baralho.size() - 1);
    }

    private void reabastecerBaralho(List<Cartas> pilhaDeDescarte) {
        Cartas cartaNoTopo = pilhaDeDescarte.remove(pilhaDeDescarte.size() - 1); // Deixar a última carta de fora
        baralho.addAll(pilhaDeDescarte);
        pilhaDeDescarte.clear();
        pilhaDeDescarte.add(cartaNoTopo); // Recolocar a última carta na pilha de descarte
    }

    public List<Cartas> getBaralho() {
        return baralho;
    }

    @Override
    public String toString() {
        return "Baralho{" + "baralho=" + baralho + '}';
    }

    public void imprimirBaralho() {
        for (Cartas carta : baralho) {
            if (carta.getTipo() == TipoDeCarta.NUMERO) {
                System.out.println("Cor: " + carta.getCor() + ", Numero: " + carta.getNumero());
            } else {
                System.out.println("Cor: " + carta.getCor() + ", Tipo: " + carta.getTipo());
            }
        }
    }
}
