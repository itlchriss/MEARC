import java.util.Collection;
import java.util.Collections;

public class Empty {
    //@ requires e != null && collection != null;
    /*@ ensures (
            collection.size() == \old(collection).size() - 1 &&
            Collections.frequency(collection, e) == Collections.frequency(\old(collection), e) - 1
        );
    @*/
    public void searchStack(Collection<Integer> collection, Integer e) {
        collection.remove(e);
    }
}