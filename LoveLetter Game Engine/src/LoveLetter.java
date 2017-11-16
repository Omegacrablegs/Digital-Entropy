import java.util.Random;
import java.util.Scanner;

public class LoveLetter {
    // Declare the cards for the game, Starting with 5 Guards Cards
    private static Card Guard1 = new Card("Guard",10,10,1);
    private static Card Guard2 = new Card("Guard",10,10,1);
    private static Card Guard3 = new Card("Guard",10,10,1);
    private static  Card Guard4 = new Card("Guard",10,10,1);
    private static  Card Guard5 = new Card("Guard",10,10,1);
    // 2 Priest Cards
    private static  Card Priest1 = new Card("Priest",10,10,2);
    private static  Card Priest2 = new Card("Priest",10,10,2);
    // 2 Baron Cards
    private static Card Baron1 = new Card("Baron",10,10,3);
    private static  Card Baron2 = new Card("Baron",10,10,3);
    // 2 Handmaiden Cards
    private static  Card Handmaiden1 = new Card("Handmaiden",10,10,4);
    private static  Card Handmaiden2 = new Card("Handmaiden",10,10,4);
    // 2 Prince Cards
    private static  Card Prince1 = new Card("Prince",10,10,5);
    private static  Card Prince2 = new Card("Prince",10,10,5);
    // 1 King Cards
    private static  Card King = new Card("King",10,10,6);
    // 1 Countess
    private static  Card Countess = new Card("Countess",10,10,7);
    // 1 Princess
    private static  Card Princess = new Card("Princess",10,10,8);

    // Declare 4 players for the game, although not all will be actually used

    private static Player Player0 = new Player("PlayerZero",0);
    private static Player Player1 = new Player("PlayerOne",0);
    private static Player Player2 = new Player("PlayerTwo",0);
    private static Player Player3 = new Player("PlayerThree",0);

    private static int numberOfPlayers;
    private static int roundNumber;
    private static Card [] Deck = new Card [16];  // Deck size is the same each game
    private static Player [] Players = new Player [] {Player0,Player1,Player2,Player3};
    private static Card [] DiscardPile = new Card [16];
    private static boolean isRunning = true;
    private static int currentPlayer;
    private static int targetPlayer;
    private static int lastRoundWinner = 0;

