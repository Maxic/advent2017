import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import Helpers.Scanner;

public class day13 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input13.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problem1((ArrayList) input);
        problem2((ArrayList) input);
    }

    private static void problem1(ArrayList<String> input){

        // Initialize all scanners defined in the input also keep a set of scanner depths
        for(String scannerDetails : input){
            String[] splitScannerDetails = scannerDetails.split(" ");
            int depth = Integer.parseInt(splitScannerDetails[0].substring(0,splitScannerDetails[0].length()-1));
            int range = Integer.parseInt(splitScannerDetails[1]);
            new Scanner(depth, range);
        }

        int packetLayer = -1;
        int severitySum = 0;

        // Main loop
        while (packetLayer < 100){
            // Move packet forward
            packetLayer++;

            // Check if a scanner exists at this layer
            if (Scanner.scannerDepths.containsKey(packetLayer)){
                Scanner currentScanner = Scanner.scannerDepths.get(packetLayer);
                // Check if the scanner is on top (has its initial state)
                if (currentScanner.state == Scanner.initialState){
                    // Add severity to total
                    severitySum += currentScanner.depth * currentScanner.range;
                }

            }

            // Move scanners
            for(Scanner s : Scanner.scannerDepths.values()){
                s.step();
            }
        }
        System.out.println("The problem to answer 1 is: " + severitySum);
    }

    private static void problem2(ArrayList<String> input){
    }
}
