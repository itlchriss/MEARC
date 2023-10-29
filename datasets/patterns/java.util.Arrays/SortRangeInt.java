import java.util.Arrays;

public class SortRangeInt {   
    //@ requires (*The parameter fromIndex must be greater than or equal to 0 and the parameter toIndex must be less than or equal to the (array.length) and the parameter fromIndex must be less than or equal to the parameter toIndex.*);
    //@ ensures (*The parameter array is sorted in ascending order from fromIndex to toIndex.*);
    public void ArraySort(int[] array, int fromIndex, int toIndex) {
        Arrays.sort(array, fromIndex, toIndex);
    }
}

