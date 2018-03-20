package game.demineur.ingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.demineur.items.Case;
import game.demineur.utils.GameConstants;
import game.library.Coordonnees;
import game.library.MineUtils;

public class GameWindow {
	public final int TAILLE_X = 800;
	public final int TAILLE_Y = 600;
	public final Dimension TAILLE_DEFAULT = new Dimension(TAILLE_X, TAILLE_Y);

	private JFrame frame;
	private final Action action = new SwingAction();

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
		getFrame().pack();
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		getFrame().setJMenuBar(menuBar);

		JMenu mnDmineur = new JMenu("D\u00E9mineur");
		menuBar.add(mnDmineur);

		JMenu mnPartie = new JMenu("Partie");
		menuBar.add(mnPartie);

		JMenu mnAPropos = new JMenu("A propos");
		menuBar.add(mnAPropos);

		JMenuItem mntmAProposDu = new JMenuItem("A propos du développeur");
		mntmAProposDu.setAction(action);
		mnAPropos.add(mntmAProposDu);

		JMenuItem mntmChangelog = new JMenuItem("Changelog");
		mnAPropos.add(mntmChangelog);

		JMenuItem mntmVersion = new JMenuItem("Version " + GameConstants.VERNUM);
		mntmVersion.setEnabled(false);
		mnAPropos.add(mntmVersion);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 420, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		frame.getContentPane().setLayout(gridBagLayout);

		JPanel gamePane = new JPanel();
		gamePane.setPreferredSize(new Dimension(200, 400));
		GridBagConstraints cGamePane = new GridBagConstraints();
		cGamePane.gridx = 0;
		cGamePane.gridy = 0;
		cGamePane.weightx = 225;
		cGamePane.weighty = 225;
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

		Case aX;

		ArrayList<Integer> arrayCases = chooseRandomPlaceToBombs(9);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int neighboor = MineUtils.giveNeighbourNumber(MineUtils.generateCoordonneesVoisines(i, j), arrayCases);

				if (MineUtils.isABomb(i, j, arrayCases)) {
					aX = new Case(Case.EXPLOSIVE, neighboor, new Coordonnees(i, j), arrayCases);
				} else {
					aX = new Case(Case.SAFE, neighboor, new Coordonnees(i, j), arrayCases);
				}

				aX.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				GridBagConstraints cAX = new GridBagConstraints();
				cAX.gridx = j;
				cAX.gridy = i;
				cAX.weightx = 25;
				cAX.weighty = 25;
				cAX.fill = GridBagConstraints.BOTH;

				MineUtils.addToList(aX);

				gamePane.add(aX, cAX);
			}
		}
	}

	public ArrayList<Integer> chooseRandomPlaceToBombs(int maxSize) {
		ArrayList<Integer> arrayOfInt = new ArrayList<>();

		if (maxSize == 9) {
			int bombToPlace = 10;

			for (int i = 1; i <= bombToPlace; i++) {
				int num = randomBetween(0, 81);

				if (arrayOfInt.size() == 0) {
					arrayOfInt.add(num);
				} else if (isAlreadyExistant(arrayOfInt, num)) {
					i--;
				} else {
					arrayOfInt.add(num);
				}
			}
		}

		return arrayOfInt;
	}

	public int randomBetween(int min, int max) {
		int randomNumber = (int) (Math.random() * (max - min));
		return randomNumber;
	}

	public boolean isAlreadyExistant(ArrayList<Integer> list, int numberToCheck) {
		boolean exist = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == numberToCheck) {
				exist = false;
				break;
			}
		}

		return exist;
	}

	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "A propos du développeur");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Tristan BOULESTEIX\nÉtudiant à l'Exia.CESI", "About me",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
