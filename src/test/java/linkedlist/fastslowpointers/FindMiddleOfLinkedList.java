package linkedlist.fastslowpointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FindMiddleOfLinkedList {

  /**
   * represents LinkedList node
   */
  record Node(int value, Node next) {

  }

  /**
   * <pre>
   * Example:
   * 1 -> 2 -> 3 -> [4] -> 5 -> 6 -> 7
   *
   * Try FSP with double speed:
   * s:1,f:1 -> s:2,f:3 -> s:3,f:5 -> s:4,f:7-null = result found [4]
   *
   * Example 2:
   * 1 -> 2 -> [3] -> 4
   * s:1,f:1 -> s:2,f:3 -> s:3,f:null = result found
   *
   * Example 3:
   * 1 -> [2]
   * s:1,f:1 -> s:2,f:null - result found
   *
   * Algorithm:
   * 1. Init slow and fast pointer at the head of linked list
   * 2. While fast and fast.next are not null:
   *    - move slow pointer 1 step forward
   *    - move fast pointer 1 step forward, null check, 1 step forward
   * 3. Slow pointer will be referencing middle of the list - return it
   * </pre>
   */
  static Node findMiddleNode(final Node head) {
    Node slow = head;
    Node fast = head;
    while (fast != null && fast.next() != null) {
      slow = slow.next();
      fast = fast.next().next();
    }

    return slow;
  }

  static Stream<Arguments> testCases() {
    return Stream.of(
        // Odd num of nodes
        Arguments.of(
            new Node(1, new Node(2, new Node(3, null))),
            2),
        // Even num of nodes
        Arguments.of(
            new Node(1, new Node(2, new Node(3, new Node(4, null)))),
            3
        ),
        // Single node
        Arguments.of(new Node(1, null), 1),
        // Two nodes
        Arguments.of(new Node(1, new Node(2, null)), 2)
    );
  }

  @ParameterizedTest
  @MethodSource("testCases")
  void testFindMiddleNode(final Node head, final int expectedResultValue) {
    final Node result = findMiddleNode(head);

    assertEquals(expectedResultValue, result.value());
  }
}
