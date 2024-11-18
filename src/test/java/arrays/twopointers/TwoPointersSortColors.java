package arrays.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersSortColors {

  public static final int C_1 = 1;
  public static final int C_0 = 0;
  public static final int C_2 = 2;

  int[] sortColors(final int[] input) {
    if (input.length < 2) {
      return input;
    }

    int headIndex = 0;
    int currentIndex = 0;
    int tailIndex = input.length - 1;
    while (currentIndex <= tailIndex) {
      final int current = input[currentIndex];

      if (current == C_0) {
        input[currentIndex] = input[headIndex];
        input[headIndex] = C_0;
        headIndex++;
        currentIndex++;
      } else if (current == C_2) {
        input[currentIndex] = input[tailIndex];
        input[tailIndex] = C_2;
        tailIndex--;
      } else {
        currentIndex++;
      }
    }

    return input;
  }

  static Stream<Arguments> sortColorsTests() {
    return Stream.of(
        // base case
        Arguments.of(
            new int[]{C_1, C_1, C_0, C_2, C_0, C_2},
            new int[]{C_0, C_0, C_1, C_1, C_2, C_2}),
        // single-element array
        Arguments.of(new int[]{C_1}, new int[]{C_1}),
        // two-element array
        Arguments.of(new int[]{C_1, C_0}, new int[]{C_0, C_1}),
        // array of same symbols
        Arguments.of(new int[]{C_0, C_0, C_0}, new int[]{C_0, C_0, C_0}),
        Arguments.of(
            new int[]{C_0, C_1, C_0, C_1, C_0, C_2, C_0},
            new int[]{C_0, C_0, C_0, C_0, C_1, C_1, C_2})
    );
  }

  @ParameterizedTest
  @MethodSource("sortColorsTests")
  void testSortColors(final int[] input, final int[] expectedOutput) {
    final int[] result = sortColors(input);

    assertArrayEquals(expectedOutput, result);
  }
}
