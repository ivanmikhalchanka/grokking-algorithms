package strings.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersValidPalindromeRemovingSingleLetter {

  /**
   * <pre>
   * 1. initialise 2 pointers: left and right
   * 2. move pointers until letters are match or pointers are meet
   * 3. once letters not match:
   *    3.1 increment mismatch counter, if counter more than 1 - return false
   *    3.2 check letter at the `right - 1` position, if it matches `left` letter - continue
   *    3.3 check letter at the `left + 1` position, if it matches `right` letter - continue
   *    3.4 if both `right - 1` and `left + 1` are mismatches - return `false`
   *
   * abbac
   * 1. 0:a,4:c -> 0:a,3:a, count: 1
   * 2. 1:b,2b -> pointers meet
   *
   * cabba
   * 1. 0:c,4:a -> 0:c,3:b -> 0:c,2:b -try_left-> 0:c,4:a -> 1:a,4:a, count: 1
   * 2. 2:b,3:b -> pointers meet
   *
   * abbcac
   * 1. 0:a,5:c -> 0:a,4:a, count 1
   * 2. 1:b,3:c -> false
   * </pre>
   */
  static boolean isValidPalindromeRemovingOneLetter(final String input) {
    int maxMismatchCounter = input.length() > 2 ? 1 : 0;
    int mismatchCounter = 0;
    for (int leftIndex = 0, rightIndex = input.length() - 1;
        leftIndex < rightIndex;
        leftIndex++, rightIndex--) {

      char leftChar = input.charAt(leftIndex);
      char rightChar = input.charAt(rightIndex);
      if (leftChar != rightChar) {
        mismatchCounter++;
        if (mismatchCounter > maxMismatchCounter) {
          return false;
        }

        if (leftChar == input.charAt(rightIndex - 1)) {
          rightIndex--;
        } else if (rightChar == input.charAt(leftIndex + 1)) {
          leftIndex++;
        } else {
          return false;
        }
      }
    }

    return true;
  }

  static Stream<Arguments> testCases() {
    return Stream.of(
        // letter at the end
        Arguments.of("abbac", true),
        // letter at the beginning
        Arguments.of("cabba", true),
        // letter at the middle
        Arguments.of("abbca", true),
        // not possible
        Arguments.of("abbcac", false),
        // single letter
        Arguments.of("a", true),
        // two letters
        Arguments.of("ab", false),
        // three letters
        Arguments.of("abc", false)
    );
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testAlgorithm(final String input, final boolean expectedResult) {
    final boolean result = isValidPalindromeRemovingOneLetter(input);

    assertEquals(expectedResult, result);
  }
}
