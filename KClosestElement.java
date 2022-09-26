import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 *
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Example 1:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 *
 *
 * Constraints:
 *
 * 1 <= k <= arr.length
 * 1 <= arr.length <= 104
 * arr is sorted in ascending order.
 * -104 <= arr[i], x <= 104
 */
public class KClosestElement {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        PriorityQueue<Integer> priorityQueueOfClosestElements = new PriorityQueue<>();
        int i = 0;
        for(; i < k; i++){
            priorityQueueOfClosestElements.add(arr[i]);
        }
        for(; i < arr.length; i++){
           int topOfQueue = priorityQueueOfClosestElements.peek();
           if(Math.abs(arr[i] - x) < Math.abs(topOfQueue - x) || ((Math.abs(arr[i] - x) == Math.abs(topOfQueue - x)) && arr[i] < topOfQueue)){
               priorityQueueOfClosestElements.poll();
               priorityQueueOfClosestElements.add(arr[i]);
           }
        }
        List<Integer> resultList = new ArrayList<Integer>(priorityQueueOfClosestElements);
        Collections.sort(resultList);
        return resultList;
    }
}
