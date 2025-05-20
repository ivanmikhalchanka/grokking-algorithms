package linkedlist.fastslowpointers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Function to check if liked list is a palindrome.
 *
 * <pre>
 * Examples:
 * - 1->2->3->2->1 - true
 * - 1->2->3->4->2 - false
 * </pre>
 */
public class PalindromeLinkedList {
    /**
     * represents LinkedList node
     */
    static class Node {
        private final int value;
        private Node next;

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int value() {
            return value;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node node) {
            this.next = node;
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            Node node = this;
            while (node != null) {
                output.append(node.value());
                if (node.next() != null) {
                    output.append("->");
                }
                node = node.next();
            }

            return output.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            if (value != node.value) return false;
            return Objects.equals(next, node.next);
        }

        @Override
        public int hashCode() {
            int result = value;
            result = 31 * result + (next != null ? next.hashCode() : 0);
            return result;
        }

        public Node deepCopy() {
            Node cloneHead = new Node(this.value, null);
            Node cloneCurrent = cloneHead;
            Node next = this.next;
            while (next != null) {
                cloneCurrent.setNext(new Node(next.value(), null));
                cloneCurrent = cloneCurrent.next();
                next = next.next();
            }

            return cloneHead;
        }
    }

    /**
     * <pre>
     * Algorithm:
     * 1. find middle of the linked list using fast and slow pointer:
     *    - move slow pointer one step forward, fast - two steps until fast is not null
     * 2. reverse second half using 3 pointers
     *    - init pointers: prev=null, next=null, current=head
     *    - store next=head.next, link current.next=prev
     *    - link prev=current, current=next until current!=null
     *    - result in the prev
     * 3. compare second half with the first half
     * 4. before returning the result - reverse second half back
     *
     * Example:
     * 1->2->3->2->1
     * 1. Find middle:
     *    - slow:3(3), fast:5(1)
     * 2. Reverse second half (3->2->1), p:null,c:3->2->1,n:null
     *    - n:2->1,c:3 -> p:3,c:2->1
     *    - n:1, c:2->3 -> p:2->3,c:1
     *    - n:null, c:1->2->3 -> p:1->2->3, c:null
     * 3. Compare halves: same -> palindrome
     * 4. Reverse second half back (1->2->3), p:null,c:1->2->3,n:null -> p:3->2->1
     *
     * </pre>
     */
    boolean isPalindrome(Node head) {
        // 1. Find the middle of the linked list
        Node middleElement = findMiddleElement(head);

        // 2. Reverse second half
        Node reversedSecondHalf = reverseLinkedList(middleElement);

        // 3. Compare reversed second half with the first half
        boolean contentEqual = isListsEquals(head, reversedSecondHalf);

        // 4. Reverse back second half
        reverseLinkedList(reversedSecondHalf);

        return contentEqual;
    }

    private static boolean isListsEquals(Node first, Node second) {
        while (second != null) {
            if (!Objects.equals(first.value(), second.value())) {
                return false;
            }
            first = first.next();
            second = second.next();
        }

        return true;
    }

    private static Node reverseLinkedList(Node middleElement) {
        Node prev = null;
        Node current = middleElement;
        Node next;
        while (current != null) {
            next = current.next();
            current.setNext(prev);

            prev = current;
            current = next;
        }
        return prev;
    }

    private static Node findMiddleElement(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next() != null) {
            slow = slow.next();
            fast = fast.next().next();
        }
        return slow;
    }

    static Stream<Arguments> palindromeTestCases() {
        return Stream.of(
                // minimum args palindrome
                Arguments.of(
                        new Node(1, new Node(1, null)),
                        true),
                // even num of nodes palindrome
                Arguments.of(
                        new Node(1, new Node(2, new Node(3, new Node(3, new Node(2, new Node(1, null)))))),
                        true),
                // odd num of nodes palindrome
                Arguments.of(
                        new Node(1, new Node(2, new Node(3, new Node(4, new Node(3, new Node(2, new Node(1, null))))))),
                        true),
                // even num of nodes non-palindrome
                Arguments.of(
                        new Node(1, new Node(2, new Node(3, new Node(4, new Node(2, new Node(1, null)))))),
                        false),
                // odd num of nodes non-palindrome
                Arguments.of(
                        new Node(1, new Node(2, new Node(3, new Node(2, new Node(2, null))))),
                        false),
                // minimum args non-palindrome
                Arguments.of(
                        new Node(1, new Node(2, null)),
                        false));
    }

    @ParameterizedTest
    @MethodSource("palindromeTestCases")
    void testLinkedListPalindrome(Node head, boolean expectedResult) {
        boolean result = isPalindrome(head);

        assertEquals(expectedResult, result);
    }

    static Stream<Arguments> noChangesTestCases() {
        return Stream.of(
                // odd num of nodes
                Arguments.of(new Node(1, new Node(2, new Node(3, new Node(4, new Node(3, new Node(2, new Node(1, null)))))))),
                // even num of nodes
                Arguments.of(new Node(1, new Node(2, new Node(3, new Node(3, new Node(2, new Node(1, null))))))));
    }

    @ParameterizedTest
    @MethodSource("noChangesTestCases")
    void testListNotChanges(Node head) {
        // Given: linked list and it's deep copy
        Node copy = head.deepCopy();

        // When: check if linked list is palindrome
        isPalindrome(head);

        // Then: linked list should not change
        assertEquals(copy, head);
    }
}
