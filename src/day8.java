import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day8 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input8.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problem1((ArrayList) input);

    }

    private static void problem1(ArrayList<String> input){
        Hashtable<String, Integer> registers = new Hashtable<>();

        // Declare some vars
        String[] instruction;
        String register;
        String operation;
        int value;
        String conditionRegister;
        String conditionOperation;
        int conditionValue;
        int result2 = 0;

        // main loop
        for(String s : input){

            // parse instruction using regex
            instruction = s.split(" ");
            register = instruction[0];
            operation = instruction[1];
            value = Integer.parseInt(instruction[2]);
            conditionRegister = instruction[4];
            conditionOperation = instruction[5];
            conditionValue = Integer.parseInt(instruction[6]);

            // If register is not yet in dict, add it to dict
            if(!registers.containsKey(register)){
                registers.put(register, 0);
            }
            if(!registers.containsKey(conditionRegister)){
                registers.put(conditionRegister, 0);
            }

            if(conditionValid(registers, conditionRegister, conditionOperation, conditionValue)){
                int oldValue = registers.get(register);
                if(operation.equals("inc")){
                    int newValue = oldValue + value;
                    registers.replace(register, oldValue, newValue);
                } else {
                    int newValue = registers.get(register) - value;
                    registers.replace(register, oldValue, newValue);
                }
            }
            result2 = Math.max(result2, Collections.max(registers.values()));
        }
        int result1 = Collections.max(registers.values());

        System.out.println("The answer to problem 1 is: " + result1);
        System.out.println("The answer to problem 2 is: " + result2);
    }

    private static boolean conditionValid(Hashtable<String, Integer> registers, String register, String operation, int value){
        switch (operation){
            case ">":
                return registers.get(register) > value;
            case "<":
                return registers.get(register) < value;
            case "==":
                return registers.get(register) == value;
            case ">=":
                return registers.get(register) >= value;
            case "<=":
                return registers.get(register) <= value;
            case "!=":
                return registers.get(register) != value;
        }
        return false;
    }
}
