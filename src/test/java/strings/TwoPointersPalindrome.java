package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersPalindrome {

  public static final int MAX_INPUT_LENGTH = 2 * (int) Math.pow(10, 5);

  static boolean isPalindrome(final String input) {
    if (input == null || input.isEmpty()) {
      return false;
    }

    int leftIndex = 0;
    int rightIndex = input.length() - 1;
    while (leftIndex < rightIndex) {
      final char leftChar = input.charAt(leftIndex);
      final char rightChar = input.charAt(rightIndex);

      if (leftChar != rightChar) {
        return false;
      }

      leftIndex++;
      rightIndex--;
    }

    return true;
  }

  static Stream<Arguments> palindromes() {
    return Stream.of(
        Arguments.of("abba", true),
        Arguments.of("abab", false),
        Arguments.of("", false),
        Arguments.of(null, false),
        Arguments.of("a", true),
        Arguments.of("a".repeat(MAX_INPUT_LENGTH), true));
  }

  @ParameterizedTest
  @MethodSource("palindromes")
  void testPalindrome(final String input, final boolean expectedResult) {
    final boolean result = isPalindrome(input);

    assertEquals(expectedResult, result);
  }
}
