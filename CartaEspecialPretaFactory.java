public class CartaEspecialPretaFactory extends CartaFactory {
    @Override
    public Carta criarCarta(Cor cor, TipoDeCarta tipo, int numero) {
        if (cor != Cor.PRETA || (tipo != TipoDeCarta.COMPRA_MAIS_4 && tipo != TipoDeCarta.ALTERACOR)) {
            throw new IllegalArgumentException("Tipo de carta ou cor inválidos para CartaEspecialPretaFactory.");
        }
        return new Cartas(tipo);
    }
}
