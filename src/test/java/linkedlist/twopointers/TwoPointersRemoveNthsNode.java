package linkedlist.twopointers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Removes n-th node from the end of the linked list.
 * <pre>
 * Example:
 * - 1->2->3, 2 = 1->3
 * </pre>
 */
public class TwoPointersRemoveNthsNode {

  public static class Node<T> {

    private final T value;
    private Node<T> next;

    public Node(final T value, final Node<T> next) {
      this.value = value;
      this.next = next;
    }

    public Node<T> next() {
      return next;
    }

    public void setNext(final Node<T> next) {
      this.next = next;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return Objects.equals(value, node.value) && Objects.equals(next, node.next);
    }

    @Override
    public int hashCode() {
      return Objects.hash(value, next);
    }
  }

  static <T> Node<T> removeNthNode(final Node<T> linkedList, final int countFromEndToRemove) {
    Node<T> nodeBeforeOneToRemove = linkedList;
    Node<T> endOfTheList = linkedList;

    for (int counter = 0; counter < countFromEndToRemove; counter++) {
      endOfTheList = endOfTheList.next();
    }

    if (endOfTheList == null) { // means removing head
      return linkedList.next();
    }

    while (endOfTheList.next() != null) {
      nodeBeforeOneToRemove = nodeBeforeOneToRemove.next();
      endOfTheList = endOfTheList.next();
    }

    nodeBeforeOneToRemove.setNext(nodeBeforeOneToRemove.next().next());

    return linkedList;
  }

  static Stream<Arguments> removeNthNodeArgs() {
    return Stream.of(
        // Remove middle
        Arguments.of(
            new Node<>(1, new Node<>(2, new Node<>(3, null))),
            2,
            List.of(1, 3)),
        // Remove first
        Arguments.of(
            new Node<>(1, new Node<>(2, new Node<>(3, null))),
            3,
            List.of(2, 3)),
        // Remove last
        Arguments.of(
            new Node<>(1, new Node<>(2, new Node<>(3, null))),
            1,
            List.of(1, 2))
    );
  }

  @ParameterizedTest
  @MethodSource("removeNthNodeArgs")
  void testRemoveNthNode(
      final Node<Integer> list,
      final int n,
      final List<Integer> expectedResult) {
    Node<Integer> result = removeNthNode(list, n);

    final List<Integer> actualResult = new ArrayList<>();
    while (result != null) {
      actualResult.add(result.value);
      result = result.next();
    }
    assertEquals(expectedResult, actualResult);
  }
}
