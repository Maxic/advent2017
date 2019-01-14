import Helpers.HexCoordinate;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day11 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        List<String> input = new ArrayList<>();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input11.txt"));
        } catch (Exception e){
            System.out.println(e);
        }

        problem1(input.get(0));
        problem2(input.get(0));
    }

    private static void problem1(String input){
        // get coordinates from input string
        HexCoordinate childPosition = parse_Path(input);

        // calc dist using coordinates
        HexCoordinate originPosition = new HexCoordinate(0,0,0);
        int result = calculateDistance(originPosition, childPosition);

        // Print answer
        System.out.println("The answer to problem 1 is: " + result);
    }

    private static void problem2(String input){
    }

    private static HexCoordinate parse_Path(String path){
        String[] splitPath = path.split(",");
        HexCoordinate hexCoordinate = new HexCoordinate(0,0, 0);

        for(String step : splitPath){
            switch (step){
                case "n":
                    hexCoordinate.y += 1;
                    hexCoordinate.z -= 1;
                    break;
                case "ne":
                    hexCoordinate.x += 1;
                    hexCoordinate.z -= 1;
                    break;
                case "se":
                    hexCoordinate.x += 1;
                    hexCoordinate.y -= 1;
                    break;
                case "s":
                    hexCoordinate.y -= 1;
                    hexCoordinate.z += 1;
                    break;
                case "sw":
                    hexCoordinate.x -= 1;
                    hexCoordinate.z += 1;
                    break;
                case "nw":
                    hexCoordinate.x -= 1;
                    hexCoordinate.y += 1;
                    break;
                default:
                    throw new RuntimeException("Input string or parser incorrect. step string: " + step);
            }
        }
        return hexCoordinate;
    }

    private static int calculateDistance(HexCoordinate a, HexCoordinate b){
        return (Math.abs(a.x - b.x) + Math.abs(a.y - b.y) + Math.abs(a.z - b.z)) / 2;
    }
}
