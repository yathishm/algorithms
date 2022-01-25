/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 *
 * Integers in each row are sorted from left to right.
 * The first integer of each row is greater than the last integer of the previous row.
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
 * Output: true
 * Example 2:
 *
 *
 * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
 * Output: false
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -104 <= matrix[i][j], target <= 104
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][0] <= target && matrix[i][matrix[0].length - 1] >= target){
                return binarySearch(matrix, target, i);
            }
        }
        return false;
    }

    /**
     * returns true if the target is found by doing a binary search
     * returns false, otherwise
     * @param matrix
     * @param target
     * @param row
     * @return
     */
    private boolean binarySearch(int[][] matrix, int target, int row){
        int low = 0; int high = matrix[0].length - 1;

        while(low <= high){
            int mid = (high - low)/2 + low;
            if(matrix[row][mid] == target)
                return true;
            if(target > matrix[row][mid])
                low = mid + 1;
             else
                high = mid - 1;
        }
        return false;
    }
}
