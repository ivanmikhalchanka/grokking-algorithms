package arrays.fastslowpointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Given:
 * - array of int numbers
 * - 1 <= array[i] < array.length
 * - two numbers in array are same Task:
 * - identify duplicate numbers
 */
public class FindDuplicateNumbersInLinkedListRepresentedAsArray {

  /**
   * <pre>
   * Assumptions:
   * 1. Due to the next conditions looks like array can be traversed as a linked list:
   *    - `1 <= array[i] < array.length` - means that each value - is an index of another array element
   * 2. Duplicate elements forms a relationship loop, i.e.:
   *       0  1  2  3  4
   *    - [1, 2, 3, 4, 1] <=> [0] -> [1] -> [2]
   *                                  |      |
   *                                 [4] <- [3]
   * 3. In order to find duplicate elements:
   *    3.1 detect the loop by using fast and slow pointers util they meet
   *    3.2 use two pointers: first starting at 0 and second at the fast-and-slow pointers intersection till they meet
   *
   * Example:
   * - [1, 2, 3, 4, 1]
   * Iterations:
   * - s:1,f:1 -> s:2,f:3 -> s:3,f:1 -> s:4,f:3 -> s:1,f:1 - pointers met, loop detected
   * - restart slow from the beginning to detect duplicate numbers:
   * - f:1,s:1 - duplicate number detected
   *
   * - [2, 3, 4, 4, 1]
   * Iterations:
   * - s:[0]2,f:[0]2 -> s:[2]4,f:[4]1 -> s:[4]1,f:[3]4 -> s:[1]3,f:[1]3 - loop detected
   * - restart slow from the beginning to detect duplicate numbers:
   * - f:[0]2,s:[1]3 -> f:[2]4,s:[3]4 - duplicate number detected
   *
   * - [1, 2, 4, 3, 1]
   * Iterations:
   * - s:[0]1,f:[0]1 -> s:[1]2,f:[2]4 -> s:[2]4,f:[1]2 -> s:[4]1,f:[4]1 - loop detected
   * - restart slow from the beginning:
   * - f:[0]1,s:[4]1 - duplicate number detected
   * </pre>
   */
  static int findDuplicateNumber(final int[] array) {
    int slowIndex = 0, fastIndex = 0;
    do {
      slowIndex = array[slowIndex];
      fastIndex = array[array[fastIndex]];
    } while (array[slowIndex] != array[fastIndex]);

    int firstIndex = 0, secondIndex = fastIndex;
    while (array[firstIndex] != array[secondIndex]) {
      firstIndex = array[firstIndex];
      secondIndex = array[secondIndex];
    }

    return array[secondIndex];
  }

  static Stream<Arguments> testCases() {
    return Stream.of(
        // Basic test case:
        Arguments.of(new int[]{1, 3, 4, 3, 2}, 3),
        // Duplicates one by one
        Arguments.of(new int[]{2, 3, 4, 4, 1}, 4),
        // Duplicates at the different ends of array
        Arguments.of(new int[]{1, 2, 4, 3, 1}, 1),
        // Cycle in the middle
        Arguments.of(new int[]{1, 2, 3, 4, 5, 1, 6}, 1));
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testFindDuplicateNumber(final int[] array, final int expectedResult) {
    final int result = findDuplicateNumber(array);

    assertEquals(expectedResult, result);
  }
}
