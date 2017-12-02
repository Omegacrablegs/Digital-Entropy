package digital_entropy;

import java.util.Random;
import java.util.Scanner;

public class GameRun implements GameState {
    // Declare the cards for the game, Starting with 5 Guards Cards
    private Card Guard1 = new Card("Guard",10,10,1,1);
    private Card Guard2 = new Card("Guard",10,10,1,2);
    private Card Guard3 = new Card("Guard",10,10,1,3);
    private Card Guard4 = new Card("Guard",10,10,1,4);
    private Card Guard5 = new Card("Guard",10,10,1,5);
    // 2 Priest Cards
    private Card Priest1 = new Card("Priest",10,10,2,6);
    private Card Priest2 = new Card("Priest",10,10,2,7);
    // 2 Baron Cards
    private Card Baron1 = new Card("Baron",10,10,3,8);
    private Card Baron2 = new Card("Baron",10,10,3,9);
    // 2 Handmaiden Cards
    private Card Handmaiden1 = new Card("Handmaiden",10,10,4,10);
    private Card Handmaiden2 = new Card("Handmaiden",10,10,4,11);
    // 2 Prince Cards
    private Card Prince1 = new Card("Prince",10,10,5,12);
    private Card Prince2 = new Card("Prince",10,10,5,13);
    // 1 King Cards
    private Card King = new Card("King",10,10,6,14);
    // 1 Countess
    private Card Countess = new Card("Countess",10,10,7,15);
    // 1 Princess
    private Card Princess = new Card("Princess",10,10,8,16);

    private Card NULL = new Card("NULL",10,10,-1,-1);

    // Declare 4 players for the game, although not all will be actually used

    private  HumanPlayer Player0 = new HumanPlayer();
    private  RandomBot Player1 = new RandomBot("Rando1");
    private  RandomBot Player2 = new RandomBot("Rando2");
    private  RandomBot Player3 = new RandomBot("Rando3");

    private int numberOfPlayers;
    private int roundNumber;
    private Card [] Deck = new Card [16];  // Deck size is the same each game
    private Player [] Players = new Player [] {Player0,Player1,Player2,Player3};
    private boolean isRunning = true;
    private int currentPlayer;
    private int targetPlayer;
    private int lastRoundWinner = 0;

    /**
     * digital_entropy.Main Method
     *
     */
    public void startGame() {
        makeDeck();
        makePlayers();
        int roundWinner;
        while (isRunning){
            roundWinner = runGame();
            switch (roundWinner) {
                case 0:
                    Player0.tickWinNumber();
                    break;
                case 1:
                    Player1.tickWinNumber();
                    break;
                case 2:
                    Player2.tickWinNumber();
                    break;
                case 3:
                    Player3.tickWinNumber();
                    break;
            }
            if(Player0.isWinner()||Player1.isWinner()||Player2.isWinner()||Player3.isWinner()){
                isRunning = false;
            }
        }

        System.out.println("Thanks for playing! Game Over");
    }


    /**
     * Prepares the Deck for the game, deck construction is the same each game but shuffled after being created
     *
     */
    public void makeDeck(){
        Deck[0] = Guard1;
        Deck[1] = Guard2;
        Deck[2] = Guard3;
        Deck[3] = Guard4;
        Deck[4] = Guard5;
        Deck[5] = Priest1;
        Deck[6] = Priest2;
        Deck[7] = Baron1;
        Deck[8] = Baron2;
        Deck[9] = Handmaiden1;
        Deck[10] = Handmaiden2;
        Deck[11] = Prince1;
        Deck[12] = Prince2;
        Deck[13] = King;
        Deck[14] = Countess;
        Deck[15] = Princess;
        shuffleDeck();

    }

    /**
     * Selects the number of players in the game, the type of bots and the name of the player
     */
    public  void makePlayers(){
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Welcome to Love Letter, Please enter your Name");
        String playerName = playerInput.nextLine();
        Player0.setName(playerName);
        System.out.println(playerName + " please select the number of bots to play with (1-3)");
        numberOfPlayers = playerInput.nextInt();
        resetPlayers();
    }

    /**
     *  Resets all players to be playing depending on how many are selected at the start
     */
    public  void resetPlayers(){
        switch (numberOfPlayers) {
            case 1:
                Player0.setPlaying(true);
                Player0.setTargetable(true);
                Player1.setPlaying(true);
                Player1.setTargetable(true);
                break;
            case 2:
                Player0.setPlaying(true);
                Player0.setTargetable(true);
                Player1.setPlaying(true);
                Player1.setTargetable(true);
                Player2.setPlaying(true);
                Player2.setTargetable(true);
                break;
            case 3:
                Player0.setPlaying(true);
                Player0.setTargetable(true);
                Player1.setPlaying(true);
                Player1.setTargetable(true);
                Player2.setPlaying(true);
                Player2.setTargetable(true);
                Player3.setPlaying(true);
                break;
            default:
                System.out.println("Invalid Selection, Setting number of bots to 3");
                Player0.setPlaying(true);
                Player0.setTargetable(true);
                Player1.setPlaying(true);
                Player1.setTargetable(true);
                Player2.setPlaying(true);
                Player2.setTargetable(true);
                Player3.setPlaying(true);
                Player3.setTargetable(true);
                break;
        }
    }

