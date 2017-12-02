package digital_entropy;
import java.util.Random;
public class RandomBot extends Player implements PlayerTurn {
    Random rand = new Random();

    public RandomBot (String name){
        super();
        this.name = name;
    }

    public int getTurn(){
        return (rand.nextInt(1)+1);

    }

    public int getTarget(){
        return (rand.nextInt(3));
    }

    public int getGuess(){
        return (rand.nextInt(6)+2);
    }
}
