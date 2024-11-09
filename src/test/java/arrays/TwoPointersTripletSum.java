package arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TwoPointersTripletSum {

  static int[] findTripletWithSum(final int[] array, final long sum) {
    final int[] resultNotFound = {};
    if (array == null || array.length < 3) {
      return resultNotFound;
    }

    Arrays.sort(array);

    for (int currentIndex = 0; currentIndex < array.length - 2; currentIndex++) {
      final int current = array[currentIndex];

      int leftIndex = currentIndex + 1;
      int rightIndex = array.length - 1;
      while (leftIndex < rightIndex) {
        final int left = array[leftIndex];
        final int right = array[rightIndex];

        final long currentSum = (long) current + left + right;
        if (currentSum == sum) {
          return new int[]{current, left, right};
        }

        if (currentSum > sum) {
          rightIndex--;
        } else {
          leftIndex++;
        }
      }
    }

    return resultNotFound;
  }

  static Stream<Arguments> sumOfThree() {
    return Stream.of(
        // basic happy path
        Arguments.of(
            new int[]{2, 1, 8, 6, 4},
            12,
            new int[]{2, 4, 6}),
        // less elements
        Arguments.of(new int[]{1, 2}, 3, new int[]{}),
        // empty input data
        Arguments.of(new int[]{}, 3, new int[]{}),
        // negative values
        Arguments.of(new int[]{0, -2, -1, 6, 4}, 2, new int[]{-2, 0, 4}),
        // maximum values
        Arguments.of(
            new int[]{0, 1, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 1, Integer.MAX_VALUE},
            Integer.MAX_VALUE,
            new int[]{0, 1, Integer.MAX_VALUE - 1}));
  }

  @ParameterizedTest
  @MethodSource("sumOfThree")
  void testSumOfThree(final int[] array, final int sum, final int[] expected) {
    final int[] result = findTripletWithSum(array, sum);

    assertArrayEquals(expected, result);
  }
}
