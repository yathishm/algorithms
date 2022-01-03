/**
 * Given a binary array nums, you should delete one element from it.
 *
 * Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,0,1]
 * Output: 3
 * Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 * Example 2:
 *
 * Input: nums = [0,1,1,1,0,1,1,0,1]
 * Output: 5
 * Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 * Example 3:
 *
 * Input: nums = [1,1,1]
 * Output: 2
 * Explanation: You must delete one element.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 */
public class LongestSubarrayOfOne {

    /**
     * Uses Sliding window approach to find the size of longest subarray containing only 1's
     * @param nums
     * @return
     */
    public int longestSubarray(int[] nums) {
        int startIndex = 0, result = Integer.MIN_VALUE;
        Integer lastDeletedElementIndex = null;
        int i = 0;
        for(; i < nums.length; i++){
            if(nums[i] == 0 && null != lastDeletedElementIndex){
                result = Integer.max(result, (lastDeletedElementIndex - startIndex) + (i - 1 - lastDeletedElementIndex));
                startIndex = lastDeletedElementIndex + 1;
                lastDeletedElementIndex = i;
            } else if(nums[i] == 0 && null == lastDeletedElementIndex){
                lastDeletedElementIndex = i;
            }
        }
        if(null != lastDeletedElementIndex){
            result = Integer.max(result, (lastDeletedElementIndex - startIndex) + (i - 1 - lastDeletedElementIndex));
        } else {
            result = Integer.max(result, i - startIndex);
        }
        // If the entire arrays contains only 1's then we should delete one element.
        if(result == nums.length) result--;
        return result;
    }
}
