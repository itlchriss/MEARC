// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringEqualsIgnoreCaseTest
{

    //@ requires(*anotherString - The String to compare this String against.*);
    //@ ensures(*Returns true if the argument is not null and it represents an equivalent String ignoring case; false otherwise.*);
    public boolean equalsIgnoreCase(String s, String anotherString) {
      return s.equalsIgnoreCase(anotherString);
    }
}