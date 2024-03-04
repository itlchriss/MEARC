package g1101_1200.s1169_invalid_transactions;

// #Medium #Array #String #Hash_Table #Sorting #2023_06_02_Time_9_ms_(98.31%)_Space_44.9_MB_(7.40%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    private static class Transaction {
        String name;
        int time;
        int amount;
        String city;

        Transaction(String trans) {
            String[] s = trans.split(",");
            name = s[0];
            time = Integer.parseInt(s[1]);
            amount = Integer.parseInt(s[2]);
            city = s[3];
        }
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input array `transactions` is not null.*);
//@ ensures(*The length of the input array `transactions` is less than or equal to 1000.*);
//@ ensures(*Each element in the input array `transactions` is in the form "{name},{time},{amount},{city}".*);
//@ ensures(*Each name and city in the input array `transactions` consists of lowercase English letters and has a length between 1 and 10.*);
//@ ensures(*Each time in the input array `transactions` is a non-negative integer between 0 and 1000.*);
//@ ensures(*Each amount in the input array `transactions` is a non-negative integer between 0 and 2000.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns a list of transactions that are possibly invalid.*);
//@ ensures(*The order of the transactions in the output list can be arbitrary.*);

    public List<String> invalidTransactions(String[] input) {
        List<String> res = new ArrayList<>();
        if (input == null || input.length == 0) {
            return res;
        }
        Map<String, List<Transaction>> map = new HashMap<>();
        for (String s : input) {
            Transaction trans = new Transaction(s);
            if (!map.containsKey(trans.name)) {
                map.put(trans.name, new ArrayList<>());
            }
            map.get(trans.name).add(trans);
        }
        for (String s : input) {
            Transaction trans = new Transaction(s);
            if (!isValid(trans, map)) {
                res.add(s);
            }
        }
        return res;
    }

    private boolean isValid(Transaction transaction, Map<String, List<Transaction>> map) {
        if (transaction.amount > 1000) {
            return false;
        }
        for (Transaction s : map.get(transaction.name)) {
            if (Math.abs(s.time - transaction.time) <= 60 && !s.city.equals(transaction.city)) {
                return false;
            }
        }
        return true;
    }
}