package g0201_0300.s0223_rectangle_area;

// #Medium #Math #Geometry #2022_07_04_Time_4_ms_(65.35%)_Space_43.4_MB_(31.18%)

@SuppressWarnings("java:S107")
public class Solution {
	//@ requires(*The method should take eight integer parameters: ax1, ay1, ax2, ay2, bx1, by1, bx2, by2.*);
	//@ requires(*The values of ax1, ay1, ax2, ay2, bx1, by1, bx2, by2 should be within the range -10^4 to 10^4.*);
	//@ ensures(*The method should return an integer value representing the total area covered by the two rectangles.*);
	//@ ensures(*The returned area should be calculated by summing the areas of the two rectangles and subtracting the overlapping area.*);
	//@ ensures(*The area of a rectangle can be calculated by multiplying the length (difference between x-coordinates) by the width (difference between y-coordinates).*);
	//@ ensures(*The overlapping area can be calculated by finding the intersection of the two rectangles and calculating its area.*);
	//@ ensures(*The intersection of the two rectangles can be found by determining the maximum of the minimum x-coordinate and the minimum of the maximum x-coordinate, and the maximum of the minimum y-coordinate and the minimum of the maximum y-coordinate.*);
	//@ ensures(*If the two rectangles do not overlap, the overlapping area should be 0.*);
	//@ ensures(*The method should handle cases where one rectangle is completely inside the other rectangle. In such cases, the overlapping area should be equal to the area of the smaller rectangle.*);
    public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
        long left = Math.max(ax1, bx1);
        long right = Math.min(ax2, bx2);
        long top = Math.min(ay2, by2);
        long bottom = Math.max(ay1, by1);
        long area = (right - left) * (top - bottom);
        // if not overlaping, either of these two will be non-posittive
        // if right - left = 0, are will automtically be 0 as well
        if (right - left < 0 || top - bottom < 0) {
            area = 0;
        }
        return (int) ((ax2 - ax1) * (ay2 - ay1) + (bx2 - bx1) * (by2 - by1) - area);
    }
}