import java.util.List;
import java.util.ArrayList;


public class Baralho {
    private List<Cartas> baralho;

    public Baralho() {
        this.baralho = new ArrayList<>();

        Cartas carta1 = new Cartas(Cor.AMARELA, TipoDeCarta.COMUM, 0);
        Cartas carta2 = new Cartas(Cor.PRETA, TipoDeCarta.ALTERAcOR, -1);
    
        baralho.add(carta1);
        baralho.add(carta2);
    }

    
    
    //________CONSTRUTOR____________________ 
    public Baralho(List<Cartas> baralho) {
        this.baralho = baralho;
    }
    
    //_______GETTER E SETTERS________________
    public List<Cartas> getBaralho() {
        return baralho;
    }

    public void setBaralho(List<Cartas> baralho) {
        this.baralho = baralho;
    }

}
