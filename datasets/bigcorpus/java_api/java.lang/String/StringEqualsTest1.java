// package java.lang;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.Comparator;
import java.nio.charset.Charset;


// Note: we cannot check the constructor because java.lang.String is `final`, therefore, we cannot extend it.
public class StringEqualsTest
{


    // Note: this method cannot be verified initially    
    public boolean equals(String s, Object anObject) {
      return s.equals(anObject);
    }
}