    /**
     * Resets the players card and then draws from the start of the deck
     */
    public void drawStartingHand(){
        // gives each player a card
        if(Player3.isPlaying()){
            Player0.setInHand(Deck[1]);;
            System.out.println(Player0.getName()+" drew the "+Player0.getInHand().getName()+" card.");
            Player1.setInHand(Deck[2]);
            Player2.setInHand(Deck[3]);
            Player3.setInHand(Deck[4]);
            roundNumber = 5;
        }else if (Player2.isPlaying()){
            Player0.setInHand(Deck[1]);;
            System.out.println(Player0.getName()+" drew the "+Player0.getInHand().getName()+" card.");
            Player1.setInHand(Deck[2]);
            Player2.setInHand(Deck[3]);
            roundNumber = 4;
        }else {
            Player0.setInHand(Deck[1]);;
            System.out.println(Player0.getName()+" drew the "+Player0.getInHand().getName()+" card.");
            Player1.setInHand(Deck[2]);
            roundNumber = 3;
        }
    }

    /**
     *  Plays the game out until a player wins a round
     *
     * @return return an int to show which player has won the round
     */
    public int runGame(){
        Scanner scan = new Scanner (System.in);
        // first card is discarded
        currentPlayer = lastRoundWinner;
        int cardSelection = 0;
        drawStartingHand();
        // Game runs until one player remains or the deck runs out of cards
        while   ((((Player0.isPlaying()&&(Player1.isPlaying()||Player2.isPlaying()||Player3.isPlaying()))
                ||(Player1.isPlaying()&&(Player0.isPlaying()||Player2.isPlaying()||Player3.isPlaying()))
                ||(Player2.isPlaying()&&(Player0.isPlaying()||Player1.isPlaying()||Player3.isPlaying()))
                ||(Player3.isPlaying()&&(Player0.isPlaying()||Player1.isPlaying()||Player2.isPlaying())))&& roundNumber <15) ){

            // digital_entropy.Player who's turn it is goes
            if(Players[currentPlayer].isPlaying()) {
                Players[currentPlayer].setTargetable(true);
                Players[currentPlayer].setDrawnCard(Deck[roundNumber]);
                cardSelection = Players[currentPlayer].getTurn();
                playedCard(cardSelection);

                if (currentPlayer == numberOfPlayers) {
                    currentPlayer = 0;
                } else {
                    currentPlayer++;
                    roundNumber++;
                }
            }
                else if(currentPlayer == numberOfPlayers){
                    currentPlayer=0;
                    roundNumber++;
                }else {
                    currentPlayer++;
                    roundNumber++;
                }
            }

        // Round over game is reset for the next round
        shuffleDeck();
        if(Player0.isPlaying()){
            resetPlayers();
            lastRoundWinner=0;
            return 0;
        }else if(Player1.isPlaying()){
            resetPlayers();
            lastRoundWinner=1;
            return 1;
        }else if(Player2.isPlaying()){
            resetPlayers();
            lastRoundWinner=2;
            return 2;
        }else{
            resetPlayers();
            lastRoundWinner=3;
            return 3;
        }
    }

    /**
     * Fisher–Yates shuffle with O(n) complexity
     *
     */
    public void shuffleDeck() {
        Random card = new Random();
        for (int i = Deck.length - 1; i > 0; i--) {
            int index = card.nextInt(i);
            // swap positions;
            Card temp = Deck[index];
            Deck[index] = Deck[i];
            Deck[i] = temp;
        }
    }

