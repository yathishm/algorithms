import java.util.HashMap;
import java.util.Map;

public class MinimumPathSum {
    public static void main(String[] args){
        MinimumPathSum minimumPathSum = new MinimumPathSum();
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        System.out.println(minimumPathSum.minPathSum(grid));
    }

    private static final String UNDER_SCORE = "_";

    public int minPathSum(int[][] grid) {
        return minPathSum(grid, 0, 0, new HashMap<String, Integer>());
    }

    /**
     * Dynamic programming memorization approach to find the minimum path sum
     * @param grid
     * @param currentRowIndex
     * @param currentColIndex
     * @param computedCache
     * @return
     */
    private int minPathSum(int[][] grid, int currentRowIndex, int currentColIndex, Map<String, Integer> computedCache){
        int maxSoFarRight = Integer.MAX_VALUE;
        int maxSoFarDown = Integer.MAX_VALUE;
        if(currentRowIndex == grid.length - 1 && currentColIndex == grid[0].length -1){
            return grid[currentRowIndex][currentColIndex];
        }
        // if already available in cache return immediately
        else if(computedCache.containsKey(currentRowIndex + UNDER_SCORE + currentColIndex)){
            return computedCache.get(currentRowIndex + UNDER_SCORE + currentColIndex);
        } else {
            // move towards right direction
            if(currentColIndex + 1 < grid[0].length){
                maxSoFarRight = minPathSum(grid, currentRowIndex, currentColIndex + 1, computedCache);
            }
            // move towards downward direction
            if(currentRowIndex + 1 < grid.length){
                maxSoFarDown = minPathSum(grid, currentRowIndex + 1, currentColIndex, computedCache);
            }
            // store the min sum for the current cell in the cache
            computedCache.put(currentRowIndex + UNDER_SCORE + currentColIndex, (grid[currentRowIndex][currentColIndex] + Math.min(maxSoFarRight, maxSoFarDown)));
        }
        return computedCache.get(currentRowIndex + UNDER_SCORE + currentColIndex);
    }
}
