import java.util.ArrayList;

import com.astralinear.app.Kartu.Kartu;

public class Deck{
    private List<Kartu> deckKartu;
    private int capacity;
    private int currentCardCount;

    public Deck(int capacity){
        this.deckKartu = new ArrayList<>();
        this.capacity = capacity;
        this.currentCardCount = 0;
    }

    public void shuffle(){

    }

    public void setDeckKartu(){

    }

    public void drawKartu(){
        //exception ketika kartu sudah 0
    }

    public void shuffleDraw(){

    }

    public void useKartu(){
        
    }
}
