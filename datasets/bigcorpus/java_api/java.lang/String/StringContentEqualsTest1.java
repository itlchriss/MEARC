// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringContentEqualsTest
{

    //Note: this method cannot be verified initially
    //@ requires(*sb - The StringBuffer to compare this String against*);
    //@ ensures(*Returns true if this String represents the same sequence of char values as the specified sequence, false otherwise*);
    public boolean contentEquals(String s, StringBuffer sb) {
      return s.contentEquals(sb);
    }

}