import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Baralho {
    private List<Carta> baralho;

    // --------------------------- CONSTRUTOR-----------------------------------
    public Baralho() {
        this.baralho = new ArrayList<>();
        formarBaralho(); //função que cria o baralho a partir das cartas que podem existir
        embaralhar(); // função que embaralha o baralho todas as vezes necessárias
    }

    public void formarBaralho() {
        CartaFactory numericaFactory = new CartaNumericaFactory();
        CartaFactory especialColoridaFactory = new CartaEspecialColoridaFactory();
        CartaFactory especialPretaFactory = new CartaEspecialPretaFactory();

        //adiciona cartas de número de cada cor
        for (Cor cor : Cor.values()) {
            if (cor != Cor.PRETA) {
                // adiciona a carta 0 apenas uma vez por cor
                baralho.add(numericaFactory.criarCarta(cor, TipoDeCarta.NUMERO, 0));
                
                // adiciona cartas de número 1 a 9 duas vezes cada
                for (int i = 1; i <= 9; i++) {
                    baralho.add(numericaFactory.criarCarta(cor, TipoDeCarta.NUMERO, i));
                    baralho.add(numericaFactory.criarCarta(cor, TipoDeCarta.NUMERO, i));
                }

                // adiciona cartas especiais de cada cor (duas vezes cada)
                for (int i = 0; i < 2; i++) {
                    baralho.add(especialColoridaFactory.criarCarta(cor, TipoDeCarta.COMPRA_MAIS_2, -1));
                    baralho.add(especialColoridaFactory.criarCarta(cor, TipoDeCarta.BLOQUEIO, -1));
                    baralho.add(especialColoridaFactory.criarCarta(cor, TipoDeCarta.INVERTE, -1));
                }
            }
        }

        // adiciona cartas pretas (especiais)
        for (int i = 0; i < 4; i++) {
            baralho.add(especialPretaFactory.criarCarta(Cor.PRETA, TipoDeCarta.COMPRA_MAIS_4, -1));
            baralho.add(especialPretaFactory.criarCarta(Cor.PRETA, TipoDeCarta.ALTERACOR, -1));
        }
    }

    //Embaralha o baralho
    public void embaralhar() {
        Collections.shuffle(baralho);
    }

    // Método para comprar cartas quando o jogador precisar
    // Se o baralho estiver vazio, ele atualiza o baralho com uma pilha de descarte embaralhada 
    public Carta comprarCarta(List<Carta> pilhaDeDescarte) {
        if (baralho.isEmpty()) {
            if (pilhaDeDescarte == null || pilhaDeDescarte.isEmpty()) {
                return null;
            }
            reabastecerBaralho(pilhaDeDescarte);
            embaralhar();
        }
        return baralho.remove(baralho.size() - 1);
    }

    //Caso o baralho acabe, adiciona um novo na pilha de compra/descarte
    private void reabastecerBaralho(List<Carta> pilhaDeDescarte) {
        Carta cartaNoTopo = pilhaDeDescarte.remove(pilhaDeDescarte.size() - 1); // Não usa a última carta
        baralho.addAll(pilhaDeDescarte);
        pilhaDeDescarte.clear();
        pilhaDeDescarte.add(cartaNoTopo); // Recoloca a última carta na pilha de descarte
    }

    // --------------------------- GETTERS E SETTERS ---------------------------
    public List<Carta> getBaralho() {
        return baralho;
    }

    public void setBaralho(List<Carta> baralho) {
        this.baralho = baralho;
    }

    // Método toString para imprimir o estado do baralho
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Carta carta : baralho) {
            sb.append(carta).append("\n");
        }
        return sb.toString();
    }

    // Método que imprime o baralho para teste
    public void imprimirBaralho() {
        for (Carta carta : baralho) {
            if (carta.getTipo() == TipoDeCarta.NUMERO) {
                System.out.println("Cor: " + carta.getCor() + ", Numero: " + carta.getNumero());
            } else {
                System.out.println("Cor: " + carta.getCor() + ", Tipo: " + carta.getTipo());
            }
        }
    }
}
