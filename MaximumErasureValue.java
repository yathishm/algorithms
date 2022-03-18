import java.util.HashMap;
import java.util.Map;

/**
 * You are given an array of positive integers nums and want to erase a subarray containing unique elements. The score you get by erasing the subarray is equal to the sum of its elements.
 *
 * Return the maximum score you can get by erasing exactly one subarray.
 *
 * An array b is called to be a subarray of a if it forms a contiguous subsequence of a, that is, if it is equal to a[l],a[l+1],...,a[r] for some (l,r).
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [4,2,4,5,6]
 * Output: 17
 * Explanation: The optimal subarray here is [2,4,5,6].
 * Example 2:
 *
 * Input: nums = [5,2,1,2,5,2,1,2,5]
 * Output: 8
 * Explanation: The optimal subarray here is [5,2,1] or [1,2,5].
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 */
public class MaximumErasureValue {

    /**
     * Sliding window on the given input array elements to calculate the maximum score contiguous subsequence subarray
     * @param nums
     * @return
     */
    public int maximumUniqueSubarray(int[] nums) {
        Map<Integer, Integer> uniqueElementIndexMap = new HashMap<>();
        int maxScore = 0, tempScore = 0;
        int startIndex = 0;
        for(int i = 0; i < nums.length; i++){
            Integer previousIndexOfCurrentElement = uniqueElementIndexMap.get(nums[i]);
            if(null != previousIndexOfCurrentElement  && previousIndexOfCurrentElement >= startIndex ){
                maxScore = Math.max(maxScore, tempScore);
                tempScore = 0;
                startIndex = previousIndexOfCurrentElement + 1;
                int j = startIndex;
                while(j < i) tempScore += nums[j++];
            }
            uniqueElementIndexMap.put(nums[i], i);
            tempScore += nums[i];
        }
        maxScore = Math.max(maxScore, tempScore);
        return maxScore;
    }
}
