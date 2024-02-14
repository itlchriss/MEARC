package g2701_2800.s2739_total_distance_traveled;

// #Easy #Math #Simulation #2023_09_23_Time_4_ms_(100.00%)_Space_43.3_MB_(10.42%)

public class Solution {
	//@ requires(*The values of `mainTank` and `additionalTank` must be integers.*);
	//@ requires(*The values of `mainTank` and `additionalTank` must be between 1 and 100 (inclusive).*);
	//@ ensures(*The method returns an integer representing the maximum distance that can be traveled.*);
	//@ ensures(*The distance traveled is calculated based on the mileage of 10 km per liter.*);
	//@ ensures(*Whenever 5 liters of fuel are used up in the main tank, if the additional tank has at least 1 liter of fuel, 1 liter of fuel will be transferred from the additional tank to the main tank.*);
	//@ ensures(*The injection of fuel from the additional tank is not continuous. It happens suddenly and immediately for every 5 liters consumed.*);
	//@ ensures(*After spending 5 liters of fuel, the remaining fuel in the main tank is reduced by 5 liters and increased by 1 liter (if the additional tank has at least 1 liter of fuel).*);
	//@ ensures(*The main tank becomes empty when there is no fuel left in it.*);
	//@ ensures(*The total distance traveled is the sum of the distances traveled after each fuel consumption.*);
    public int distanceTraveled(int mainTank, int additionalTank) {
        int transferableTimes = (mainTank - 1) / 4;
        int transferredLiters = Math.min(transferableTimes, additionalTank);
        return (mainTank + transferredLiters) * 10;
    }
}