// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringCharAtTest
{

    //@ requires(*index - the index of the char value.*);
    //@ ensures(*Returns the char value at the specified index of this string. The first char value is at index 0.*);
    //@ signals(StringIndexOutOfBoundsException e)(*if the index argument is negative or not less than the length of this string.*);
    public char charAt(String s, int index)
        throws StringIndexOutOfBoundsException {
          return s.charAt(index);
        }


}
