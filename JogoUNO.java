public class JogoUNO {

    public static void main(String[] args) {
        // Exemplo de uso:
        Mesa mesa = Mesa.getInstance(); // Obtém a instância única da Mesa
        mesa.primeiraCarta(new Baralho()); // Define a primeira carta da mesa
        mesa.embaralharMonteDeCompra(); // Embaralha o monte de compra

        // Salvar o estado da mesa
        mesa.salvarEstado("estado_mesa.dat");

        // Exemplo de carregar o estado da mesa
        Mesa mesaCarregada = Mesa.carregarEstado("estado_mesa.dat");
        System.out.println("Mesa carregada: \n" + mesaCarregada);
    }
}
