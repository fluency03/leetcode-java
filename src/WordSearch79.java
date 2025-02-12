/**
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 *
 * For example,
 * Given board =
 *
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A','D','E','E']
 * ]
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 *
 */


Class Solution{
    public boolean exist(char board[][],String word){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(dfs(board,word,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(char board[][],String word,int i,int j,int index){
        if(index==word.length())return true;
        if(i<0  || i>=board.length || j<0 || j>=board.length||board[i][j]!=word.charAt(i))return false;
        char temp=board[i][j];
        board[i][j]="#";
        boolean found=dfs(board,word,i-1,j,index+1)||
                    dfs(board,word,i+1,j,index+1)||
            dfs(board,word,i,j-1,index+1)||
            dfs(board,word,i,j-1,index+1);
        board[i][j]=temp;
        return found;
    }
}
        
        
        
