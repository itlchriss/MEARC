import java.util.Arrays;

public class FillLong2 {
    //@ ensures (*Every element in parameter x equals to the parameter val.*);
    public void fill(long[] x, long val) {
        Arrays.fill(x, val);
    }
}
