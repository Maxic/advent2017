import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class day5 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input5.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problem1((ArrayList) input);
        problem2((ArrayList) input);
    }

    private static void problem1(ArrayList<String> input){

        // Create instruction array
        int[] jumps = new int[input.size()];

        for (int i = 0; i < input.size(); i++) {
            jumps[i] = Integer.parseInt(input.get(i));
        }

        int tick = 0;
        int currentIndex = 0;

        while (true){
            try{
                // Get current jump
                int currentJump = jumps[currentIndex];

                // Increment jump
                jumps[currentIndex] += 1;

                // Change current index
                currentIndex += currentJump;


                tick++;
            } catch (IndexOutOfBoundsException e){
                System.out.println("The answer to problem 1 is: " + tick);
                break;
            }
        }
    }

    private static void problem2(ArrayList<String> input){

        // Create instruction array
        int[] jumps = new int[input.size()];

        for (int i = 0; i < input.size(); i++) {
            jumps[i] = Integer.parseInt(input.get(i));
        }

        int tick = 0;
        int currentIndex = 0;

        while (true){
            try{
                // Get current jump
                int currentJump = jumps[currentIndex];

                // Increment jump
                if (currentJump >= 3){
                    jumps[currentIndex] -= 1;
                } else{
                    jumps[currentIndex] += 1;
                }

                // Change current index
                currentIndex += currentJump;

                tick++;
            } catch (IndexOutOfBoundsException e){
                System.out.println("The answer to problem 2 is: " + tick);
                break;
            }
        }
    }
}
