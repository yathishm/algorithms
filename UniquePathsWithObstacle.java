import java.util.HashMap;
import java.util.Map;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 *
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * Output: 2
 * Explanation: There is one obstacle in the middle of the 3x3 grid above.
 * There are two ways to reach the bottom-right corner:
 * 1. Right -> Right -> Down -> Down
 * 2. Down -> Down -> Right -> Right
 * Example 2:
 *
 *
 * Input: obstacleGrid = [[0,1],[0,0]]
 * Output: 1
 *
 *
 * Constraints:
 *
 * m == obstacleGrid.length
 * n == obstacleGrid[i].length
 * 1 <= m, n <= 100
 * obstacleGrid[i][j] is 0 or 1.
 *
 */
public class UniquePathsWithObstacle {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        Map<String, Integer> cache = new HashMap<>();
        // return 1 when matrix contains only one element and that element is 0;
        if(obstacleGrid.length == 1 && obstacleGrid[0].length == 1 && obstacleGrid[0][0] == 0){
            return 1;
        }
        // return 0 when matrix contains only one element and that element is 1;
        if(obstacleGrid.length == 1 && obstacleGrid[0].length == 1 || obstacleGrid[0][0] == 1){
            return 0;
        }
        return uniquePathsWithObstacles(obstacleGrid,0, 0, cache);
    }

    /**
     * returns numbers of ways to reach bottom-right corner of the grid using dynamic programming memoization approach.
     * @param obstacleGrid
     * @param m
     * @param n
     * @param cache
     * @return
     */
    private int uniquePathsWithObstacles(int[][] obstacleGrid, int m, int n, Map<String, Integer> cache) {
        if(m == obstacleGrid.length - 1 && n == obstacleGrid[0].length - 1 && obstacleGrid[m][n] == 0)
            return 1;

        if(m >= obstacleGrid.length || n >= obstacleGrid[0].length || obstacleGrid[m][n] == 1)
            return 0;

        String cacheKey  = m + "-" + n;
        if(cache.containsKey(cacheKey)){
           return cache.get(cacheKey);
        }
        int temp = uniquePathsWithObstacles(obstacleGrid, m, n + 1, cache) + uniquePathsWithObstacles(obstacleGrid, m + 1, n, cache);
        cache.put(cacheKey , temp);
        return temp;
    }
}
