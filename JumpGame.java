import java.util.HashMap;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 */
class JumpGame {
    public boolean canJump(int[] nums) {
        return canJump(nums, 0, new HashMap<Integer, Boolean>());
    }

    /**
     * This method check if its possible to reach the last index using Dynamic programming memoization approach.
     * @param nums
     * @param currentIndex
     * @param cache
     * @return
     */
    private boolean canJump(int[] nums, int currentIndex, HashMap<Integer, Boolean> cache){
        if(currentIndex == nums.length - 1){
            return true;
        }

        boolean temp = false;
        for(int i = nums[currentIndex]; i > 0; i--){
            if(currentIndex + i <= nums.length - 1){
                if(cache.containsKey(currentIndex + i)){
                    return cache.get(currentIndex + i);
                }
                temp = canJump(nums, currentIndex + i, cache);
                cache.put(currentIndex , temp);
                if(temp) return true;
            }
        }
        return false;
    }
}
