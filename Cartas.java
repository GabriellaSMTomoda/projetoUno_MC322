public class Cartas {
    private Cor cor;
    private TipoDeCarta tipo;
    private int numero; //-1 se for muda de cor
    
    //___________CONSTRUTOR__________________
    public Cartas(Cor cor, TipoDeCarta tipo, int numero) {
        this.tipo = tipo;
        this.cor = cor;
        this.numero = numero;
    }
    
    //________GETTERS E SETTERS________________
    public Cor getCor() {
        return cor;
    }
    public void setCor(Cor cor) {
        this.cor = cor;
    }
    
    public TipoDeCarta getTipo() {
        return tipo;
    }
    
    public void setTipo(TipoDeCarta tipo) {
        this.tipo = tipo;
    }
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
}

