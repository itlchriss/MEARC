package g0801_0900.s0853_car_fleet;

// #Medium #Array #Sorting #Stack #Monotonic_Stack
// #2022_03_27_Time_118_ms_(74.11%)_Space_88.6_MB_(44.48%)

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Solution {
    private static class Car {
        int position;
        int speed;
    }
	//@ requires(*The input arrays `position` and `speed` must have the same length `n`.*);
	//@ requires(*The length `n` must be greater than or equal to 1.*);
	//@ requires(*The target distance must be greater than 0.*);
	//@ requires(*The position values in the `position` array must be between 0 and the target distance.*);
	//@ requires(*The speed values in the `speed` array must be greater than 0.*);
	//@ ensures(*The method returns an integer representing the number of car fleets that will arrive at the destination.*);
	//@ ensures(*The returned value is greater than or equal to 0.*);
	//@ ensures(*The returned value is less than or equal to the length `n` of the input arrays.*);
	//@ ensures(*The car fleets are determined based on the positions and speeds of the cars.*);
	//@ ensures(*A car fleet is a non-empty set of cars driving at the same position and same speed.*);
	//@ ensures(*If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.*);

    public int carFleet(int target, int[] position, int[] speed) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < position.length; i++) {
            Car c = new Car();
            c.position = position[i];
            c.speed = speed[i];
            cars.add(c);
        }
        cars.sort(Comparator.comparingInt(c -> c.position));
        int numFleets = 1;
        float lastTime =
                ((float) (target - cars.get(cars.size() - 1).position))
                        / (cars.get(cars.size() - 1).speed);
        for (int i = cars.size() - 2; i >= 0; i--) {
            float timeToTarget = ((float) (target - cars.get(i).position)) / (cars.get(i).speed);
            if (timeToTarget > lastTime) {
                numFleets++;
                lastTime = timeToTarget;
            }
        }
        return numFleets;
    }
}