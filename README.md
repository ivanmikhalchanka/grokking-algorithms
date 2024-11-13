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

<details>

<summary>1. Reversing an array</summary>

---

#### Algorithm:

1. Starting from the first and last element
2. Flip elements and move pointers

---
</details>

<details>
<summary>2. Pair with a given sum in the sorted array</summary>

---

#### Code example:

[pair with a given sum in a sorted array](src/test/java/arrays/TwoPointersPairSum.java).

#### Algorithm:

1. Starting from the first and last element
2. Compare sum of current elements:
    - if sum more than expected - decrement right pointer index
    - if sum less than expected - increment left pointer index

![TwoPointers - sum of tuple.drawio.png](diagrams%2FTwoPointers%20-%20sum%20of%20tuple.drawio.png)

---
</details>

<details>
<summary>3. Triplet with a given sum</summary>

---

#### Code example:

[triplet with a given sum](src/test/java/arrays/TwoPointersTripletSum.java)

#### Algorithm:

1. Sort an array in ascending order
2. Iterating through all elements from the start to the `length - 2`
3. On each iteration:
    1. initialise 2 pointers: start as `i + 1` and end as `lenght - 1`
    2. calculate sum of 3 elements: start, end and current

![TwoPointers - sum of triple.drawio.png](diagrams/TwoPointers%20-%20sum%20of%20triple.drawio.png)

---
</details>

<details>
<summary>4. Given an array with 3 colors group them by color</summary>

---

#### Example:

Colors:

- red - 0
- green - 1
- blue - 2

Input: `[0 1 0 2 1 0 1]`

Output: `[0 0 0 1 1 1 2]`

#### Code example:

[sort colors](src/test/java/arrays/TwoPointersSortColors.java)

#### Algorithm:

1. Initialise 3 pointers:
    - `left`: start of the array, responsible for `0`
    - `current`: start of the array, responsible for `1`
    - `right`: end of the array, responsible for `1`
2. On each step check `current` element:
    - if it is `1` - increment `current` index
    - if it is `0` - swap `current` and `left` and increment **both `current` and `left`**
    - if it is `2` - swap `current` and `right` and decrement **only** `right`
3. Do this until `current` will reach `right`

![TP-sort-colors.png](diagrams/TP-sort-colors.png)

---
</details>

#### Strings:

<details>
<summary>1. Detecting a valid palindrome</summary>

---

#### Code example:

[detecting a palindrome](src/test/java/strings/TwoPointersPalindrome.java).

#### Algorithm:

1. starting from start and end
2. on each steps checking if letters match
3. if pointers reached same index - then string is a palindrome

---
</details>

#### Linked lists

<details>
<summary>1. Remove N-th node from the end of the linked list</summary>

---

#### Code example:

[remove n-th node from the end of linked list](src/test/java/linkedlist/TwoPointersRemoveNthsNode.java)

#### Algorithm:

1. Init 2 pointers at the beginning of the list: left and right
2. Move right pointer to `n` positions
3. Move both pointers to till right hits the end of the list
4. Relink `next` element of the left pointer

![TP-remove-nth-node.drawio.png](diagrams/TP-remove-nth-node.drawio.png)

---
</details>

## References:

- [Manning: Grokking algorithms](https://www.manning.com/books/grokking-algorithms)
- [Educative: Grokking the Coding Interview Patterns](https://www.educative.io/courses/grokking-coding-interview)
