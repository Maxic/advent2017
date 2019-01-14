import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class day7 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input7.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        // problem1: veboyvy
        problem2((ArrayList) input); // obxrn is 7 too heavy: 756 - 7 = 749

    }

    private static void problem2(ArrayList<String> input){
        // Declare two dict's, one with weights, and one with children
        Hashtable<String, Integer> discWeight = new Hashtable<>();
        Hashtable<String, String[]> discChildren = new Hashtable<>();

        // Fill dicts
        for(String s : input){
            String[] splitDef = s.split(" ");
            discWeight.put(splitDef[0], Integer.parseInt(splitDef[1].substring(1,splitDef[1].length()-1)));

            // if a disc has subtowers, add it to the dict
            if(splitDef.length >2){
                int childrenAmount = splitDef.length-3;
                String[] children = new String[childrenAmount];
                for (int i = 0; i < childrenAmount; i++) {
                    if(i == childrenAmount-1){
                        children[i] = splitDef[3+i];
                    } else { // strip comma if not last child
                        children[i] = splitDef[3+i].replaceAll(",","");
                    }
                }
                discChildren.put(splitDef[0], children);
            }
        }

    }

    private static int calculateSumWeight(String disc, Hashtable<String, String[]> discChildren, Hashtable<String, Integer> discWeight){
        String[] children = discChildren.get(disc);

        int sum = 0;

        for(String child : children){
            if( discChildren.containsKey(child)){
                sum += calculateSumWeight(child, discChildren, discWeight);
            } else {
                sum += discWeight.get(child);
            }
        }

        // Finally, add your own weight
        sum += discWeight.get(disc);

        return sum;
    }
}
