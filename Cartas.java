public class Cartas implements Carta {
    private Cor cor;
    private TipoDeCarta tipo;
    private int numero; // -1 se não for uma carta de número

// ----------------------CONSTRUTORES----------------------------
    public Cartas(Cor cor, int numero) {
        this.tipo = TipoDeCarta.NUMERO;
        this.cor = cor;
        this.numero = numero;
    }

    // Construtor para cartas especiais coloridas
    public Cartas(Cor cor, TipoDeCarta tipo) {
        if (cor == Cor.PRETA) {
            throw new IllegalArgumentException("Cor preta não é permitida para cartas coloridas.");
        }
        this.tipo = tipo;
        this.cor = cor;
        this.numero = -1;
    }


    // Construtor para cartas especiais pretas
    public Cartas(TipoDeCarta tipo) {
        if (tipo != TipoDeCarta.COMPRA_MAIS_4 && tipo != TipoDeCarta.ALTERACOR) {
            throw new IllegalArgumentException("Tipo de carta inválido para cartas pretas.");
        }
        this.tipo = tipo;
        this.cor = Cor.PRETA;
        this.numero = -1;
    }

    //-------------------------GETTERS E SETTERS---------------------------
    @Override
    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        if (this.tipo == TipoDeCarta.NUMERO && cor == Cor.PRETA) {
            throw new IllegalArgumentException("Cartas de número não podem ser de cor preta.");
        }
        this.cor = cor;
    }

    @Override
    public TipoDeCarta getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeCarta tipo) {
        this.tipo = tipo;
    }

    @Override
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        if (this.tipo != TipoDeCarta.NUMERO) {
            throw new IllegalArgumentException("Somente cartas de número podem ter um número.");
        }
        this.numero = numero;
    }

    @Override
    public String toString() {
        if (tipo == TipoDeCarta.NUMERO) {
            return "Carta{" + "cor=" + cor + ", numero=" + numero + '}';
        } else {
            return "Carta{" + "cor=" + cor + ", tipo=" + tipo + '}';
        }
    }
}
