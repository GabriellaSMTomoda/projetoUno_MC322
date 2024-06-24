public class CartaEspecialColoridaFactory extends CartaFactory {
    @Override
    public Carta criarCarta(Cor cor, TipoDeCarta tipo, int numero) {
        if (tipo == TipoDeCarta.NUMERO || cor == Cor.PRETA) {
            throw new IllegalArgumentException("Tipo de carta ou cor inválidos para CartaEspecialColoridaFactory.");
        }
        return new Cartas(cor, tipo);
    }
}
