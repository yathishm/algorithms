/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the nodes of the list from position left to position right, and return the reversed list.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: head = [1,2,3,4,5], left = 2, right = 4
 * Output: [1,4,3,2,5]
 * Example 2:
 *
 * Input: head = [5], left = 1, right = 1
 * Output: [5]
 *
 *
 * Constraints:
 *
 * The number of nodes in the list is n.
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */
public class ReverseLinkedListII {

    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right) return head;
        return reverseBetween(head, head, left, right, 1,null, null, null);
    }

    /**
     * In a single pass reverses the nodes of the list from position left to position right in a recursive method
     * @param head
     * @param currentNode
     * @param left
     * @param right
     * @param currentCount
     * @param firstLeftNode
     * @param previousNode
     * @param reversedNode
     * @return ListNode
     */
    private ListNode reverseBetween(ListNode head, ListNode currentNode, int left, int right, int currentCount, ListNode firstLeftNode, ListNode previousNode, ListNode reversedNode) {
        if(null == currentNode) return null;
        if(currentCount >= left && currentCount < right){
            if(null != previousNode)
                previousNode.next = currentNode.next;

            ListNode tempNode = currentNode.next;
            currentNode.next = reversedNode;
            reversedNode = currentNode;
            if(currentCount == left)
                firstLeftNode = currentNode;
            return reverseBetween(head, tempNode, left, right, currentCount + 1, firstLeftNode, previousNode, reversedNode);
        }
        else if(currentCount == right){
            currentCount++;
            ListNode tempNode = currentNode.next;
            currentNode.next = reversedNode;
            firstLeftNode.next = tempNode;
            if(null != previousNode)
                previousNode.next = currentNode;
            else
                head = currentNode;
        }
        else if(currentCount <= right){
            return reverseBetween(head, currentNode.next, left, right, currentCount + 1, firstLeftNode, currentNode, reversedNode);
        }
        return head;
    }
}

/**
 * Definition for singly-linked list.
 */
class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

