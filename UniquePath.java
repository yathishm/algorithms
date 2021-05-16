import java.util.HashMap;
import java.util.Map;

/**
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 *
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 *
 * How many possible unique paths are there?
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation:
 * From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 * Example 3:
 *
 * Input: m = 7, n = 3
 * Output: 28
 * Example 4:
 *
 * Input: m = 3, n = 3
 * Output: 6
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 * It's guaranteed that the answer will be less than or equal to 2 * 109.
 */
public class UniquePath {

    private static final String UNDERSCORE = "_";

    public int uniquePaths(int m, int n) {
        return findUniquePaths(m, n, 0, 0, new HashMap<String, Integer>());
    }

    /**
     * finds unique paths from top-left corner to bottom-right corner of a m x n grid using {dynamic programming memoization} approach.
     * @param m
     * @param n
     * @param currentRowIndex
     * @param currentColIndex
     * @param cacheOfUniquepaths
     * @return
     */
    private int findUniquePaths(int m, int n, int currentRowIndex, int currentColIndex, Map<String, Integer> cacheOfUniquepaths) {
        // Base condition, If the currentRowIndex and CurrentColIndex is at bottom-right corner of grid, then return 1.
        if(currentRowIndex == m - 1 && currentColIndex == n - 1)
            return 1;

        String cachekey = currentRowIndex + UNDERSCORE + currentColIndex;
        // check and return from the cache if the all the unique paths are already explored from currentRowIndex, CurrentColIndex to bottom-right corner of grid.
        if(cacheOfUniquepaths.containsKey(cachekey)){
            return cacheOfUniquepaths.get(cachekey);
        }

        int numberOfUniquePathsTraversingRightSide = 0;
        // explore all the unique paths by moving right side.
        if(currentColIndex + 1 < n){
            numberOfUniquePathsTraversingRightSide = findUniquePaths(m, n, currentRowIndex, currentColIndex + 1, cacheOfUniquepaths);
        }

        int numberOfUniquePathsTraversingBottomSide = 0;
        // explore all the unique paths by moving bottom side.
        if(currentRowIndex + 1 < m){
            numberOfUniquePathsTraversingBottomSide = findUniquePaths(m, n, currentRowIndex + 1, currentColIndex, cacheOfUniquepaths);
        }
        int totalnumberOfUniquePaths = numberOfUniquePathsTraversingRightSide + numberOfUniquePathsTraversingBottomSide;
        // cache the results of all the unique paths explored from currentRowIndex, CurrentColIndex to bottom-right corner of grid.
        cacheOfUniquepaths.put(cachekey, totalnumberOfUniquePaths);

        return totalnumberOfUniquePaths;
    }
}
