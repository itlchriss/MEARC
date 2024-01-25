// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringCopyValueOfTest
{
    //@ requires(*data - the character array.*);
    //@ ensures(*Returns a String that contains the characters of the specified subarray of the character array.*);
    public static String copyValueOf(String s, char[] data) {
      return s.copyValueOf(data);
    }

}
