public class WordSearch2DArray {

    public static void main(String[] args){
        WordSearch2DArray wordSearch2DArray = new WordSearch2DArray();
        char[][] board = {
                {'A','B','C','E', 'T'},
                {'S','F','C','S','T'},
                {'A','D','E','E','T'}
                        };
        System.out.println(" Can the given word be constructed from letters of sequentially adjacent cell (horizontally or vertically neighboring) : " + wordSearch2DArray.exist(board, "ABCCSTT"));
    }

    /**
     * Check if the word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
     * The same letter cell may not be used more than once.
     * Given word = "ABCCED", return true.
     * Given word = "SEE", return true.
     * Given word = "ABCB", return false.
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if(null == word || word.length() == 0 || board == null){
            return Boolean.FALSE;
        }
        boolean[][] isAlreadyVisited = new boolean[board.length][board[0].length];
        for(int i =0; i < board.length; i++){
            for(int j =0; j < board[0].length; j++){
                if(word.charAt(0) == board[i][j] && isWordExistsIn2DArray(i, j, word, 0, board, isAlreadyVisited)){
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    /**
     * recuresively find if the word can be constructed from the 2D array
     * @param row
     * @param col
     * @param word
     * @param wordIndex
     * @param board
     * @param isAlreadyVisited
     * @return
     */
    private boolean isWordExistsIn2DArray(int row, int col, String word, int wordIndex, char[][] board, boolean[][] isAlreadyVisited){
        // base condition to stop
        if(wordIndex == word.length()-1){
            return Boolean.TRUE;
        }
        if(board[row][col] == word.charAt(wordIndex)){
            //mark the current row/col as visited
            isAlreadyVisited[row][col] = Boolean.TRUE;
            //check towards left
            boolean isExists = Boolean.FALSE;
            if(col-1 >= 0 && board[row][col-1] == word.charAt(wordIndex+1) && !isAlreadyVisited[row][col-1]) {
                isExists = isWordExistsIn2DArray(row, col-1, word, wordIndex + 1, board, isAlreadyVisited);
            }
            //check towards right
            if(!isExists && col+1 < board[row].length && board[row][col+1] == word.charAt(wordIndex+1) && !isAlreadyVisited[row][col+1])  {
                isExists = isWordExistsIn2DArray(row, col+1, word, wordIndex + 1, board, isAlreadyVisited);
            }
            // check towards top
            if(!isExists && row-1 >= 0 && board[row-1][col] == word.charAt(wordIndex+1) && !isAlreadyVisited[row-1][col]) {
                isExists = isWordExistsIn2DArray(row-1, col, word, wordIndex + 1, board, isAlreadyVisited);
            }
            // check towards bottom
            if(!isExists && row+1 < board.length && board[row+1][col] == word.charAt(wordIndex+1) && !isAlreadyVisited[row+1][col])  {
                isExists = isWordExistsIn2DArray(row+1, col, word, wordIndex + 1, board, isAlreadyVisited);
            }
            // mark the row back to not visited - cleanup
            isAlreadyVisited[row][col] = Boolean.FALSE;
            return isExists;
        }
        return Boolean.FALSE;
    }
}
