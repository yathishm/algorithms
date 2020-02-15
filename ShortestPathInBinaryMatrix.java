/**
 * Shortest Path in Binary Matrix
 *
 * In an N by N square grid, each cell is either empty (0) or blocked (1).
 *
 * A clear path from top-left to bottom-right has length k if and only if it is composed of cells C_1, C_2, ..., C_k such that:
 *
 * Adjacent cells C_i and C_{i+1} are connected 8-directionally (ie., they are different and share an edge or corner)
 * C_1 is at location (0, 0) (ie. has value grid[0][0])
 * C_k is at location (N-1, N-1) (ie. has value grid[N-1][N-1])
 * If C_i is located at (r, c), then grid[r][c] is empty (ie. grid[r][c] == 0).
 * Return the length of the shortest such clear path from top-left to bottom-right.  If such a path does not exist, return -1.
 *
 * Example 1:
 *
 * Input: [[0,1],[1,0]]
 *
 *
 * Output: 2
 *
 *
 *
 * Example 2:
 *
 * Input: [[0,0,0],[1,1,0],[1,1,0]]
 *
 *
 * Output: 4
 */
public class ShortestPathInBinaryMatrix {

    private static class Result {
        int minShortestPath;
        Result(){
            minShortestPath = Integer.MAX_VALUE;
        }
    }
    private Result result = new Result();

    public static void main(String[] args){
        ShortestPathInBinaryMatrix shortestPathInBinaryMatrix = new ShortestPathInBinaryMatrix();
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {0, 0, 0}
        };
        System.out.println(shortestPathInBinaryMatrix.shortestPathBinaryMatrix(grid));
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        if(null == grid || grid[0][0] != 0){
            return -1;
        }
        boolean[][] markVisitedCell = new boolean[grid.length][grid[0].length];
        shortestPathBinaryMatrix(grid, 0, 0, markVisitedCell, 1);
        return (result.minShortestPath == Integer.MAX_VALUE) ? -1 : result.minShortestPath;
    }

    /**
     * Backtrack and explore all the eight adjacent cells of a current cell to find the shortest path from top left to bottom right in a matrix
     * @param grid
     * @param rowIndex
     * @param colIndex
     * @param markVisitedCell
     * @param maxPathSoFar
     */
    private void shortestPathBinaryMatrix(int[][] grid, int rowIndex, int colIndex, boolean[][] markVisitedCell, int maxPathSoFar){
        if(rowIndex == grid.length -1 && colIndex == grid[0].length -1 && grid[rowIndex][colIndex] == 0){
            result.minShortestPath = Math.min(maxPathSoFar , result.minShortestPath);
        }
        markVisitedCell[rowIndex][colIndex] = Boolean.TRUE;
        // backtrack top left
        if(rowIndex-1 >=0 && colIndex-1 >= 0 && !markVisitedCell[rowIndex -1][colIndex -1] && grid[rowIndex - 1][colIndex - 1] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex - 1, colIndex - 1, markVisitedCell, maxPathSoFar + 1);
        }
        // backtrack top middle
        if(rowIndex-1 >=0 && !markVisitedCell[rowIndex - 1][colIndex] && grid[rowIndex - 1][colIndex] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex - 1, colIndex, markVisitedCell, maxPathSoFar + 1);
        }
        // backtrack top right
        if(rowIndex-1 >=0 && colIndex+1 < grid[0].length && !markVisitedCell[rowIndex - 1][colIndex + 1] && grid[rowIndex - 1][colIndex + 1] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex - 1, colIndex + 1, markVisitedCell, maxPathSoFar + 1);
        }
        // backtrack left of current row
        if(rowIndex >=0 && colIndex-1 >=0 && !markVisitedCell[rowIndex][colIndex - 1] && grid[rowIndex][colIndex - 1] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex, colIndex - 1, markVisitedCell, maxPathSoFar + 1);
        }
        // backtrack right of current row
        if(rowIndex >=0 && colIndex+1 < grid[0].length && !markVisitedCell[rowIndex][colIndex + 1] && grid[rowIndex][colIndex + 1] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex, colIndex + 1, markVisitedCell, maxPathSoFar + 1);
        }
        // backtrack bottom left
        if(rowIndex+1 < grid.length && colIndex -1 >= 0 && !markVisitedCell[rowIndex + 1][colIndex - 1] && grid[rowIndex + 1][colIndex - 1] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex + 1, colIndex - 1, markVisitedCell, maxPathSoFar + 1);
        }
        // backtrack bottom middle
        if(rowIndex+1 < grid.length && !markVisitedCell[rowIndex + 1][colIndex] && grid[rowIndex + 1][colIndex] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex + 1, colIndex, markVisitedCell, maxPathSoFar + 1);
        }
        // backtrack bottom right
        if(rowIndex + 1 < grid.length && colIndex + 1 < grid[0].length && !markVisitedCell[rowIndex + 1][colIndex + 1] && grid[rowIndex + 1][colIndex + 1] == 0 ){
            shortestPathBinaryMatrix(grid , rowIndex + 1, colIndex + 1, markVisitedCell, maxPathSoFar + 1);
        }
        markVisitedCell[rowIndex][colIndex] = Boolean.FALSE;
    }
}
