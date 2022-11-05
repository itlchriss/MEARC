import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class DeepEquals {
    //@ ensures (*If the parameter x deeply equals to the parameter y, the result is true*);
    //--@ ensures(((((x == null && y == null) && ((x == y) || (x.equals(y)) || Arrays.equals(x, y))) || ((x != null && y != null && x.length == y.length) && (\forall int i; 0 <= i < x.length; ((x[i] == null && y[i] == null) || (x[i] != null && y[i] != null && (!x[i].getClass().isArray() && !y[i].getClass().isArray() && x[i].equals(y[i])))))))) ==> (\result==true));
    public boolean deepEquals(Integer[] x, Integer[] y) {
        return Arrays.deepEquals(x, y);
    }
}


// (x[i] instanceof Integer) && 
// (y[i] instanceof Integer) && 
// x[i].equals(y[i])