import java.util.Arrays;

public class SortInt {
    //@ ensures (*The parameter x is sorted in ascending numerical order.*);
    public void sort(int[] x) {
        Arrays.sort(x);
    }
}
