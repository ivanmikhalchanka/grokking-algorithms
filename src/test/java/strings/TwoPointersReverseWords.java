package strings;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersReverseWords {

  static void reverse(final char[] input, int startIndex, int endIndex) {
    while (startIndex < endIndex) {
      final char temp = input[startIndex];
      input[startIndex] = input[endIndex];
      input[endIndex] = temp;

      startIndex++;
      endIndex--;
    }
  }

  static String reverseWords(final String input) {
    // reverse the string
    final char[] reversed = input.toCharArray();
    reverse(reversed, 0, input.length() - 1);

    final char space = ' ';
    for (int left = 0, right = 0; right < reversed.length; right++) {
      if (right == reversed.length - 1 || space == reversed[right + 1]) {
        final int spaceIndex = right + 1;
        reverse(reversed, left, right);

        left = spaceIndex + 1;
        right = spaceIndex;
      } else if (space == reversed[left]) {
        left++;
      }
    }

    return String.valueOf(reversed);
  }

  static Stream<Arguments> reverseWordsCases() {
    return Stream.of(
        // Base case
        Arguments.of(
            "Red big elf hat",
            "hat elf big Red"),
        // Single word
        Arguments.of("elf", "elf"),
        // Odd number of words
        Arguments.of("big elf hat", "hat elf big"),
        // Additional spaces
        Arguments.of("big elf   hat ", " hat   elf big"));
  }

  @ParameterizedTest
  @MethodSource("reverseWordsCases")
  void testReverseWords(final String input, final String expectedOutput) {
    final String output = reverseWords(input);

    assertEquals(expectedOutput, output);
  }
}
