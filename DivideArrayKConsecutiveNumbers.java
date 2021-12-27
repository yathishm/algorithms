import java.util.Collections;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * Given an array of integers nums and a positive integer k, find whether it is possible to divide this array into sets of k consecutive numbers.
 *
 * Return true if it is possible. Otherwise, return false.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,3,4,4,5,6], k = 4
 * Output: true
 * Explanation: Array can be divided into [1,2,3,4] and [3,4,5,6].
 * Example 2:
 *
 * Input: nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
 * Output: true
 * Explanation: Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
 * Example 3:
 *
 * Input: nums = [1,2,3,4], k = 3
 * Output: false
 * Explanation: Each array should be divided in subarrays of size 3.
 *
 *
 * Constraints:
 *
 * 1 <= k <= nums.length <= 105
 * 1 <= nums[i] <= 109
 *
 */
public class DivideArrayKConsecutiveNumbers {

    /**
     * 1) Iterate through nums array and add it to TreeMap with the count of each number
     * 2) Iterate through the above created map and do the following
     *      a) Poll first 4 elements one by one from the TreeMap and add it to PriorityQueue(Max Heap) if the current polled number is consecutive to the number present in the top of the PriorityQueue heap.
     *      b) If the size of the PriorityQueue is less than K, return false (This indicated that the numbers are not consecutive)
     *      c) Iterate through the PriorityQueue, Insert the elements back to TreeMap by decrementing the value of the count only if the count of the elements are greated than 1
     *      d) Repeat step a) through c) until the TreeMap is empty or size of TreeMap is less than K
     * 3) Return True is the TreeMap is empty or False otherwise.
     * @param nums
     * @param k
     * @return boolean
     */
    public boolean isPossibleDivide(int[] nums, int k) {
        if(nums.length < k){
            return Boolean.FALSE;
        }
        TreeMap<Integer, Integer> sortedNumsMap = new TreeMap<>();
        for(int i : nums){
            sortedNumsMap.merge(i, 1, Integer::sum);
        }
        Queue<Map.Entry<Integer, Integer>> queueOfKConsecutiveNumbers = new PriorityQueue<>(Map.Entry.comparingByKey(Collections.reverseOrder()));

        while(sortedNumsMap.size() >= k){
            int temp = k;
            while(temp > 0 && sortedNumsMap.size() > 0){
                Map.Entry<Integer, Integer> topOfHeap = queueOfKConsecutiveNumbers.peek();
                if(null == topOfHeap || sortedNumsMap.firstEntry().getKey().equals(topOfHeap.getKey() + 1)){
                    queueOfKConsecutiveNumbers.add(sortedNumsMap.pollFirstEntry());
                }
                temp--;
            }
            if(queueOfKConsecutiveNumbers.size() < k) return Boolean.FALSE;
            while(!queueOfKConsecutiveNumbers.isEmpty()){
                Map.Entry<Integer, Integer> topOfHeap = queueOfKConsecutiveNumbers.poll();
                if(topOfHeap.getValue() > 1)
                    sortedNumsMap.put(topOfHeap.getKey(), topOfHeap.getValue() - 1);
            }
        }

        if(!sortedNumsMap.isEmpty()) return Boolean.FALSE;
        return Boolean.TRUE;
    }
}
