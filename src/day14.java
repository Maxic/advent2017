import Helpers.Scanner;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day14 {
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
    }

    private static void problem2(ArrayList<String> input){
    }
}
