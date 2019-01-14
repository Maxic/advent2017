import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class day9 {

    public static int _score = 0;
    public static int _garbageCount = 0;

    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input9.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problems(input.get(0));
    }

    private static void problems(String input){
        List<Character> stream = input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.toList());

        stream = cleanUp(stream);
        countScore(stream);

        System.out.println("The answer to problem 1 is: " + _score);
        System.out.println("The answer to problem 2 is: " + _garbageCount);
    }

    private static List<Character> cleanUp(List<Character> stream){

        // First, let's clean it up, lets get rid of all ignored chars first (!)
        for (int i = 0; i < stream.size(); i++) {
            if (stream.get(i) == '!'){
                stream.remove(i);
                stream.remove(i);
                i--;
            }
        }

        // Then removing garbage is easier
        for (int i = 0; i < stream.size(); i++) {
            if (stream.get(i) == '<' ){
                _garbageCount--;
                while (stream.get(i) != '>'){
                    stream.remove(i);
                    _garbageCount++;
                }
                stream.remove(i); // Gets rid of the final '>'
            }
        }
        
        return stream;
    }

    private static void countScore(List<Character> stream){
        int scoreCount = 0;

        for (Character c : stream) {
            if (c == '{'){
                scoreCount++;
            } else if (c == '}'){
                _score += scoreCount;
                scoreCount--;
            }
        }
    }

    private static void printStream(List<Character> stream) {
        for (Character character : stream) {
            System.out.print(character);
        }
        System.out.println();
    }
}
