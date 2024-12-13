package arrays.fastslowpointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DetectCircularArrayLoop {

  /**
   * <pre>
   * Example forward:
   * - [3, 1, 2]
   * Iterations:
   * - 3 -> 3 - not a cycle cause single element
   * - 1 -> 2 -> 1 - cycle, 2 elements, same direction
   *
   * Example backward:
   * - [-2, -1, -3]
   * Iterations:
   * -2 -> -1 -> -2 - cycle detected
   *
   * Example misdirection:
   * - [2, 1, -1, -2]
   * Iterations:
   * - 2 -> -1 - misdirection found, not a cycle
   * - 1 -> -1 - not a cycle
   * - -1 -> 1 - not a cycle
   * - -2 -> 1 - not a cycle
   *
   * Example no cycles:
   * - [3, 6, 9]
   * Iterations:
   * - 3 -> 3 - single element, not a cycle
   * - 6 -> 6 - single element, not a cycle
   * - 9 -> 9 - single element, not a cycle
   *
   * Base algorithm:
   *  - For each element of the array:
   *    - init fast and slow pointers on the element
   *    - store direction by the starting element: more that 0 - forward
   *    - init misdirection flag
   *    - init steps counter at 0
   *    - until slow pointer is equal to the fast pointer or misdirection found:
   *      - increment steps counter
   *      - move slow pointer one step forward by iterating through an array
   *        - check direction of slow pointer and update misdirection flag
   *      - move fast pointer two steps forward
   *        - check direction of fast pointer and update misdirection flag
   *    - once fast and slow pointers met - check misdirection flag and steps counter and if valid cycle detected - return true
   *
   * Next element of the sequence algorithm:
   * - append number of steps to the current index
   * - calculate reminder of result number division to the array length
   * - if number is negative - add array length, this happens when moving backwards
   *
   * Examples:
   * - [3, 6, 9] -> 9
   * - index: 2, steps: 9
   * - 2 + 9 = 11, 11 % 3 = 2
   *
   * - [-2, -1, -3] -> 9
   * - index: 0, steps: -2
   * - 0 + (-2) = -2, -2 % 3 = -2, 0 > -> -2 + 3 = 1
   *
   * </pre>
   */
  static boolean isLoopPresented(final int[] nums) {
    for (int arrayElementIndex = 0; arrayElementIndex < nums.length; arrayElementIndex++) {
      int slowIndex = arrayElementIndex;
      int fastIndex = arrayElementIndex;
      boolean isForward = nums[arrayElementIndex] > 0;
      boolean isMisdirectionDetected = false;
      int stepsCounter = 0;

      do {
        stepsCounter++;

        // move slow index one step forward
        slowIndex = calculateNextIndex(nums, slowIndex);

        // move fast index 2 steps forward
        final int fastNext = calculateNextIndex(nums, fastIndex);
        fastIndex = calculateNextIndex(nums, fastNext);

        // verify misdirection
        if (nums[slowIndex] > 0 != isForward
            || nums[fastNext] > 0 != isForward
            || nums[fastIndex] > 0 != isForward) {
          isMisdirectionDetected = true;
        }
      } while (slowIndex != fastIndex && !isMisdirectionDetected);

      if (!isMisdirectionDetected && stepsCounter > 1) {
        return true;
      }
    }

    return false;
  }

  static int calculateNextIndex(final int[] nums, final int currentIndex) {
    int nextIndex = (currentIndex + nums[currentIndex]) % nums.length;
    if (nextIndex < 0) {
      nextIndex += nums.length;
    }

    return nextIndex;
  }

  static Stream<Arguments> testCases() {
    return Stream.of(
        // Basic true
        Arguments.of(new int[]{3, 1, 2}, true),
        // Backwards true
        Arguments.of(new int[]{-2, -1, -3}, true),
        // Misdirection false
        Arguments.of(new int[]{2, 1, -1, -2}, false),
        // Single-element false
        Arguments.of(new int[]{3, 6, 9}, false),
        // Single element
        Arguments.of(new int[]{2}, false)
    );
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testIsCyclePresented(final int[] array, final boolean expectedResult) {
    final boolean result = isLoopPresented(array);

    assertEquals(expectedResult, result);
  }
}