    /**
     * Main Method
     * @param args unused
     */
    public static void main(String [] args) {
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
    public static void makeDeck(){
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
    public static void makePlayers(){
        Scanner playerInput = new Scanner(System.in);
        System.out.println("Welcome to Love Letter, Please enter your Player Name");
        String playerName = playerInput.nextLine();
        Player0.setName(playerName);
        System.out.println(playerName + " please select the number of bots to play with (1-3)");
        numberOfPlayers = playerInput.nextInt();
        resetPlayers();
    }

    /**
     *  Resets all players to be playing depending on how many are selected at the start
     */
    public static void resetPlayers(){
        switch (numberOfPlayers) {
            case 1:
                Player0.setPlaying(true);
                Player1.setPlaying(true);
                break;
            case 2:
                Player0.setPlaying(true);
                Player1.setPlaying(true);
                Player2.setPlaying(true);
                break;
            case 3:
                Player0.setPlaying(true);
                Player1.setPlaying(true);
                Player2.setPlaying(true);
                Player3.setPlaying(true);
                break;
            default:
                System.out.println("Invalid Selection, Setting number of bots to 3");
                Player0.setPlaying(true);
                Player1.setPlaying(true);
                Player2.setPlaying(true);
                Player3.setPlaying(true);
                break;
        }
    }

    /**
     * Resets the players card and then draws from the start of the deck
     */
    public static void drawStartingHand(){
        // gives each player a card
        if(Player3.isPlaying()){
            Player0.setCurrentCard(Deck[1].getName());
            Player0.setCardValue(Deck[1].getValue());
            System.out.println(Player0.getName()+" drew the "+Player0.getCurrentCard()+" card.");
            Player1.setCurrentCard(Deck[2].getName());
            Player1.setCardValue(Deck[2].getValue());
            System.out.println(Player1.getName()+" drew the "+Player1.getCurrentCard()+" card.");
            Player2.setCurrentCard(Deck[3].getName());
            Player2.setCardValue(Deck[3].getValue());
            System.out.println(Player2.getName()+" drew the "+Player2.getCurrentCard()+" card.");
            Player3.setCurrentCard(Deck[4].getName());
            Player3.setCardValue(Deck[4].getValue());
            System.out.println(Player3.getName()+" drew the "+Player3.getCurrentCard()+" card.");
            roundNumber = 5;
        }else if (Player2.isPlaying()){
            Player0.setCurrentCard(Deck[1].getName());
            Player0.setCardValue(Deck[1].getValue());
            System.out.println(Player0.getName()+" drew the "+Player0.getCurrentCard()+" card.");
            Player1.setCurrentCard(Deck[2].getName());
            Player1.setCardValue(Deck[2].getValue());
            System.out.println(Player1.getName()+" drew the "+Player1.getCurrentCard()+" card.");
            Player2.setCurrentCard(Deck[3].getName());
            Player2.setCardValue(Deck[3].getValue());
            System.out.println(Player2.getName()+" drew the "+Player2.getCurrentCard()+" card.");
            roundNumber = 4;
        }else {
            Player0.setCurrentCard(Deck[1].getName());
            Player0.setCardValue(Deck[1].getValue());
            System.out.println(Player0.getName()+" drew the "+Player0.getCurrentCard()+" card.");
            Player1.setCurrentCard(Deck[2].getName());
            Player1.setCardValue(Deck[2].getValue());
            System.out.println(Player1.getName()+" drew the "+Player1.getCurrentCard()+" card.");
            roundNumber = 3;
        }
    }

    /**
     *  Plays the game out until a player wins a round
     *
     * @return return an int to show which player has won the round
     */
    public static int runGame(){
        Scanner scan = new Scanner (System.in);
        // first card is discarded
        currentPlayer = lastRoundWinner;
        int cardSelection = 0;
        drawStartingHand();
        // Game runs until one player remains or the deck runs out of cards
        while   (((Player0.isPlaying()&&(Player1.isPlaying()||Player2.isPlaying()||Player3.isPlaying()))
                ||(Player1.isPlaying()&&(Player0.isPlaying()||Player2.isPlaying()||Player3.isPlaying()))
                ||(Player2.isPlaying()&&(Player0.isPlaying()||Player1.isPlaying()||Player3.isPlaying()))
                ||(Player3.isPlaying()&&(Player0.isPlaying()||Player1.isPlaying()||Player2.isPlaying())))&& roundNumber <15 ){

            // Player who's turn it is goes

        switch (currentPlayer){
            case 0:
                if(Player0.isPlaying()){
                    Player0.setTargetable(true);
                    Player0.setSecondCardValue(Deck [roundNumber].getValue());
                    Player0.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player0.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player0.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player0.getCardValue());
                        Player0.setCardValue(Player0.getSecondCardValue());
                        Player0.setCurrentCard(Player0.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player0.getSecondCardValue());
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    currentPlayer++;
                    roundNumber++;
                    break;
                }else{
                    currentPlayer++;
                    break;
                }
            case 1:
                if(Player1.isPlaying()){
                    Player1.setTargetable(true);
                    Player1.setSecondCardValue(Deck [roundNumber].getValue());
                    Player1.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player1.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player1.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player1.getCardValue());
                        Player1.setCardValue(Player1.getSecondCardValue());
                        Player1.setCurrentCard(Player1.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player1.getSecondCardValue());
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    currentPlayer++;
                    roundNumber++;
                    break;
                }else{
                    currentPlayer++;
                    break;
                }
            case 2:
                if(Player2.isPlaying()){
                    Player2.setTargetable(true);
                    Player2.setSecondCardValue(Deck [roundNumber].getValue());
                    Player2.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player2.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player2.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player2.getCardValue());
                        Player2.setCardValue(Player2.getSecondCardValue());
                        Player2.setCurrentCard(Player2.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player2.getSecondCardValue());
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    currentPlayer++;
                    roundNumber++;
                    break;
                }else{
                    currentPlayer++;
                    break;
                }
            case 3:
                if(Player3.isPlaying()){
                    Player3.setTargetable(true);
                    Player3.setSecondCardValue(Deck [roundNumber].getValue());
                    Player3.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player3.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player3.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player3.getCardValue());
                        Player3.setCardValue(Player3.getSecondCardValue());
                        Player3.setCurrentCard(Player3.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player3.getSecondCardValue());
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    roundNumber++;
                    currentPlayer=0;
                    break;
                }else{
                    currentPlayer=0;
                    break;
                }
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;

        }

