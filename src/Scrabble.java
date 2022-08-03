

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.*;

public class Scrabble {

    private Tile[] tiles;

    public Scrabble(){
        this.tiles = new Tile[7];

        for (int i = 0; i < tiles.length; i++){
            Tile randomTiles = new Tile();
            randomTiles.pickup();
            tiles[i] = randomTiles;
        }

    }
    public Scrabble(Tile[] tile){
        this.tiles = tile;
    }
    public String getLetters(){
        String str = "";
        for(int i = 0; i < tiles.length; i++){
            str += tiles[i].getValue();
        }
        return str;
    }
/**
   private boolean checkString3(String WTC){
        String base = WTC;
        int finale = base.length();
        String remove = this.getLetters();
       for (int i=0; i <= base.length()-remove.length(); i++)
       {
           if (base.substring(i, i+remove.length()).equals(remove))
           {
               base = base.substring(0, i) + base.substring(i + remove.length() , base.length());
               i--;
           }
       }
       System.out.println(base);
       if(finale==base.length()+remove.length())
           return true;
       else
           return false;
   }
 */
    private boolean checkString2(String WTC){
        StringBuilder str = new StringBuilder(this.getLetters());
        StringBuilder str2 = new StringBuilder(WTC);
        String current = this.getLetters();
        String stringToCompare = WTC;

        int total = str2.length();
        int asciiVal = 0;
        int asciiVal2 = 0;
        for(int i = 0; i< str2.length();i++){
            char char1 = str2.charAt(i);
            int ascii = (int) char1;

            for(int j = 0; j < str.length();j++){
                char char2 = str.charAt(j);
                int ascii2 = (int) char2;

                if(ascii==ascii2){
                    --total;

                }
            }
        }
        if(total ==0)
            return true;
        else
            return false;

    }

    private boolean checkString(String WTC){

        String testString = this.getLetters();
        String compareString = WTC;
//COOOLYY
//YOLD
        int trueCounter = 0;
        int counter = 0;
        for(int i = 0; i < compareString.length(); i++){

            char character = compareString.charAt(i);

            for(int j = 0; j < testString.length(); j++){

                char character2 = testString.charAt(j);
                if(character == character2){
                    testString = testString.replaceFirst(character2+"","");
                    counter++;

                }
            }
        }
        if(counter == compareString.length()){
            //System.out.println(compareString);
            return true;
        }

        else{
            return false;
        }

    }

    private boolean checkStringNew(String WTC) {
        //wtc = happy
        //tiles = AYXWPZH
        String tiles =  this.getLetters();
        for (int i = 0; i < WTC.length(); i++){
            System.out.println("current tiles= " + tiles + " check against word: " + WTC.charAt(i));
            String letter = WTC.charAt(i) + "";
            if (tiles.contains(letter))
                tiles = tiles.replaceFirst(letter,"");
            else
                return false;
        }
        return true;
    }


    public ArrayList<String> getWords() throws FileNotFoundException {
        File file = new File("CollinsScrabbleWords2019.txt");
        ArrayList<String> str = new ArrayList<String>();
        Scanner inputFile = new Scanner (file);
        String l1 = this.getLetters();
        checkString(l1);
        while (inputFile.hasNext()) {
            String wordToCompare = inputFile.nextLine();
            //System.out.println("test2");


            if(wordToCompare.length() < 8){
                if(checkString4(wordToCompare)   == true){
                    //System.out.println("test");
                    str.add(wordToCompare);
                }
            }

            //System.out.println(wordToCompare);
        }

        //BURAS  is it a valid word that can be created from Scrabble: A B R U T Z S
/*
        char[] sArr = new char[7];




        for(int i = 0; i < tiles.length; i++){
            sArr[i]= l1.charAt(i);
        }
        Arrays.sort(sArr);

*/
        System.out.println(str);
        return str;
    }

    public boolean checkString4(String WTC){
        String letters1 = this.getLetters();
        for(int i = 0; i< WTC.length();i++){
            String tempStr = WTC.charAt(i) + "";
            if(letters1.contains(tempStr))
                letters1 = letters1.replaceFirst(tempStr,"");
            else return false;
        }
        return true;
    }

