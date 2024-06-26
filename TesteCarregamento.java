import java.util.List;

public class TesteCarregamento {
    public static void main(String[] args) {
        //carregar o estado da mesa
        Mesa mesa = Mesa.carregarEstado("estado_mesa.dat");

        if (mesa != null) {
            System.out.println("Estado da mesa carregado com sucesso!");
            System.out.println(mesa);

            //ve se tem a lista de jogadores
            List<Jogador> jogadores = Jogador.getJogadores();
            for (Jogador jogador : jogadores) {
                System.out.println("Jogador: " + jogador.getNome());
                System.out.println("Mão: " + jogador.getMao());
            }
        } else {
            System.out.println(mesa);
            System.out.println("Falha ao carregar o estado da mesa.");
        }
    }
}
