package g1301_1400.s1386_cinema_seat_allocation;

// #Medium #Array #Hash_Table #Greedy #Bit_Manipulation
// #2022_03_21_Time_32_ms_(69.41%)_Space_68.7_MB_(68.13%)

import java.util.HashMap;
import java.util.Map;

public class Solution {
	//@ requires(*The input `n` is a positive integer representing the number of rows in the cinema.*);
	//@ requires(*The input `reservedSeats` is a non-empty array of arrays, where each inner array contains two positive integers representing the row and seat number of a reserved seat.*);
	//@ requires(*The reserved seats in `reservedSeats` are distinct, meaning no two inner arrays have the same values.*);
	//@ requires(*The row numbers in `reservedSeats` are between 1 and `n`, inclusive.*);
	//@ requires(*The seat numbers in `reservedSeats` are between 1 and 10, inclusive.*);
	//@ requires(*The length of `reservedSeats` is less than or equal to the minimum of 10 times `n` and 10,000.*);
	//@ ensures(*The method returns an integer representing the maximum number of four-person groups that can be assigned on the cinema seats.*);
	//@ ensures(*A four-person group occupies four adjacent seats in one single row.*);
	//@ ensures(*Seats across an aisle are not considered to be adjacent.*);
	//@ ensures(*If an aisle splits a four-person group in the middle, there will be two people on each side of the aisle.*);
	//@ ensures(*The returned maximum number of four-person groups is based on the given `reservedSeats` and the available seats in the cinema.*);
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, int[]> occupiedFamilySeats = new HashMap<>();
        for (int[] reservedSeat : reservedSeats) {
            int row = reservedSeat[0];
            int col = reservedSeat[1];
            if (col == 1 || col == 10) {
                continue;
            }
            int[] rowFamilySeats = occupiedFamilySeats.getOrDefault(row, new int[3]);
            if (col == 2 || col == 3) {
                // mark left family seating as occupied
                rowFamilySeats[0] = 1;
                occupiedFamilySeats.put(row, rowFamilySeats);
            }
            if (col == 8 || col == 9) {
                // mark right family seating as occupied
                rowFamilySeats[2] = 1;
                occupiedFamilySeats.put(row, rowFamilySeats);
            }
            if (col == 4 || col == 5) {
                // mark left family seating as occupied
                rowFamilySeats[0] = 1;
                // mark min family seating as occupied
                rowFamilySeats[1] = 1;
                occupiedFamilySeats.put(row, rowFamilySeats);
            }
            if (col == 6 || col == 7) {
                // mark min family seating as occupied
                rowFamilySeats[1] = 1;
                // mark right family seating as occupied
                rowFamilySeats[2] = 1;
                occupiedFamilySeats.put(row, rowFamilySeats);
            }
        }
        // max number of family seats per row is 2, so we start that minus the rows for which we
        // have reservations
        int count = n * 2 - 2 * occupiedFamilySeats.size();
        // for each row with reservations, count remaining family seatings
        for (int[] familySeats : occupiedFamilySeats.values()) {
            if (familySeats[0] == 0) {
                count++;
            }
            if (familySeats[2] == 0) {
                count++;
            }
            if (familySeats[0] != 0 && familySeats[2] != 0 && familySeats[1] == 0) {
                count++;
            }
        }
        return count;
    }
}