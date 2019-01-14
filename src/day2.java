import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day2 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input2.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        //problem1((ArrayList) input);
        problem2((ArrayList) input);

    }

    private static void problem1(ArrayList<String> input){

        int[] diffArray = new int[input.size()];

        for(int i=0; i < input.size(); i++){
            // Split on tabs, create new list
            String[] line = input.get(i).split("\t");

            // Initialize vars
            int min = Integer.MAX_VALUE;
            int max = 0;

            // Calculate differences
            for(int j=0; j < line.length; j++){
                max = Math.max(max, Integer.parseInt(line[j]));
                min = Math.min(min, Integer.parseInt(line[j]));
            }
            diffArray[i] = max - min;
        }

        // Sum array
        int result = 0;
        for(int i=0; i < diffArray.length; i++){
            result += diffArray[i];
        }

        System.out.println("Result for problem 1: " + result);
    }

    private static void problem2(ArrayList<String> input){

        // Keep track of all integer results in this array
        double[] results = new double[input.size()];

        // Calculate result of each evenly divisible pair per line
        for(int lineNumber =0; lineNumber < input.size(); lineNumber++) {

            // Create string array
            String[] line = input.get(lineNumber).split("\t");

            // Reset flag
            boolean resultFound = false;

            for (int i = 0; i < line.length; i++) {

                // initialize first value to divide by the rest of line
                double firstValue = Double.parseDouble(line[i]);

                // break if result is found
                if (resultFound){
                    break;
                }

                // Divide first against second value, break if result is found and add to array
                for (int j = i+1; j < line.length; j++) {

                    double secondValue = Double.parseDouble(line[j]);
                    double result;

                    if (firstValue > secondValue){
                        result = firstValue / secondValue;
                    } else {
                        result = secondValue / firstValue;
                    }


                    if (result % 1 == 0.0){
                        results[lineNumber] = result;
                        resultFound = true;
                        break;
                    }
                }
            }
        }

        int sum = 0;

        // Sum results
        for (double result : results) {
            sum += result;
        }

        System.out.println("The answer to problem 2 is: " + sum);
    }
}
