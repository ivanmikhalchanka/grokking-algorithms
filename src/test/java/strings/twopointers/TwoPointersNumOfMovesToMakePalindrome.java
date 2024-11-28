package strings.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersNumOfMovesToMakePalindrome {

  static void swapWithNext(final char[] chars, final int index) {
    final char temp = chars[index];

    chars[index] = chars[index + 1];
    chars[index + 1] = temp;
  }

  /*
   * 1. init 2 pointers: at start and at the end
   * 2. if start and end letters are not same:
   *    2.1. move right pointer till it more that left and not equal to left letter
   *    2.2. if found:
   *         2.2.1 move end pointer back swapping letters and incrementing number of moves
   *         2.2.2 increment left pointer and decrement right
   *    2.3. if not found:
   *         2.3.1 move end pointer back and move start pointer letter to the middle and reset pointers to recheck current letters
   *         2.3.2 do not move pointers to recheck letters after replacement
   *
   * aabb
   * - counter: 2
   * - a0, b3 -> a0, b2 -> a0, a1 -> abab -> abba
   * - b1, b2
   * - end
   *
   * arcacer
   * - counter: 4
   * - a0:r6 -> a0:e5 -> a0:c4 -> a0:a3 -> arccaer -> arccear -> arccera
   * - r1:r5
   * - c2:e4 -> c2:c3 -> arcecra
   * - e3:e3
   *
   * bobr
   * - counter: 1
   * - b0:r3 -> b0:b2 -> borb
   * - o1:b:2 -> o1:o1 -> limit exceed -> -1
   *
   * abcda
   * - counter: 1
   * - b1:d3 -> b1:c2 -> b1:c1 -> acbda
   * - c1:d3 -> c1:b2 -> limit of distinct reached
   * */
  static int calculateNumOfMovesToMakePalindrome(final String word) {
    final int NOT_POSSIBLE = -1;

    final char[] letters = word.toCharArray();

    int distinctLettersLimit = letters.length % 2;

    int numOfMoves = 0;

    // 1. init 2 pointers: at start and at the end
    for (int leftIndex = 0, rightIndex = letters.length - 1; leftIndex < rightIndex;) {
      final char startChar = letters[leftIndex];
      char endChar = letters[rightIndex];

      // 2. if start and end letters are not same:
      if (startChar != endChar) {
        // 2.1 move right pointer till it more that left and not equal to left letter
        int swapIndex = rightIndex;
        while (swapIndex > leftIndex && letters[swapIndex] != startChar) {
          swapIndex--;
        }

        if (swapIndex != leftIndex) {
          // 2.2.1 if found - move end pointer back swapping letters and incrementing number of moves
          for (; swapIndex < rightIndex; swapIndex++) {
            swapWithNext(letters, swapIndex);
            numOfMoves++;
          }
          // 2.2.2 increment left pointer and decrement right pointer
          leftIndex++;
          rightIndex--;
        } else {
          // 2.3.1 if not found - move end pointer back and move start pointer letter to the middle
          if (distinctLettersLimit-- == 0) {
            return NOT_POSSIBLE;
          }

          for (swapIndex = leftIndex; swapIndex < letters.length / 2; swapIndex++) {
            swapWithNext(letters, swapIndex);
            numOfMoves++;
          }
          // 2.3.2 do not move pointers to recheck letters again
        }
      }
    }

    return numOfMoves;
  }

  static Stream<Arguments> testCases() {
    return Stream.of(
        // base positive case: aabb -> abab -> abba
        Arguments.of("aabb", 2),
        // base positive case: arcacer -> arccaer -> arccear -> arccera -> arcecra
        Arguments.of("arcacer", 4),
        // already a palindrome
        Arguments.of("racecar", 0),
        // can't be a palindrome
        Arguments.of("bobr", -1),
        // can't be a palindrome
        Arguments.of("abcda", -1));
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testMovesToMakePalindrome(final String input, final int expectedResult) {
    final int result = calculateNumOfMovesToMakePalindrome(input);

    assertEquals(expectedResult, result);
  }
}
