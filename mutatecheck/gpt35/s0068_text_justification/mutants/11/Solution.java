package g0001_0100.s0068_text_justification;

// #Hard #Array #String #Simulation #2023_08_11_Time_0_ms_(100.00%)_Space_40.8_MB_(72.37%)

import java.util.ArrayList;
import java.util.List;

public class Solution {
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; (\forall int j; 0 <= j && j < \result.get(i).length(); \result.get(i).charAt(j) != ' ')));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).split(" ").length <= maxWidth));
//@ ensures(\result != null);
//@ requires(words != null && maxWidth > 0);
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).length() <= maxWidth && \result.get(i).length() > 0));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).split(" ").length == maxWidth));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).endsWith(" ")));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).contains(" ")));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).split(" ").length == words.length));
//@ requires((\forall int i; 0 <= i && i < words.length; words[i] != null && words[i].length() > 0 && words[i].length() <= maxWidth));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).split(" ").length == words.length + 1));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).split(" ").length > 1));
//@ ensures(\result.get(\result.size() - 1).equals(words[words.length - 1]));
//@ requires(words.length > 0);
//@ ensures((\forall int i; 0 <= i && i < \result.size(); \result.get(i) != null && \result.get(i).length() == maxWidth));
//@ ensures((\forall int i; 0 <= i && i < \result.size() - 1; \result.get(i).split(" ").length == words.length - 1));
    public List<String> fullJustify(String[] words, int maxWidth) {
        // Trying to gauge the number of lines so the ArrayList doesn't need to resize
        List<String> output = new ArrayList<>((words.length + 1) / (1 / (maxWidth / 7)));
        // Setting StringBuilder capacity also
        StringBuilder sb = new StringBuilder(maxWidth);
        int lineTotal = 0;
        int numWordsOnLine = 0;
        int startWord = 0;
        // looping until the 2nd last word, since we're checking words[i + 1] for
        // overflows
        for (int i = 0; i < words.length - 1; i++) {
            lineTotal += words[i].length();
            // tracking line length + #words
            numWordsOnLine++;
            // checking if the next word causes an overflow
            if (lineTotal + numWordsOnLine + words[i + 1].length() > maxWidth) {
                // if only one word fits on the line...
                if (numWordsOnLine == 1) {
                    // append word
                    sb.append(words[i]);
                    // pad right with spaces
                    while (lineTotal++ < maxWidth) {
                        sb.append(' ');
                    }
                } else {
                    // # of extra spaces
                    int extraSp = (maxWidth - lineTotal) % (numWordsOnLine - 1);

                    // Creating the line
                    for (int j = startWord; j < (startWord + numWordsOnLine - 1); j++) {
                        // appending the word
                        sb.append(words[j]);
                        if (extraSp-- > 0) {
                            // appending an extra space, if required
                            sb.append(' ');
                        }
                        // appending the rest of the required spaces
                        for (int k = 0; k < (maxWidth - lineTotal) / (numWordsOnLine - 1); k++) {
                            sb.append(' ');
                        }
                    }
                    // appending the last word of the line
                    sb.append(words[startWord + numWordsOnLine - 1]);
                }
                // adding to output list
                output.add(sb.toString());
                // reset everything for next line
                // keeping track of the first word for next line
                startWord = i + 1;
                // resetting these to 0 for processing next line
                numWordsOnLine = lineTotal = 0;
                // need a new StringBuilder for the next line
                sb = new StringBuilder(maxWidth);
            }
        }
        // handling the final line (no justification, right padded with spaces)
        lineTotal = 0;
        for (int i = startWord; i < words.length; i++) {
            lineTotal += words[i].length();
            sb.append(words[i]);
            if (lineTotal++ < maxWidth) {
                sb.append(' ');
            }
        }
        // padding right side with spaces
        while (lineTotal++ < maxWidth) {
            sb.append(' ');
        }
        // add the final line to output list
        output.add(sb.toString());
        return output;
    }
}
