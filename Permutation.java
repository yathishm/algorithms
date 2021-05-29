import java.util.ArrayList;
import java.util.List;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * Example 2:
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * Example 3:
 *
 * Input: nums = [1]
 * Output: [[1]]
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutation {

    private List<List<Integer>> resultList;

    public List<List<Integer>> permute(int[] nums) {
        resultList = new ArrayList<>();
        permute(arrayToList(nums), 0);
        return resultList;
    }

    private void permute(List<Integer> currentIterationList, int index){
        if(index >= currentIterationList.size() - 1){
            resultList.add(currentIterationList);
            return;
        }
        for(int i = index; i < currentIterationList.size(); i++){
            List<Integer> tempList =  new ArrayList<>(currentIterationList);
            int temp = tempList.get(index);
            tempList.set(index, tempList.get(i));
            tempList.set(i, temp);
            permute(tempList, index + 1);
        }
    }

    private List<Integer> arrayToList(int[] nums){
        List<Integer> tempList = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            tempList.add(nums[i]);
        }
        return tempList;
    }
}
