package g0001_0100.s0012_integer_to_roman;

// #Medium #String #Hash_Table #Math #2023_08_09_Time_2_ms_(100.00%)_Space_43.2_MB_(81.21%)

public class Solution {
	//@ requires(*1. The input `num` is an integer.*);
	//@ requires(*2. The value of `num` is between 1 and 3999 (inclusive).*);
	//@ ensures(*1. The method returns a string representing the Roman numeral equivalent of the input `num`.*);
	//@ ensures(*2. The length of the returned string is greater than or equal to 1.*);
	//@ ensures(*3. The length of the returned string is less than or equal to 15.*);
	//@ ensures(*4. The returned string consists only of the characters 'I', 'V', 'X', 'L', 'C', 'D', and 'M'.*);
	//@ ensures(*5. The returned string represents the Roman numeral equivalent of the input `num` according to the given rules and symbols.*);
	//@ ensures(*6. The returned string is written in the largest to smallest order from left to right, except for the cases where subtraction is used.*);
	//@ ensures(*7. The returned string does not contain any invalid combinations of symbols (e.g., 'IIII' for 4).*);
	//@ ensures(*8. The returned string does not contain any unnecessary repetitions of symbols (e.g., 'VV' for 10).*);
	//@ ensures(*9. The returned string represents the input `num` accurately, without any loss of information.*);
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int m = 1000;
        int c = 100;
        int x = 10;
        int i = 1;
        num = numerals(sb, num, m, ' ', ' ', 'M');
        num = numerals(sb, num, c, 'M', 'D', 'C');
        num = numerals(sb, num, x, 'C', 'L', 'X');
        numerals(sb, num, i, 'X', 'V', 'I');
        return sb.toString();
    }

    private int numerals(StringBuilder sb, int num, int one, char cTen, char cFive, char cOne) {
        int div = num / one;
        switch (div) {
            case 9:
                sb.append(cOne);
                sb.append(cTen);
                break;
            case 8:
                sb.append(cFive);
                sb.append(cOne);
                sb.append(cOne);
                sb.append(cOne);
                break;
            case 7:
                sb.append(cFive);
                sb.append(cOne);
                sb.append(cOne);
                break;
            case 6:
                sb.append(cFive);
                sb.append(cOne);
                break;
            case 5:
                sb.append(cFive);
                break;
            case 4:
                sb.append(cOne);
                sb.append(cFive);
                break;
            case 3:
                sb.append(cOne);
                sb.append(cOne);
                sb.append(cOne);
                break;
            case 2:
                sb.append(cOne);
                sb.append(cOne);
                break;
            case 1:
                sb.append(cOne);
                break;
            default:
                break;
        }
        return num - (div * one);
    }
}