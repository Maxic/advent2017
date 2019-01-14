import java.nio.file.Files;
import java.nio.file.Paths;

public class day1 {

    public static void main(String[] args) {
        // Literally 2 lines in python
        String path = System.getProperty("user.dir");
        String content = new String();
        try {
            content = Files.readAllLines(Paths.get(path+"/src/input/input1.txt")).get(0);
        } catch (Exception e){
            System.out.println(e);
        }

        int answer1 = problem1(content);
        int answer2 = problem2(content);

        System.out.println("Answer to problem 1: " + answer1);
        System.out.println("Answer to problem 2: " + answer2);
    }

    private static int problem1(String input) {

        // Initialize vars
        int sum = 0;
        Character baseDigit;
        Character nextDigit;

        // Main loop: check entire string to determine duplicates and sum these
        for(int i=0; i < input.length(); i++){

            // String is circular, so take last character for case i == 0
            baseDigit = input.charAt(i);
            if(i == 0){
                nextDigit = input.charAt(input.length()-1);
            } else {
                nextDigit = input.charAt(i-1);
            }

            // Sum duplicates
            if(baseDigit == nextDigit){
                sum = sum + (baseDigit.charValue() - 48);
            }
        }
        // return answer
        return sum;
    }

    private static int problem2(String input){

        // Initialize vars
        int half = input.length() / 2;
        int sum = 0;
        Character baseDigit;
        Character halfwayDigit;
        int halfwayDigitIndex;

        // Main loop, determine duplicates halfway over, cycle around string if necessary
        for(int i = 0; i < input.length(); i++){
            baseDigit = input.charAt(i);
            halfwayDigitIndex = i + half;

            // Make sure to loop around the input
            if(halfwayDigitIndex >= input.length()) {
                halfwayDigitIndex = halfwayDigitIndex - input.length();
            }

            // Get other digit
            halfwayDigit = input.charAt(halfwayDigitIndex);

            if(baseDigit == halfwayDigit){
                sum = sum + (baseDigit.charValue() - 48);
            }

        }
        return sum;
    }
}

