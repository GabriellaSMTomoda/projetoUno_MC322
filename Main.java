public class Main {
    public static void main(String[] args) {
        System.out.println("Bem vindx ao nosso...");
        System.out.println(" .----------------.  .-----------------. .----------------.  .----------------. \n" + //
                "| .--------------. || .--------------. || .--------------. || .--------------. |\n" + //
                "| | _____  _____ | || | ____  _____  | || |     ____     | || |              | |\n" + //
                "| ||_   _||_   _|| || ||_   \\|_   _| | || |   .'    `.   | || |      _       | |\n" + //
                "| |  | |    | |  | || |  |   \\ | |   | || |  /  .--.  \\  | || |     | |      | |\n" + //
                "| |  | '    ' |  | || |  | |\\ \\| |   | || |  | |    | |  | || |     | |      | |\n" + //
                "| |  \\  `--'  /  | || | _| |_\\   |_  | || |  \\  `--'  /  | || |     | |      | |\n" + //
                "| |   `. __ .'   | || ||_____|\\____| | || |   `.____.'   | || |     |_|      | |\n" + //
                "| |              | || |              | || |              | || |     (_)      | |\n" + //
                "| '--------------' || '--------------' || '--------------' || '--------------' |\n" + //
                " '----------------'  '----------------'  '----------------'  '----------------' \n" + //
                "\n" + //
                "");

                Baralho baralho = new Baralho();
                Jogador.listaDeJogadores();

                //baralho.imprimirBaralho();
        
                Jogador jogador = new Jogador("Temp", 0); 
                jogador.distribuirCartas(baralho);
        
                for (Jogador j : Jogador.getJogadores()) {
                    j.imprimirMao();
                }
                //baralho.imprimirBaralho();


        
    }
}
