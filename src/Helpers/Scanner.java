package Helpers;
import java.util.HashMap;

public class Scanner {
    public static final int initialState = 1;
    public int state;
    private boolean movingDown;
    public int depth;
    public int range;
    public static HashMap<Integer, Scanner> scannerDepths = new HashMap<>();

    public Scanner(int depth, int range){
        this.state = initialState;
        this.movingDown = true;
        this.depth = depth;
        this.range = range;
        scannerDepths.put(depth, this);
    }

    public void step(){
        if (movingDown){
            state++;
            if(state == range){
                movingDown = false;
            }
        } else{
            state--;
            if(state == initialState){
                movingDown = true;
            }
        }
    }
}
