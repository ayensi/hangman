import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Hangman {

    public static String[] words = {"ant", "baboon", "badger", "bat", "bear", "beaver", "camel",
    "cat", "clam", "cobra", "cougar", "coyote", "crow", "deer",
    "dog", "donkey", "duck", "eagle", "ferret", "fox", "frog", "goat",
    "goose", "hawk", "lion", "lizard", "llama", "mole", "monkey", "moose",
    "mouse", "mule", "newt", "otter", "owl", "panda", "parrot", "pigeon", 
    "python", "rabbit", "ram", "rat", "raven","rhino", "salmon", "seal",
    "shark", "sheep", "skunk", "sloth", "snake", "spider", "stork", "swan",
    "tiger", "toad", "trout", "turkey", "turtle", "weasel", "whale", "wolf",
    "wombat", "zebra"};

    public static String[] gallows = {"+---+\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "    |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    "+---+\n" +
    "|   |\n" +
    "O   |\n" +
    "|   |\n" +
    "    |\n" +
    "    |\n" +
    "=========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|   |\n" +
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + //if you were wondering, the only way to print '\' is with a trailing escape character, which also happens to be '\'
    "     |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" +
    "/    |\n" +
    "     |\n" +
    " =========\n",

    " +---+\n" +
    " |   |\n" +
    " O   |\n" +
    "/|\\  |\n" + 
    "/ \\  |\n" +
    "     |\n" +
    " =========\n"};

    public static void main(String[] args) {
        HashMap<Integer,Character> entries = new HashMap<Integer,Character>();
        ArrayList<Character> misses = new ArrayList<Character>();
        int counter = 0;
        char input;
        boolean isFalse = false;
        String word = randomWord(words);
        while(true){
            input = getGuess(counter, gallows, word, misses,entries);
            isFalse = checkForInput(word, input, entries, misses,isFalse);
            gameIsOver(counter, entries, word,gallows);
            if(isFalse){
                counter++;
            }
        }
    }

    public static String randomWord(String[] words)
    {
        Random random = new Random();
        int randomNumber = random.nextInt(64);
        String word = words[randomNumber];
        return word;
    }

    public static char getGuess(int turnNumber, String[] gallows,String word,ArrayList<Character> misses,HashMap<Integer,Character> entries)
    {
        System.out.println("\n"+ gallows[turnNumber]);
        System.out.print("\n Word: \t");
        for (int i = 0; i < word.length(); i++) {
            if(entries.get(i)==null){
                System.out.print("_");
            }
            else{
                System.out.print(entries.get(i));
            }
        }
        System.out.print("\n\n Misses: ");

        for (int i = 0; i < misses.size(); i++) {
            System.out.print(misses.get(i));
            }
            System.out.print("\n\n Guess: ");
            Scanner scan = new Scanner(System.in);
            char input = scan.next().charAt(0);
            scan.nextLine();
            turnNumber++;
            return input;

    }

    public static boolean checkForInput(String word,char enter,HashMap<Integer,Character> entries,ArrayList<Character> misses,boolean isWrong){
        int counter=0;
        char[] wordArray = word.toCharArray();
        for (int i = 0; i < wordArray.length; i++) {
            if(wordArray[i] == enter){
                entries.put(i, enter);
                counter++;
            }
        }
        if(counter==0){
            misses.add(enter);
            return true;
        }
        else {
            return false;
        }
    }

    public static void gameIsOver(int turnNumber,HashMap<Integer,Character> entries,String word,String[] gallows)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < entries.size(); i++) {
            builder.append(entries.get(i));
        }
        String finalWord = builder.toString();

        if(finalWord.equals(word)){
            System.out.println("\n You won!");
            System.exit(0);
        }
        if(turnNumber>=5){
            System.out.println("\n You lose :(");
            System.out.println("\n"+ gallows[turnNumber+1]);
            System.exit(0);
        }
    }
    

}





