public class Even {
    //@ ensures (*If result is true, the input number is even.*);
    //@ ensures (*If result is false, the input number is not even.*);
    //@ ensures \result==true;
    //@ ensures \result==false;
    public static boolean isEven(int number) {
        if(number % 2 == 0)
            return true;
        else
            return false;
    }
}
