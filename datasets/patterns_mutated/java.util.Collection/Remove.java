import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Remove {
    //@ ensures(*Returns true if the parameter collection contained the parameter o and the parameter o was removed from the parameter collection.*);
    /*@ 
        ensures(
            (
                (\exists int i; 0 <= i < \old(collection).size(); \old(collection).get(i) == o) && !(\exists int i; 0 <= i < collection.size(); collection.get(i) == o)
            ) ==> \result == true);
    @*/
    public Boolean remove(List<Integer> collection, Integer o) {
        return collection.remove(o);
    }
}
