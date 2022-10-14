import java.util.HashMap;
import java.util.Map;


/**
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
 *
 *
 *
 * Example 1:
 *
 *
 * Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 * Example 2:
 *
 *
 * Input: matrix = [[3,4,5],[3,2,6],[2,2,1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 * Example 3:
 *
 * Input: matrix = [[1]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 200
 * 0 <= matrix[i][j] <= 231 - 1
 */
public class LongestIncreasingPathMatrix {

    public int longestIncreasingPath(int[][] matrix) {
        int result = 0;
        Map<String, Integer> cache = new HashMap<>();
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                result = Math.max( result , longestIncreasingPath(matrix, i, j, cache) );
            }
        }
        return result + 1;
    }


    private int longestIncreasingPath(int[][] matrix, int currentRow, int currentColumn, Map<String, Integer> cache) {

        String key = currentRow + "-" + currentColumn;
        if(cache.containsKey(key))
            return cache.get(key);

        int currentCellValue = matrix[currentRow][currentColumn];

        // top move
        int topMoveLength = 0;
        if(isValidMove(matrix, currentRow - 1, currentColumn) && matrix[currentRow - 1][currentColumn] > currentCellValue){
            topMoveLength = 1 + longestIncreasingPath(matrix,  currentRow - 1, currentColumn, cache);
        }

        // bottom move
        int bottomMoveLength = 0;
        if(isValidMove(matrix, currentRow + 1, currentColumn) && matrix[currentRow + 1][currentColumn] > currentCellValue){
            bottomMoveLength = 1 + longestIncreasingPath(matrix,  currentRow + 1, currentColumn, cache);
        }

        // left move
        int leftMoveLength = 0;
        if(isValidMove(matrix, currentRow, currentColumn - 1) && matrix[currentRow][currentColumn - 1] > currentCellValue){
            leftMoveLength = 1 + longestIncreasingPath(matrix,  currentRow, currentColumn - 1, cache);
        }

        // right move
        int rightMoveLength = 0;
        if(isValidMove(matrix, currentRow, currentColumn + 1) && matrix[currentRow][currentColumn + 1] > currentCellValue){
            rightMoveLength = 1 + longestIncreasingPath(matrix,  currentRow, currentColumn + 1, cache);
        }

        cache.put(key, Math.max(Math.max(Math.max(topMoveLength, bottomMoveLength), leftMoveLength), rightMoveLength) );

        return cache.get(key);
    }

    private boolean isValidMove(int[][] matrix, int row, int column){
        if(row >= matrix.length || row < 0)
            return false;
        if(column >= matrix[0].length || column < 0)
            return false;
        return true;
    }
}
