public class AsciiCaseConverter {
    /**
     * Changes the case of an ASCII letter according to specified rules:
     * 1. If input is uppercase (A-Z), converts to lowercase (a-z)
     * 2. If input is lowercase (a-z), converts to uppercase (A-Z)
     * 3. If input is not an ASCII letter, returns the input unchanged
     *
     * @param c the character to convert
     * @return the converted character according to the rules
     */
    //@ ensures(((c <= 'Z') && (c >= 'A')) ==> ((\result <= 'z') && (\result >= 'a')));
    //@ also
    //@ ensures(((c <= 'z') && (c >= 'a')) ==> ((\result <= 'Z') && (\result >= 'A')));
    //@ also
    //@ ensures((!(c <= 'Z') && !(c >= 'A')) ==> ((\result == c)));
    public static char changeCase(char c) {
        if (c >= 'A' && c <= 'Z') {
            return (char)(c + 32);
        } else if (c >= 'a' && c <= 'z') {
            return (char)(c - 32);
        } else {
            return c;
        }
    }
}