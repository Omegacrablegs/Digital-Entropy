public class Player {
    private String name, currentCard, secondCard;
    private int playerType, playerNumber, winNumber, cardValue, secondCardValue;
    private boolean isPlaying=false, isTargetable=true, isWinner=false;

    public Player(String name, int playerType){
        this.name = name;
        this.playerType = playerType;
        this.playerNumber = 0;
        this.currentCard = "NULL";
        this.winNumber = 0;
    }

    public void setCurrentCard(String currentCard) {
        this.currentCard = currentCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public void setTargetable(boolean targetable) {
        isTargetable = targetable;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public void tickWinNumber(){
        this.winNumber++;
        System.out.println("Round Over "+ this.name+" wins a point!");
        if (this.winNumber ==3){
            this.isWinner = true;
            System.out.println(this.name+" has won three points and wins the game!!!");
            System.out.println("Thanks for playing!!");
        }
    }

    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }

    public void setSecondCard(String secondCard) {
        this.secondCard = secondCard;
    }

    public void setSecondCardValue(int secondCardValue){
        this.secondCardValue = secondCardValue;
    }

    public int getCardValue() {
        return cardValue;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public String getCurrentCard() {
        return currentCard;
    }

    public String getSecondCard() {
        return secondCard;
    }

    public int getSecondCardValue(){
        return secondCardValue;
    }

    public boolean isTargetable(){
        return isTargetable;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isWinner(){
        return isWinner;
    }

    public String getName() {
        return name;
    }

    public int getPlayerType() {
        return playerType;
    }

    public int getWinNumber() {
        return winNumber;
    }
}
