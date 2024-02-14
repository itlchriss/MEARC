package g0401_0500.s0478_generate_random_point_in_a_circle;

// #Medium #Math #Geometry #Randomized #Rejection_Sampling
// #2022_07_20_Time_342_ms_(56.21%)_Space_128_MB_(45.56%)

import java.util.Random;

@SuppressWarnings("java:S2245")
public class Solution {
    private final double radius;
    private final double xCenter;
    private final double yCenter;
    private final Random random = new Random();
	//@ requires(*The radius must be greater than 0 and less than or equal to 10^8.*);
	//@ requires(*The x_center and y_center coordinates must be within the range -10^7 to 10^7.*);
	//@ ensures(*The Solution object is initialized with the given radius and center coordinates.*);
	//@ ensures(*The randPoint() method returns a random point inside the circle as an array [x, y].*);
	//@ ensures(*The randPoint() method can be called multiple times, up to a maximum of 3 * 10^4 times.*);

    public Solution(double radius, double xCenter, double yCenter) {
        this.radius = radius;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    public double[] randPoint() {
        double x = getCoordinate(xCenter);
        double y = getCoordinate(yCenter);
        while (getDistance(x, y) >= radius * radius) {
            x = getCoordinate(xCenter);
            y = getCoordinate(yCenter);
        }
        return new double[] {x, y};
    }

    private double getDistance(double x, double y) {
        return (xCenter - x) * (xCenter - x) + (yCenter - y) * (yCenter - y);
    }

    private double getCoordinate(double center) {
        return center - radius + random.nextDouble() * 2 * radius;
    }
}

/*
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(radius, x_center, y_center);
 * double[] param_1 = obj.randPoint();
 */