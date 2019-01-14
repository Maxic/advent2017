import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class day6 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input6.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problems((ArrayList) input);
    }

    private static void problems(ArrayList<String> input){

        // Convert input to int array
        String[] inputArr = input.get(0).split("\t");
        int[] memBanks = new int[inputArr.length];

        for(int i=0; i < memBanks.length; i++){
            memBanks[i] = Integer.parseInt(inputArr[i]);
        }

        // Initialize some vars
        boolean duplicateNotFound = true;
        int tick = 0;
        int infLoopTick = 0;
        int redistributionBankIndex;
        String state;
        // Checking for duplicates, so lets use a set again
        HashSet<String> states = new HashSet<>();

        // Main loop
        while(duplicateNotFound){

            // Convert to string, check for duplicates and stop cycle if found
            state = "";
            for(int membank : memBanks){
                state += membank;
            }
            states.add(state);

            tick++;
            infLoopTick++;

            if(states.size() < tick){
                System.out.println("The answer to problem 1 is: " + (tick-1));
                System.out.println("The answer to problem 2 is: " + (infLoopTick-1));
                duplicateNotFound = false;
            }

            // State retrieved from run for problem 1
            if(state.equals("0141312111088665332110")){
                infLoopTick = 1;
            }

            // Find membank index for max block count. Returns lowest membank index on ties
            redistributionBankIndex = findMembankWithMostBlocks(memBanks);

            // Cycle through membanks and redistribute blocks
            memBanks = redistributeBlocks(redistributionBankIndex, memBanks);
        }
    }

    private static int findMembankWithMostBlocks(int[] memBanks){
        int max = 0;
        int result = -1;

        // go in reverse through membanks, so the lowest b
        for (int i = memBanks.length - 1; i >= 0; i--) {
            if(memBanks[i] >= max){
                max = memBanks[i];
                result = i;
            }
        }

        return result;
    }

    private static int[] redistributeBlocks(int redistributionBankIndex, int[] memBanks){

        // initialize vars
        int blockAmount = memBanks[redistributionBankIndex];
        int currentBankIndex = redistributionBankIndex+1;

        // Empty current bank
        memBanks[redistributionBankIndex] = 0;

        while (blockAmount > 0){
            if(currentBankIndex >= memBanks.length){
                currentBankIndex = 0;
            }

            memBanks[currentBankIndex] += 1;

            blockAmount--;
            currentBankIndex++;
        }

        return memBanks;
    }
}
