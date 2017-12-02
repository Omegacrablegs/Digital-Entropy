package digital_entropy;

import java.util.Scanner;

public class HumanPlayer extends Player implements PlayerTurn {

    public HumanPlayer() {
        super();

    }

    public int getTurn(){
        Scanner scan = new Scanner(System.in);
        int cardPlayed;
        int cardPlayed2;
        System.out.println(getName() + " has drawn the " + getDrawnCard().getName() + " Card");
        System.out.println("Enter 1 to play the " +getInHand().getName() + " card or 2 to play the " + getDrawnCard().getName() + " card.");
        cardPlayed2 = scan.nextInt();
        if(cardPlayed2==1){
            cardPlayed = getInHand().getValue();
            setDiscard(getInHand());
            setInHand(getDrawnCard());
            return cardPlayed;
        }else if(cardPlayed2==2){
            cardPlayed = getDrawnCard().getValue();


            return cardPlayed;
        }else{
            return -1;
        }

    }

    public int getTarget(){
        Scanner scan = new Scanner(System.in);
        int target;
        System.out.println("Who do you wish to Target? [0-3]");
        return target = scan.nextInt();
    }

    public int getGuess(){
        Scanner scan = new Scanner(System.in);
        int guess;
        System.out.println("What card do they have? [1-8]");
        return guess=scan.nextInt();
    }
}
