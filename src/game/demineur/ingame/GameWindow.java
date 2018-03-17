package game.demineur.ingame;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import game.demineur.items.CarreEmpty;
import game.demineur.items.Case;

public class GameWindow {

	private JFrame frame;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GameWindow window = new GameWindow(9);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @param size
	 */
	public GameWindow(int size) {
		initialize(size);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param size
	 */
	void initialize(int size) {
		setFrame(new JFrame());
		// getFrame().setBounds(100, 100, 800, 600);
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);

		JMenu mnDmineur = new JMenu("D\u00E9mineur");
		menuBar.add(mnDmineur);

		JMenu mnPartie = new JMenu("Partie");
		menuBar.add(mnPartie);

		JMenu mnAPropos = new JMenu("A propos");
		menuBar.add(mnAPropos);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getFrame().getContentPane().setLayout(gridBagLayout);

		addCubes(size);
	}

	private void addCubes(int size) {
		if (size == 9) {
			for (int i = 1; i < 9; i++) {
				CarreEmpty cube = new CarreEmpty(Case.CARRE);
				GridBagConstraints gbc_label1 = new GridBagConstraints();
				gbc_label1.gridx = i;
				gbc_label1.gridy = 1;
				getFrame().getContentPane().add(cube, gbc_label1);

				for (int j = 2; j < 9; j++) {
					CarreEmpty cubeLargeur = new CarreEmpty(Case.CARRE);
					GridBagConstraints gbc_label = new GridBagConstraints();
					gbc_label.gridx = i;
					gbc_label.gridy = j;
					getFrame().getContentPane().add(cubeLargeur, gbc_label1);
				}

			}
		}
	}

}
