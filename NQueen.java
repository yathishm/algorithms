import java.util.ArrayList;
import java.util.List;

public class NQueen {

    private boolean[][] chessBoard;

    public static void main(String[] args){
        NQueen nQueen = new NQueen();
        System.out.println(nQueen.solveNQueens(20));
    }

    /**     print all the possible distinct solution of n-Queen
    *    [
    *            [".Q..",  // Solution 1
    *            "...Q",
    *            "Q...",
    *            "..Q."],
    *
    *            ["..Q.",  // Solution 2
    *            "Q...",
    *            "...Q",
    *            ".Q.."]
     *     ]
    */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        chessBoard = new boolean[n][n];
        backTrackChessBoard(0, result);
        return result;
    }

    /**
     * Backtrack to find n queen
     * @param i
     * @param result
     */
    private void backTrackChessBoard(int i, List<List<String>> result){
        for(int j = 0 ;j < chessBoard.length; j++){
                if( ! isQueenAlreadyPresent(i,j)){
                    chessBoard[i][j] = Boolean.TRUE;
                    if(i + 1 < chessBoard.length){
                        backTrackChessBoard(i + 1, result);
                    }
                    if(i == chessBoard.length - 1){
                        result.add(addValidNQueenBoard());
                    }
                }
                chessBoard[i][j] = Boolean.FALSE;
            }
        }

    private boolean isQueenAlreadyPresent(int row, int col){
        // check for same row
        for(int j= 0; j < chessBoard.length; j++){
            if(chessBoard[row][j])
                return Boolean.TRUE;
        }
        // check for same column
        for(int i= 0; i < chessBoard.length; i++){
            if(chessBoard[i][col])
                return Boolean.TRUE;
        }
        // check for adjacent top left
        for(int i = row-1, j = col-1; i>=0 && j>=0 ; i--,j--){
            if(chessBoard[i][j])
                return Boolean.TRUE;
        }
        // check for adjacent bottom left
        for(int i = row+1, j = col-1; i <= chessBoard.length-1 && j >= 0; i++,j--){
            if(chessBoard[i][j])
                return Boolean.TRUE;
        }

        // check for adjacent top right
        for(int i = row-1, j = col+1; j <= chessBoard.length-1 && i >= 0; i--,j++){
            if(chessBoard[i][j])
                return Boolean.TRUE;
        }
        // check for adjacent bottom right
        for(int i = row+1,j = col+1; i <= chessBoard.length-1 && j <= chessBoard.length-1; i++,j++){
            if (chessBoard[i][j])
                return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * add the current board to the list in below format
     *    [".Q..",
     *      "...Q",
     *      "Q...",
     *      "..Q."]
     * @return List<String>
     */
    private List<String> addValidNQueenBoard(){
        List<String> result = new ArrayList<>();
        for(int i =0; i < chessBoard.length; i++){
            StringBuilder builder = new StringBuilder();
            for(int j =0; j < chessBoard.length; j++){
                builder.append(chessBoard[i][j] ? "Q" : ".");
            }
            result.add(builder.toString());
        }
        return result;
    }

}
