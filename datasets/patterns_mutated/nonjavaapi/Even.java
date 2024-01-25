public class Even {
    // ensures (*If result is true, the input number is even.*);
    // ensures (*If the result is true, the input number n is even.*);
    // ensures (*If true is the result, the input number n is even.*);
    // ensures (*If result is false, the input number is not even.*);
    // ensures \result==true;
    // ensures \result==false;
    public static boolean isEven(int n) {
        if(n % 2 == 0)
            return true;
        else
            return false;
    }
}
