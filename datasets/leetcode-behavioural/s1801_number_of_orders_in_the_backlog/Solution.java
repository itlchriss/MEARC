package g1801_1900.s1801_number_of_orders_in_the_backlog;

// #Medium #Array #Heap_Priority_Queue #Simulation
// #2022_05_02_Time_39_ms_(78.46%)_Space_95.1_MB_(47.69%)

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    private static class Order {
        int price;
        int qty;

        Order(int price, int qty) {
            this.price = price;
            this.qty = qty;
        }
    }
//@ ensures(*Preconditions:*);
//@ ensures(*The input `orders` is a 2D integer array.*);
//@ ensures(*Each element `orders[i]` is an array of length 3, representing a batch of orders.*);
//@ ensures(*The first element of `orders[i]` is the price of the orders.*);
//@ ensures(*The second element of `orders[i]` is the amount of orders.*);
//@ ensures(*The third element of `orders[i]` is the order type, which is either 0 or 1.*);
//@ ensures(*The orders in `orders` are sorted in ascending order based on the price.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an integer representing the total amount of orders in the backlog after placing all the orders from the input.*);
//@ ensures(*The returned value is modulo 10^9 + 7.*);

    public int getNumberOfBacklogOrders(int[][] orders) {
        PriorityQueue<Order> sell = new PriorityQueue<>(Comparator.comparingInt(a -> a.price));
        PriorityQueue<Order> buy = new PriorityQueue<>((a, b) -> b.price - a.price);
        for (int[] order : orders) {
            int price = order[0];
            int amount = order[1];
            int type = order[2];
            if (type == 0) {
                while (!sell.isEmpty() && sell.peek().price <= price && amount > 0) {
                    Order ord = sell.peek();
                    int toRemove = Math.min(amount, ord.qty);
                    ord.qty -= toRemove;
                    amount -= toRemove;
                    if (ord.qty == 0) {
                        sell.poll();
                    }
                }
                if (amount > 0) {
                    buy.add(new Order(price, amount));
                }
            } else {
                while (!buy.isEmpty() && buy.peek().price >= price && amount > 0) {
                    Order ord = buy.peek();
                    int toRemove = Math.min(amount, ord.qty);
                    ord.qty -= toRemove;
                    amount -= toRemove;
                    if (ord.qty == 0) {
                        buy.poll();
                    }
                }
                if (amount > 0) {
                    sell.add(new Order(price, amount));
                }
            }
        }
        long sellCount = 0;
        for (Order ord : sell) {
            sellCount += ord.qty;
        }
        long buyCount = 0;
        for (Order ord : buy) {
            buyCount += ord.qty;
        }
        long total = sellCount + buyCount;
        return (int) (total % 1000000007L);
    }
}