package g2401_2500.s2468_split_message_based_on_limit;

// #Hard #String #Binary_Search #2023_01_11_Time_27_ms_(99.08%)_Space_50.2_MB_(94.85%)

@SuppressWarnings("java:S3518")
public class Solution {
//@ ensures(*Preconditions:*);
//@ ensures(*The input string `message` is not null.*);
//@ ensures(*The input string `message` is not empty.*);
//@ ensures(*The input integer `limit` is a positive integer.*);
//@ ensures(*The length of `message` is greater than or equal to `limit`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The returned array `parts` is not null.*);
//@ ensures(*The length of the returned array `parts` is equal to the number of parts the `message` is split into.*);
//@ ensures(*Each element in the returned array `parts` is a string.*);
//@ ensures(*The length of each element in the returned array `parts` (including its suffix) is equal to `limit`, except for the last part whose length can be at most `limit`.*);
//@ ensures(*The suffix of each element in the returned array `parts` is in the format "<a/b>", where "b" is the total number of parts and "a" is the index of the part, starting from 1 and going up to b.*);
//@ ensures(*When the suffixes are removed from each element in the returned array `parts` and they are all concatenated in order, they should be equal to the input `message`.*);
//@ ensures(*The number of parts the `message` is split into is as few as possible.*);
    public String[] splitMessage(String message, int limit) {
        int total = 0;
        int running = 0;
        int count;
        int totalReq;
        int valUsed = -1;
        int minLimitReq;
        for (int i = 1; i <= message.length(); ++i) {
            count = getCount(i);
            running += count;
            total = running + (count * i) + 3 * i;
            totalReq = total + message.length();
            minLimitReq = (totalReq + i - 1) / i;
            if (minLimitReq <= limit) {
                valUsed = i;
                break;
            }
        }
        if (valUsed == -1) {
            return new String[] {};
        }
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        StringBuilder sb2 = new StringBuilder();
        int left;
        String[] result = new String[valUsed];
        for (int i = 1; i <= valUsed; ++i) {
            sb2.setLength(0);
            sb.setLength(0);
            sb2.append('<');
            sb2.append(i);
            sb2.append('/');
            sb2.append(valUsed);
            sb2.append('>');
            left = limit - sb2.length();
            while (idx < message.length() && left-- > 0) {
                sb.append(message.charAt(idx++));
            }
            sb.append(sb2);
            result[i - 1] = sb.toString();
        }
        return result;
    }

    private int getCount(int val) {
        int result = 0;
        while (val != 0) {
            val /= 10;
            ++result;
        }
        return result;
    }
}