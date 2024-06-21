import java.util.Scanner;

class OperacaoJogador extends Jogador {
    
    private int ultimaJogada;
    
    public OperacaoJogador(String nome, int numeroDeCartas, int ultimaJogada) {
        super(nome, numeroDeCartas);
        this.ultimaJogada = ultimaJogada;
    }
    
    public void menuJogador() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o número do que deseja fazer: ");
        System.out.println("0 - Jogar carta\n" + 
                            "1 - Comprar carta\n" +
                            "2 - UNO!!!!\n");
        int operacao = scanner.nextInt();
        switch (operacao) {
            case 0:
                if((ultimaJogada != 2) && (getNumeroDeCartas() == 1)) {
                    System.out.println("Ihhh...Você esqueceu de gritar UNO na última rodada, ganhou +1 carta");
                }
                ultimaJogada = 0;
   
                break;
            case 1:
                ultimaJogada = 1;
            
                break;
            case 2:
                if(getNumeroDeCartas() != 1) {
                    System.out.println("Acho que você gritou UNO na hora errada... Ganhou +1 carta");
                }
                ultimaJogada = 2;

                break;
            
            default:
                System.out.println("Número escolhido inválido, tente novamente");
                menuJogador();
                break;
        }
    }
                        
    public int getUltimaJogada() {
        return ultimaJogada;
    }
                        
    public void setUltimaJogada(int ultimaJogada) {
        this.ultimaJogada = ultimaJogada;
    }
                        
}