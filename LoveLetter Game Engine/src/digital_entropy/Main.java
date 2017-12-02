package digital_entropy;


public class Main {
    public static void main(String [] args){
        GameRun game = new GameRun();
        do {
            game.startGame();
        }while(game.isRunning() == true);
    }
}
