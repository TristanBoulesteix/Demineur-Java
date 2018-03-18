package game.demineur.ingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import game.demineur.items.CarreEmpty;

public class GameWindow {
	public final int TAILLE_X = 800;
	public final int TAILLE_Y = 600;
	public final Dimension TAILLE_DEFAULT = new Dimension(TAILLE_X, TAILLE_Y);

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
		getFrame().setBounds(100, 100, TAILLE_X, TAILLE_Y);
		getFrame().setMinimumSize(TAILLE_DEFAULT);
		getFrame().addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent evt) {
				Dimension size = getFrame().getSize();
				Dimension min = getFrame().getMinimumSize();
				if (size.getWidth() < min.getWidth()) {
					getFrame().setSize((int) min.getWidth(), (int) size.getHeight());
				}
				if (size.getHeight() < min.getHeight()) {
					getFrame().setSize((int) size.getWidth(), (int) min.getHeight());
				}
			}
		});

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
		gridBagLayout.columnWidths = new int[] { 420, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel gamePane = new JPanel();
		gamePane.setPreferredSize(new Dimension(200, 400));
		GridBagConstraints cGamePane = new GridBagConstraints();
		cGamePane.fill = GridBagConstraints.BOTH;
		cGamePane.gridx = 0;
		cGamePane.gridy = 0;
		frame.getContentPane().add(gamePane, cGamePane);

		JPanel timePanel = new JPanel();
		timePanel.setPreferredSize(new Dimension(400, 400));
		timePanel.setBackground(Color.DARK_GRAY);
		GridBagConstraints cTimePane = new GridBagConstraints();
		cTimePane.fill = GridBagConstraints.BOTH;
		cTimePane.gridx = 1;
		cTimePane.gridy = 0;
		frame.getContentPane().add(timePanel, cTimePane);

		addComponents(gamePane);
	}

	public void addComponents(JPanel gamePane) {
		GridBagLayout gridBagLayout1 = new GridBagLayout();
		gridBagLayout1.columnWidths = new int[] { 0, 0 };
		gridBagLayout1.rowHeights = new int[] { 0, 0 };
		gridBagLayout1.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		gamePane.setLayout(gridBagLayout1);

		CarreEmpty a1 = new CarreEmpty(CarreEmpty.CARRE);
		GridBagConstraints cA1 = new GridBagConstraints();
		cA1.gridx = 0;
		cA1.gridy = 0;
		cA1.fill = GridBagConstraints.HORIZONTAL;
		gamePane.add(a1, cA1);

		for (int i = 0; i < 9; i++) {
			CarreEmpty aX = new CarreEmpty(CarreEmpty.CARRE);
			GridBagConstraints cAX = new GridBagConstraints();
			cAX.gridx = GridBagConstraints.RELATIVE;
			cAX.gridy = 0;
			cAX.fill = GridBagConstraints.HORIZONTAL;
			gamePane.add(aX, cAX);
		}

		for (int i = 0; i < 8; i++) {

		}
	}

}
