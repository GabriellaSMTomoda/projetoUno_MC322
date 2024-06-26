public class JogoUNO {

    public static Mesa configurarJogo() {
        Mesa mesa = Mesa.carregarEstado("estado_mesa.dat");
        if (mesa == null) {
            mesa = Mesa.getInstance();
            Baralho baralho = new Baralho();
            mesa.primeiraCarta(baralho);
            mesa.embaralharMonteDeCompra();
        }
        return mesa;
    }

    public static void salvarJogo(Mesa mesa) {
        mesa.salvarEstado("estado_mesa.dat");
    }
}
