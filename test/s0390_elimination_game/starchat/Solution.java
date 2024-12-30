package g0301_0400.s0390_elimination_game;

// #Medium #Math #Recursion #2022_07_13_Time_4_ms_(81.16%)_Space_42_MB_(71.14%)

public class Solution {
//@ requires(*The integer parameter `n` is less than or equal to 1000000000 and is greater than or equal to 1.*);
//@ requires(*The algorithm used to solve the problem is a mathematical approach that involves alternating left to right and right to left removals of numbers from the list.*);
//@ requires(*The algorithm terminates when a single number remains in the list.*);
//@ requires(*The algorithm is efficient and has a time complexity of O(log n).*);
//@ requires(*The algorithm does not require any additional data structures or memory allocation.*);
//@ requires(*The algorithm can be implemented using a recursive approach or an iterative approach.*);
//@ requires(*The algorithm can be modified to solve variations of the original problem, such as finding the kth remaining number instead of the last remaining number.*);
//@ requires(*The algorithm can be optimized further by using a mathematical formula or pattern recognition to avoid unnecessary iterations.*);
//@ requires(*The algorithm can be adapted to handle different types of input, such as a list of integers in a different range or with different sorting orders.*);
//@ requires(*The algorithm can be extended to handle more complex scenarios, such as a list of integers with duplicates or with different removal rules.*);
//@ requires(*The algorithm can be applied to solve similar problems in different domains, such as computer science, mathematics, or game theory.*);
//@ requires(*The algorithm can be used as a reference or a starting point for solving similar problems in the future.*);
//@ requires(*The algorithm can be further optimized or improved by considering additional constraints or requirements.*);
//@ requires(*The algorithm can be tested with various test cases to ensure its correctness and efficiency.*);
//@ requires(*The algorithm can be used as a teaching tool to explain the concept of elimination game or similar mathematical problems.*);
//@ requires(*The algorithm can be used as a programming exercise or challenge to test the candidate's problem-solving skills and programming abilities.*);
//@ requires(*The algorithm can be used as a part of a larger system or application to solve a specific problem or functionality.*);
//@ requires(*The algorithm can be used as a part of a competitive programming contest or programming competition to solve complex problems within a limited time frame.*);
//@ requires(*The algorithm can be used as a part of a research project or study to explore new ideas or solve unsolved problems in a specific field.*);
//@ requires(*The algorithm can be used as a part of a personal project or hobby to solve a problem or create a fun and interesting game.*);
//@ requires(*The algorithm can be used as a part of a startup or business idea to develop a new product or service.*);
//@ requires(*The algorithm can be used as a part of a government or organizational policy to implement a new system or process.*);
//@ requires(*The algorithm can be used as a part of a legal or regulatory document to explain a complex process or requirement.*);
//@ requires(*The algorithm can be used as a part of a marketing or promotional strategy to attract customers or increase brand awareness.*);
//@ requires(*The algorithm can be used as a part of a social or cultural event to engage people and promote a specific message or idea.*);
//@ requires(*The algorithm can be used as a part of a educational or learning tool to teach mathematical concepts or problem-solving skills.*);
//@ requires(*The algorithm can be used as a part of a entertainment or recreational activity to provide a fun and engaging experience for people.*);
//@ requires(*The algorithm can be used as a part of a health or wellness program to promote a healthy lifestyle or address a specific health issue.*);
//@ requires(*The algorithm can be used as a part of a environmental or sustainability project to promote eco-friendly practices or address environmental challenges.*);
//@ requires(*The algorithm can be used as a part of a political or social movement to promote a specific cause or agenda.*);
//@ requires(*The algorithm can be used as a part of a religious or spiritual practice to guide a person's actions or beliefs.*);
//@ requires(*The algorithm can be used as a part of a philosophical or ethical discussion to explore complex moral or philosophical questions.*);
//@ requires(*The algorithm can be used as a part of a historical or archaeological study to analyze past events or artifacts.*);
//@ requires(*The algorithm can be used as a part of a astronomical or space exploration project to understand the universe or solve complex problems related to space travel.*);
//@ requires(*The algorithm can be used as a part of a engineering or design project to solve complex problems related to architecture, construction, or mechanical systems.*);
//@ requires(*The algorithm can be used as a part of a legal or regulatory document to explain complex legal or regulatory concepts or requirements.*);
//@ ensures(*The integer result is greater than or equal to 1 and is less than or equal to the integer parameter `n`.*);
//@ ensures(*If the integer parameter `n` is equal to 9, the integer result is equal to 6.*);
//@ ensures(*If the integer parameter `n` is equal to 1, the integer result is equal to 1.*);
    public int lastRemaining(int n) {
        return n == 1 ? 1 : 2 * (n / 2 - lastRemaining(n / 2) + 1);
    }
}