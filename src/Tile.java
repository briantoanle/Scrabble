import java.util.Random;

public class Tile {
    private char value;
    public Tile(){
        this.value = ' ';
    }

    public Tile (char randChar){
        value = randChar;
    }

    public void pickup(){
        Random random = new Random();
        value = (char)(random.nextInt(26) + 'A');
    }
    public char getValue(){
        return value;
    }

    public static void main(String[] args){
        /**
        Tile t1 = new Tile();
        t1.pickup();
        System.out.println("<"+t1.getValue()+">");
         */

    }

}
