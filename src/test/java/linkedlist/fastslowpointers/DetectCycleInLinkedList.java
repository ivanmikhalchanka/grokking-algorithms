package linkedlist.fastslowpointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DetectCycleInLinkedList {

  /**
   * Representing a node in the linked list
   */
  static final class Node {

    int value;
    Node next;

    public Node(int value, Node next) {
      this.value = value;
      this.next = next;
    }

    public Node(int value) {
      this.value = value;
    }
  }

  /**
   * <pre>
   * 1. Initialise 2 pointers: at fist and at next elements
   * 2. Until fast reaches null or slow equal to the fast:
   *    - move slow pointer one step forward
   *    - move fast pointer two steps forward
   * 3. If fast pointer is null - return false
   *
   * Example 1:
   * - 1 -> 2 -> 3 -> 4 -> 5 -> 2
   * - s:1,f:2 -> s:2,f:4 -> s:3,f:2 -> s:4,f:4 - cycle detected
   *
   * Example 2:
   * - 1 -> 2 -> 1
   * - s:1,f:2 -> s:2,f:2 - cycle detected
   * </pre>
   */
  static boolean hasCycle(final Node node) {
    if (node.next == null) {
      return false;
    }

    Node slow = node;
    Node fast = node.next;
    // Until the end of the list
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;       // move slow pointer one step forward
      fast = fast.next.next;  // move fast pointer two steps forward

      // check if cycle detected
      if (fast.value == slow.value) {
        return true;
      }
    }

    return false;
  }

  static Stream<Arguments> testCases() {
    final Node basicListWithCycle = new Node(1);
    final Node second = new Node(2);
    basicListWithCycle.next = second;
    final Node third = new Node(3);
    second.next = third;
    final Node fourth = new Node(4);
    third.next = fourth;
    fourth.next = second;

    final Node shortListWithCycle = new Node(1);
    shortListWithCycle.next = new Node(2, shortListWithCycle);

    return Stream.of(
        // Base list without cycles
        Arguments.of(
            new Node(1, new Node(2, new Node(3, null))),
            false
        ),
        // Single-node list:
        Arguments.of(new Node(1, null), false),
        // Basic cycle
        Arguments.of(basicListWithCycle, true),
        // Short cycle
        Arguments.of(basicListWithCycle, true));
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testHasCycle(final Node list, final boolean expectedResult) {
    final boolean result = hasCycle(list);

    assertEquals(expectedResult, result);
  }
}
