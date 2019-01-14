import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day4 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input4.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        // Testcases:
        System.out.println(isValidPassphrase("aa bb cc dd ee".split(" "))); // true
        System.out.println(isValidPassphrase("aa bb cc dd aa".split(" "))); // false
        System.out.println(isValidPassphrase("aa bb cc dd aaa".split(" "))); // true
        System.out.println();
        System.out.println(isValidNonAnagramPassphrase("abcde fghij".split(" "))); // true
        System.out.println(isValidNonAnagramPassphrase("abcde xyz ecdab".split(" "))); // false
        System.out.println(isValidNonAnagramPassphrase("a ab bac abd abj".split(" "))); // true
        System.out.println(isValidNonAnagramPassphrase("iiii oiii ooii oooi oooo".split(" "))); // true
        System.out.println(isValidNonAnagramPassphrase("oii ioii iioi iiio".split(" "))); // false


        System.out.println("Length of input: " + input.size());
        problem1((ArrayList) input);
        problem2((ArrayList) input);
    }

    private static void problem1(ArrayList<String> input){

        int sumValid = 0;

        for(int i=0; i < input.size(); i++)
        {
            if (isValidPassphrase(input.get(i).split(" "))){
                sumValid++;
            }
        }

        System.out.println("Number of valid passphrases: " + sumValid);
    }

    private static void problem2(ArrayList<String> input){

        int sumValid = 0;

        for(int i=0; i < input.size(); i++)
        {
            if (isValidNonAnagramPassphrase(input.get(i).split(" "))){
                sumValid++;
            }
        }

        System.out.println("Number of valid non-anagram passphrases: " + sumValid);
    }



    private static boolean isValidPassphrase(String[] passphrase) {

        boolean valid = false;

        // add all phrases to a Set, which doesn't allow duplicate entries
        Set<String> passphraseSet = new HashSet<>();
        for (int j = 0; j < passphrase.length; j++) {
            passphraseSet.add(passphrase[j]);
        }

        // if length differs, there ust be a duplicate
        if(passphrase.length == passphraseSet.size()){
            valid = true;
        }

        return valid;
    }

    private static boolean isValidNonAnagramPassphrase(String[] passphrase) {

        boolean valid = false;

        // add all phrases to a Set, which doesn't allow duplicate entries
        Set<String> passphraseSet = new HashSet<>();
        for (int j = 0; j < passphrase.length; j++) {

            // Convert string to char array and sort
            char[] sortedPassphrase = passphrase[j].toCharArray();
            Arrays.sort(sortedPassphrase);

            String passphraseSortedString = "";
            for (char c : sortedPassphrase) {
                passphraseSortedString = passphraseSortedString + c;
            }
            passphraseSet.add(passphraseSortedString);

        }

        // if length differs, there ust be a duplicate
        if(passphrase.length == passphraseSet.size()){
            valid = true;
        }

        return valid;
    }
}
