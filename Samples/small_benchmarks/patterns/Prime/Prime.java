// Java program to check whether a number is prime or not
class Prime{        
    // ensures (n == 2 || (n > 2 && (\forall int k; n > 2 && 2 <= k && k <= n/2; n%k != 0))) ==> (\result == true);
    // ensures !(n == 2 || (n > 2 && (\forall int k; n > 2 && 2 <= k && k <= n/2; n%k != 0))) ==> (\result == false);
    // ensures (\result == true) ==> (n == 2 || (n > 2 && (\forall int k; n > 2 && 2 <= k && k <= n/2; n%k != 0)));
    // ensures (\result == false) ==> !(n == 2 || (n > 2 && (\forall int k; n > 2 && 2 <= k && k <= n/2; n%k != 0)));
    //@ ensures (\result == false) ==> !(n == 2 || (n > 2 && (\forall int k; n > 2 && 2 <= k && k <= n/2; n%k != 0)));
    /*
        ensures (
            (\exists boolean x; x == true && (\exists boolean x1; x1 == \result && x1 == x))
            ==>
            (\exists int num; num == n && 
                (\exists int x; 
                x == n && (x == 2 || (x > 2 && (\forall int k; x > 2 && 2 <= k && k <= x/2; x%k != 0))))
            )              
        ); 
    */
    // /*@ 
    //     ensures (\exists int x1;  x1 == n; \exists int x; (x == 2 || (x > 2 && !(\forall int k; 2 <= k && k <= x/2; x%k != 0))); x == x1) 
    //     ==> 
    //     (\result == true);
    // @*/
    // /*@
    //     ensures (\exists int x1;  x1 == n; \exists int x; !(x == 2 || (x > 2 && !(\forall int k; 2 <= k && k <= x/2; x%k != 0))); x == x1) 
    //     ==> 
    //     (\result == false);
    // @*/
    // /*@ 
    //     ensures (\exists int x; (x == 2 || (x > 2 && !(\exists int k; 2 <= k && k <= x/2; x%k == 0))); x == n) 
    //     ==> 
    //     (\result == true);
    // @*/
    // /*@
    //     ensures (\exists int x; !(x == 2 || (x > 2 && !(\exists int k; 2 <= k && k <= x/2; x%k == 0))); x == n) 
    //     ==> 
    //     (\result == false);
    // @*/
    //+ semantics "prime number", nn, 1, (x):(x == 2 || (x > 2 && (\forall int k; x > 2 && 2 <= k && k <= x/2; x%k != 0)
    //+ semantics "input number", nn, 1, (*):n
    //@ ensures (*If the result is true, the input n is a prime number.*);
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
