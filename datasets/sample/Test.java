public class Test {
    public class A {
        // ensures(\result == input + 1);
        //@ pure
        public int go(int input) {
            //@ assume Integer.MIN_VALUE < input < Integer.MAX_VALUE;
            return input + 1;
        }
    }
    
    //@ requires(Integer.MIN_VALUE < input < Integer.MAX_VALUE);
    //@ ensures(\result == input + 1);
    //@ pure
    public int gogo(int input) {
        A a = new A();
        return a.go(input);
    }
}