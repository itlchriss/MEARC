// Java program to check whether a number is prime or not
class Prime{        
    //@ ensures ((n == 2 || (n > 2 && (\forall int k; n > 2 && 2 <= k && k <= n/2; n % k != 0))) ==> (\result==true));
    //+ semantics "input number", nn, 1, (*), n
    //@ ensures (*If the input n is a prime number, the result is true.*);
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
