public class LongestIncreasingSubSequence {

    public static void main(String[] args){
        LongestIncreasingSubSequence longestIncreasingSubSequence = new LongestIncreasingSubSequence();
        int[] nums = {1,3,6,7,9,4,10,5,6};
        System.out.println(longestIncreasingSubSequence.lengthOfLIS(nums));
    }

    /**
     * Given an unsorted array of integers, find the length of longest increasing subsequence.
     *
     * Example
     *
     * Input: [10,9,2,5,3,7,101,18]
     * Output: 4
     * Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0){
            return 0;
        }
        int[] subSequenceLengthArray = new int[nums.length];
        int i = 0 , j = 1;
        initialize(subSequenceLengthArray);
            while(i < j && j < nums.length){
                if(nums[i] < nums[j]){
                    subSequenceLengthArray[j] = Math.max(subSequenceLengthArray[j], subSequenceLengthArray[i] + 1);
                }
                if(i+1 == j){
                    i = 0;
                    j++;
                }else{
                    i++;
                }
            }
            return findMax(subSequenceLengthArray);
    }

    /**
     * Initialize the array which holds the length of increasing subsequence to 1 as each element is itself a increasing subsequence
     * @param subSequenceLengthArray
     */
    private void initialize(int[] subSequenceLengthArray){
        for(int i = 0; i < subSequenceLengthArray.length; i++){
            subSequenceLengthArray[i] = 1;
        }
    }

    /**
     * return max element in subsequence array
     * @param subSequenceLengthArray
     * @return
     */
    private int findMax(int[] subSequenceLengthArray){
        int max = 0;
        for(int i =0; i < subSequenceLengthArray.length; i++){
            max = Math.max(max, subSequenceLengthArray[i]);
        }
        return max;
    }
}
