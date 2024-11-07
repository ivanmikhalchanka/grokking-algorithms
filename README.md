# Grokking algorithms

## Motivation:

Personal sandbox project for studying and practicing algorithms needed for
solving [Leetcode.com](https://leetcode.com) tasks.

## Algorithms

### Two Pointers

The two pointers pattern allows efficiently traverse linear data structures, such as array or
string. Pointers are typically starts from the beginning and from the end and then dynamically
adjust based on some condition or criteria.

Use cases:

- [detecting a palindrome](src/test/java/strings/TwoPointers.java):
    - starting from start and end
    - on each steps checking if letters match
    - if pointers reached same index - then string is a palindrome
- [pair with a given sum in a sorted array](src/test/java/arrays/TwoPointers.java):
    - starting from start and end
    - on each step check if sum of pointers match required result
    - if sum is greater - decrement ending pointer
    - if sum is smaller - increment starting pointer
- reversing an array: flip elements and move pointers

## References:

- [Manning: Grokking algorithms](https://www.manning.com/books/grokking-algorithms)
- [Educative: Grokking the Coding Interview Patterns](https://www.educative.io/courses/grokking-coding-interview)
