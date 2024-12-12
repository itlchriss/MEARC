public class ChangeCase {
    //@ ensures(((c <= 'Z') && (c >= 'A')) ==> ((\result <= 'z') && (\result >= 'a')));
    //@ also
    //@ ensures(((c <= 'z') && (c >= 'a')) ==> ((\result <= 'Z') && (\result >= 'A')));
    //@ also
    //@ ensures((!(c <= 'Z') && !(c >= 'A')) ==> ((\result == c)));
    public static char changeCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char) (c + ('a' - 'A'));
        } else if (c >= 'a' && c <= 'z') {
            return (char) (c - ('a' - 'A'));
        } else {
            return c;
        }
    }
}