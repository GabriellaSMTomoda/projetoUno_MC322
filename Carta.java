import java.io.Serializable;

public interface Carta extends Serializable {
    Cor getCor();
    TipoDeCarta getTipo();
    int getNumero();
}