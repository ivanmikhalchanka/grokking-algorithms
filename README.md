# Grokking algorithms

## Motivation:

Personal sandbox project for studying and practicing algorithms needed for
solving [Leetcode.com](https://leetcode.com) tasks.

## Algorithms

### Two Pointers

The two pointers pattern allows efficiently traverse linear data structures, such as array or
string. Pointers are typically starts from the beginning and from the end and then dynamically
adjust based on some condition or criteria.

#### Arrays:

##### 1. Reversing an array

1. Starting from the first and last element
2. Flip elements and move pointers

##### 2. Pair with a given sum in the sorted array

Code
example: [pair with a given sum in a sorted array](src/test/java/arrays/TwoPointersPairSum.java).

1. Starting from the first and last element
2. Compare sum of current elements:
    - if sum more than expected - decrement right pointer index
    - if sum less than expected - increment left pointer index

![TwoPointers - sum of tuple.drawio.png](diagrams%2FTwoPointers%20-%20sum%20of%20tuple.drawio.png)

##### 3. Triplet with a given sum

Code example: [triplet with a given sum](src/test/java/arrays/TwoPointersTripletSum.java)

1. Sort an array in ascending order
2. Iterating through all elements from the start to the `length - 2`
3. On each iteration:
    1. initialise 2 pointers: start as `i + 1` and end as `lenght - 1`
    2. calculate sum of 3 elements: start, end and current

![TwoPointers - sum of triple.drawio.png](diagrams/TwoPointers%20-%20sum%20of%20triple.drawio.png)

#### Strings:

##### 1. Detecting a valid palindrome

Code example: [detecting a palindrome](src/test/java/strings/TwoPointersPalindrome.java).

1. starting from start and end
2. on each steps checking if letters match
3. if pointers reached same index - then string is a palindrome

## References:

- [Manning: Grokking algorithms](https://www.manning.com/books/grokking-algorithms)
- [Educative: Grokking the Coding Interview Patterns](https://www.educative.io/courses/grokking-coding-interview)
