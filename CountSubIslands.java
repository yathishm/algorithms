import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.
 *
 * An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.
 *
 * Return the number of islands in grid2 that are considered sub-islands.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
 * Output: 3
 * Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
 * Example 2:
 *
 *
 * Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
 * Output: 2
 * Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
 * The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 *
 *
 * Constraints:
 *
 * m == grid1.length == grid2.length
 * n == grid1[i].length == grid2[i].length
 * 1 <= m, n <= 500
 * grid1[i][j] and grid2[i][j] are either 0 or 1.
 */
public class CountSubIslands {

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        List<List<IslandIndex>> grid2IslandList = new ArrayList<>();
        Set<String> visitedIndexes = new HashSet<>();

        // identify and store all the islands present in grid2
        for(int i = 0; i < grid2.length; i++) {
            for(int j = 0; j < grid2[0].length; j++) {
                String key = i + "-" + j;
                if(grid2[i][j] == 1 && !visitedIndexes.contains(key)){
                    grid2IslandList.add(bfs(grid2, i, j, visitedIndexes));
                }
            }
        }
        
        int result = 0;
        for(List<IslandIndex> grid2Island : grid2IslandList){
            boolean isSubislandOfGrid1 = true;
            for(IslandIndex islandIndex : grid2Island){
                if(grid1[islandIndex.i][islandIndex.j] != 1){
                    isSubislandOfGrid1 = false;
                }
            }
            if(isSubislandOfGrid1){
                result++;
            }
        }
        return result;
    }

    /**
     *finds and returns the island which exist in grid2 using breadth first search
     * @param grid2
     * @param row
     * @param col
     * @param visitedIndexes
     * @return
     */
    private List<IslandIndex> bfs(int[][] grid2, int row, int col, Set<String> visitedIndexes){
        List<IslandIndex> island = new ArrayList<>();

        Queue<IslandIndex> queue = new LinkedList<>();
        queue.add(new IslandIndex(row, col));
        visitedIndexes.add(row + "-" + col);

        while(!queue.isEmpty()){
            IslandIndex tos = queue.poll();
            island.add(tos);
            int i = tos.i;
            int j = tos.j;
            // check for left boundary
            if( j - 1 >= 0 && j - 1 < grid2[0].length && grid2[i][j - 1] == 1 && !visitedIndexes.contains(i + "-" + (j-1))) {
                queue.add(new IslandIndex(i, j - 1));
                visitedIndexes.add(i + "-" + (j-1));
            }
            // check for right boundary
            if( j + 1 >= 0 && j + 1 < grid2[0].length && grid2[i][j + 1] == 1 && !visitedIndexes.contains(i + "-" + (j+1))) {
                queue.add(new IslandIndex(i, j + 1));
                visitedIndexes.add(i + "-" + (j+1));
            }
            // check for top boundary
            if( i - 1 >= 0 && i - 1 < grid2.length && grid2[i - 1][j] == 1 && !visitedIndexes.contains((i-1) + "-" + j)) {
                queue.add(new IslandIndex(i - 1, j));
                visitedIndexes.add((i-1) + "-" + j);
            }
            // check for bottom boundary
            if( i + 1 >= 0 && i + 1 < grid2.length && grid2[i + 1][j] == 1 && !visitedIndexes.contains((i+1) + "-" + j)) {
                queue.add(new IslandIndex(i + 1, j));
                visitedIndexes.add((i+1) + "-" + j);
            }
        }
        return island;
    }

    static class IslandIndex {
        int i;
        int j;

        public IslandIndex(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
}
