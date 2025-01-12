import sys
import openai
import time
import re
import os
import random
from text_generation import Client

NLP = '/Users/chrissleong/Documents/Phd_Studies/NLP/ccg2lambda'
PYCMD = '/Users/chrissleong/Documents/Phd_Studies/venv/py3/bin/python'
KEY = '/Users/chrissleong/Documents/Phd_Studies/openai.key'

PRPS = ['I', 'she', 'he', 'it', 'you', 'we', 'they', 'them', 'him', 'her']


def normalisation(sentence: str) -> str:
    if sentence[-1] != '.':
        sentence += '.'
    return sentence + '\n'

def get_key() -> str:
    key = None
    with open(KEY, 'r') as fp:
        key = fp.read()
    return key


def send_prompt(prompt: str) -> str:
    s = None
    while True:
        try:
            print('Sending prompt....')
            response = openai.ChatCompletion.create(
                            # model="gpt-3.5-turbo",
                            model="gpt-4o",
                            messages=[
                                {"role": "user", "content": prompt}
                            ],
                            temperature=0
                        )
            s = response.choices[0].message.content
            print('Message received...')
        except openai.error.APIError:
            print('API Error. Retry...')
            continue
        except openai.error.ServiceUnavailableError:
            print('Server overloaded...Retry after 120 seconds...')
            time.sleep(120)
            continue
        else:
            break
    return s


def starchat_send_prompt(prompt: str) -> str:
    API_TOKEN = ""
    with open('../starchat.key', 'r') as fp:
        API_TOKEN = fp.read()
    ENDPOINT = "https://api-inference.huggingface.co/models/HuggingFaceH4/starchat-beta"

    model2endpoint = {
        "starchat-alpha": "https://api-inference.huggingface.co/models/HuggingFaceH4/starcoderbase-finetuned-oasst1",
        "starchat-beta": "https://api-inference.huggingface.co/models/HuggingFaceH4/starchat-beta",
        "starchat-2": "https://api-inference.huggingface.co/models/HuggingFaceH4/starchat2-15b-v0.1"
    }

    client = Client(
        model2endpoint["starchat-2"],
        headers={"Authorization": f"Bearer {API_TOKEN}"},
        timeout=1000,
    )

    generate_kwargs = dict(
        temperature=0.001,
        max_new_tokens=1024,
        top_p=0.95,
        repetition_penalty=1.0,
        do_sample=True,
        truncate=4096,
        seed=random.randint(0, 1000000),
        stop_sequences=["<|end|>", "Example"],
    )

    stream = client.generate_stream(prompt, **generate_kwargs)

    output = ""

    for idx, response in enumerate(stream):
        if response.token.special:
            continue
        output += response.token.text

    return output



