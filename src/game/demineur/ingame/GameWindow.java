package game.demineur.ingame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.demineur.endIt.DetectVictory;
import game.demineur.endIt.endTheGame;
import game.demineur.items.Case;
import game.demineur.items.Chrono;
import game.demineur.menu.settings.SettingReader;
import game.demineur.popup.Popup;
import game.demineur.utils.GameConstants;
import game.demineur.utils.Path;
import game.library.Coordonnees;
import game.library.MineUtils;
import game.library.SaveAs;

public class GameWindow {
	public final int TAILLE_X = 800;
	public final int TAILLE_Y = 600;
	public final Dimension TAILLE_DEFAULT = new Dimension(TAILLE_X, TAILLE_Y);

	private String profileName, colorGrid;
	private int size;
	private SettingReader settings;
	private JFrame menuFrame;
	private Chrono chronometer;

	private JFrame frmDmineur;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();

	public JFrame getFrame() {
		return frmDmineur;
	}

	public void setFrame(JFrame frame) {
		this.frmDmineur = frame;
		frmDmineur.setTitle("Démineur");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource(Path.MENU_PICTURE)));
	}

	/**
	 * Create the application.
	 * 
	 * @param profilName
	 * @param size
	 * @param gridColor
	 * @param settings
	 * @param menuFrame
	 */
	public GameWindow(String profilName, int size, String gridColor, SettingReader settings, JFrame menuFrame) {
		this.settings = settings;
		this.profileName = profilName;
		this.colorGrid = gridColor;
		this.size = size;
		this.menuFrame = menuFrame;
		endTheGame.initialize(settings, profilName);
		DetectVictory.initialize(10);
		initialize(size, gridColor);
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @param size
	 * @param gridColor
	 */
	void initialize(int size, String gridColor) {
		setFrame(new JFrame());
		getFrame().setBounds(100, 100, TAILLE_X, TAILLE_Y);
		getFrame().setMinimumSize(TAILLE_DEFAULT);
		getFrame().setLocationRelativeTo(null);
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

		JMenuItem mntmMenuPrincipal = new JMenuItem("Menu principal");
		mntmMenuPrincipal.setAction(action_3);
		mnDmineur.add(mntmMenuPrincipal);

		JMenuItem mntmQuitter = new JMenuItem("Quitter");
		mntmQuitter.setAction(action_2);
		mnDmineur.add(mntmQuitter);

		JMenu mnPartie = new JMenu("Partie");
		menuBar.add(mnPartie);

		JMenuItem mntmRejouer = new JMenuItem("Rejouer");
		mntmRejouer.setAction(action_1);
		mnPartie.add(mntmRejouer);

		JMenuItem mntmCaptureDcran = new JMenuItem("Capture d'écran");
		mntmCaptureDcran.setAction(action_4);
		mnPartie.add(mntmCaptureDcran);

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
		frmDmineur.getContentPane().setLayout(gridBagLayout);

		JPanel gamePane = new JPanel();
		Dimension d = new Dimension(200, 400);
		gamePane.setPreferredSize(d);
		gamePane.setMaximumSize(d);
		GridBagConstraints cGamePane = new GridBagConstraints();
		cGamePane.gridx = 0;
		cGamePane.gridy = 0;
		cGamePane.weightx = 225;
		cGamePane.weighty = 225;
		frmDmineur.getContentPane().add(gamePane, cGamePane);

		JPanel timePanel = new JPanel();
		timePanel.setPreferredSize(new Dimension(400, 400));
		timePanel.setBackground(Color.BLACK);
		Chrono timer = new Chrono();
		timer.initialize();
		chronometer = timer;
		timer.startTimer();
		timePanel.add(timer);
		GridBagConstraints cTimePane = new GridBagConstraints();
		cTimePane.fill = GridBagConstraints.BOTH;
		cTimePane.gridx = 1;
		cTimePane.gridy = 0;
		frmDmineur.getContentPane().add(timePanel, cTimePane);

		addComponents(gamePane, timer);
	}

	public void addComponents(JPanel gamePane, Chrono timer) {
		GridBagLayout gridBagLayout1 = new GridBagLayout();
		gridBagLayout1.columnWidths = new int[] { 0, 0 };
		gridBagLayout1.rowHeights = new int[] { 0, 0 };
		gridBagLayout1.columnWeights = new double[] { 1.0, 1.0 };
		gridBagLayout1.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		gamePane.setLayout(gridBagLayout1);

		Case aX;

		String colorPath = setCasesColorPicturePath();

		ArrayList<Coordonnees> arrayCases = chooseRandomPlaceToBombs(9);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int neighboor = MineUtils.giveNeighbourNumber(MineUtils.generateCoordonneesVoisines(i, j), arrayCases);

				aX = new Case(neighboor, new Coordonnees(i, j), arrayCases, timer, colorPath, getFrame(), menuFrame);

				aX.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				GridBagConstraints cAX = new GridBagConstraints();
				cAX.gridx = i;
				cAX.gridy = j;
				cAX.weightx = 25;
				cAX.weighty = 25;
				cAX.fill = GridBagConstraints.BOTH;

				MineUtils.casePositionArray(aX);

				gamePane.add(aX, cAX);
			}
		}

		placeBombs(arrayCases);
	}

	private String setCasesColorPicturePath() {
		String path;

		switch (colorGrid) {
		case "Gris":
			path = Path.GREY_CUBE;
			break;

		case "Bleu":
			path = Path.BLUE_CUBE;
			break;

		case "Vert":
			path = Path.GREEN_CUBE;
			break;

		case "Rouge":
			path = Path.RED_CUBE;
			break;

		default:
			path = Path.GREY_CUBE;
		}

		return path;
	}

	private void placeBombs(ArrayList<Coordonnees> coordinatesOfBombs) {
		Case[][] casesList = MineUtils.getCaseList();

		for (int i = 0; i < casesList.length; i++) {
			for (int j = 0; j < casesList[i].length; j++) {
				for (int k = 0; k < coordinatesOfBombs.size(); k++) {
					if (casesList[i][j].getPosition().equals(coordinatesOfBombs.get(k))) {
						casesList[i][j].setState(Case.EXPLOSIVE);
					}
				}
			}
		}
	}

	private ArrayList<Coordonnees> chooseRandomPlaceToBombs(int maxSize) {
		ArrayList<Coordonnees> arrayOfcoordinates = new ArrayList<>();

		if (maxSize == 9) {
			int bombToPlace = 10;

			for (int i = 1; i <= bombToPlace; i++) {
				int x = randomBetween(0, 8);
				int y = randomBetween(0, 8);

				Coordonnees coor = new Coordonnees(x, y);

				if (isAlreadyExistant(arrayOfcoordinates, coor)) {
					i--;
				} else {
					arrayOfcoordinates.add(coor);
				}
			}
		}

		return arrayOfcoordinates;
	}

	private int randomBetween(int min, int max) {
		int randomNumber = (int) (Math.random() * (max - min));
		return randomNumber;
	}

	private boolean isAlreadyExistant(ArrayList<Coordonnees> arrayOfcoordinates, Coordonnees coor) {
		boolean exist = false;

		for (int i = 0; i < arrayOfcoordinates.size(); i++) {
			if (arrayOfcoordinates.get(i).equals(coor)) {
				exist = true;
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

	@SuppressWarnings("serial")
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Nouvelle partie");
			putValue(SHORT_DESCRIPTION, "Rejouer");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			getFrame().dispose();
			GameWindow newWindow = new GameWindow(profileName, size, colorGrid, settings, menuFrame);
			newWindow.getFrame().setVisible(true);
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Quitter");
			putValue(SHORT_DESCRIPTION, "Quitter le jeu");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Menu principal");
			putValue(SHORT_DESCRIPTION, "Retour au menu");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean allowExitGame = Popup.confirmGoBackToMenu();

			if (allowExitGame) {
				endTheGame end = new endTheGame();
				end.finishGame(chronometer);
				getFrame().dispose();
				menuFrame.setVisible(true);
			}
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Capture d'écran");
			putValue(SHORT_DESCRIPTION, "Effectuer une capture d'écran de la fenêtre actuelle.");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean allowSavePicture = true;

			BufferedImage capture = new BufferedImage(getFrame().getWidth(), getFrame().getHeight(),
					BufferedImage.TYPE_INT_RGB);
			getFrame().paint(capture.getGraphics());

			chronometer.stopTimer();
			SaveAs save = new SaveAs();
			File pathToSave = save.SaveFile();

			if (pathToSave.exists()) {
				allowSavePicture = Popup.confirmReplaceFile(pathToSave.getName());
			}

			if (pathToSave != null || !allowSavePicture) {
				try {
					ImageIO.write(capture, "png", pathToSave);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			chronometer.startTimer();
		}
	}
}
