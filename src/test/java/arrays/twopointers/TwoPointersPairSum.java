package arrays.twopointers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersPairSum {

  static int[] findElementsWithSum(final int[] array, final int sum) {
    final int[] EMPTY_RESULT = {};

    if (array == null || array.length == 0) {
      return EMPTY_RESULT;
    }
    if (array.length == 1) {
      return array[0] == sum ? array : EMPTY_RESULT;
    }

    int startIndex = 0;
    int endIndex = array.length - 1;
    while (startIndex < endIndex) {
      final long currentSum = (long) array[startIndex] + array[endIndex];

      if (currentSum == sum) {
        return new int[]{array[startIndex], array[endIndex]};
      }

      if (currentSum > sum) {
        endIndex--;
      } else {
        startIndex++;
      }
    }

    return EMPTY_RESULT;
  }

  static Stream<Arguments> sumOfTwoInSortedArray() {
    return Stream.of(
        // basic happy path
        Arguments.of(new int[]{1, 3, 4, 6, 8, 10}, 12, new int[]{4, 8}),
        // sum not presented
        Arguments.of(new int[]{1, 3, 5, 6, 8, 10}, 2, new int[]{}),
        // no iteration required
        Arguments.of(new int[]{3, 5}, 8, new int[]{3, 5}),
        // Checking max possible values
        Arguments.of(
            new int[]{2, 3, Integer.MAX_VALUE - 3, Integer.MAX_VALUE},
            Integer.MAX_VALUE,
            new int[]{3, Integer.MAX_VALUE - 3}));
  }

  @ParameterizedTest
  @MethodSource("sumOfTwoInSortedArray")
  void testFindSumMatchingResult(
      final int[] inputArray,
      final int inputSum,
      final int[] expectedResult) {
    final int[] result = findElementsWithSum(inputArray, inputSum);

    assertArrayEquals(expectedResult, result);
  }
}
