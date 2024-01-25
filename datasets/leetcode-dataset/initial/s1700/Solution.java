package g1601_1700.s1700_number_of_students_unable_to_eat_lunch;

// #Easy #Array #Stack #Simulation #Queue #2022_04_11_Time_2_ms_(63.14%)_Space_42.2_MB_(48.76%)

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class Solution {
//@ ensures(*- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1]. - Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1]. - Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0]. - Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
You are given two integer arrays `students` and `sandwiches` where `sandwiches[i]` is the type of the <code>i<sup>th</sup></code> sandwich in the stack (`i = 0` is the top of the stack) and `students[j]` is the preference of the <code>j<sup>th</sup></code> student in the initial queue (`j = 0` is the front of the queue). Return _the number of students that are unable to eat._*);

    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> studentsQueue = new LinkedList<>();
        Queue<Integer> sandwichesQueue = new LinkedList<>();
        for (int i = 0; i < sandwiches.length; i++) {
            studentsQueue.add(students[i]);
            sandwichesQueue.add(sandwiches[i]);
        }
        do {
            if (!studentsQueue.isEmpty()) {
                if (Objects.equals(studentsQueue.peek(), sandwichesQueue.peek())) {
                    studentsQueue.poll();
                    sandwichesQueue.poll();
                } else {
                    if (!studentsQueue.contains(sandwichesQueue.peek())) {
                        break;
                    }
                    studentsQueue.add(studentsQueue.poll());
                }
            }
        } while (!studentsQueue.isEmpty());
        return studentsQueue.size();
    }
}