import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Equals {    
    //@ ensures(*Returns true if the specified object is equal to this collection.*);
    //@ requires collection != null && collection instanceof Collection && target != null && target instanceof Collection;
    // ensures(\result == true ==> collection.size() == target.size());
    /*
        ensures(
            \result == true ==> (collection.size() == target.size()) && collection != null && target != null && (
                \forall int j; 0 <= j < target.size(); (
                    (
                        target.get(j) == null && (
                            \exists int i; 0 <= i < collection.size(); collection.get(i) == null
                        )
                    )  || 
                    (
                        target.get(j) != null && (
                            \exists int i; 0 <= i < collection.size(); ((Integer)target.get(j)).equals((Integer)collection.get(i))
                        )
                    ) 
                )
            )
        );
    */
    /*@
        ensures(
            \result == true ==> (collection.size() == target.size()) && (
                \forall int j; 0 <= j < target.size(); (
                    collection.contains(target.get(j))
                )
            )
        );
    @*/
    public Boolean CollectionEquals(List<Integer> collection, List<Integer> target) {
        return collection.equals(target);
    }
}


