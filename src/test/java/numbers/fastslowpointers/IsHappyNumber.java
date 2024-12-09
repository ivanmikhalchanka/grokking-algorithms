package numbers.fastslowpointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class IsHappyNumber {

  /**
   * <pre>
   * Happy number - is a number, that will convert to 1 repeatedly summing squares of digits.
   *
   * 23 -> 2^2+3^2=4+9=[13] -> 1^2+3^2=1+9=[10] -> 1^2+0^2=[1] - true
   * 23 -> 13 -> 10 -> 1
   *
   * 2:
   * 0. 2^2=[4]
   * 1. 4^2=[16]
   * 2. 1^2+6^2=1+36=[37]
   * 3. 3^2+7^2=9+49=[58]
   * 4. 5^2+8^2=25+64=[89]
   * 5. 8^2+9^2=64+81=[145]
   * 6. 1^2+4^2+5^2=1+16+25=[42]
   * 7. 4^2+2^2=16+4=[20]
   * 8. 2^2+0^2=[4] - cycle detected, false
   *
   * 2->4->16->37->58->89->145->42->20->4->16->37->58->89->145->42->20->4->16->37->58->89->145->42->20->4
   *
   * 0. 0:2 0:2
   * 1. 1:4 2:16
   * 2. 2:16 4:58
   * 3. 3:37 6:145
   * 4. 4:58 8:20
   * 5. 5:89 10:16
   * 6. 6:145 12:58
   * 7. 7:42 14:145
   * 8. 8:20 16:20
   *
   * Algorithm:
   * 1. Initialise slow pointer as number
   * 2. Initialise fast pointer as number after initial one
   * 3. Until fast pointer is eq to 1 or fast and slow pointers are equal:
   *    3.1 replace slow pointer with sum of it's squares
   *    3.2 replace fast pointer summing it's squares two times
   * 4. If fast pointer is 1 - number is happy
   *
   * Calculate next number:
   * - while number is not equal to 0
   *   - get next digit - `number % 10` and calculate it power 2
   *   - update number: `number / 10`
   * </pre>
   */
  static boolean isHappyNumber(final int number) {
    int slowPonter = number;
    int fastPointer = calculateNextNumber(number);

    while (fastPointer != 1 && slowPonter != fastPointer) {
      slowPonter = calculateNextNumber(slowPonter);
      fastPointer = calculateNextNumber(calculateNextNumber(fastPointer));
    }

    return fastPointer == 1;
  }

  /**
   * @return sum of the squares of number's digits, e.g. 123 -> 1^2+2^2+3^2=14
   */
  static int calculateNextNumber(int number) {
    int result = 0;
    while (number != 0) {
      final int nextDigit = number % 10;
      number /= 10;

      result += nextDigit * nextDigit;
    }

    return result;
  }

  static Stream<Arguments> calculateNextTestCases() {
    return Stream.of(
        Arguments.of(0, 0),
        Arguments.of(100, 1),
        Arguments.of(123, 14));
  }

  @ParameterizedTest
  @MethodSource("calculateNextTestCases")
  void testCalculateNextNumber(final int number, final int expectedResult) {
    final int result = calculateNextNumber(number);

    assertEquals(expectedResult, result);
  }

  static Stream<Arguments> testCases() {
    return Stream.of(
        // Basic happy numbers
        Arguments.of(23, true),
        Arguments.of(28, true),
        Arguments.of(1, true),
        // Basic non-happy number
        Arguments.of(2, false),
        Arguments.of(4, false),
        Arguments.of(2147483646, false));
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testIsHappyNumber(final int number, final boolean expectedResult) {
    final boolean result = isHappyNumber(number);

    assertEquals(expectedResult, result);
  }
}
