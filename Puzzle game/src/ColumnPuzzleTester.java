import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class ColumnPuzzleTester {
	
	private static ColumnPuzzle cp;
	
	public static void main(String[] args) {
		testAcceptsDifferentNumberOfRows();
		testSetup();
		testSwap();
		testShuffle();
		testSetTitle();
		testIsSolved();
		testClosestBlackSquare();
//		testMousePressed();
	}
	
	private static void testAcceptsDifferentNumberOfRows() {
		int tests = 0;
		int failed = 0;
		
		printHeader("Testing Accepts Different Number of Rows");
		
		for(int i = 1; i < 5; i++) {
			try {
				ColumnPuzzle temp = new ColumnPuzzle(i);
				temp.window.setVisible(false);
				temp.window.dispose();
				temp = null;
			} catch(Exception e) {
				sop("ERROR! Tried to initialize ColumnPuzzle with "+i+" rows, java said: "+e);
				failed++;
			} finally {
				tests++;
			}
		}
		
		printStats(tests, failed);
	}
	
	private static void testSetup() {
		int tests = 0;
		int failed = 0;
		
		printHeader("Testing Setup");
		
		// make sure setup actually works and uses the correct number of rows
		for(int i = 1; i < 5; i++) {
			try {
				ColumnPuzzle temp = new ColumnPuzzle(i);
				temp.window.setVisible(false);
				temp.window.dispose();
				
				if(temp.grid.length != i) {
					sop("FAILED! Setup did not create the correct dimensions for the grid");
					failed++;
				}
				
				temp = null;
			} catch(Exception e) {
				sop("ERROR! Tried to run setup with "+i+" rows, java said: "+e);
				failed++;
			} finally {
				tests++;
			}
		}
		
		// make sure colors are in the right order to start - only shuffle in shuffle
		try {
			Color[] colorsList = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA};
			
			ColumnPuzzle temp = new ColumnPuzzle(3);
			temp.setup(3);
			temp.window.setVisible(false);
			temp.window.dispose();
			
			for(int i = 0; i < temp.grid[0].length; i++) {
				if(!temp.grid[0][i].equals(colorsList[i])) {
					sop("FAILED! Did not insert original colors in the correct order");
					failed++;
					break;
				}
			}
		} catch(Exception e) {
			sop("ERROR! Tried to make sure colors were in the correct order, but java said: "+e);
			failed++;
		} finally {
			tests++;
		}
		
		// make sure the last one is blank (black)
		try {
			ColumnPuzzle temp = new ColumnPuzzle(3);
			temp.setup(3);
			temp.window.setVisible(false);
			temp.window.dispose();
			
			if(!temp.grid[temp.grid.length-1][5].equals(Color.BLACK)) {
				sop("FAILED! The bottom-right block should be black, was "+ temp.grid[temp.grid.length-1][5]);
				failed++;
			}
			
			temp = null;
		} catch(Exception e) {
			sop("ERROR! Tried to make sure the last square was black, java said: "+e);
			failed++;
		} finally {
			tests++;
		}
		
		printStats(tests, failed);
	}
	
	private static void testSwap() {
		int tests = 0;
		int failed = 0;
		
		printHeader("Testing Swap");
		
		try {
			ColumnPuzzle temp = new ColumnPuzzle(3);
			temp.setup(3);
			temp.window.setVisible(false);
			temp.window.dispose();
			
			for(int row = 0; row < temp.grid.length-1; row++) {
				for(int col = 0; col < temp.grid[row].length-1; col++) {
					try {
						// remember what the colors were...
						Color color1 = temp.grid[row][col];
						Color color2 = temp.grid[row+1][col+1];
						
						// swap them
						temp.swap(row, col, row+1, col+1);
						
						// make sure they swapped
						if(!temp.grid[row+1][col+1].equals(color1) || !temp.grid[row][col].equals(color2)) {
							sop("FAILED! Swap of "+row+","+col+" to "+(row+1)+","+(col+1)+" did not swap!");
							failed++;
						}
					} catch(Exception e) {
						sop("ERROR! Tried to swap("+row+", "+col+", "+(row+1)+", "+(col+1)+"), java said: "+e);
						failed++;
					} finally {
						tests++;
					}
				}
			}
		} catch(Exception e) {
			sop("ERROR! Tried to create a column puzzle for testing swap, java said: "+e);
		}
		
		printStats(tests, failed);
	}

	private static void testShuffle() {
		int tests = 0;
		int failed = 0;
		
		printHeader("Testing Shuffle");
		
		try {
			for(int j = 2; j < 7; j++) {
				ColumnPuzzle temp = new ColumnPuzzle(j);
				
				for(int i = 0; i < 100; i++) {
					try {
						Color[][] originalColors = deepCopy(temp.grid);
						temp.window.setVisible(false);
						temp.window.dispose();
						temp.shuffle();
						
						if(equal(originalColors, temp.grid)) {
							sop("FAILED! shuffle did not move anything around!");
							failed++;
							break;
						}
					} catch(Exception e) {
						sop("ERROR! Tried to call shuffle, java said: "+e);
						failed++;
					} finally {
						tests++;
					}
				}
			}
		} catch(Exception e) {
			sop("ERROR! Tried to create a column puzzle for testing shuffle, java said: "+e);
		}
		
		printStats(tests, failed);
	}
	
	private static void testSetTitle() {
		int tests = 0;
		int failed = 0;
		
		printHeader("Testing SetTitle");
		
		try {
			ColumnPuzzle temp = new ColumnPuzzle(3);
			temp.window.setVisible(false);
			temp.window.dispose();
			
			for(int i = 0; i < 10; i++) {
				tests++;
	
				temp.setTitle(i+"");
				
				if(!temp.window.getTitle().contains(i+"")) {
					sop("FAILED! setTitle does not update the title");
					failed++;
				}
			}
		} catch(Exception e) {
			sop("ERROR! Tried to create column puzzle for set title, java said: "+e);
		}
		
		printStats(tests, failed);
	}
	
	private static void testIsSolved() {
		int tests = 0;
		int failed = 0;
		
		printHeader("Testing isSolved");
		
		Color[][] solved1 = {{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA},
				                 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.BLACK}};
		Color[][] solved2 = {{Color.RED, Color.YELLOW, Color.GREEN, Color.BLACK, Color.CYAN, Color.BLUE},
			                   {Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.BLUE}};
		Color[][] solved3 = {{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA},
				                 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA}, 
				                 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA}, 
				                 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA},
				                 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA},
				                 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA},
				                 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.BLACK}};
		Color[][] solved4 = {{Color.BLACK, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.RED},
				                 {Color.MAGENTA, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.RED}};
		Color[][] notSolved = {{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA},
				                   {Color.YELLOW, Color.RED, Color.GREEN, Color.CYAN, Color.BLUE, Color.BLACK}};
		
		try {
			ColumnPuzzle temp = new ColumnPuzzle(3);
			temp.window.setVisible(false);
			temp.window.dispose();
			
			try {
				temp.grid = solved1;
				if(!temp.isSolved()) {
					sop("FAILED! Puzzle was solved, but isSolved was false using the default solved position");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! isSolved threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			try {
				temp.grid = solved2;
				if(!temp.isSolved()) {
					sop("FAILED! Puzzle was solved, but isSolved was false, using a black square that isn't in the bottom right corner");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! isSolved threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			try {
				temp.grid = solved3;
				if(!temp.isSolved()) {
					sop("FAILED! Puzzle was solved, but isSolved was false, using a board with lots of rows");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! isSolved threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
	
			try{	
				temp.grid = solved4;
				if(!temp.isSolved()) {
					sop("FAILED! Puzzle was solved, but isSolved was false, having the black square in the upper left");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! isSolved threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			try {
				temp.grid = notSolved;
				if(temp.isSolved()) {
					sop("FAILED! Puzzle was NOT solved, but isSolved was true (was the method implemented?)");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! isSolved threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
		} catch(Exception e) {
			sop("ERROR! Tried to create column puzzle for isSolved, java said: "+e);
		}
		
		printStats(tests, failed);
	}
	
	private static void testClosestBlackSquare() {
		int tests = 0;
		int failed = 0;
		
		printHeader("Testing closestBlackSquare");

		Color[][] colors1 = {{Color.RED, Color.YELLOW, Color.RED, Color.BLUE, Color.BLUE, Color.MAGENTA},
				                 {Color.YELLOW, Color.GREEN, Color.GREEN, Color.CYAN, Color.CYAN, Color.BLACK}};
		Color[][] colors2 = {{Color.BLACK, Color.YELLOW, Color.RED, Color.BLUE, Color.BLUE, Color.MAGENTA},
                         {Color.YELLOW, Color.GREEN, Color.GREEN, Color.CYAN, Color.CYAN, Color.RED}};
		
		try {
			ColumnPuzzle temp = new ColumnPuzzle(3);
			temp.window.setVisible(false);
			temp.window.dispose();
			temp.grid = colors1;
			
			try {
				int[] cbs = temp.closestBlackSquare(1, 4); 
				if(!(cbs[0] == 1 && cbs[1] == 5)) {
					sop("FAILED! Closest black square should have been (1,5), was ("+cbs[0]+","+cbs[1]+")");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! closestBlackSquare threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			
			try {
				int[] cbs = temp.closestBlackSquare(0,5);
				if(!(cbs[0] == 1 && cbs[1] == 5)) {
					sop("FAILED! Closest black square should have been (1,5), was ("+cbs[0]+","+cbs[1]+")");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! closestBlackSquare threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			
			try {
				int[] cbs = temp.closestBlackSquare(0,4);
				if(!(cbs[0] == -1 && cbs[1] == -1)) {
					sop("FAILED! Closest black square should have been (-1,-1) [not found], was ("+cbs[0]+","+cbs[1]+")");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! closestBlackSquare threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			
			temp.grid = colors2;
			try {
				int[] cbs = temp.closestBlackSquare(0,0);
				if(!(cbs[0] == -1 && cbs[1] == -1)) {
					sop("FAILED! Closest black square should have been (-1,-1) [not found], was ("+cbs[0]+","+cbs[1]+")");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! closestBlackSquare threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			
			try {
				int[] cbs = temp.closestBlackSquare(0,1);
				if(!(cbs[0] == 0 && cbs[1] == 0)) {
					sop("FAILED! Closest black square should have been (0,0), was ("+cbs[0]+","+cbs[1]+")");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! closestBlackSquare threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			try {
				int[] cbs = temp.closestBlackSquare(1,0);
				if(!(cbs[0] == 0 && cbs[1] == 0)) {
					sop("FAILED! Closest black square should have been (0,0), was ("+cbs[0]+","+cbs[1]+")");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! closestBlackSquare threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
			
			
			try {
				int[] cbs = temp.closestBlackSquare(1,1);
				if(!(cbs[0] == -1 && cbs[1] == -1)) {
					sop("FAILED! Closest black square should have been (-1,-1) [not found], was ("+cbs[0]+","+cbs[1]+")");
					failed++;
				}
			} catch(Exception e) {
				sop("ERROR! closestBlackSquare threw exception: "+e);
				failed++;
			} finally {
				tests++;
			}
		} catch(Exception e) {
			sop("ERROR! Tried to create column puzzle for closestBlackSquare, java said: "+e);
		}
		
		printStats(tests, failed);
	}
	
	private static void testMousePressed() {
		int tests = 0;
		int failed = 0;
		
		Color[][] colors1 = {{Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA},
												 {Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.CYAN, Color.BLUE},
												 {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE, Color.CYAN, Color.BLACK}};
										
		printHeader("Testing mousePressed");
		
		try {
			ColumnPuzzle temp = new ColumnPuzzle(3);
			temp.grid = colors1;
			temp.paint();
			
			Robot bot = new Robot();
			
			// move black all the way to the left
			for(int i = 0; i < 5; i++) {
				bot.mouseMove(450-(100*i), 250);
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(500);
			}
			
			// move black square all the way to the top
			for(int i = 0; i < 2; i++) {
				bot.mouseMove(50, 150-(100*i));
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(500);
			}
			
			// move black all the way to the right
			for(int i = 1; i < 6; i++) {
				bot.mouseMove(50+(100*i), 50);
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(500);
			}
			
			// move black down one
			bot.mouseMove(550, 150);
			bot.mousePress(InputEvent.BUTTON1_MASK);
			bot.mouseRelease(InputEvent.BUTTON1_MASK);
			Thread.sleep(500);
			
			// move all the way left through the center
			for(int i = 0; i < 5; i++) {
				bot.mouseMove(450-(100*i), 150);
				bot.mousePress(InputEvent.BUTTON1_MASK);
				bot.mouseRelease(InputEvent.BUTTON1_MASK);
				Thread.sleep(500);
			}
		} catch(Exception e) {
			sop("ERROR! Tried to create column puzzle and robot for testing the mouse pressed, java said: "+e);
		}
		
		printStats(tests, failed);
	}
	
	
	
	
	
	/*
	 * Helper methods follow
	 */
	
	private static void printHeader(String s) {
		System.out.print("+");
		for(int i = 0; i < s.length()+4; i++)	System.out.print("-");
		System.out.println("+");
		
		sop("|  "+s+"  |");
		
		System.out.print("+");
		for(int i = 0; i < s.length()+4; i++) System.out.print("-");
		System.out.println("+\n");
	}
	
	private static void sop(String s) {
		System.out.println(s);
	}
	
	private static void printStats(int tests, int failed) {
		sop("\n\nSUMMARY:");
		sop("  PASSED: "+(tests-failed)+"\tFAILED: "+failed);
		sop("\n\n");
	}
	
	private static Color[][] deepCopy(Color[][] colors) {
		Color[][] newColors = new Color[colors.length][colors[0].length];
		
		for(int row = 0; row < colors.length; row++) {
			for(int col = 0; col < colors[row].length; col++) {
				newColors[row][col] = colors[row][col];
			}
		}
		
		return newColors;
	}
	
	private static boolean equal(Color[][] colors1, Color[][] colors2) {
		if(colors1.length != colors2.length || colors1[0].length != colors2[0].length) {
			return false;
		}
		
		for(int row = 0; row < colors1.length; row++) {
			for(int col = 0; col < colors1[row].length; col++) {
				if(colors1[row][col] != colors2[row][col]) {
					return false;
				}
			}
		}
		
		return true;
	}
}