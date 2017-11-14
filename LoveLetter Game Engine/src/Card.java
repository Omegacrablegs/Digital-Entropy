public class Card {
    private int height, width, value, count;
    private String name;

    public Card (String name,int height,int width,int value){
        this.height = height;
        this.width = width;
        this.value = value;
        this.name = name;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public int getValue(){
        return value;
    }

    public int getCount() {
        return count;
    }

    public String getName(){
        return name;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
