package game.demineur.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import game.demineur.ingame.LaunchGame;
import game.demineur.popup.Popup;
import game.demineur.utils.GameConstants;
import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

public class MainMenuWindow {

	private JFrame frmMenu;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final Action action_4 = new SwingAction_4();

	public JFrame getFrmMenu() {
		return frmMenu;
	}

	public void setFrmMenu(JFrame frmMenu) {
		this.frmMenu = frmMenu;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainMenuWindow window = new MainMenuWindow();
					window.getFrmMenu().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenuWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmMenu(new JFrame());
		getFrmMenu().setTitle("Menu");
		getFrmMenu().setBounds(100, 100, 600, 500);
		getFrmMenu().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmMenu.setJMenuBar(menuBar);

		JMenu mnDmineur = new JMenu("D\u00E9mineur");
		menuBar.add(mnDmineur);

		JMenuItem mntmParamtres = new JMenuItem("Param\u00E8tres");
		mntmParamtres.setAction(action_4);
		mnDmineur.add(mntmParamtres);

		JMenuItem mntmQuitter = new JMenuItem("Quitter le jeu");
		mnDmineur.add(mntmQuitter);

		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);

		JMenuItem mntmRglesDuJeu = new JMenuItem("R\u00E8gles du jeu");
		mntmRglesDuJeu.setAction(action_3);
		mnAide.add(mntmRglesDuJeu);

		JMenu mnPropos = new JMenu("\u00C1 propos");
		menuBar.add(mnPropos);

		JMenuItem mntmProposDu = new JMenuItem("\u00C1 propos du d\u00E9veloppeur");
		mntmProposDu.setAction(action_1);
		mnPropos.add(mntmProposDu);

		JMenuItem mntmChangelog = new JMenuItem("Changelog");
		mntmChangelog.setAction(action_2);
		mnPropos.add(mntmChangelog);

		JMenuItem mntmVersion = new JMenuItem("Version " + GameConstants.VERNUM);
		mntmVersion.setEnabled(false);
		mnPropos.add(mntmVersion);
		frmMenu.getContentPane().setLayout(new BorderLayout(0, 0));

		JButton btnDmarrerLaPartie = new JButton("D\u00E9marrer la partie");
		btnDmarrerLaPartie.setAction(action);
		frmMenu.getContentPane().add(btnDmarrerLaPartie, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel();
		ImagesSettings resizePicture = new ImagesSettings();
		lblNewLabel = resizePicture.displayImage(lblNewLabel, Path.MENU_PICTURE, 280, 400);
		frmMenu.getContentPane().add(lblNewLabel, BorderLayout.EAST);

		JEditorPane txtpnPane = new JEditorPane();
		txtpnPane.setDisabledTextColor(Color.BLACK);
		txtpnPane.setEnabled(false);
		txtpnPane.setEditable(false);
		txtpnPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnPane.setText(
				"Bienvenue dans le jeu du démineur !\n\nPour commencer une partie cliquez sur le bouton ci-dessous.");
		frmMenu.getContentPane().add(txtpnPane, BorderLayout.CENTER);

		JEditorPane txtpnJeuDuDmineur = new JEditorPane();
		txtpnJeuDuDmineur.setContentType("text/html");
		txtpnJeuDuDmineur.setDisabledTextColor(Color.BLACK);
		txtpnJeuDuDmineur.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnJeuDuDmineur.setEditable(false);
		txtpnJeuDuDmineur.setEnabled(false);
		txtpnJeuDuDmineur.setText("<html><center>Menu pricipal</center></html>");
		frmMenu.getContentPane().add(txtpnJeuDuDmineur, BorderLayout.NORTH);
	}

	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Démarrer la partie");
			putValue(SHORT_DESCRIPTION, "Cliquez ici pour commencer une nouvelle partie.");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String gridSize = Popup.askForGridSize();

			if (gridSize != null) {
				frmMenu.dispose();
				LaunchGame game = new LaunchGame();
				game.newGame(gridSize);
			}
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "A propos du développeur");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Tristan BOULESTEIX\nÉtudiant à l'Exia.CESI", "About me",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Changelog");
			putValue(SHORT_DESCRIPTION, "Voir les nouveautés");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Règles du jeu");
			putValue(SHORT_DESCRIPTION, "Afficher les règles du jeu");
		}

		@Override
		public void actionPerformed(ActionEvent e) {
		}
	}

	@SuppressWarnings("serial")
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Paramètres");
			putValue(SHORT_DESCRIPTION, "Options du jeu");
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
}
