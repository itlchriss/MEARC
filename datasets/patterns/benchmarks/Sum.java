public class Sum {
    //@ requires ((num2 >= 0) && (num1 >= 0));
    //@ requires ((num2 <= 1000) && (num1 <= 1000));
    //@ ensures ((\result >= 0) && (\result <= 2000));
    //@ requires (*The input num1 is greater than or equal to 0 and the input num2 is greater than or equal to 0.*);
    //@ requires (*The input num1 is less than or equal to 1000 and the input num2 is less than or equal to 1000.*);
    //@ ensures (*The result is less than or equal to 2000 and the result is greater than or equal to 0.*);
    public static int Sum(int num1, int num2) {
        int r = num1 + num2;
        return r;
    }
}