        // Round over game is reset for the next round
        }
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
    public static void shuffleDeck() {
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
    public static void playedCard(int card){
        Scanner scan = new Scanner(System.in);
        switch(card){
            case 1 :
                do {
                    System.out.println("Who do you want to target? (0-3)");
                    targetPlayer = scan.nextInt();
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                System.out.println("What card do you think they have? (1-8)");
                int guess = scan.nextInt();
                playGuard(guess);
                break;
            case 2:
                do {
                    System.out.println("Who do you want to target? (0-3)");
                    targetPlayer = scan.nextInt();
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playPriest();
                break;
            case 3:
                do {
                    System.out.println("Who do you want to target? (0-3)");
                    targetPlayer = scan.nextInt();
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
                    System.out.println("Who do you want to target? (0-3)");
                    targetPlayer = scan.nextInt();
                    if(Players[targetPlayer].isTargetable()==false){
                        System.out.println(Players[targetPlayer].getName()+" is untargetable please select another player");
                    }
                }while(Players[targetPlayer].isTargetable()==false);
                playPrince();
                break;
            case 6:
                do {
                    System.out.println("Who do you want to target? (0-3)");
                    targetPlayer = scan.nextInt();
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

    public static void playGuard(int guess) {
        if (Players[targetPlayer].getCardValue() == guess) {
            System.out.println(Players[targetPlayer].getName() + " has been eliminated");
            Players[targetPlayer].setPlaying(false);
        } else {
            System.out.println("Wrong guess " + Players[currentPlayer].getName());
        }
    }

    /**
     * Priest allows a player to see another players card
     */
    public static void playPriest(){
        System.out.println(Players[targetPlayer].getName()+" is Holding a "+Players[targetPlayer].getCurrentCard());
    }

    /**
     * Baron compares the value of the players card and the targets and the loser is eliminated
     */
    public static void playBaron(){
        if(Players[currentPlayer].getCardValue()>Players[targetPlayer].getCardValue()){
            Players[currentPlayer].setPlaying(false);
            System.out.println(Players[currentPlayer].getName()+" has defeated "+Players[targetPlayer].getName());
        }else if(Players[currentPlayer].getCardValue()==Players[targetPlayer].getCardValue()){
            System.out.println("It's a draw!");
        }else{
            Players[currentPlayer].setPlaying(false);
            System.out.println(Players[targetPlayer].getName()+" has defeated "+Players[currentPlayer].getName());
        }
    }

    /**
     * Playing the handmaiden makes the player untargetable
     */
    public static void playHandmaiden(){

        System.out.println(Players[currentPlayer].getName()+" Played the Handmaiden");
        Players[currentPlayer].setTargetable(false);
    }

    /**
     * The Price Card causes the target to discard his current card and redrawn a new card from the deck
     * If the princess is discarded the player is defeated
     */
    public static void playPrince() {
        if (Players[targetPlayer].getCardValue() == 8) {
            System.out.println(Players[targetPlayer].getName() + " discarded the Princess and is Eliminated!");
            Players[targetPlayer].setPlaying(false);
            Players[targetPlayer].setCardValue(0);
            Players[targetPlayer].setCurrentCard("NULL");
        } else {
            System.out.println(Players[targetPlayer].getName() + " discarded the " + Players[targetPlayer].getCurrentCard() + " card!");
            roundNumber++;
            Players[targetPlayer].setCardValue(Deck[roundNumber].getValue());
            Players[targetPlayer].setCurrentCard(Deck[roundNumber].getName());
        }
    }

    /**
     * The King Card causes the target to trade their hand with the player who played the king
     */
    public static void playKing(){
        int tempCardNumber;
        String tempCardName;
        System.out.println(Players[currentPlayer].getName()+" has played the king against "+ Players[targetPlayer].getName());
        tempCardNumber = Players[currentPlayer].getCardValue();
        tempCardName = Players[currentPlayer].getCurrentCard();
        Players[currentPlayer].setCardValue(Players[targetPlayer].getCardValue());
        Players[currentPlayer].setCurrentCard(Players[targetPlayer].getCurrentCard());
        Players[targetPlayer].setCardValue(tempCardNumber);
        Players[targetPlayer].setCurrentCard(tempCardName);
        System.out.println(Players[currentPlayer].getName()+ " has the card "+Players[currentPlayer].getCurrentCard());
    }

    /**
     *  The Countess has no effect when played but must be played when a player has either a Prince or the King Card
     */
    public static void playCountess(){
        System.out.println(Players[currentPlayer].getName()+" Played the Countess!");
    }

    /**
     * The Princess if played or discarded causes the player who played her to be defeated
     */
    public static void playPrincess() {
        System.out.println(Players[currentPlayer].getName() + " Played the Princess and is defeated");
        Players[currentPlayer].setPlaying(false);
    }
}
