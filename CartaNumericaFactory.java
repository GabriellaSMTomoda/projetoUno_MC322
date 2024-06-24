public class CartaNumericaFactory extends CartaFactory {
    @Override
    public Carta criarCarta(Cor cor, TipoDeCarta tipo, int numero) {
        if (tipo != TipoDeCarta.NUMERO) {
            throw new IllegalArgumentException("Tipo de carta inválido para CartaNumericaFactory.");
        }
        return new Cartas(cor, numero);
    }
}