    /**
     * Method to help set up the played card effects by passing the player and card chosen
     * @param card the card played, either 1 or 2
     */
    public void playedCard(int card){
        switch(card){
            case 1 :
                do {
                    targetPlayer = Players[currentPlayer].getTarget();
                    System.out.println(Players[currentPlayer].getName()+" is Playing a Guard and targeting "+Players[targetPlayer].getName());
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                int guess;
                do {
                    guess = Players[currentPlayer].getGuess();
                    if(guess==1){
                        System.out.println("You can't guess Guard");
                    }
                }while(guess == 1);
                playGuard(guess);
                break;
            case 2:
                do {
                    targetPlayer = Players[currentPlayer].getTarget();
                    System.out.println(Players[currentPlayer].getName()+" is Playing a Priest");
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playPriest();
                break;
            case 3:
                do {
                    targetPlayer = Players[currentPlayer].getTarget();
                    System.out.println(Players[currentPlayer].getName()+" is Playing a Baron");
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playBaron();
                break;
            case 4:
                playHandmaiden();
                break;
            case 5:
                do {
                    targetPlayer = Players[currentPlayer].getTarget();
                    System.out.println(Players[currentPlayer].getName()+" is Playing a Prince");
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playPrince();
                break;
            case 6:
                do {
                    targetPlayer = Players[currentPlayer].getTarget();
                    System.out.println(Players[currentPlayer].getName()+" is Playing the King");
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playKing();
                break;
            case 7:
                playCountess();
                break;
            case 8:
                playPrincess();
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    // Methods for playing each card,

    /**
     * Guard picks a player and checks is guess == targets card value
     * @param guess The card type the player believes target has
     */

    public void playGuard(int guess) {
        System.out.println(Players[currentPlayer].getName()+" is guessing "+guess);
        if (Players[targetPlayer].getInHand().getValue() == guess) {
            System.out.println(Players[targetPlayer].getName() + " has been eliminated");
            Players[targetPlayer].setPlaying(false);
            Players[targetPlayer].setTargetable(false);
        } else {
            System.out.println("Wrong guess " + Players[currentPlayer].getName());
        }
    }

    /**
     * Priest allows a player to see another players card
     */
    public void playPriest(){
        System.out.println(Players[targetPlayer].getName()+" is Holding a "+Players[targetPlayer].getInHand().getName());
    }

    /**
     * Baron compares the value of the players card and the targets and the loser is eliminated
     */
    public void playBaron(){
        if(Players[currentPlayer].getInHand().getValue()==3){
            Players[currentPlayer].setInHand(Players[currentPlayer].getDrawnCard());
        }
        if(Players[currentPlayer].getInHand().getValue()>Players[targetPlayer].getInHand().getValue()){
            Players[targetPlayer].setPlaying(false);
            Players[targetPlayer].setTargetable(false);
            System.out.println(Players[currentPlayer].getName()+" has defeated "+Players[targetPlayer].getName());
        }else if(Players[currentPlayer].getInHand().getValue()==Players[targetPlayer].getInHand().getValue()){
            System.out.println("It's a draw!");
        }else{
            Players[currentPlayer].setPlaying(false);
            Players[currentPlayer].setTargetable(false);
            System.out.println(Players[targetPlayer].getName()+" has defeated "+Players[currentPlayer].getName());
        }
    }

    /**
     * Playing the handmaiden makes the player untargetable
     */
    public void playHandmaiden(){

        System.out.println(Players[currentPlayer].getName()+" Played the Handmaiden");
        Players[currentPlayer].setTargetable(false);
    }

    /**
     * The Price digital_entropy.Card causes the target to discard his current card and redrawn a new card from the deck
     * If the princess is discarded the player is defeated
     */
    public void playPrince() {
        if (Players[targetPlayer].getInHand().getValue() == 8) {
            System.out.println(Players[targetPlayer].getName() + " discarded the Princess and is Eliminated!");
            Players[targetPlayer].setPlaying(false);
            Players[targetPlayer].setTargetable(false);
            Players[targetPlayer].setInHand(NULL);
        } else {
            System.out.println(Players[targetPlayer].getName() + " discarded the " + Players[targetPlayer].getInHand().getName() + " card!");
            roundNumber++;
            Players[targetPlayer].setInHand(Deck[roundNumber]);
            if(Players[targetPlayer].getPlayerType()==0){
                System.out.println(Players[targetPlayer].getName()+ " drew the "+Players[targetPlayer].getInHand().getName()+" card");
            }
        }
    }

    /**
     * The King digital_entropy.Card causes the target to trade their hand with the player who played the king
     */
    public void playKing(){
        Card tempCard;
        System.out.println(Players[currentPlayer].getName()+" has played the king against "+ Players[targetPlayer].getName());
        if(Players[currentPlayer].getInHand().getValue()==6){
            Players[currentPlayer].setInHand(Players[currentPlayer].getDrawnCard());
        }
        tempCard = Players[currentPlayer].getInHand();
        Players[currentPlayer].setInHand(Players[targetPlayer].getInHand());
        Players[targetPlayer].setInHand(tempCard);
        if(Players[currentPlayer].getPlayerType()==0){
            System.out.println(Players[currentPlayer].getName()+ " has the card "+Players[currentPlayer].getInHand().getName());
        }
    }

    /**
     *  The Countess has no effect when played but must be played when a player has either a Prince or the King digital_entropy.Card
     */
    public void playCountess(){
        System.out.println(Players[currentPlayer].getName()+" Played the Countess!");
    }

    /**
     * The Princess if played or discarded causes the player who played her to be defeated
     */
    public void playPrincess() {
        System.out.println(Players[currentPlayer].getName() + " Played the Princess and is defeated");
        Players[currentPlayer].setPlaying(false);
        Players[currentPlayer].setTargetable(false);
    }

    public boolean isRunning() {
        return isRunning;
    }

     public void getGameState(){
        for(int i = 0; i<4; i++){
            
        }
    }
}
