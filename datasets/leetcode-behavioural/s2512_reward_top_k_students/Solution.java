package g2501_2600.s2512_reward_top_k_students;

// #Medium #Array #String #Hash_Table #Sorting #Heap_Priority_Queue
// #2023_03_21_Time_72_ms_(94.76%)_Space_51.1_MB_(21.67%)

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {
    public List<Integer> topStudents(
            String[] positiveFeedback,
            String[] negativeFeedback,
            String[] report,
            int[] studentId,
            int k) {
        HashMap<String, Integer> feedback = new HashMap<>();
        for (String s : positiveFeedback) {
            feedback.put(s, 3);
        }
        for (String s : negativeFeedback) {
            feedback.put(s, -1);
        }
        PriorityQueue<Student> pq =
                new PriorityQueue<>(
                        (a, b) -> {
                            int result = Integer.compare(a.points, b.points);
                            return result == 0 ? Integer.compare(a.id, b.id) : -result;
                        });
        for (int i = 0; i < report.length; i++) {
            String[] split = report[i].split(" ");
            int sum = 0;
            for (String subStr : split) {
                sum += feedback.getOrDefault(subStr, 0);
            }
            pq.offer(new Student(studentId[i], sum));
        }
        List<Integer> result = new ArrayList<>();
        while (!pq.isEmpty() && k-- > 0) {
            result.add(pq.poll().id);
        }
        return result;
    }

    private static class Student {
        int points;
        int id;
//@ ensures(*Preconditions:*);
//@ ensures(*The `positive_feedback` array is not null and contains at least one word.*);
//@ ensures(*The `negative_feedback` array is not null and contains at least one word.*);
//@ ensures(*The `report` array is not null and contains at least one feedback report.*);
//@ ensures(*The `student_id` array is not null and contains at least one student ID.*);
//@ ensures(*The length of `positive_feedback` is less than or equal to 10^4.*);
//@ ensures(*The length of `negative_feedback` is less than or equal to 10^4.*);
//@ ensures(*The length of each word in `positive_feedback` is less than or equal to 100.*);
//@ ensures(*The length of each word in `negative_feedback` is less than or equal to 100.*);
//@ ensures(*The length of `report` is equal to the length of `student_id`.*);
//@ ensures(*The length of `report` is less than or equal to 10^4.*);
//@ ensures(*The length of each feedback report in `report` is less than or equal to 100.*);
//@ ensures(*The values in `student_id` are unique.*);
//@ ensures(*The value of `k` is greater than or equal to 1 and less than or equal to the length of `report`.*);
//@ ensures(**);
//@ ensures(*Postconditions:*);
//@ ensures(*The method returns an array of length `k`.*);
//@ ensures(*The array contains the IDs of the top `k` students after ranking them in non-increasing order by their points.*);
//@ ensures(*If multiple students have the same points, the one with the lower ID ranks higher.*);

        public Student(int id, int points) {
            this.id = id;
            this.points = points;
        }
    }
}