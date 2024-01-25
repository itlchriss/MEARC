import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Equals {
    //@ ensures(*Returns true if the parameter target is equal to the parameter collection.*);
    // requires target instanceof List<Integer> && collection instanceof List<Integer>;
    // requires target != null && collection != null;
    /* ensures(
        (\result==true) <==>
            (
                (target.size() == collection.size()) &&
                (
                    \forall int j; 0 <= j < collection.size(); (collection.get(j) == null <==> target.get(j) == null) || 
                    (collection.get(j) != null && target.get(j) != null <==> collection.get(j).equals(target.get(j)))
                ) && collection.equals(target) && target.equals(collection)
            )        
        );
    */
    public Boolean CollectionEquals(List<Integer> collection, List<Integer> target) {
        return collection.equals(target);
    }
}
