import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class day3 {
    public static void main(String args[]){

        String path = System.getProperty("user.dir");
        String input = new String();
        try {
            input = Files.readAllLines(Paths.get(path + "/src/input/input3.txt")).get(0);
        } catch (Exception e){
            System.out.println(e);
        }

        //problem1(Integer.parseInt(input));
        problem2(Integer.parseInt(input));

    }

    private static void problem1(int input){

        // Calculate square size
        int squareSize = (int)Math.round(Math.sqrt(input) + .5);

        // Determine input position
        boolean sameRow = (input > (Math.pow(squareSize,2) - squareSize));

        // Declare vars
        int xpos1;
        int ypos1;
        int xpos2;
        int ypos2;

        // Only write implementation for sameRow cause it applies to my input
        if (sameRow){
            // ypos is bottom row because squaresize is odd
            ypos1 = squareSize-1;

            // Bottom row is ascending, we can calculate xpos
            xpos1 = ((int)Math.pow(squareSize,2)-input);

            // Determine middle (xpos2, ypos2)
            xpos2 = squareSize/2;
            ypos2 = squareSize/2;

            // Calc manhattan distance
            int result = calculateManhattanDistance(xpos1, ypos1, xpos2, ypos2);
            System.out.println(result);
        }
    }

    private static void problem2(int input){
        // Calculate the value of each index in a grid
        // value is the sum of all values of the immediate neighbours


        int gridSize = 21;

        // initialize grid and fill with 0's
        int[][] grid = new int[gridSize][gridSize];
        for (int[] ints : grid) {
            for (int anInt : ints) {
                anInt = 0;
            }
        }

        // start at the midway point and fill grid
        int x = gridSize/2;
        int y = gridSize/2;
        grid[y][x] = 1;
        int stepcount = 0;
        int newValue = 1;
        boolean ansNotFound = true;

        // Spiral outward
        while (ansNotFound){
            // Move up stepcount
            stepcount++;

            // Up
            for( int i=0; i < stepcount; i++){
                x += 1;
                newValue = sumNeighbours(x, y, grid);
                if(newValue > input){
                    System.out.println("The answer to problem 2 is: " + newValue);
                    ansNotFound = false;
                    break;
                }
                grid[y][x] = newValue;
            }

            // Left
            for( int i=0; i < stepcount; i++){
                y -= 1;
                newValue = sumNeighbours(x, y, grid);
                if(newValue > input){
                    System.out.println("The answer to problem 2 is: " + newValue);
                    ansNotFound = false;
                    break;
                }
                grid[y][x] = newValue;
            }

            // Once again
            stepcount++;

            // Down
            for( int i=0; i < stepcount; i++){
                x -= 1;
                newValue = sumNeighbours(x, y, grid);
                if(newValue > input){
                    System.out.println("The answer to problem 2 is: " + newValue);
                    ansNotFound = false;
                    break;
                }
                grid[y][x] = newValue;
            }

            // Right
            for( int i=0; i < stepcount; i++){
                y += 1;
                newValue = sumNeighbours(x, y, grid);
                if(newValue > input){
                    System.out.println("The answer to problem 2 is: " + newValue);
                    ansNotFound = false;
                    break;
                }
                grid[y][x] = newValue;
            }
        }
    }

    private static int calculateManhattanDistance(int x1, int y1, int x2, int y2){
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    private static int sumNeighbours(int x, int y, int[][] grid){
        return  grid[y-1][x-1] + grid[y-1][x] + grid[y-1][x+1] +
                grid[y][x-1]   +                grid[y][x+1]   +
                grid[y+1][x-1] + grid[y+1][x] + grid[y+1][x+1];
    }

    private static void printGrid(int[][] grid){
        for (int[] ints : grid) {
            for (int anInt : ints){
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
