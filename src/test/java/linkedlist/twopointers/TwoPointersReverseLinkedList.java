package linkedlist.twopointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Reverses linked list in place
 * <pre>
 * Example:
 * 1->2->3 -> 3->2->1
 * </pre>
 */
public class TwoPointersReverseLinkedList {
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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            if (!Objects.equals(value, node.value)) return false;
            return Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            int result = value != null ? value.hashCode() : 0;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }
    }

    /**
     * <pre>
     * Algorithm:
     * 1. initialise 3 pointers: prev, next, current
     * 2. store next node: next = current.next
     * 3. link current node with previous one as next: current.next = prev
     * 4. store current node as next prev: prev = current
     * 5. move current one step forward: current = next
     *
     * Example:
     * 1->2->3
     * init: n:null,c:1->2->3,p:null
     * 1. n:2->3,c:1->null -> p:1->null,c:2->3
     * 2. n:3,c:2->1 -> p:2->1,c:3
     * 3. n:null,c:3->2->1 -> p:3->2->1,c:null
     * </pre>
     */
    static <T> Node<T> reverseLinkedList(Node<T> head) {
        Node<T> current = head;
        Node<T> prev = null;
        Node<T> next;
        while (current != null) {
            next = current.next();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        return prev;
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                Arguments.of(new Node<>(1, null), new Node<>(1, null)),
                Arguments.of(
                        new Node<>(1, new Node<>(2, new Node<>(3, null))),
                        new Node<>(3, new Node<>(2, new Node<>(1, null)))));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void testReverseLinkedList(Node<Integer> head, Node<Integer> expectedResult) {
        Node<Integer> result = reverseLinkedList(head);

        assertEquals(expectedResult, result);
    }
}
