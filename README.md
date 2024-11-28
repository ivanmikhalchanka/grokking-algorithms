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

[pair with a given sum in a sorted array](src/test/java/arrays/twopointers/TwoPointersPairSum.java).

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

[triplet with a given sum](src/test/java/arrays/twopointers/TwoPointersTripletSum.java)

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

[sort colors](src/test/java/arrays/twopointers/TwoPointersSortColors.java)

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

[detecting a palindrome](src/test/java/strings/twopointers/TwoPointersPalindrome.java).

#### Algorithm:

1. starting from start and end
2. on each steps checking if letters match
3. if pointers reached same index - then string is a palindrome

---
</details>

<details>
<summary>2. Reverse words in the string</summary>

---

#### Example:

- Given: `Red Big Elf Hat`
- Expected result: `Hat Elf Big Red`

#### Code example:

[reverse words](src/test/java/strings/twopointers/TwoPointersReverseWords.java)

#### Algorithm:

1. Reverse a string using two pointers:
    1. initialise `start` pointer at the start of the string
    2. initialise `end` pointer at the end of the string
    3. swap symbols for `start` and `end` pointers
    4. increment `start` pointer and decrement `end` pointer
2. On the reverses string initialise two pointers both on the start of the string
3. Move `end` pointer until next symbol is space or end of the string
4. Reverse word between `start` and `end` pointers
5. Move both start and end pointers to the index of found space

![TP-reverse-words.drawio.png](diagrams/TP-reverse-words.drawio.png)

---
</details>

<details>
<summary>3. Valid abbreviation</summary>

---

Valid examples:

- kubernetes -> k8s
- internationalisation -> i18n

Invalid examples:

- car -> c2t
- car -> c0ar
- hat -> 2d

#### Code example:

[Valid palindrome](src/test/java/strings/twopointers/TwoPointerValidAbbreviation.java)

#### Algorithm:

1. Initialise 2 pointers: first for word and second for abbreviation
2. For each letter of abbreviation:
    - if it is a digit:
        - if it 0 - return false
        - if not 0 - parse number moving abbreviation pointer and increment word pointer on this
          number
    - if not a digit:
        - if word pointer is out of word length - return false
        - if word letter does not match abbreviation letter - return false
        - if letters match - increment both pointers
3. If abbreviation fully checked and there are no additional letters in word - return true

![TP-valid-abbreviation.drawio.png](diagrams/TP-valid-abbreviation.drawio.png)

---
</details>

<details>
<summary>4. Strobogrammatic number</summary>

---

[Strobogrammatic number](https://en.wikipedia.org/wiki/Strobogrammatic_number) - is a number, that
reads the same rotated 180 degrees.

Valid examples:

- 101
- 609
  Invalid examples:
- 1010
- 828

#### Code example:

[Strobogrammatic number](src/test/java/strings/twopointers/TwoPointersStrobogrammaticNumber.java)

#### Algorithm:

1. Initialise 2 pointers: first at the beginning of the number and second at the end
2. For the beginning digit find corresponding strobogrammatic digit
3. If strobogrammatic digit not match digit at the end - return false
4. Otherwise increment start pointer and decrement end pointer
5. If pointers met - number is strobogrammatic

---
</details>

<details>
<summary>5. Number of moves to make a palindrome</summary>

---

Example: `aabb -> abab -> abba`

#### Code example:

[Number of moves to make a palindrome](src/test/java/strings/twopointers/TwoPointersNumOfMovesToMakePalindrome.java)

#### Algorithm:
1. Initialise 2 pointers: `left` at start and at `right` the end of the string
2. If `left` and `right` letters are not the same:
   - Move `right` pointer till it bigger the `left` and letter is not equal to `left` letter
   - if found: 
     - move `right` pointer back swapping letters and incrementing number of moves
     - increment `left` pointer and decrement `right` pointer
   - if not found for the first time:
     - move `left` pointer to the middle swapping letters, so `left` letter is in the middle
     - set `left` and `right` pointer back to their positions
   - if not found for the second time - it is not possible to make a palindrome 
3. Continue till pointer meet

![TP-num-of-moves-for-palindrome.drawio.png](diagrams/TP-num-of-moves-for-palindrome.drawio.png)

---
</details>

<details>
<summary>6. Find next greater palindrome number</summary>

---

Example:
- 1221 - 2112
- 14322341 - 21344312
- 131 - null

#### Code example:

[Find next greater palindrome number.java](src/test/java/strings/twopointers/TwoPointersNextGreaterPalindrome.java)

#### Algorithm:
1. Split palindrome into 2 halves, in case of odd number of letters - store middle one separately
2. start iterating from the end of the left half
3. find **digit to replace**: the one, that is less than next one, e.g.: `[3]4`, `[1]3`, etc.
4. restart iteration from the end of the left half
5. find **replacement digit**: the one, that is bigger than **digit to replace**
6. swap **digit to replace** with **replacement digit**
7. reverse all the digits to the right of the swapped position
8. mirror left half and add middle digit if required to return the answer

![TP-next-greater-palindrome.drawio.png](diagrams/TP-next-greater-palindrome.drawio.png)

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