    public int[] getScores() throws FileNotFoundException {
        ArrayList<String> matchingWords = getWords();
        int total = matchingWords.size();
        int[] arr = new int[total];

        //System.out.println(total);

        List<Integer> arrList = new ArrayList<Integer>();
        //1 = A E I L N R O S U T
        //2 = D G
        //3 = B C M P
        //4 = F H K V W Y
        //5 = K
        //8 = J X
        //10 = Q Z

        //go through array
        for(int i = 0; i < total; i++){
            String tempStr = matchingWords.get(i);
            int valOfString = 0;
            //go through each string
            for(int j = 0; j < tempStr.length();j++){
                if(tempStr.charAt(j) == 'A' || tempStr.charAt(j) == 'E' ||tempStr.charAt(j) == 'I' ||tempStr.charAt(j) == 'L' ||tempStr.charAt(j) == 'N' ||
                        tempStr.charAt(j) == 'R' ||tempStr.charAt(j) == 'O' ||tempStr.charAt(j) == 'S' ||tempStr.charAt(j) == 'T' || tempStr.charAt(j) == 'U')
                    valOfString++;
                else if(tempStr.charAt(j) == 'D' ||tempStr.charAt(j) == 'G')
                    valOfString+=2;
                else if(tempStr.charAt(j) == 'B' ||tempStr.charAt(j) == 'C' ||tempStr.charAt(j) == 'M' ||tempStr.charAt(j) == 'P')
                    valOfString+=3;
                else if(tempStr.charAt(j) == 'F' ||tempStr.charAt(j) == 'H' ||tempStr.charAt(j) == 'K' ||tempStr.charAt(j) == 'W'||
                    tempStr.charAt(j) == 'V' ||tempStr.charAt(j) == 'Y')
                    valOfString+=4;
                else if(tempStr.charAt(j) == 'k')
                    valOfString+=5;
                else if(tempStr.charAt(j) == 'J' ||tempStr.charAt(j) == 'X')
                    valOfString+=8;
                else if(tempStr.charAt(j) == 'Q' ||tempStr.charAt(j) == 'Z')
                    valOfString+=10;
            }
            arr[i] = valOfString;
        }


        Arrays.sort(arr);
        return arr;
    }

    private static char[] removeChar(char[] arr, int index){

        // Create another array of size one less
        char[] anotherArray = new char[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    //used a source online for this answer
    public boolean equals(Scrabble s) {
        String l1 = this.getLetters();
        String l2 = s.getLetters();

        int asciiVal = 0;
        int asciiVal2 = 0;

        if(l1.length() != l2.length())
            return false;

        //source https://stackoverflow.com/a/34659303/14640220
        //compare ascii value of the two letters
        else{
            for(int i = 0; i < l1.length() ; i++){
                char character = l1.charAt(i);
                int ascii = (int) character;
                asciiVal += ascii;

                char character2 = l2.charAt(i);
                int ascii2 = (int) character2;
                asciiVal2 += ascii2;

            }
            if(asciiVal == asciiVal2)
                return true;
            else
                return false;
        }

    }

    public Boolean equals3(Scrabble s){

        //Boolean value = false;
        char[] sArr = new char[7];
        char[] sArr2 = new char[7];
        String l1 = this.getLetters();
        String l2 = s.getLetters();

        for(int i = 0; i < tiles.length; i++){
            sArr[i]= l1.charAt(i);
            sArr2[i]= l2.charAt(i);
        }
        Arrays.sort(sArr);
        Arrays.sort(sArr2);

        return Arrays.equals(sArr,sArr2);

    }

    public static void main(String[] args) throws FileNotFoundException {

        Scrabble round1 = new Scrabble();
        //System.out.println(round1.getLetters());

        Tile[] gameThreeTiles = new Tile[7];
        Tile[] gameFourTiles = new Tile[7];

        //System.out.println(game3.getLetters()); // ZYXWVUT --> sort: TUVWXYZ
        //System.out.println(game4.getLetters()); // YZTWUVX --> sort: TUVWXYZ
        gameThreeTiles[0] = new Tile('A');
        gameThreeTiles[1] = new Tile('Y');
        gameThreeTiles[2] = new Tile('X');
        gameThreeTiles[3] = new Tile('W');
        gameThreeTiles[4] = new Tile('P');
        gameThreeTiles[5] = new Tile('P');
        gameThreeTiles[6] = new Tile('H');

        gameFourTiles[0] = new Tile('Y');
        gameFourTiles[1] = new Tile('Z');
        gameFourTiles[2] = new Tile('T');
        gameFourTiles[3] = new Tile('W');
        gameFourTiles[4] = new Tile('U');
        gameFourTiles[5] = new Tile('V');
        gameFourTiles[6] = new Tile('X');


        Scrabble game3, game4;
        game3 = new Scrabble(gameThreeTiles);
        game4 = new Scrabble(gameFourTiles);


        //System.out.println(game3.getLetters()); // ZYXWVUT --> sort: TUVWXYZ
        //System.out.println(game4.getLetters()); // YZTWUVX --> sort: TUVWXYZ
        //System.out.println(game3.equals(game4));

        // A B N O T Z O   --> AT, NOT, BAN, ZOO , TO, ...


        //A A B B
        //A A
        //BURAS  is it a valid word that can be created from Scrabble: A B R U T Z S
        //System.out.println(game3.equals(game4));
        //System.out.println(game3.getWords());
        //ArrayList<String> str = game3.getWords();
        //System.out.println(str);

        System.out.println(game3.checkStringNew("HAPPY"));

    }
}