def main(filename, mode = None):
    openai.api_key = get_key().strip()


    sentence = None
    with open(filename, 'r') as fp:
        sentence = fp.read()

    if not sentence.strip():
        print('Warning: %s is empty...Nothing to be done in phase 1.....' % filename)
        exit(-1)

    _arr = filename.split('/')
    folder = '/'.join(_arr[:-1])
    index = _arr[-1].split('.')[0]

    signature = None
    with open('%s/methodsignature' % folder, 'r') as fp:
        signature = fp.read()
    signature = signature.strip()
    if not signature:
        print('error in getting signature of ' % folder)
        exit(-1)


    # prompt = 'Please extract all possible behavioural requirements (preconditions and postconditions) such that they clearly apply to the Java method %s from the given requirements: "%s" ' % (signature, sentence)
    prompt = """

    You act as a software specification analyst that rewrites the given software specification into behavioural specifications.
Each method behavioural specification must refer to either the prerequisites of the parameters or the result after executing the method specified with the method signature. 
Each method behavioural specification must not contain implementation detail.
Each method behavioural specification must explicitly specify the data types and parameter names if parameters are referred, and the parameter names must be surrounded by quotes(``) and these parameter names should be referred from the method signature. 
Each method behavioural specification must explicitly specify the data type and use the term 'result' as subject if result is referred, and the data type of the result should be referred from the method signature.
Each method behavioural specification must clearly apply to the context of the method signature to explicitly recognising the parameters used in the method signature. 
The syntax of each method behavioural specification must be strictly adhere to the syntax of the method behavioural specifications in the given examples.
Do not provide information that are related to the implementation of methods.
Do not use parentheses in the method behavioural specifications.
Do not enumerate behavioural specifications that are not listed in the given software specification.


Examples:
Example #1:
Software specification: Given an integer number, return true if the number is even.
Method signature: public static boolean isEven(int number)
Method behavioural specifications:
- If boolean result is equal to the true literal, the integer parameter `number` mod 2 is equal to 0.
- If boolean result is equal to the false literal, the integer parameter `number` mod 2 is equal to 1.
- The integer parameter `number` is less than or equal to the minimum value of java integer and is greater than or equal to the maximum value of java integer.

Example #2:
Software specification: Given two integer numbers, return the sum of these two numbers. Both numbers must be within 0 and 1000.
Method signature: public static int Sum(int num1, int num2)
Method behavioural specifications:
- The integer parameter `num1` is greater than or equal to 0 and is greater than or equal to 0.
- The integer parameter `num2` is less than or equal to 1000 and is greater than or equal to 0.
- The integer result is less than or equal to 2000 and the integer result is greater than or equal to 0.
- The integer result is equal to the sum between the integer parameter `num1` and the integer parameter `num2`.


Example #3
Software specification:  Peter wants to generate some prime numbers for his cryptosystem. Help him! Your task is to generate all prime numbers between two given numbers!
There are two numbers m and n (1 <= m <= n <= 1000000000, n-m<=100000).
For each prime number p such that m <= p <= n.
Find all prime numbers.
Method signature: public static int[] GetPrime(int m, int n)
Method behavioural specifications:
- The integer parameter `m` is greater than or equal to 1 and is less than or equal to 1000000000.
- The integer parameter `n` is greater than or equal to 1 and is less than or equal to 1000000000.
- The integer parameter `m` is less than or equal to the integer parameter `n`.
- The integer parameter `n` minus the integer parameter `m` is less than or equal to 100000.
- All values in the integer array result are prime numbers.
- All values in the integer array result are greater than or equal to the integer parameter `m` and are less than or equal to the integer parameter `n`.

Example #4
Software specification: Let A = [a1, a2, ... an] be a permutation of integers 1, 2, ... n. A pair of indices (i, j), 1 <= i <= j <= n, is an inversion of the permutation A if ai > aj. We are given integers n > 0 and k >= 0. What is the number of n-element permutations containing exactly k inversions?
For instance, the number of 4-element permutations with exactly 1 inversion equals 3.
Write a program which for each data set from a sequence of several data sets:
    reads integers n and k from input,
    computes the number of n-element permutations with exactly k inversions,
    writes the result to output.
1 <= n <= 12 and 0 <= k <= 98
Method signature: public static int Permutation(int n, int k)
Method behavioural specifications:
- The integer parameter `k` is greater than or equal to 0 and is less than or equal to 98.
- The integer parameter `n` is greater than or equal to 1 and is less than or equal to 12.
- The integer result is less than or equal to the maximum value of java integer and is greater than or equal to the minimum value of java integer.
- If the integer parameter `n` is equal to 4 and the integer parameter `k` is equal to 1, the integer result is equal to 3.
- The integer parameter `k` is less than or equal to the quotient between 2 and the product between the integer parameter `n` and the difference between the integer parameter `n` and 1.

Example #5
Software specification: Given an array A of length N. Find an ordered pair which satisfy the given condition
    (A[i]-A[j])/(i-j) = 1
    A[i]-A[j] should be divisible by i-j
    i != j
constraints
1 <= N <= 5 * 10^5
1 <= A[i] <= 10^6
Method signature: public static int[] FindOrderedPairs(int[] A, int N)
Method behavioural specifications:
- The integer parameter `N` is greater than or equal to 1 and is less than or equal to 500000.
- All values in the integer array parameter `A` are greater than or equal to 1 and are less than or equal to 1000000.
- The length of the integer array result is equal to 2.
- All values in the integer array result are unique.

Example #6
Software specification: 1051\. Height Checker
Easy
A school is trying to take an annual photo of all the students. The students are asked to stand in a single file line in **non-decreasing order** by height. Let this ordering be represented by the integer array `expected` where `expected[i]` is the expected height of the <code>i<sup>th</sup></code> student in line.
You are given an integer array `heights` representing the **current order** that the students are standing in. Each `heights[i]` is the height of the <code>i<sup>th</sup></code> student in line (**0-indexed**).
Return _the **number of indices** where_ `heights[i] != expected[i]`.
**Example 1:**
**Input:** heights = [1,1,4,2,1,3]
**Output:** 3
**Explanation:**
heights: [1,1,4,2,1,3]
expected: [1,1,1,2,3,4]
Indices 2, 4, and 5 do not match.
**Example 2:**
**Input:** heights = [5,1,2,3,4]
**Output:** 5
**Explanation:**
heights: [5,1,2,3,4]
expected: [1,2,3,4,5]
All indices do not match.
**Example 3:**
**Input:** heights = [1,2,3,4,5]
**Output:** 0
**Explanation:**
heights: [1,2,3,4,5]
expected: [1,2,3,4,5]
All indices match.
**Constraints:**
*   `1 <= heights.length <= 100`
*   `1 <= heights[i] <= 100`
Method signature: public int heightChecker(int[] heights)
Method behavioural specifications:
- The length of the integer array parameter `heights` is less than or equal to 100 and is greater than or equal to 1.
- All the values in the integer array parameter `heights` are less than or equal to 100 and are greater than or equal to 1.
- The integer result is less than or equal to the length of the integer array parameter `heights`.
- If the integer result is greater than or equal to 1, the integer array parameter `heights` is not sorted in ascending order.


Example #7
Software specification: 1317\. Convert Integer to the Sum of Two No-Zero Integers
Easy
**No-Zero integer** is a positive integer that **does not contain any `0`** in its decimal representation.
Given an integer `n`, return _a list of two integers_ `[A, B]` _where_:
*   `A` and `B` are **No-Zero integers**.
*   `A + B = n`
The test cases are generated so that there is at least one valid solution. If there are many valid solutions you can return any of them.
**Example 1:**
**Input:** n = 2
**Output:** [1,1]
**Explanation:** A = 1, B = 1. A + B = n and both A and B do not contain any 0 in their decimal representation.
**Example 2:**
**Input:** n = 11
**Output:** [2,9]
**Constraints:**
*   <code>2 <= n <= 10<sup>4</sup></code>
Method signature: public int[] getNoZeroIntegers(int n)
Method behavioural specifications: 
- The integer parameter `n` is less than or equal to 10000 and is greater than or equal to 2.
- The sum between the first element of the integer array result and the second element of the integer array result is equal to the integer parameter `n`.
- All the values in the integer array result do not contain 0.
- All the values in the integer array result is greater than or equal to -10000 and is less than or equal to 10000.
- The length of the integer array result is equal to 2.


Example #8
Software specification: 1331\. Rank Transform of an Array
Easy
Given an array of integers `arr`, replace each element with its rank.
The rank represents how large the element is. The rank has the following rules:
*   Rank is an integer starting from 1.
*   The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
*   Rank should be as small as possible.
**Example 1:**
**Input:** arr = [40,10,20,30]
**Output:** [4,1,2,3]
**Explanation:** 40 is the largest element. 10 is the smallest. 20 is the second smallest. 30 is the third smallest.
**Example 2:**
**Input:** arr = [100,100,100]
**Output:** [1,1,1]
**Explanation:** Same elements share the same rank.
**Example 3:**
**Input:** arr = [37,12,28,9,100,56,80,5,12]
**Output:** [5,3,4,2,8,6,7,1,3]
**Constraints:**
*   <code>1 <= arr.length <= 10<sup>5</sup></code>
*   <code>-10<sup>9</sup> <= arr[i] <= 10<sup>9</sup></code>
Method signature: public int[] arrayRankTransform(int[] arr)
Method behavioural specifications: 
- The length of the integer array parameter `arr` is less than or equal to 1 and is greater than or equal to 100000.
- All the values in the integer array parameter `arr` are less than or equal to -1000000000 and are greater than or equal to 1000000000.
- The length of the integer array result is equal to the length of the integer array parameter `arr`.
- All the values in the integer array result is greater than or equal to 1 and is less than or equal to the length of the integer array parameter `arr`.
- If all the values of the integer array result are equal 1, all the values in the integer array parameter `arr` are the same.
- If all the values of the integer array parameter `arr` are unique, all the values in the integer array result are unique.
- If the integer array parameter `arr` is in ascending order, the integer array result is in ascending order.
- If the integer array parameter `arr` is in decending order, the integer array result is in decending order.


Example #9
Software specification: 1903\. Largest Odd Number in String
Easy
You are given a string `num`, representing a large integer. Return _the **largest-valued odd** integer (as a string) that is a **non-empty substring** of_ `num`_, or an empty string_ `""` _if no odd integer exists_.
A **substring** is a contiguous sequence of characters within a string.
**Example 1:**
**Input:** num = "52"
**Output:** "5"
**Explanation:** The only non-empty substrings are "5", "2", and "52". "5" is the only odd number.
**Example 2:**
**Input:** num = "4206"
**Output:** ""
**Explanation:** There are no odd numbers in "4206".
**Example 3:**
**Input:** num = "35427"
**Output:** "35427"
**Explanation:** "35427" is already an odd number.
**Constraints:**
*   <code>1 <= num.length <= 10<sup>5</sup></code>
*   `num` only consists of digits and does not contain any leading zeros.
Method signature: public String largestOddNumber(String num)
Method behavioural specifications: 
- The length of the string parameter `num` is less than or equal to 100000 and is greater than or equal to 1.
- The string parameter `num` only consists of digits.
- The string parameter `num` does not contain leading zeros.
- The integer value of the string result is odd, or the string result is empty.
- If the string parameter `num` is equal to "52", the string result is equal to "5".
- If the string parameter `num` is equal to "4206", the string result is empty.
- If the string parameter `num` is equal to "35427", the string result is equal to "35427".

Example #10
Software specification: 2000\. Reverse Prefix of Word
Easy
Given a **0-indexed** string `word` and a character `ch`, **reverse** the segment of `word` that starts at index `0` and ends at the index of the **first occurrence** of `ch` (**inclusive**). If the character `ch` does not exist in `word`, do nothing.
*   For example, if `word = "abcdefd"` and `ch = "d"`, then you should **reverse** the segment that starts at `0` and ends at `3` (**inclusive**). The resulting string will be `"dcbaefd"`.
Return _the resulting string_.
**Example 1:**
**Input:** word = "abcdefd", ch = "d"
**Output:** "dcbaefd"
**Explanation:** The first occurrence of "d" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "dcbaefd". 
**Example 2:**
**Input:** word = "xyxzxe", ch = "z"
**Output:** "zxyxxe"
**Explanation:** The first and only occurrence of "z" is at index 3.
Reverse the part of word from 0 to 3 (inclusive), the resulting string is "zxyxxe". 
**Example 3:**
**Input:** word = "abcd", ch = "z"
**Output:** "abcd"
**Explanation:** "z" does not exist in word.
You should not do any reverse operation, the resulting string is "abcd". 
**Constraints:**
*   `1 <= word.length <= 250`
*   `word` consists of lowercase English letters.
*   `ch` is a lowercase English letter.
Method signature: public String reversePrefix(String s, char c)
Method behavioural specifications: 
- The length of the string parameter `s` is less than or equal to 250 and is greater than or equal to 1.
- The character parameter `c` is a lowercase English letter.
- The string parameter `s` consists of only lowercase English letters.
- If the string parameter `s` contains the character parameter `c`, the first character of the string result is equal to the character parameter `c`.
- If the string parameter `s` does not contain the character parameter `c`, the first character of the string result is equal to the string parameter `s`.
- If the string parameter `s` is equal to "abcd" and the character parameter `ch` is equal to 'z', the string result is equal to "abcd".
- If the string parameter `s` is equal to "xyxzxe" and the character parameter `ch` is equal to 'z', the string result is equal to "zxyxxe".
- If the string parameter `s` is equal to "abcdefd" and the character parameter `ch` is equal to 'd', the string result is equal to "dcbaefd".


Example #11
Software specification:2981\. Find Longest Special Substring That Occurs Thrice I

Medium
You are given a string `s` that consists of lowercase English letters.
A string is called **special** if it is made up of only a single character. For example, the string `"abc"` is not special, whereas the strings `"ddd"`, `"zz"`, and `"f"` are special.
Return _the length of the **longest special substring** of_ `s` _which occurs **at least thrice**_, _or_ `-1` _if no special substring occurs at least thrice_.
A **substring** is a contiguous **non-empty** sequence of characters within a string.
**Example 1:**
**Input:** s = "aaaa"
**Output:** 2
**Explanation:** The longest special substring which occurs thrice is "aa": substrings "<ins>**aa**</ins>aa", "a<ins>**aa**</ins>a", and "aa<ins>**aa**</ins>".
It can be shown that the maximum length achievable is 2. 
**Example 2:**
**Input:** s = "abcdef"
**Output:** -1
**Explanation:** There exists no special substring which occurs at least thrice. Hence return -1. 
**Example 3:**
**Input:** s = "abcaba"
**Output:** 1
**Explanation:** The longest special substring which occurs thrice is "a": substrings "<ins>**a**</ins>bcaba", "abc<ins>**a**</ins>ba", and "abcab<ins>**a**</ins>".
It can be shown that the maximum length achievable is 1. 
**Constraints:**
*   `3 <= s.length <= 50`
*   `s` consists of only lowercase English letters.
Method signature: public int maximumLength(String data)
Method behavioural specifications: 
- The string parameter `data` consists of only lowercase English letters.
- The length of the string parameter `data` is less than or equal to 50 and is greater than or equal to 3.
- If the integer result is equal to 2 and the length of the string parameter `data` is equal to 4, all the characters in the string parameter `data` are equal to the first character of the string parameter `data`.
- If the string parameter `data` is equal to "abcaba", the integer result is equal to 1.
- If the string parameter `data` is equal to "abcdef", the integer result is equal to -1.
- If the string parameter `data` is equal to "aaaa", the integer result is equal to 2.
- The integer result is greater than or equal to -1.

Example #12
Software specification:2840\. Check if Strings Can be Made Equal With Operations II
Medium
You are given two strings `s1` and `s2`, both of length `n`, consisting of **lowercase** English letters.
You can apply the following operation on **any** of the two strings **any** number of times:
*   Choose any two indices `i` and `j` such that `i < j` and the difference `j - i` is **even**, then **swap** the two characters at those indices in the string.
Return `true` _if you can make the strings_ `s1` _and_ `s2` _equal, and_`false` _otherwise_.
**Example 1:**
**Input:** s1 = "abcdba", s2 = "cabdab"
**Output:** true
**Explanation:** We can apply the following operations on s1: 
- Choose the indices i = 0, j = 2. The resulting string is s1 = "cbadba". 
- Choose the indices i = 2, j = 4. The resulting string is s1 = "cbbdaa". 
- Choose the indices i = 1, j = 5. The resulting string is s1 = "cabdab" = s2.
**Example 2:**
**Input:** s1 = "abe", s2 = "bea"
**Output:** false
**Explanation:** It is not possible to make the two strings equal.
**Constraints:**
*   `n == s1.length == s2.length`
*   <code>1 <= n <= 10<sup>5</sup></code>
*   `s1` and `s2` consist only of lowercase English letters.
Method signature: public boolean checkStrings(String s1, String s2)
Method behavioural specifications: 
- The length of the string parameter `s1` is less than or equal to 100000 and is greater than or equal to 1.
- The length of the string parameter `s2` is less than or equal to 100000 and is greater than or equal to 1.
- The string parameter `s2` consists of only lowercase English letters.
- The string parameter `s1` consists of only lowercase English letters.
- If the string parameter `s1` is equal to "abe" and the string parameter `s2` is equal to "bea", the boolean result is equal to the false literal.
- If the string parameter `s1` is equal to "abcdba" and the string parameter `s2` is equal to "cabdab", the boolean result is equal to the true literal.

Example #13
Software specification:2781\. Length of the Longest Valid Substring
Hard
You are given a string `word` and an array of strings `forbidden`.
A string is called **valid** if none of its substrings are present in `forbidden`.
Return _the length of the **longest valid substring** of the string_ `word`.
A **substring** is a contiguous sequence of characters in a string, possibly empty.
**Example 1:**
**Input:** word = "cbaaaabc", forbidden = ["aaa","cb"]
**Output:** 4
**Explanation:**
There are 9 valid substrings in word: "c", "b", "a", "ba", "aa", "bc", "baa", "aab", "ab", "abc"and "aabc". The length of the longest valid substring is 4.
It can be shown that all other substrings contain either "aaa" or "cb" as a substring. 
**Example 2:**
**Input:** word = "leetcode", forbidden = ["de","le","e"]
**Output:** 4
**Explanation:**
There are 11 valid substrings in word: "l", "t", "c", "o", "d", "tc", "co", "od", "tco", "cod", and "tcod". The length of the longest valid substring is 4.
It can be shown that all other substrings contain either "de", "le", or "e" as a substring. 
**Constraints:**
*   <code>1 <= word.length <= 10<sup>5</sup></code>
*   `word` consists only of lowercase English letters.
*   <code>1 <= forbidden.length <= 10<sup>5</sup></code>
*   `1 <= forbidden[i].length <= 10`
*   `forbidden[i]` consists only of lowercase English letters.
Method signature: public int longestValidSubstring(String word, List<String> forbidden)
Method behavioural specifications: 
- The length of the string parameter `word` is less than or equal to 100000 and is greater than or equal to 1.
- The string parameter `word` consists of only lowercase English letters.
- The length of the list parameter `forbidden` is less than or equal to 100000 and is greater than or equal to 1.
- All strings in the list parameter `forbidden` consist of only lowercase English letters.
- All the lengths of the strings in the list parameter `forbidden` are less than or equal to 10 and are greater than or equal to 1.
- The integer result is greater than or equal to 0.
- If the string parameter `word` is equal to "leetcode" and the list parameter `forbidden` is equal to ["de","le","e"], the integer result is equal to 4.
- If the string parameter `word` is equal to "cbaaaabc" and the list parameter `forbidden` is equal to ["aaa","cb"], the integer result is equal to 4.

Example #14
Software specification:2529\. Maximum Count of Positive Integer and Negative Integer
Easy
Given an array `nums` sorted in **non-decreasing** order, return _the maximum between the number of positive integers and the number of negative integers._
*   In other words, if the number of positive integers in `nums` is `pos` and the number of negative integers is `neg`, then return the maximum of `pos` and `neg`.
**Note** that `0` is neither positive nor negative.
**Example 1:**
**Input:** nums = [-2,-1,-1,1,2,3]
**Output:** 3
**Explanation:** There are 3 positive integers and 3 negative integers. The maximum count among them is 3.
**Example 2:**
**Input:** nums = [-3,-2,-1,0,0,1,2]
**Output:** 3
**Explanation:** There are 2 positive integers and 3 negative integers. The maximum count among them is 3.
**Example 3:**
**Input:** nums = [5,20,66,1314]
**Output:** 4
**Explanation:** There are 4 positive integers and 0 negative integers. The maximum count among them is 4.
**Constraints:**
*   `1 <= nums.length <= 2000`
*   `-2000 <= nums[i] <= 2000`
*   `nums` is sorted in a **non-decreasing order**.
**Follow up:** Can you solve the problem in `O(log(n))` time complexity?
Method signature: public int maximumCount(int[] nums)
Method behavioural specifications:
- The length of the integer array parameter `nums` is less than or equal to 2000 and is greater than or equal to 1.
- All values in the integer array parameter `nums` are less than or equal to 2000 and is greater than or equal to -2000.
- The integer array parameter `nums` is sorted in ascending order.
- If the integer result is less than the length of the integer array parameter `nums`, all the values in the integer array parameter `nums` are not greater than 0 and all the values in the integer array parameter `nums` are not less than 0.
- If the integer result is equal to the length of the integer array parameter `nums` and the first value of the integer array parameter `nums` is greater than 0, all the values in the integer array parameter `nums` are greater than 0.
- If the integer result is equal to the length of the integer array parameter `nums` and the first value of the integer array parameter `nums` is less than 0, all the values in the integer array parameter `nums` are less than 0.
- If the integer array parameter `nums` is equal to [-2,-1,-1,1,2,3], the integer result is equal to 3.
- If the integer array parameter `nums` is equal to [-3,-2,-1,0,0,1,2], the integer result is equal to 3.
- If the integer array parameter `nums` is equal to [5,20,66,1314], the integer result is equal to 4.


Example #15
Software specification:2770\. Maximum Number of Jumps to Reach the Last Index
Medium
You are given a **0-indexed** array `nums` of `n` integers and an integer `target`.
You are initially positioned at index `0`. In one step, you can jump from index `i` to any index `j` such that:
*   `0 <= i < j < n`
*   `-target <= nums[j] - nums[i] <= target`
Return _the **maximum number of jumps** you can make to reach index_ `n - 1`.
If there is no way to reach index `n - 1`, return `-1`.
**Example 1:**
**Input:** nums = [1,3,6,4,1,2], target = 2
**Output:** 3
**Explanation:**
To go from index 0 to index n - 1 with the maximum number of jumps, you can perform the following jumping sequence:
- Jump from index 0 to index 1.
- Jump from index 1 to index 3.
- Jump from index 3 to index 5.
It can be proven that there is no other jumping sequence that goes from 0 to n - 1 with more than 3 jumps.
Hence, the answer is 3. 
**Example 2:**
**Input:** nums = [1,3,6,4,1,2], target = 3
**Output:** 5
**Explanation:**
To go from index 0 to index n - 1 with the maximum number of jumps, you can perform the following jumping sequence:
- Jump from index 0 to index 1.
- Jump from index 1 to index 2.
- Jump from index 2 to index 3.
- Jump from index 3 to index 4.
- Jump from index 4 to index 5.
It can be proven that there is no other jumping sequence that goes from 0 to n - 1 with more than 5 jumps.
Hence, the answer is 5. 
**Example 3:**
**Input:** nums = [1,3,6,4,1,2], target = 0
**Output:** -1
**Explanation:** It can be proven that there is no jumping sequence that goes from 0 to n - 1. Hence, the answer is -1. 
**Constraints:**
*   `2 <= nums.length == n <= 1000`
*   <code>-10<sup>9</sup> <= nums[i] <= 10<sup>9</sup></code>
*   <code>0 <= target <= 2 * 10<sup>9</sup></code>
Method signature: public int maximumJumps(int[] nums, int target)
Method behavioural specifications:
- The length of the integer array parameter `nums` is less than or equal to 1000 and is greater than or equal to 2.
- All values in the integer array parameter `nums` are less than or equal to 1000000000 and is greater than or equal to -1000000000.
- The integer parameter `target` is less than or equal to 2000000000 and is greater than or equal to 0.
- If the integer array parameter `nums` is equal to [1,3,6,4,1,2] and the integer parameter `target` is equal to 0, the integer result is equal to -1.
- If the integer array parameter `nums` is equal to [1,3,6,4,1,2] and the integer parameter `target` is equal to 3, the integer result is equal to 5.
- If the integer array parameter `nums` is equal to [1,3,6,4,1,2] and the integer parameter `target` is equal to 2, the integer result is equal to 3.
- If the integer parameter `target` is equal to 0, the integer result is equal to -1.
- The integer result is less than or equal to the length of the integer array parameter `nums` and is greater than or equal to -1.


Example #16
Software specification:2951\. Find the Peaks
Easy
You are given a **0-indexed** array `mountain`. Your task is to find all the **peaks** in the `mountain` array.
Return _an array that consists of_ indices _of **peaks** in the given array in **any order**._
**Notes:**
*   A **peak** is defined as an element that is **strictly greater** than its neighboring elements.
*   The first and last elements of the array are **not** a peak.
**Example 1:**
**Input:** mountain = [2,4,4]
**Output:** []
**Explanation:** mountain[0] and mountain[2] can not be a peak because they are first and last elements of the array. 
mountain[1] also can not be a peak because it is not strictly greater than mountain[2]. 
So the answer is [].
**Example 2:**
**Input:** mountain = [1,4,3,8,5]
**Output:** [1,3]
**Explanation:** mountain[0] and mountain[4] can not be a peak because they are first and last elements of the array. 
mountain[2] also can not be a peak because it is not strictly greater than mountain[3] and mountain[1]. 
But mountain [1] and mountain[3] are strictly greater than their neighboring elements. So the answer is [1,3].
**Constraints:**
*   `3 <= mountain.length <= 100`
*   `1 <= mountain[i] <= 100`
Method signature: public List<Integer> findPeaks(int[] mountain)
Method behavioural specifications:
- The length of the integer array parameter `mountain` is less than or equal to 100 and is greater than or equal to 3.
- All values in the integer array parameter is less than or equal to 100 and is greater than or equal to 1.
- If the list result is not empty, all values in the list result are the indices of local maximas in the integer array parameter `mountain`.
- The list result does not contain 0 and the length of the integer array parameter minus 1.
- If the integer array parameter `mountain` is equal to [2,4,4], the list result is empty.
- If the integer array parameter `mountain` is equal to [1,4,3,8,5], the list result is equal to [1,3].

Example #17
Software specification:2520\. Count the Digits That Divide a Number
Easy
Given an integer `num`, return _the number of digits in `num` that divide_ `num`.
An integer `val` divides `nums` if `nums %% val == 0`.
**Example 1:**
**Input:** num = 7
**Output:** 1
**Explanation:** 7 divides itself, hence the answer is 1.
**Example 2:**
**Input:** num = 121
**Output:** 2
**Explanation:** 121 is divisible by 1, but not 2. Since 1 occurs twice as a digit, we return 2.
**Example 3:**
**Input:** num = 1248
**Output:** 4
**Explanation:** 1248 is divisible by all of its digits, hence the answer is 4.
**Constraints:**
*   <code>1 <= num <= 10<sup>9</sup></code>
*   `num` does not contain `0` as one of its digits.
Method signature: public int countDigits(int x)
Method behavioural specifications:
- The integer parameter `x` is less than or equal to 1000000000 and is greater than or equal to 1.
- All digits in the integer parameter `x` are not equal to 0.
- If the integer result is not equal to the number of digits in the integer parameter `x`, not all digits in the integer parameter `x` are factors of integer parameter `x`.
- If the integer parameter `x` is equal to 7, the integer result is equal to 1.
- If the integer parameter `x` is equal to 121, the integer result is equal to 2.
- If the integer parameter `x` is equal to 1248, the integer result is equal to 4.

Example #18
Software specification:2414\. Length of the Longest Alphabetical Continuous Substring
Medium
An **alphabetical continuous string** is a string consisting of consecutive letters in the alphabet. In other words, it is any substring of the string `"abcdefghijklmnopqrstuvwxyz"`.
*   For example, `"abc"` is an alphabetical continuous string, while `"acb"` and `"za"` are not.
Given a string `inputstring` consisting of lowercase letters only, return the _length of the **longest** alphabetical continuous substring._
**Example 1:**
**Input:** inputstring = "abacaba"
**Output:** 2
**Explanation:** There are 4 distinct continuous substrings: "a", "b", "c" and "ab".
"ab" is the longest continuous substring. 
**Example 2:**
**Input:** inputstring = "abcde"
**Output:** 5
**Explanation:** "abcde" is the longest continuous substring. 
**Constraints:**
*   <code>1 <= inputstring.length <= 10<sup>5</sup></code>
*   `inputstring` consists of only English lowercase letters.
Method signature: public int longestContinuousSubstring(String x)
Method behavioural specifications:
- The length of the string parameter `x` is less than or equal to 100000 and is greater than or equal to 1.
- The string parameter `x` consists of only English lowercase letters.
- The integer result is greater than or equal to 1 and is less than or equal to the length of the string parameter `x`.
- If the string parameter `x` is equal to "abacaba", the integer result is equal to 2.
- If the string parameter `x` is equal to "abcde", the integer result is equal to 5.


Given the following context,
Software specification: 
%s
Method signature: %s

What are the method behavioural specifications for the given context?

output format: a list with '-' as bullets
"""


    sentence = sentence.replace('<sup>', '^').replace('</sup>', '')
    if not mode:
        s = starchat_send_prompt(prompt % (sentence, signature))

        print('Writing starchat result...')
        if os.path.exists('%s/starchat/rnl.txt' % (folder)):
            os.remove('%s/starchat/rnl.txt' % (folder))
            print('Removing old file...')
        with open('%s/starchat/rnl.txt' % (folder), 'w') as fp:
            fp.write(s)
        
        with open('%s/starchat/rnl.txt' % (folder), 'r') as fp:
            lines = fp.readlines()
            print(lines)
        # s = send_prompt(prompt % (sentence, signature))
        # print('Writing gpt4 result...')
        # with open('%s/gpt4/rnl.txt' % (folder), 'w') as fp:
        #     fp.write(s)
    elif mode.strip() == 's':
        s = starchat_send_prompt(prompt % (sentence, signature))
        # print('Writing starchat result...')
        # with open('%s/rnl-starchat.txt' % (folder), 'w') as fp:
                # fp.write(s)
        print(s)
    elif mode.strip() == 'g':
        s = send_prompt(prompt % (sentence, signature))
        # print('Writing gpt4 result...')
        # with open('%s/rnl-gpt-4o.txt' % (folder), 'w') as fp:
                # fp.write(s)
        print(s)
    else:
        print('Unknown mode...')
   



if __name__ == "__main__":
    if len(sys.argv) == 3:
        main(filename = sys.argv[1], mode = sys.argv[2])
    else:
        main(filename = sys.argv[1])