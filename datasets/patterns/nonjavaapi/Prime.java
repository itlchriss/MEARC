<<<<<<< HEAD
// Java program to check whether a number is prime or not
class Prime{        
    //@ ensures (*If and only if the input n is a prime number, the result is true.*);
    //@ requires Prime.isPrime(n);
    //@ pure
    public static boolean isPrime(int n)
    {                
        if(n <= 1){
            return false;
        } else if (n <= 3) {
            return true;
        } else {      
            int i = 2, m = n/2 + 1;            
            boolean res = true;
            //@ loop_invariant i >= 2 && m >= i;
            //@ decreasing m-i;
            for(i = 2;i < m ;i++){
                if(n % i==0) {
                    res = false;
                    // assert n % i == 0 && res == false;                     
                    break;
                } 
            }
            return res;
        }
    }
}
=======
// Java program to check whether a number is prime or not
class Prime{        
    //@ ensures (*If and only if the input n is a prime number, the result is true.*);
    // requires Prime.isPrime(n);
    //@ pure
    public static boolean isPrime(int n)
    {                
        if(n <= 1){
            return false;
        } else if (n <= 3) {
            return true;
        } else {      
            int i = 2, m = n/2 + 1;            
            boolean res = true;
            //@ loop_invariant i >= 2 && m >= i;
            //@ decreasing m-i;
            for(i = 2;i < m ;i++){
                if(n % i==0) {
                    res = false;
                    // assert n % i == 0 && res == false;                     
                    break;
                } 
            }
            return res;
        }
    }
}
>>>>>>> 98564b86c8a5a162de5f9f90ad7282335e2b2a03
