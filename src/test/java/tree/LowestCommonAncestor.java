package tree;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LowestCommonAncestor {

  record Node(int value, Node parent) {

  }

  /**
   * <pre>
   * 1. Initialise two pointers: one on the first and another on second
   * 2. Move both pointers one step up
   * 3. If pointers meet - return the node
   * 4. If pointer reached root - move it to the starting position of the other node
   *
   *          10
   *    11           22
   *  6    5       19  14
   *     13 15
   * Find: 6, 15
   *
   * 1. 6->11, 15->5
   * 2. 11->10, 5->11
   * 3. 10->null->15, 11->10
   * 4. 15->5, 10->null->6
   * 5. 5->11, 6->11
   * </pre>
   */
  static Node findLowestCommonAncestorV2(final Node first, final Node second) {
    Node firstPointer = first;
    Node secondPointer = second;
    while (!firstPointer.equals(secondPointer)) {
      firstPointer = firstPointer.parent() != null
          ? firstPointer.parent()
          : second;

      secondPointer = secondPointer.parent() != null
          ? secondPointer.parent()
          : first;
    }

    return firstPointer;
  }

  static Stream<Arguments> testCases() {
    //          10
    //    11           22
    //  6    5       19  14
    //     13 15
    final Node head = new Node(10, null);

    final Node n11 = new Node(11, head);
    final Node n6 = new Node(6, n11);
    final Node n5 = new Node(5, n11);
    final Node n13 = new Node(13, n5);
    final Node n15 = new Node(15, n5);

    final Node n22 = new Node(22, head);
    final Node n19 = new Node(19, n22);
    final Node n14 = new Node(14, n22);

    return Stream.of(
        // Base case
        Arguments.of(n6, n15, n11),
        // different branches
        Arguments.of(n13, n14, head),
        // same branch
        Arguments.of(n11, n13, n11));
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testAlgorithm(final Node first, final Node second, final Node expectedResult) {
    Node result = findLowestCommonAncestorV2(first, second);

    assertEquals(expectedResult, result);
  }
}
