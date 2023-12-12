package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudproj.SudokuSolver;
import sudproj.Solver;

class SolveTest {
	private SudokuSolver s;
	
	@BeforeEach
	void setUp() throws Exception {
		s = new Solver();
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
		
	}

	@Test
	void testGetSetBoard() {
		int[][] board = new int[9][9];
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				board[x][y] = x+y;
			}
		}
		s.setBoard(board);
		assertArrayEquals(s.getBoard(), board, "boards are not equal");
		assertNotSame(s.getBoard(), board, "boards refer to each other 1");
		board = s.getBoard();
		assertNotSame(s.getBoard(), board, "board refer to each other 2");
		try {
			s.setBoard(null);
			fail("accepting wrong dimension boards");
		} catch(Exception IllegalArgumentException){ /* do nothing */ }
		board = null;
	}
	@Test
	void testClear() {
		int[][] zeroboard = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
			    {7, 8, 9, 1, 2, 3, 4, 5, 6},
			    {4, 5, 6, 9, 8, 7, 1, 2, 3}, 
			    {3, 1, 2, 8, 4, 5, 9, 6, 7}, 
			    {9, 6, 7, 3, 1, 2, 8, 4, 5}, 
			    {8, 4, 5, 6, 7, 9, 3, 1, 2},   
			    {2, 3, 1, 5, 9, 4, 6, 7, 8},
			    {6, 7, 8, 2, 3, 1, 5, 9, 4},
			    {5, 9, 4, 7, 6, 8, 2, 3, 1}
			    };
		 s.setBoard(board);
		 s.clear(); 
		assertArrayEquals(zeroboard, s.getBoard(), "not  getting correct value");
		 board = null;
	}
	@Test
	void testSingleGet() {
		int[][] board = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
			    {7, 8, 9, 1, 2, 3, 4, 5, 6},
			    {4, 5, 6, 9, 8, 7, 1, 2, 3}, 
			    {3, 1, 2, 8, 4, 5, 9, 6, 7}, 
			    {9, 6, 7, 3, 1, 2, 8, 4, 5}, 
			    {8, 4, 5, 6, 7, 9, 3, 1, 2},   
			    {2, 3, 1, 5, 9, 4, 6, 7, 8},
			    {6, 7, 8, 2, 3, 1, 5, 9, 4},
			    {5, 9, 4, 7, 6, 8, 2, 3, 1}
			    };
		s.setBoard(board);
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				assertEquals(board[x][y], s.get(x, y), "not  getting correct value");
			}
		}
		try {
			s.set(9, 0, 0);
			fail("accepting too big row");
		} catch(Exception IllegalArgumentException){ /* do nothing */ }
		try {
			s.set(0, 9, 0);
			fail("accepting too big col");
		} catch(Exception IllegalArgumentException){ /* do nothing */ }
		
	}
	
	@Test
	void testSingleSet() {
		int[][] zeroboard = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
			    {7, 8, 9, 1, 2, 3, 4, 5, 6},
			    {4, 5, 6, 9, 8, 7, 1, 2, 3}, 
			    {3, 1, 2, 8, 4, 5, 9, 6, 7}, 
			    {9, 6, 7, 3, 1, 2, 8, 4, 5}, 
			    {8, 4, 5, 6, 7, 9, 3, 1, 2},   
			    {2, 3, 1, 5, 9, 4, 6, 7, 8},
			    {6, 7, 8, 2, 3, 1, 5, 9, 4},
			    {5, 9, 4, 7, 6, 8, 2, 3, 1}
			    };
		s.setBoard(board);
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				s.set(x, y, 9);
				assertEquals(9, s.get(x, y), "not equal");
			}
		}
		s.setBoard(zeroboard);
		for(int x = 0; x < board.length; x++) {
			for(int y = 0; y < board[x].length; y++) {
				s.set(x, y, 9);
				assertEquals(9, s.get(x, y), "not equal");
			}
		}
		try {
			s.get(9, 0);
			fail("accepting too big row");
		} catch(Exception IllegalArgumentException){ /* do nothing */ }
		try {
			s.get(0, 9);
			fail("accepting too big col");
		} catch(Exception IllegalArgumentException){ /* do nothing */ }
	}
	
	@Test
	void testSolve() {
		int[][] zeroboard = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board1 = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
			    {7, 8, 9, 1, 2, 3, 4, 5, 6},
			    {4, 5, 6, 9, 8, 7, 1, 2, 3}, 
			    {3, 1, 2, 8, 4, 5, 9, 6, 7}, 
			    {9, 6, 7, 3, 1, 2, 8, 4, 5}, 
			    {8, 4, 5, 6, 7, 9, 3, 1, 2},   
			    {2, 3, 1, 5, 9, 4, 6, 7, 8},
			    {6, 7, 8, 2, 3, 1, 5, 9, 4},
			    {5, 9, 4, 7, 6, 8, 2, 3, 1}
			    };
		int[][] board2 = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{7, 8, 9, 1, 2, 3, 4, 5, 6},
				{4, 5, 6, 9, 0, 7, 1, 2, 3}, 
				{3, 1, 2, 8, 4, 5, 9, 6, 7}, 
				{9, 6, 7, 3, 1, 2, 8, 4, 5}, 
				{8, 4, 5, 6, 7, 9, 3, 1, 2},   
				{2, 3, 1, 5, 9, 4, 6, 7, 8},
				{6, 7, 8, 2, 3, 1, 5, 9, 4},
				{5, 9, 4, 7, 6, 8, 2, 3, 1}
		    	};
		int[][] badboard = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 1, 1}
			    };
		
		
		s.setBoard(board1);
		assertTrue(s.solve(), "not solving board 1");
		assertArrayEquals(board1, s.getBoard(), "solving a already solved board diffrently");
		s.setBoard(zeroboard);
		assertTrue(s.solve(), "not solving empty board");
		s.setBoard(board2);
		assertTrue(s.solve(), "not solving board 2");
		assertArrayEquals(board1, s.getBoard(), "solving board diffrently than the only possible solution");
		s.setBoard(badboard);
		assertFalse(s.solve(), "solving unsolvable board");
	}
	@Test
	void testIsLegal() {
		int[][] zeroboard = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };

		int[][] board2 = {
				{1, 2, 3, 4, 5, 6, 7, 8, 9},
				{7, 8, 9, 1, 2, 3, 4, 5, 6},
				{4, 5, 6, 9, 8, 7, 1, 2, 3}, 
				{3, 1, 2, 8, 4, 5, 9, 6, 7}, 
				{9, 6, 7, 3, 1, 2, 8, 4, 5}, 
				{8, 4, 5, 6, 7, 9, 3, 1, 2},   
				{2, 3, 1, 5, 9, 4, 6, 7, 8},
				{6, 7, 8, 2, 3, 1, 5, 9, 4},
				{5, 9, 4, 7, 6, 8, 2, 3, 1}
		    	};
		s.setBoard(zeroboard);
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				assertTrue(s.isLegal(x, y, 1), "not saying legal moves are legal on a zero board ");
			}
		}
		assertFalse(s.isLegal(0, 0, 0), "not saying illegal when number lower than 1");
		assertFalse(s.isLegal(0, 0, 10), "not illegal when number bigger than 9");
		s.setBoard(board2);
		for(int x = 0; x < 9; x++) {
			for(int y = 0; y < 9; y++) {
				assertTrue(s.isLegal(x, y, s.get(x, y)), "not saying legal moves are legal");
			}
		}
	}
	@Test
	void testCases() {
		int[][] board1 = {
				{5, 0, 0, 0, 0, 5, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board2 = {
				{5, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {5, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board3 = {
				{5, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 5, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board4 = {
				{1, 2, 3, 0, 0, 0, 0, 0, 0},
			    {4, 5, 6, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 7, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board5 = {
				{1, 2, 3, 0, 0, 0, 0, 0, 0},
			    {4, 5, 6, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},   
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0},
			    {0, 0, 0, 0, 0, 0, 0, 0, 0}
			    };
		int[][] board6 = {
				{0, 0, 8, 0, 0, 9, 0, 6, 2},
			    {0, 0, 0, 0, 0, 0, 0, 0, 5},
			    {1, 0, 2, 5, 0, 0, 0, 0, 0}, 
			    {0, 0, 0, 2, 1, 0, 0, 9, 0}, 
			    {0, 5, 0, 0, 0, 0, 6, 0, 0}, 
			    {6, 0, 0, 0, 0, 0, 0, 2, 8},   
			    {4, 1, 0, 6, 0, 8, 0, 0, 0},
			    {8, 6, 0, 0, 3, 0, 1, 0, 0},
			    {0, 0, 0, 0, 0, 0, 4, 0, 0}
			    };
		int[][] correctboard6 = {
				{5, 4, 8, 1, 7, 9, 3, 6, 2},
			    {3, 7, 6, 8, 2, 4, 9, 1, 5},
			    {1, 9, 2, 5, 6, 3, 8, 7, 4}, 
			    {7, 8, 4, 2, 1, 6, 5, 9, 3}, 
			    {2, 5, 9, 3, 8, 7, 6, 4, 1}, 
			    {6, 3, 1, 9, 4, 5, 7, 2, 8},   
			    {4, 1, 5, 6, 9, 8, 2, 3, 7},
			    {8, 6, 7, 4, 3, 2, 1, 5, 9},
			    {9, 2, 3, 7, 5, 1, 4, 8, 6}
			    };
		
		s.setBoard(board1);
		assertFalse(s.solve(), "testfall 2.1");
		s.setBoard(board2);
		assertFalse(s.solve(), "testfall 2.2");
		s.setBoard(board3);
		assertFalse(s.solve(), "testfall 2.3");
		s.setBoard(board4);
		assertFalse(s.solve(), "testfall 3.1");
		s.setBoard(board5);
		assertTrue(s.solve(), "testfall 3.2");
		s.setBoard(board1);
		assertFalse(s.solve(), "testfall 4.1");
		s.clear();
		assertTrue(s.solve(), "testfall 4.2");
		s.setBoard(board6);
		assertTrue(s.solve(), "testfall 5.1");
		assertArrayEquals(s.getBoard(), correctboard6, "testfall 5.2");
	}
}












