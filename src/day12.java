import Helpers.HexCoordinate;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class day12 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input12_example.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problem1((ArrayList) input);
        problem2(input.get(0));
    }

    private static void problem1(ArrayList<String> input){
        // Initialize a hashmap with each house and connected houses as key and value respectively
        HashMap<String, ArrayList<String>> houseConnections = new HashMap<>();

        for(String line : input){
            String[] splitLine = line.split(" ");
            // create connected houses array;
            ArrayList<String> connectedHouses = new ArrayList<>();
            String house;
            for (int i = 2; i < splitLine.length; i++) {
                if( i == splitLine.length-1){
                    house = splitLine[i];
                } else{
                    // remove comma
                    house = splitLine[i].substring(0,splitLine[i].length()-1);
                }
                if(!house.equals(splitLine[0])){
                    connectedHouses.add(house);
                }
            }
            if(!connectedHouses.isEmpty()){
                houseConnections.put(splitLine[0],connectedHouses);
            }
        }

        // count programs linked to specific program


    }

    private static void problem2(String input){
    }

    private static int countProgramsInGroup(String originProgram, HashMap<String, ArrayList<String>> programConnections){
        int connectedPrograms = 0;

        for(String connectedProgram : programConnections.get(originProgram)){
            // This is not a tree, since connections go either way, recursive solutions probably not the best
            connectedPrograms += countProgramsInGroup(connectedProgram, programConnections);
        }

        return connectedPrograms;
    }
}
