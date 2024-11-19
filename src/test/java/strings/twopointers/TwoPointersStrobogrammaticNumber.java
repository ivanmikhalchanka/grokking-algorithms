package strings.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Number is strobogrammatic when it looks same rotated 180 degrees, e.g. 69, 101, 818
 */
public class TwoPointersStrobogrammaticNumber {

  static boolean isStrobogrammatic(final int number) {
    final Map<Character, Character> STROBOGRAMMES = Map.of(
        '1', '1',
        '6', '9',
        '8', '8',
        '9', '6',
        '0', '0');

    final String num = Integer.toString(number);
    for (int startIndex = 0, endIndex = num.length() - 1;
        startIndex <= endIndex;
        startIndex++, endIndex--) {
      final char startChar = num.charAt(startIndex);
      final char endChar = num.charAt(endIndex);

      final Character strobbograme = STROBOGRAMMES.get(startChar);
      if (strobbograme == null || strobbograme != endChar) {
        return false;
      }
    }

    return true;
  }

  static Stream<Arguments> strobTests() {
    return Stream.of(
        // basic positive cases
        Arguments.of("69", true),
        Arguments.of("101", true),
        Arguments.of("61019", true),
        // basic negative cases
        Arguments.of("89", false),
        Arguments.of("1010", false),
        Arguments.of("161", false)
    );
  }

  @ParameterizedTest
  @MethodSource("strobTests")
  void testIsStrob(final int number, final boolean expectedResult) {
    final boolean result = isStrobogrammatic(number);

    assertEquals(expectedResult, result);
  }
}
