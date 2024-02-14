package g0601_0700.s0630_course_schedule_iii;

// #Hard #Array #Greedy #Heap_Priority_Queue #2022_03_21_Time_53_ms_(58.83%)_Space_69.7_MB_(56.35%)

import java.util.Arrays;
import java.util.PriorityQueue;

@SuppressWarnings("java:S135")
public class Solution {
	//@ requires(*The input array `courses` is not null.*);
	//@ requires(*The length of the input array `courses` is greater than or equal to 1.*);
	//@ requires(*The duration and last day values for each course in the input array `courses` are positive integers.*);
	//@ requires(*The duration and last day values for each course in the input array `courses` are less than or equal to 10^4.*);
	//@ ensures(*The method returns an integer representing the maximum number of courses that can be taken.*);
	//@ ensures(*The courses are taken continuously, without any gaps in between.*);
	//@ ensures(*The courses are taken in the order specified in the input array `courses`.*);
	//@ ensures(*The courses are finished before or on their respective last days.*);
	//@ ensures(*If there are multiple valid schedules that result in the same maximum number of courses, any one of them can be returned.*);
    public int scheduleCourse(int[][] courses) {
        // Sort the courses based on their deadline date.
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // Only the duration is stored. We don't care which course
        // is the longest, we only care about the total courses can
        // be taken.
        // If the question wants the course ids to be returned.
        // Consider use a Pair<Duration, CourseId> int pair.
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        // Total time consumed.
        int time = 0;
        // At the given time `course`, the overall "time limit" is
        // course[1]. All courses in pq is already 'valid'. But
        // adding this course[0] might exceed the course[1] limit.
        for (int[] course : courses) {
            // If adding this course doesn't exceed. Let's add it
            // for now. (Greedy algo). We might remove it later if
            // we have a "better" solution at that time.
            if (time + course[0] <= course[1]) {
                time += course[0];
                pq.offer(course[0]);
            } else {
                // If adding this ecxeeds the limit. We can still add it
                // if-and-only-if there are courses longer than current
                // one. If so, by removing a longer course, current shorter
                // course can fit in for sure. Although the total course
                // count is the same, the overall time consumed is shorter.
                // Which gives us more room for future courses.
                // Remove any course that is longer than current course
                // will work, but we remove the longest one with the help
                // of heap (pq).
                if (!pq.isEmpty() && pq.peek() > course[0]) {
                    time -= pq.poll();
                    time += course[0];
                    pq.offer(course[0]);
                }
                // If no course in consider (pq) is shorter than the
                // current course. It is safe to discard it.
            }
        }
        return pq.size();
    }
}