// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringLengthTest
{
    //@ ensures(*Returns the length of the sequence of characters represented by this object.*);
    public int length(String s) {
      return s.length();
    }
    
}
