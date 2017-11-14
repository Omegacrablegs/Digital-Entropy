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
    private static boolean isRunning = true;

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
        int playerTurn = 0;
        int cardSelection = 0;
        drawStartingHand();
        // Game runs until one player remains or the deck runs out of cards
        while   (((Player0.isPlaying()&&(Player1.isPlaying()||Player2.isPlaying()||Player3.isPlaying()))
                ||(Player1.isPlaying()&&(Player0.isPlaying()||Player2.isPlaying()||Player3.isPlaying()))
                ||(Player2.isPlaying()&&(Player0.isPlaying()||Player1.isPlaying()||Player3.isPlaying()))
                ||(Player3.isPlaying()&&(Player0.isPlaying()||Player1.isPlaying()||Player2.isPlaying())))&& roundNumber <15 ){

            // Player who's turn it is goes

        switch (playerTurn){
            case 0:
                if(Player0.isPlaying()){
                    Player0.setSecondCardValue(Deck [roundNumber].getValue());
                    Player0.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player0.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player0.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player0.getCardValue(),0);
                        Player0.setCardValue(Player0.getSecondCardValue());
                        Player0.setCurrentCard(Player0.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player0.getSecondCardValue(),0);
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    playerTurn++;
                    roundNumber++;
                    break;
                }else{
                    playerTurn++;
                    break;
                }
            case 1:
                if(Player1.isPlaying()){
                    Player1.setSecondCardValue(Deck [roundNumber].getValue());
                    Player1.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player1.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player1.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player1.getCardValue(),1);
                        Player1.setCardValue(Player1.getSecondCardValue());
                        Player1.setCurrentCard(Player1.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player1.getSecondCardValue(),1);
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    playerTurn++;
                    roundNumber++;
                    break;
                }else{
                    playerTurn++;
                    break;
                }
            case 2:
                if(Player2.isPlaying()){
                    Player2.setSecondCardValue(Deck [roundNumber].getValue());
                    Player2.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player2.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player2.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player2.getCardValue(),2);
                        Player2.setCardValue(Player2.getSecondCardValue());
                        Player2.setCurrentCard(Player2.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player2.getSecondCardValue(),2);
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    playerTurn++;
                    roundNumber++;
                    break;
                }else{
                    playerTurn++;
                    break;
                }
            case 3:
                if(Player3.isPlaying()){
                    Player3.setSecondCardValue(Deck [roundNumber].getValue());
                    Player3.setSecondCard(Deck [roundNumber].getName());
                    System.out.println(Player3.getName()+" drew the "+ Deck[roundNumber].getName()+" Card");
                    System.out.println("Enter 1 to play the "+Player3.getCurrentCard()+" card or 2 to play the "+Deck[roundNumber].getName()+" card.");
                    cardSelection = scan.nextInt();
                    if(cardSelection == 1){
                        playedCard(Player3.getCardValue(),3);
                        Player3.setCardValue(Player3.getSecondCardValue());
                        Player3.setCurrentCard(Player3.getSecondCard());
                    } else if (cardSelection == 2){
                        playedCard(Player3.getSecondCardValue(),3);
                    }else{
                        System.out.println("Invalid Selection, effect not applied");
                    }
                    roundNumber++;
                    playerTurn=0;
                    break;
                }else{
                    playerTurn=0;
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
            return 0;
        }else if(Player1.isPlaying()){
            resetPlayers();
            return 1;
        }else if(Player2.isPlaying()){
            resetPlayers();
            return 2;
        }else{
            resetPlayers();
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
     * @param player the player who is playing the card
     */
    public static void playedCard(int card, int player){
        Scanner scan = new Scanner(System.in);
        switch(card){
            case 1 :
                System.out.println("Who do you want to target? (0-3");
                int target = scan.nextInt();
                System.out.println("What card do you think they have? (0-9)");
                int guess = scan.nextInt();
                playGuard(target,guess,player);
                break;
            case 2:
                System.out.println("Which Players card do you wish to view? (0-3");
                int priest = scan.nextInt();
                playPriest(priest,player);
                break;
            case 3:
                System.out.println("Which player do you wish to duel? (0-3)");
                int baron = scan.nextInt();
                playBaron(baron,player);
                break;
            case 4:
                playHandmaiden(player);
                break;
            case 5:
                System.out.println("Which player do you wish to target (0-3)");
                int prince = scan.nextInt();
                playPrince(prince,player);
                break;
            case 6:
                System.out.println("Which player do you wish to trade hands with? (0-3)");
                int king = scan.nextInt();
                playKing(king, player);
                break;
            case 7:
                playCountess(player);
                break;
            case 8:
                playPrincess(player);
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    // Methods for playing each card,

    /**
     * Guard picks a player and checks is guess == targets card value
     * @param target player who is being targeted by the Guard card
     * @param guess The card type the player believes target has
     * @param player The player who has played the card
     */

    public static void playGuard(int target, int guess, int player){
        switch (player) {
            case 0:
                switch (target) {
                    case 1:
                        if(Player1.getCardValue()==guess){
                            System.out.println(Player1.getName()+" has been eliminated");
                            Player1.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player0.getName());
                        }
                        break;
                    case 2:
                        if(Player2.getCardValue()==guess){
                            System.out.println(Player2.getName()+" has been eliminated");
                            Player2.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player0.getName());
                        }
                        break;
                    case 3:
                        if(Player3.getCardValue()==guess){
                            System.out.println(Player3.getName()+" has been eliminated");
                            Player3.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player0.getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection");
                        break;
                }
                break;
            case 1:
                switch (target) {
                    case 0:
                        if(Player0.getCardValue()==guess){
                            System.out.println(Player0.getName()+" has been eliminated");
                            Player0.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player1.getName());
                        }
                        break;
                    case 2:
                        if(Player2.getCardValue()==guess){
                            System.out.println(Player2.getName()+" has been eliminated");
                            Player2.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player1.getName());
                        }
                        break;
                    case 3:
                        if(Player3.getCardValue()==guess){
                            System.out.println(Player3.getName()+" has been eliminated");
                            Player3.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player1.getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection");
                        break;
                }
                break;
            case 2:
                switch (target) {
                    case 0:
                        if(Player0.getCardValue()==guess){
                            System.out.println(Player0.getName()+" has been eliminated");
                            Player0.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player2.getName());
                        }
                        break;
                    case 1:
                        if(Player1.getCardValue()==guess){
                            System.out.println(Player1.getName()+" has been eliminated");
                            Player1.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player2.getName());
                        }
                        break;
                    case 3:
                        if(Player3.getCardValue()==guess){
                            System.out.println(Player3.getName()+" has been eliminated");
                            Player3.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player2.getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection");
                        break;
                }
                break;
            case 3:
                switch (target) {
                    case 0:
                        if(Player0.getCardValue()==guess){
                            System.out.println(Player0.getName()+" has been eliminated");
                            Player0.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player3.getName());
                        }
                        break;
                    case 1:
                        if(Player1.getCardValue()==guess){
                            System.out.println(Player1.getName()+" has been eliminated");
                            Player1.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player3.getName());
                        }
                        break;
                    case 2:
                        if(Player2.getCardValue()==guess){
                            System.out.println(Player2.getName()+" has been eliminated");
                            Player2.setPlaying(false);
                        }else{
                            System.out.println("Wrong guess "+Player3.getName());
                        }
                        break;

                    default:
                        System.out.println("Invalid Selection");
                        break;
                }
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    /**
     * Priest allows a player to see another players card
     * @param target player who is being targeted by the Priest card
     * @param player The player who has played the card
     */
    public static void playPriest(int target, int player){
        switch (player) {
            case 0:
            switch (target){
                case 1:
                    System.out.println(Player1.getName()+" is Holding a "+Player1.getCurrentCard());
                    break;
                case 2:
                    System.out.println(Player2.getName()+" is Holding a "+Player2.getCurrentCard());
                    break;
                case 3:
                    System.out.println(Player3.getName()+" is Holding a "+Player3.getCurrentCard());
                    break;
                default:
                    System.out.println("Invalid Selection, effect not applied");
                    break;
            }
                break;
            case 1:
                switch (target){
                    case 0:
                        System.out.println(Player0.getName()+" is Holding a "+Player0.getCurrentCard());
                        break;
                    case 2:
                        System.out.println(Player2.getName()+" is Holding a "+Player2.getCurrentCard());
                        break;
                    case 3:
                        System.out.println(Player3.getName()+" is Holding a "+Player3.getCurrentCard());
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 2:
                switch (target){
                    case 0:
                        System.out.println(Player0.getName()+" is Holding a "+Player0.getCurrentCard());
                        break;
                    case 1:
                        System.out.println(Player1.getName()+" is Holding a "+Player1.getCurrentCard());
                        break;
                    case 3:
                        System.out.println(Player3.getName()+" is Holding a "+Player3.getCurrentCard());
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 3:
                switch (target){
                    case 0:
                        System.out.println(Player0.getName()+" is Holding a "+Player0.getCurrentCard());
                        break;
                    case 1:
                        System.out.println(Player1.getName()+" is Holding a "+Player1.getCurrentCard());
                        break;
                    case 2:
                        System.out.println(Player2.getName()+" is Holding a "+Player2.getCurrentCard());
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    /**
     * Baron compares the value of the players card and the targets and the loser is eliminated
     * @param target player who is being targeted by the Priest card
     * @param player The player who has played the card
     */
    public static void playBaron(int target, int player){
        switch (player) {
            case 0:
                switch (target){
                    case 1:
                        if(Player0.getCardValue()>Player1.getCardValue()){
                            Player1.setPlaying(false);
                            System.out.println(Player0.getName()+" has defeated "+Player1.getName());
                        }else if(Player0.getCardValue()==Player1.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player0.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player0.getName());
                        }
                        break;

                    case 2:
                        if(Player0.getCardValue()>Player2.getCardValue()){
                            Player2.setPlaying(false);
                            System.out.println(Player0.getName()+" has defeated "+Player2.getName());
                        }else if(Player0.getCardValue()==Player2.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player0.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player0.getName());
                        }
                        break;
                    case 3:
                        if(Player0.getCardValue()>Player3.getCardValue()){
                            Player3.setPlaying(false);
                            System.out.println(Player0.getName()+" has defeated "+Player3.getName());
                        }else if(Player0.getCardValue()==Player3.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player0.setPlaying(false);
                            System.out.println(Player3.getName()+" has defeated "+Player0.getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 1:
                switch (target){
                    case 0:
                        if(Player1.getCardValue()>Player0.getCardValue()){
                            Player0.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player0.getName());
                        }else if(Player1.getCardValue()==Player1.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player1.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player0.getName());
                        }
                        break;
                    case 2:
                        if(Player1.getCardValue()>Player2.getCardValue()){
                            Player2.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player2.getName());
                        }else if(Player1.getCardValue()==Player1.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player1.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player2.getName());
                        }
                        break;
                    case 3:
                        if(Player1.getCardValue()>Player3.getCardValue()){
                            Player3.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player3.getName());
                        }else if(Player1.getCardValue()==Player1.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player1.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player3.getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 2:
                switch (target){
                    case 0:
                        if(Player2.getCardValue()>Player0.getCardValue()){
                            Player0.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player0.getName());
                        }else if(Player2.getCardValue()==Player0.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player2.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player0.getName());
                        }
                        break;
                    case 1:
                        if(Player2.getCardValue()>Player1.getCardValue()){
                            Player1.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player1.getName());
                        }else if(Player2.getCardValue()==Player1.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player2.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player1.getName());
                        }
                        break;
                    case 3:
                        if(Player2.getCardValue()>Player3.getCardValue()){
                            Player3.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player3.getName());
                        }else if (Player2.getCardValue()==Player3.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player2.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player3.getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 3:
                switch (target){
                    case 0:
                        if(Player3.getCardValue()>Player0.getCardValue()){
                            Player0.setPlaying(false);
                            System.out.println(Player3.getName()+" has defeated "+Player0.getName());
                        }else if (Player3.getCardValue()==Player0.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player3.setPlaying(false);
                            System.out.println(Player0.getName()+" has defeated "+Player3.getName());
                        }
                        break;
                    case 1:
                        if(Player3.getCardValue()>Player1.getCardValue()){
                            Player1.setPlaying(false);
                            System.out.println(Player3.getName()+" has defeated "+Player1.getName());
                        }else if (Player3.getCardValue()==Player1.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player3.setPlaying(false);
                            System.out.println(Player1.getName()+" has defeated "+Player3.getName());
                        }
                        break;
                    case 2:
                        if(Player3.getCardValue()>Player2.getCardValue()){
                            Player2.setPlaying(false);
                            System.out.println(Player3.getName()+" has defeated "+Player2.getName());
                        }else if (Player3.getCardValue()==Player2.getCardValue()){
                            System.out.println("It's a draw!");
                        }else{
                            Player3.setPlaying(false);
                            System.out.println(Player2.getName()+" has defeated "+Player3.getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    /**
     *
     * @param player The player who has played the card
     */
    public static void playHandmaiden(int player){
        switch (player) {
            case 0:
                System.out.println(Player0.getName()+" Played the Handmaiden");
                Player0.setTargetable(false);
                break;
            case 1:
                System.out.println(Player1.getName()+" Played the Handmaiden");
                Player1.setTargetable(false);
                break;
            case 2:
                System.out.println(Player2.getName()+" Played the Handmaiden");
                Player2.setTargetable(false);
                break;
            case 3:
                System.out.println(Player3.getName()+" Played the Handmaiden");
                Player3.setTargetable(false);
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    /**
     * The Price Card causes the taget to discard his current card and redrawn a new card from the deck
     * If the pricess is discarded the player is defeated
     * @param target player who is being targeted by the Prince card
     * @param player The player who has played the card
     */
    public static void playPrince(int target, int player){
        switch (player) {
            case 0:
                switch (target){
                    case 1:
                        if(Player1.getCardValue()==8){
                            System.out.println(Player1.getName()+" discarded the Princess and is Eliminated!");

                            Player1.setCardValue(0);
                            Player1.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player1.getName()+" discarded the "+Player1.getCurrentCard()+" card!");
                            roundNumber++;
                            Player1.setCardValue(Deck[roundNumber].getValue());
                            Player1.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 2:
                        if(Player2.getCardValue()==8){
                            System.out.println(Player2.getName()+" discarded the Princess and is Eliminated!");
                            Player2.setCardValue(0);
                            Player2.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player2.getName()+" discarded the "+Player2.getCurrentCard()+" card!");
                            roundNumber++;
                            Player2.setCardValue(Deck[roundNumber].getValue());
                            Player2.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 3:
                        if(Player3.getCardValue()==8){
                            System.out.println(Player3.getName()+" discarded the Princess and is Eliminated!");
                            Player3.setCardValue(0);
                            Player3.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player3.getName()+" discarded the "+Player3.getCurrentCard()+" card!");
                            roundNumber++;
                            Player3.setCardValue(Deck[roundNumber].getValue());
                            Player3.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 1:
                switch (target){
                    case 0:
                        if(Player0.getCardValue()==8){
                            System.out.println(Player0.getName()+" discarded the Princess and is Eliminated!");
                            Player0.setCardValue(0);
                            Player0.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player0.getName()+" discarded the "+Player0.getCurrentCard()+" card!");
                            roundNumber++;
                            Player0.setCardValue(Deck[roundNumber].getValue());
                            Player0.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 2:
                        if(Player2.getCardValue()==8){
                            System.out.println(Player2.getName()+" discarded the Princess and is Eliminated!");
                            Player2.setCardValue(0);
                            Player2.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player2.getName()+" discarded the "+Player2.getCurrentCard()+" card!");
                            roundNumber++;
                            Player2.setCardValue(Deck[roundNumber].getValue());
                            Player2.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 3:
                        if(Player3.getCardValue()==8){
                            System.out.println(Player3.getName()+" discarded the Princess and is Eliminated!");
                            Player3.setCardValue(0);
                            Player3.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player3.getName()+" discarded the "+Player3.getCurrentCard()+" card!");
                            roundNumber++;
                            Player3.setCardValue(Deck[roundNumber].getValue());
                            Player3.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 2:
                switch (target){
                    case 0:
                        if(Player0.getCardValue()==8){
                            System.out.println(Player0.getName()+" discarded the Princess and is Eliminated!");
                            Player0.setCardValue(0);
                            Player0.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player0.getName()+" discarded the "+Player0.getCurrentCard()+" card!");
                            roundNumber++;
                            Player0.setCardValue(Deck[roundNumber].getValue());
                            Player0.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 1:
                        if(Player1.getCardValue()==8){
                            System.out.println(Player1.getName()+" discarded the Princess and is Eliminated!");
                            Player1.setCardValue(0);
                            Player1.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player1.getName()+" discarded the "+Player1.getCurrentCard()+" card!");
                            roundNumber++;
                            Player1.setCardValue(Deck[roundNumber].getValue());
                            Player1.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 3:
                        if(Player3.getCardValue()==8){
                            System.out.println(Player3.getName()+" discarded the Princess and is Eliminated!");
                            Player3.setCardValue(0);
                            Player3.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player3.getName()+" discarded the "+Player3.getCurrentCard()+" card!");
                            roundNumber++;
                            Player3.setCardValue(Deck[roundNumber].getValue());
                            Player3.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 3:
                switch (target){
                    case 0:
                        if(Player0.getCardValue()==8){
                            System.out.println(Player0.getName()+" discarded the Princess and is Eliminated!");
                            Player0.setCardValue(0);
                            Player0.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player0.getName()+" discarded the "+Player0.getCurrentCard()+" card!");
                            roundNumber++;
                            Player0.setCardValue(Deck[roundNumber].getValue());
                            Player0.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 1:
                        if(Player1.getCardValue()==8){
                            System.out.println(Player1.getName()+" discarded the Princess and is Eliminated!");
                            Player1.setCardValue(0);
                            Player1.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player1.getName()+" discarded the "+Player1.getCurrentCard()+" card!");
                            roundNumber++;
                            Player1.setCardValue(Deck[roundNumber].getValue());
                            Player1.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    case 2:
                        if(Player2.getCardValue()==8){
                            System.out.println(Player2.getName()+" discarded the Princess and is Eliminated!");
                            Player2.setCardValue(0);
                            Player2.setCurrentCard("NULL");
                        }else{
                            System.out.println(Player2.getName()+" discarded the "+Player2.getCurrentCard()+" card!");
                            roundNumber++;
                            Player2.setCardValue(Deck[roundNumber].getValue());
                            Player2.setCurrentCard(Deck[roundNumber].getName());
                        }
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    /**
     * The King Card causes the target to trade their hand with the player who played the king
     * @param target player who is being targeted by the King card
     * @param player The player who has played the card
     */
    public static void playKing(int target, int player){
        int tempCardNumber;
        String tempCardName;
        switch (player) {
            case 0:
                switch (target){
                    case 1:
                        System.out.println(Player0.getName()+" has played the king against "+ Player1.getName());
                        tempCardNumber = Player0.getCardValue();
                        tempCardName = Player0.getCurrentCard();
                        Player0.setCardValue(Player1.getCardValue());
                        Player0.setCurrentCard(Player1.getCurrentCard());
                        Player1.setCardValue(tempCardNumber);
                        Player1.setCurrentCard(tempCardName);
                        System.out.println(Player0.getName()+ " has the card "+Player0.getCurrentCard());
                        break;
                    case 2:
                        System.out.println(Player0.getName()+" has played the king against "+ Player2.getName());
                        tempCardNumber = Player0.getCardValue();
                        tempCardName = Player0.getCurrentCard();
                        Player0.setCardValue(Player2.getCardValue());
                        Player0.setCurrentCard(Player2.getCurrentCard());
                        Player2.setCardValue(tempCardNumber);
                        Player2.setCurrentCard(tempCardName);
                        System.out.println(Player0.getName()+ " has the card "+Player0.getCurrentCard());
                        break;
                    case 3:
                        System.out.println(Player0.getName()+" has played the king against "+ Player3.getName());
                        tempCardNumber = Player0.getCardValue();
                        tempCardName = Player0.getCurrentCard();
                        Player0.setCardValue(Player3.getCardValue());
                        Player0.setCurrentCard(Player3.getCurrentCard());
                        Player3.setCardValue(tempCardNumber);
                        Player3.setCurrentCard(tempCardName);
                        System.out.println(Player0.getName()+ " has the card "+Player0.getCurrentCard());
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 1:
                switch (target){
                    case 0:
                        System.out.println(Player1.getName()+" has played the king against "+ Player0.getName());
                        tempCardNumber = Player1.getCardValue();
                        tempCardName = Player1.getCurrentCard();
                        Player1.setCardValue(Player0.getCardValue());
                        Player1.setCurrentCard(Player0.getCurrentCard());
                        Player0.setCardValue(tempCardNumber);
                        Player0.setCurrentCard(tempCardName);
                        System.out.println(Player1.getName()+ " has the card "+Player1.getCurrentCard());
                        break;
                    case 2:
                        System.out.println(Player1.getName()+" has played the king against "+ Player2.getName());
                        tempCardNumber = Player1.getCardValue();
                        tempCardName = Player1.getCurrentCard();
                        Player1.setCardValue(Player2.getCardValue());
                        Player1.setCurrentCard(Player2.getCurrentCard());
                        Player2.setCardValue(tempCardNumber);
                        Player2.setCurrentCard(tempCardName);
                        System.out.println(Player1.getName()+ " has the card "+Player1.getCurrentCard());
                        break;
                    case 3:
                        System.out.println(Player1.getName()+" has played the king against "+ Player3.getName());
                        tempCardNumber = Player1.getCardValue();
                        tempCardName = Player1.getCurrentCard();
                        Player1.setCardValue(Player3.getCardValue());
                        Player1.setCurrentCard(Player3.getCurrentCard());
                        Player3.setCardValue(tempCardNumber);
                        Player3.setCurrentCard(tempCardName);
                        System.out.println(Player1.getName()+ " has the card "+Player1.getCurrentCard());
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 2:
                switch (target){
                    case 0:
                        System.out.println(Player2.getName()+" has played the king against "+ Player0.getName());
                        tempCardNumber = Player2.getCardValue();
                        tempCardName = Player2.getCurrentCard();
                        Player2.setCardValue(Player0.getCardValue());
                        Player2.setCurrentCard(Player0.getCurrentCard());
                        Player0.setCardValue(tempCardNumber);
                        Player0.setCurrentCard(tempCardName);
                        System.out.println(Player2.getName()+ " has the card "+Player2.getCurrentCard());
                        break;
                    case 1:
                        System.out.println(Player2.getName()+" has played the king against "+ Player1.getName());
                        tempCardNumber = Player2.getCardValue();
                        tempCardName = Player2.getCurrentCard();
                        Player2.setCardValue(Player1.getCardValue());
                        Player2.setCurrentCard(Player1.getCurrentCard());
                        Player1.setCardValue(tempCardNumber);
                        Player1.setCurrentCard(tempCardName);
                        System.out.println(Player2.getName()+ " has the card "+Player2.getCurrentCard());
                        break;
                    case 3:
                        System.out.println(Player2.getName()+" has played the king against "+ Player3.getName());
                        tempCardNumber = Player2.getCardValue();
                        tempCardName = Player2.getCurrentCard();
                        Player2.setCardValue(Player3.getCardValue());
                        Player2.setCurrentCard(Player3.getCurrentCard());
                        Player3.setCardValue(tempCardNumber);
                        Player3.setCurrentCard(tempCardName);
                        System.out.println(Player2.getName()+ " has the card "+Player2.getCurrentCard());
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            case 3:
                switch (target){
                    case 0:
                        System.out.println(Player3.getName()+" has played the king against "+ Player0.getName());
                        tempCardNumber = Player3.getCardValue();
                        tempCardName = Player3.getCurrentCard();
                        Player3.setCardValue(Player0.getCardValue());
                        Player3.setCurrentCard(Player0.getCurrentCard());
                        Player0.setCardValue(tempCardNumber);
                        Player0.setCurrentCard(tempCardName);
                        System.out.println(Player3.getName()+ " has the card "+Player3.getCurrentCard());
                        break;
                    case 1:
                        System.out.println(Player3.getName()+" has played the king against "+ Player1.getName());
                        tempCardNumber = Player3.getCardValue();
                        tempCardName = Player3.getCurrentCard();
                        Player3.setCardValue(Player1.getCardValue());
                        Player3.setCurrentCard(Player1.getCurrentCard());
                        Player1.setCardValue(tempCardNumber);
                        Player1.setCurrentCard(tempCardName);
                        System.out.println(Player3.getName()+ " has the card "+Player3.getCurrentCard());
                        break;
                    case 2:
                        System.out.println(Player3.getName()+" has played the king against "+ Player2.getName());
                        tempCardNumber = Player3.getCardValue();
                        tempCardName = Player3.getCurrentCard();
                        Player3.setCardValue(Player2.getCardValue());
                        Player3.setCurrentCard(Player2.getCurrentCard());
                        Player2.setCardValue(tempCardNumber);
                        Player2.setCurrentCard(tempCardName);
                        System.out.println(Player3.getName()+ " has the card "+Player3.getCurrentCard());
                        break;
                    default:
                        System.out.println("Invalid Selection, effect not applied");
                        break;
                }
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    /**
     *  The Countess has no effect when played but must be played when a player has either a Prince or the King Card
     * @param player The player who has played the card
     */
    public static void playCountess(int player){
        switch (player) {
            case 0:
                System.out.println(Player0.getName()+" Played the Countess!");
                break;
            case 1:
                System.out.println(Player1.getName()+" Played the Countess!");
                break;
            case 2:
                System.out.println(Player2.getName()+" Played the Countess!");
                break;
            case 3:
                System.out.println(Player3.getName()+" Played the Countess!");
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

    /**
     * The Princess if played or discarded causes the player who played her to be defeated
     * @param player The player who has played the card
     */
    public static void playPrincess(int player){
        switch (player) {
            case 0:
            System.out.println(Player0.getName()+" Played the Princess and is defeated");
            Player0.setPlaying(false);
                break;
            case 1:
                System.out.println(Player1.getName()+" Played the Princess and is defeated");
                Player1.setPlaying(false);
                break;
            case 2:
                System.out.println(Player2.getName()+" Played the Princess and is defeated");
                Player2.setPlaying(false);
                break;
            case 3:
                System.out.println(Player3.getName()+" Played the Princess and is defeated");
                Player3.setPlaying(false);
                break;
            default:
                System.out.println("Invalid Selection, effect not applied");
                break;
        }
    }

}
