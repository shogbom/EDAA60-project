package sudproj;

/**
 * Sudoku solving template.
 * @author Marcus Klang
 *
 */
public interface SudokuSolver{
    /**
     * Set sudoku board, numbers 1-9 are fixed values, 0 is unsolved. 
     * @param board a board to copy values from
     * @throws IllegalArgumentException if board is invalid, e.g. not 9x9
     */
    void setBoard(int[][] board);
    
    /**
     * Get a copy of the sudoku board
     * @return Returns copy of board
     */
    int[][] getBoard();

    /**
     * Solve soduko
     * @return true if solution could be found
     */
    boolean solve();

    /**
     * Check if digit is legal on the current board
     * @param row specifiles which row to check
     * @param col specifies which colum
     * @param nbr specifies which number to check
     * @return true if legal
     */
    boolean isLegal(int row, int col, int nbr);

    /**
     * Get number on board
     * @param row specifiles which row
     * @param col specifies which colum
     * @return number on board
     */
    int get(int row, int col);

    /**
     * Set number on board, numbers 1-9 are fixed values, 0 is unsolved. 
     * @param row specifies which row
     * @param col specifies which colum
     * @param nbr vilken siffra
     */
    void set(int row, int col, int nbr);

    /**
     * Clear the board
     */
    void clear();
}