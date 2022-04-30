import java.util.HashMap;
import java.util.Map;

/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII {

    public int rob(int[] nums) {
        return rob(nums, new HashMap<>(), 0, false);
    }

    public int rob(int[] nums, Map<String, Integer> cache, int currentIndex, boolean isFirstHouseRobbed) {
        if(currentIndex >= nums.length){
            return 0;
        }

        if(currentIndex == nums.length - 1) {
            if(isFirstHouseRobbed)
                return 0;
            else
                return nums[currentIndex];
        }

        String Key = currentIndex + ":" + isFirstHouseRobbed;
        if(cache.containsKey(Key)){
            return cache.get(Key);
        }

        int includeCurrentHouseMoney = nums [currentIndex] + rob(nums, cache, currentIndex + 2, (currentIndex == 0 ? true : isFirstHouseRobbed));
        int excludeCurrentHouseMoney = rob(nums, cache, currentIndex + 1, isFirstHouseRobbed);

        cache.put(Key, Math.max(includeCurrentHouseMoney, excludeCurrentHouseMoney));
        return Math.max(includeCurrentHouseMoney, excludeCurrentHouseMoney);
    }
}
