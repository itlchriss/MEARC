import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Equals {
    //@ ensures(*Returns true if the specified object is equal to this collection.*);
    public Boolean CollectionEquals(List<Integer> collection, List<Integer> target) {
        return collection.equals(target);
    }
}
