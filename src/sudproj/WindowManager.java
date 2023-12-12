package sudproj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
/**
 * Runs the GUI.
 * @author Samuel Högbom Aronsson and Oskar Hessler
 *
 */
public class WindowManager {
	private int[][] b;
	private SudokuSolver s;
	private int[][] copyb;
	private Font F;

	/**
	 * crunstructs a WindowManager object
	 * @param title what title to put in window
	 */
	public WindowManager(String title) {
		b = new int[9][9];
		s = new Solver();
		F = new Font(Font.DIALOG, 0, 18);
		SwingUtilities.invokeLater(() -> createWindow(title));

	}

	/**
	 * Creates a sudoku window and runs the gui
	 * 
	 * @param title
	 * @param w
	 * @param h
	 */
	private void createWindow(String title) {
		JFrame jf = new JFrame(title);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = jf.getContentPane();

		// Button panel, clear, solve and show solution.
		JPanel bp = new JPanel();
		JButton sb = new JButton("Solvable");
		JButton cb = new JButton("Clear");
		JButton ssb = new JButton("Show solution");

		// Suduko panel
		JTextField[][] tfm = new JTextField[9][9];
		JPanel sp = new JPanel();
		sp.setLayout(new GridLayout(9, 9));
		for (int x = 0; x < tfm.length; x++) {
			for (int y = 0; y < tfm[x].length; y++) {
				JTextField tf = new JTextField(0);
				tf.setFont(F);

				tf.setInputVerifier(new MyInputVerifier());

				if ((x / 3 + y / 3) % 2 == 1) {
					tf.setBackground(Color.orange);
				}

				tfm[x][y] = tf;
				sp.add(tfm[x][y]);
			}
		}
		// solvable action
		sb.addActionListener(k -> {
			for (int x = 0; x < tfm.length; x++) {
				for (int y = 0; y < tfm[x].length; y++) {
					String tx = tfm[x][y].getText();
					if (tx.isEmpty()) {
						tx = "0";
					}
					b[x][y] = Integer.parseInt(tx);
				}
			}
			s.setBoard(b);
			if (s.solve()) {
				JOptionPane.showMessageDialog(null, "Det är lösbart");
			} else {
				JOptionPane.showMessageDialog(null, "Det är inte lösbart");
			}
		});
		// clear action
		cb.addActionListener(event -> {
			for (int x = 0; x < tfm.length; x++) {
				for (int y = 0; y < tfm[x].length; y++) {
					tfm[x][y].setText(null);
				}
			}
		});
		// show solution action
		ssb.addActionListener(event -> {
			for (int x = 0; x < tfm.length; x++) {
				for (int y = 0; y < tfm[x].length; y++) {
					String tx = tfm[x][y].getText();
					if (tx.isEmpty()) {
						tx = "0";
					}
					b[x][y] = Integer.parseInt(tx);
				}
			}
			s.setBoard(b);
			if (!s.solve()) {
				JOptionPane.showMessageDialog(null, "Det är inte lösbart");
			} else {
				copyb = s.getBoard();
				for (int x = 0; x < tfm.length; x++) {
					for (int y = 0; y < tfm[x].length; y++) {
						String nextext = copyb[x][y] + "";

						tfm[x][y].setText(nextext);
					}
				}
			}
		});
		// add all components
		bp.add(cb);
		bp.add(sb);
		bp.add(ssb);
		pane.add(sp);
		pane.add(bp, BorderLayout.SOUTH);
		jf.pack();
		jf.setVisible(true);
	}

	private class MyInputVerifier extends InputVerifier {

		/**
		 * verifies JComponent inputs.
		 * 
		 * @param input - to be verified
		 * @returns true when input is good
		 */
		@Override
		public boolean verify(JComponent input) {
			String text = ((JTextField) input).getText(); // istället för att ge true false på dålig text fixar vi
															// texten och säger att texten alltid är bra!
			if (text.length() >= 1) {
				text = "" + text.charAt(text.length() - 1); // big brain shit, skriver man mmer än vad man får tar den
															// bara det sista
			}
			if (text.length() != 0) {
				if (!Character.isDigit(text.charAt(0))) {
					text = null;
				} else if (Integer.parseInt(text) == 0) {
					text = null;
				}
			}
			((JTextField) input).setText(text);
			return true;
		}
	}

	/**
	 * Starts the program.
	 */
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		WindowManager wm = new WindowManager("Sudoku");
	}
}
