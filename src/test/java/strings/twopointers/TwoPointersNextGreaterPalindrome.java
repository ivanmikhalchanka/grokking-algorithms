package strings.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersNextGreaterPalindrome {

  /**
   * <pre>
   *   1. split palindrome into 2 halves, in case of odd number - store middle one separately
   *   2. start iterating from the end of the left half
   *   3. find **digit to replace**: it is the one that is less than next one, e.g.: [3]4, [1]3 etc...
   *   4. restart iteration from the end of the left half
   *   5. find **replacement digit**: it is the one that is bigger than **digit to replace**
   *   6. swap **digit to replace** with **replacement digit**
   *   7. reverse all the digits to the right of the swapped position
   *   8. mirror left half and add middle digit to return the answer
   *
   *   Example: 14322341
   *   0. split: 1432
   *   1. find digit that it smaller that one to the right:
   *      - 2:[3]2 -> 1:[4]3 -> 0:[1]4
   *      - index to replace: 0, digit to replace: 1
   *   2. find replacement digit:
   *      - 3:[2]
   *      - replacement index: 3, replacement digit: 2
   *   3. swap digits:
   *      - 1432 -> 2431
   *   4. reverse digits to the right of the index to replace: 0
   *      - 2___, 3:1 -> 21__, 2:3 -> 213_, 1:4 -> 2134, 0 - index reached
   *   5. mirror the left half:
   *      - 2134____ -> 2134___2 -> 2134__12 -> 2134_312 -> 21344312
   * </pre>
   */
  static Optional<Integer> calculateNextGreaterPalindrome(final int number) {
    final char[] digits = String.valueOf(number).toCharArray();
    if (digits.length < 4) {
      return Optional.empty();
    }

    // 1. get left half of the palindrome
    final char[] leftHalf = new char[digits.length / 2];
    for (int i = 0; i < leftHalf.length; i++) {
      leftHalf[i] = digits[i];
    }

    // 2. find digit that should be replaced
    int indexToReplace = leftHalf.length - 2;
    while (indexToReplace >= 0 && leftHalf[indexToReplace] >= leftHalf[indexToReplace + 1]) {
      indexToReplace--;
    }
    if (indexToReplace == -1) {
      return Optional.empty();
    }
    final char digitToReplace = leftHalf[indexToReplace];

    // 3. find digit that can replace digitToReplace
    int replacementIndex = leftHalf.length - 1;
    char replacementDigit = leftHalf[replacementIndex];
    while (replacementDigit <= digitToReplace) {
      replacementIndex--;
      replacementDigit = leftHalf[replacementIndex];
    }

    // 4. swap digits
    leftHalf[indexToReplace] = replacementDigit;
    leftHalf[replacementIndex] = digitToReplace;

    // 5. reorder all digits to the right of the digit that was replaced
    final char[] result = new char[digits.length];
    for (int i = 0; i <= indexToReplace; i++) {
      result[i] = leftHalf[i];
    }
    for (int resultIndex = indexToReplace + 1, leftHalfEndIndex = leftHalf.length - 1;
        leftHalfEndIndex > indexToReplace;
        resultIndex++, leftHalfEndIndex--) {
      result[resultIndex] = leftHalf[leftHalfEndIndex];
    }

    // 6. mirror left part to the right
    for (int startIndex = 0, endIndex = result.length - 1;
        startIndex < endIndex;
        startIndex++, endIndex--) {
      result[endIndex] = result[startIndex];
    }

    // 7. for odd number of digits - add middle one
    if (digits.length % 2 != 0) {
      final int middleIndex = digits.length / 2;
      result[middleIndex] = digits[middleIndex];
    }

    return Optional.of(Integer.parseInt(String.valueOf(result)));
  }

  static Stream<Arguments> testCases() {
    return Stream.of(
        // basic test
        Arguments.of(1221, 2112),
        // optimal option test
        Arguments.of(14322341, 21344312),
        // optimal option test #2
        Arguments.of(1234321, 1324231),
        // no replacements found - single digit
        Arguments.of(5, null),
        // no replacement found - 3 digit
        Arguments.of(131, null),
        // no replacement found - same digits
        Arguments.of(2222, null)
    );
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testNextPalindrome(final int number, final Integer expectedResult) {
    final Optional<Integer> result = calculateNextGreaterPalindrome(number);

    assertEquals(Optional.ofNullable(expectedResult), result);
  }
}
