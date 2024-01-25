import java.util.Arrays;

public class SortRangeInt {   
    //@ requires (*The parameter fromIndex must be greater than or equal to 0 and the parameter toIndex must be less than or equal to the (array.length) and the parameter fromIndex must be less than or equal to the parameter toIndex.*);
    //@ requires (fromIndex >= 0 && toIndex <= array.length && fromIndex <= toIndex);
    //@ ensures (*The parameter array is sorted in ascending order from fromIndex to toIndex.*);
    // ---> preprocessed to (The array's elements from_fromIndex_to_toindex is sorted_in_ascending_order.);
    //@ ensures (\forall int i; fromIndex < i < toIndex; array[i - 1] <= array[i]);
    public void ArraySort(int[] array, int fromIndex, int toIndex) {
        Arrays.sort(array, fromIndex, toIndex);
    }
}

