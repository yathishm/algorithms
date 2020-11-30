import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 */
public class KLargestElement {

    public int findKthLargest(int[] nums, int k) {
        if(k > nums.length){
            return -1;
        }
        // Initialize min heap PriorityQueue of size "K"
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        // add first "k" elements in the PriorityQueue
        for(int i = 0; i < k; i++){
            priorityQueue.add(nums[i]);
        }

        List<Integer> tempListOfPriorityQueue = new ArrayList<>(k);
        for(int j = k; j < nums.length; j++){
            // When the PriorityQueue contains elements [1,1,2,1] and we try to add "1", In this case we need to remove 2 and add 1.
            // This peice of code is to handle that.
            if(priorityQueue.peek() == nums[j]){
                tempListOfPriorityQueue.add(nums[j]);
                for(int x = 0; x < k - 1; x++){
                    tempListOfPriorityQueue.add(priorityQueue.poll());
                }
                priorityQueue.poll();
                priorityQueue.addAll(tempListOfPriorityQueue);
                tempListOfPriorityQueue.clear();
                // When the root element is lesser than the new element being add, remove the root element and add the new element.
            } else if(priorityQueue.peek() < nums[j]){
                priorityQueue.poll();
                priorityQueue.add(nums[j]);
            }
        }
        return priorityQueue.peek();
    }
}
