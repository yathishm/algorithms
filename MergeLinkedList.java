import java.util.PriorityQueue;

public class MergeLinkedList {

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode retListNode = null, current = null;

        if(null == lists || lists.length <= 0){
            return retListNode;
        }
        PriorityQueue<ListNodeIndex> priorityQueue = new PriorityQueue<>(lists.length);
        initializeQueue(lists, priorityQueue);
        while( !priorityQueue.isEmpty() ){
            ListNodeIndex tempListNodeIndex = priorityQueue.remove();
            if(retListNode == null){
                retListNode = tempListNodeIndex.listNode;
                current = retListNode;
            }else{
               current.next = tempListNodeIndex.listNode;
               current = current.next;
            }
            if(null != lists[tempListNodeIndex.indexInListNodeArray]){
                removeandAddToPriorityQueue(lists, priorityQueue, tempListNodeIndex.indexInListNodeArray);
            }
        }
        return retListNode;
    }

    /**
     * Remove first element from each index from lists array and add it to priorityQueue
     * @param lists
     * @param priorityQueue
     */
    private void initializeQueue(ListNode[] lists, PriorityQueue<ListNodeIndex> priorityQueue){
        for(int i =0; i < lists.length; i++){
            removeandAddToPriorityQueue(lists, priorityQueue, i);
        }
    }

    /**
     * remove ListNode from list[index] array and add it to priorityQueue
     * @param lists
     * @param priorityQueue
     * @param index
     */
    private void removeandAddToPriorityQueue(ListNode[] lists, PriorityQueue<ListNodeIndex> priorityQueue, int index){
        if(lists[index] != null){
            ListNode temp = lists[index];
            lists[index] = lists[index].next;
            temp.next =null;
            ListNodeIndex listNodeIndex = new ListNodeIndex(temp, index);
            priorityQueue.add(listNodeIndex);
        }
    }

    /**
     * This class contains the ListNode and the array index it belongs in the ListNode[] array
     */
    static class ListNodeIndex implements Comparable<ListNodeIndex>{
        ListNode listNode;
        int indexInListNodeArray;

        ListNodeIndex(ListNode listNode, int indexInListNodeArray){
            this.listNode = listNode;
            this.indexInListNodeArray = indexInListNodeArray;
        }

        @Override
        public int compareTo(ListNodeIndex listNodeIndex) {
            return Integer.compare(this.listNode.val, listNodeIndex.listNode.val);
        }
    }

    /**
     * print the list elements
     * @param listNode
     */
    private void dispaly(ListNode listNode){
        while(null != listNode){
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
    }

    public static void main(String[] args){
       // Input:
            //  [
            //      1->4->5,
            //      1->3->4,
            //      2->6
            //   ]
        //  Output: 1->1->2->3->4->4->5->6
        MergeLinkedList mergeLinkedList = new MergeLinkedList();
        ListNode[] lists = new ListNode[3];

        ListNode listNode_00 = new ListNode(1);
        ListNode listNode_01 = new ListNode(4);
        listNode_00.next = listNode_01;
        ListNode listNode_02 = new ListNode(5);
        listNode_01.next = listNode_02;
        lists[0] = listNode_00;

        ListNode listNode_10 = new ListNode(1);
        ListNode listNode_11 = new ListNode(3);
        listNode_10.next = listNode_11;
        ListNode listNode_12 = new ListNode(4);
        listNode_11.next = listNode_12;
        lists[1] = listNode_10;

        ListNode listNode_20 = new ListNode(2);
        ListNode listNode_21 = new ListNode(6);
        listNode_20.next = listNode_21;
        lists[2] = listNode_20;

        ListNode resultList = mergeLinkedList.mergeKLists(lists);
        mergeLinkedList.dispaly(resultList);
    }
}

    /**
     * Definition for Singly Linked List
    */
    class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
