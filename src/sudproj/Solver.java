package sudproj;
/**
 * Runs the sudoku legalities.
 * @author Samuel Högbom Aronsson and Oskar Hessler
 *
 */
public class Solver implements SudokuSolver {
	private int[][] board;
	/**
	 * constructs a empty sudoku board.
	 */
	public Solver() {// oklart om man får ha den men får massa warnings när jag gör min dokumentation utan den.
		board = new int[9][9];
	}

	@Override
	 /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved. 
     * @param sudoku board
     * @throws IllegalArgumentException if board is not 9x9
     */
	public void setBoard(int[][] board) throws IllegalArgumentException {
		if(board.length != 9) {
			throw new IllegalArgumentException();
		}
		for(int x=0; x<board.length; x++) {
			if(board[x].length != 9) {
				throw new IllegalArgumentException();
			}
		}
		this.board = new int[9][9];
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				this.board[x][y] = board[x][y];
			}
		}
		
	}
	/**
     * Get the sudoku board.
     * @return a copy of the sudoku board
     */
	@Override
	public int[][] getBoard() {
		int[][] b = new int[9][9];
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				b[x][y] = board[x][y];
			}
		}
		return b;
	}
    /**
     * Solves the current sudoku.
     * @return true if solution could be found
     */
	@Override
	public boolean solve() {
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				if(board[x][y] != 0) {
					if(!isLegal(x, y, board[x][y])) {
						return false;
					}
				}
			}
		}
		return solve(0, 0);
	}
	private boolean solve(int r, int c) {
		if(	(r == 9) && (c == 8)	) { // kollar att den inte är klar.
			return true;
		}
		
		if(r >= 9) {
			r = 0;
			c++;
		}
		if(board[r][c] != 0) { // kollar om anv'ndare lagt in skit och kollar om det 'r bra.
			if(isLegal(r, c, board[r][c])) {
				System.out.println(47+ " rad " + r + "col " + c);
				return solve(r+1, c);
			} else {
				System.out.println(49+ " rad " + r + "col " + c);
				return false;
			}
		}
		
		for(int tnr = 1 ; tnr <= 9; tnr++) {
			if(isLegal(r, c, tnr)) {
				board[r][c] = tnr;
				System.out.println(58 + " rad " + r + "col " + c);
				if(solve(r+1, c)) {
					return true;
				} else {
					board[r][c] = 0;
				}
			}
		}
		return false;
	}
	
	
    /**
     * Check if digit is legal on the current board.
     * @param row specifies which row
     * @param col specifies which colum
     * @param nbr specifies which number
     * @return true if legal
     */
	@Override
	public boolean isLegal(int row, int col, int nbr) {
		// TODO Auto-generated method stub
		if(((nbr>9)	||	(nbr<1)))	return false;
		for(int x = 0; x<board[row].length; x++) {
			if(board[row][x] == nbr) {
				if(x !=col) return false; // lite onödig om man använder programet rätt och inte anvvänder sig av set innan man kollar om det är lagligt nvm i solve beh;vs det.
			}
			if(board[x][col] == nbr) {
				if(x != row) return false; // lite onödig om man använder programet rätt och inte anvvänder sig av set innan man kollar om det är lagligt
			}
		}
		
		// räknar vilken 3x3 region och vart start punkterna ligger
		int rs = row - (row%3);
		int cs = col -  (col%3);
		
		for(int x = rs; x < rs+3; x++) {
			for(int y = cs; y < cs+3; y++) {
				if((board[x][y]==nbr) && (y !=col) && (x != row)) {
					return false;
				}
			}
		}
		return true;
	}
	 /**
     * get number from selected box.
     * @param row specifies which row
     * @param col secifies which colum
     * @return number from board
     * @throws IllegalArgumentException if row or col out of bounds
     */
	@Override
	public int get(int row, int col) throws IllegalArgumentException {
		if(row > 8 || col > 8) {
			throw new IllegalArgumentException();
		}
		return board[row][col];
	}
	 /**
     * puts the digit on the board.
     * @param row specifies which row
     * @param col specifes which colum
     * @param nbr specifes which number
     * @throws IllegalArgumentException if row or col out of bounds
     */
	@Override
	public void set(int row, int col, int nbr) throws IllegalArgumentException {
		if(row > 8 || col > 8) {
			throw new IllegalArgumentException();
		}
		board[row][col] = nbr;
	}
	/**
	 * Clears sukoku board.
	 */
	@Override
	public void clear() {
		int[][] b = new int[9][9];
		board = b;
	}

}
