package digital_entropy;

public class Player implements PlayerStatus, PlayerTurn {
    private int  winNumber, discardTracker = 0;
    private Card inHand;
    private Card drawnCard;
    public boolean isPlaying=false;
    private boolean isTargetable=false;
    private boolean isWinner=false;
    private Card [] Discard = new Card [10];
    protected int playerType;
    protected String name;



    public Player(){
        this.name = "NULL" ;
        this.winNumber = 0;
    }

    public Player(Card [] Discard, boolean isTargetable, boolean isPlaying ){
        this.Discard = Discard;
        this.isTargetable = isTargetable;
        this.isPlaying = isPlaying;
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


    public void tickWinNumber(){
        this.winNumber++;
        System.out.println("Round Over "+ this.name+" wins a point!");
        if (this.winNumber ==3){
            this.isWinner = true;
            System.out.println(this.name+" has won three points and wins the game!!!");
        }
    }

    public void setDrawnCard(Card drawnCard) {
        this.drawnCard = drawnCard;
    }

    public void setPlayerType(int playerType) {
        this.playerType = playerType;
    }

    public void setInHand(Card inHand){
        this.inHand = inHand;
    }

    public Card getDrawnCard() {
        return drawnCard;
    }

    public Card getInHand() {
        return inHand;
    }

    public void setDiscard(Card discarded) {
        Discard [discardTracker] = discarded;
        discardTracker++;
    }

    public boolean isTargetable(){
        return isTargetable;
    }

    public int getPlayerType() {
        return playerType;
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


    public int getWinNumber() {
        return winNumber;
    }

    public Player getPlayerStatus(){
        Player Status = new Player(this.Discard, this.isTargetable, this.isPlaying);
        return Status;
    }

    public  int getTurn(){
        return -1;
    }

    public int getTarget(){
        return -1;
    }

    public int getGuess(){
        return -1;
    }
}
