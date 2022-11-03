import java.util.Arrays;

public class CopyOf {
    //@ ensures(*The parameter original and the copy will have identical values for all valid indices.*);
    public int[] copyOf(int[] original, int newlength) {
        return Arrays.copyOf(original, newlength);
    }
}