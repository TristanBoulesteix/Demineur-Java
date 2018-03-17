package game.demineur.ingame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

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
					GameWindow window = new GameWindow();
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize() {
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

		JLabel label = new JLabel("□");
		label.setPreferredSize(new Dimension(25, 25));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.gridx = 1;
		gbc_label.gridy = 1;
		getFrame().getContentPane().add(label, gbc_label);
	}

}
