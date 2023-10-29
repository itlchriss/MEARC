import java.util.Arrays;

public class EqualsObject2 {
    //@ ensures (*If the length of array x is equal to the length of array y and the elements of array x are correspondingly equal to the elements of array y, the result is true.*);
    //@ ensures (*If the reference of array x is null and the reference of array y is null, the result is true.*);
    public boolean equals(Object[] x, Object[] y) {
        return Arrays.equals(x, y);
    }
}


