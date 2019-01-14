import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class day10 {

    private static final int _length = 256;
    private static int _skipSize = 0;
    private static int _currentPosition = 0;

    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input10.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problem1(input.get(0));
        problem2(input.get(0));
    }

    private static void problem1(String input){
        int currentPosition = 0;
        int skipSize = 0;

        // Initialize list and empty sublist
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < _length ; i++) {
            list.add(i);
        }
        List<Integer> subList;

        // Main loop
        for (String s : input.split(",")) {
            int length = Integer.parseInt(s);

            if(length != 0 ) {
                // Select sublist of length (length) from current index
                subList = getSublist(list, currentPosition, cycle(currentPosition, length));

                // Reverse sublist
                Collections.reverse(subList);

                // Modify list with reversed sublist
                replaceWithSublist(currentPosition, list, subList);
            }

            currentPosition = cycle(currentPosition, length+skipSize);
            skipSize++;
        }
        System.out.println("The answer to problem 1 is: " + list.get(0)*list.get(1));
    }

    private static void problem2(String input){
        // Convert inputstring to list of ASCII codes
        String newInput = "";
        for(char c : input.toCharArray()){
            newInput += ((int) c) + ",";
        }

        // Append this suffix
        String suffix = "17,31,73,47,23";
        newInput += suffix;

        // Do 64 rounds of hashKnotting using this string as length saving position and skipSize
        // returns sparse hash
        ArrayList<Integer> sparseHash = new ArrayList<>();
        for (int i = 0; i < _length ; i++) {
            sparseHash.add(i);
        }
        for (int i = 0; i < 64; i++) {
            sparseHash = new ArrayList<>(hashKnotRound(newInput, sparseHash));
        }

        // convert sparse hash to dense hash
        ArrayList<Integer> denseHash = new ArrayList<>();
        for(int block=0; block < sparseHash.size()/16; block++){
            int xor = sparseHash.get(16*block) ^ sparseHash.get((16*block)+1);
            for (int i=2; i < 16; i++){
                xor = xor ^ sparseHash.get((16*block)+i);
            }
            denseHash.add(xor);
        }

        // represent dense hash as hex string
        String result = "";
        for(int i=0; i < denseHash.size(); i++){
            String hex = Integer.toHexString(denseHash.get(i));
            if (hex.length() == 1){
                result += "0" + hex;
            } else {
                result += hex;
            }
        }

        // print result
        System.out.println("The answer to problem 2 is: " + result);
    }

    private static ArrayList<Integer> hashKnotRound(String input, ArrayList<Integer> list) {
        // Initialize list and empty sublist
        /*
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < _length ; i++) {
            list.add(i);
        } */
        List<Integer> subList;

        // Main loop
        for (String s : input.split(",")) {
            int length = Integer.parseInt(s);

            if(length != 0 ) {
                // Select sublist of length (length) from current index
                subList = getSublist(list, _currentPosition, cycle(_currentPosition, length));

                // Reverse sublist
                Collections.reverse(subList);

                // Modify list with reversed sublist
                replaceWithSublist(_currentPosition, list, subList);
            }

            _currentPosition = cycle(_currentPosition, length+_skipSize);
            _skipSize++;
        }
        return list;
    }

    private static List<Integer> getSublist(List<Integer> list, int fromIndex, int toIndex){
        if (fromIndex > toIndex){
            List<Integer> subList1 = new ArrayList<>(list.subList(fromIndex, list.size()));
            List<Integer> subList2 = new ArrayList<>(list.subList(0, toIndex));
            subList1.addAll(subList2);
            return subList1;
        } else {
            return list.subList(fromIndex, toIndex);
        }
    }

    private static void replaceWithSublist(int position, ArrayList<Integer> list, List<Integer> sublist){
        for (Integer integer : sublist) {
            list.set(position, integer);
            position = cycle(position, 1);
        }
    }

    private static int cycle(int position, int move){
        return (position + move) % _length;
    }
}
