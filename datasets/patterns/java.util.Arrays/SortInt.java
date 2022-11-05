import java.util.Arrays;

public class SortInt {
    /*
        Remark: The specification should focus on what the method affects the status.
                The original specification focus on what the method does.
    */
    //@ ensures (*The parameter x is sorted in ascending numerical order.*);
    public void sort(int[] x) {
        Arrays.sort(x);
    }
}
