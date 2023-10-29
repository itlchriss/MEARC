import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DeepEquals {
    //@ ensures (*If the parameter x deeply equals to the parameter y, the result is true*);
    public boolean deepEquals(Integer[] x, Integer[] y) {
        return Arrays.deepEquals(x, y);
    }
}
