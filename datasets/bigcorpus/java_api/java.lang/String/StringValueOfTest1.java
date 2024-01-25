// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringValueOfTest1
{
    //@ requires(*c - a char.*);
    //@ ensures(*Returns a string of length 1 containing as its single character the argument c.*);
    public static  String valueOf(String s, char c) {
      return s.valueOf(c);
    }

}
