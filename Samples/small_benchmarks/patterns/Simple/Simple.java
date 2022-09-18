public class Simple {
    //+ semantics "input num1", nn, 1, (*):num1
    //+ semantics "input num2", nn, 1, (*):num2
    //@ requires (*The input num1 is greater than or equal to 0 and the input num2 is greater than or equal to 0.*);
    //@ requires (*The input num1 is less than or equal to 1000 and the input num2 is less than or equal to 1000.*);
    //@ ensures (*The result is less than or equal to 2000 and the result is greater than or equal to 0.*);
    public static int Sum(int num1, int num2) {
        int r = num1 + num2;
        return r;
    }
}
