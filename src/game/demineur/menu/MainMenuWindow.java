package game.demineur.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;

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
import javax.swing.JSeparator;

import game.demineur.ingame.LaunchGame;
import game.demineur.menu.settings.SettingPopup;
import game.demineur.menu.settings.SettingReader;
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

	private SettingReader settings;

	private String profilName, gridSize;

	public JFrame getFrmMenu() {
		return frmMenu;
	}

	public void setFrmMenu(JFrame frmMenu) {
		this.frmMenu = frmMenu;
		frmMenu.setIconImage(Toolkit.getDefaultToolkit().getImage(MainMenuWindow.class.getResource(Path.MENU_PICTURE)));
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

	public MainMenuWindow(SettingReader settings) {
		this.settings = settings;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmMenu(new JFrame());
		getFrmMenu().setTitle("Menu");
		getFrmMenu().setBounds(100, 100, 600, 500);
		getFrmMenu().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmMenu.setJMenuBar(menuBar);

		JMenu mnDmineur = new JMenu("D\u00E9mineur");
		menuBar.add(mnDmineur);

		JMenu mnProfils = new JMenu("Profils");
		mnDmineur.add(mnProfils);

		JMenu mnListeDesProfils = new JMenu("Liste des profils");
		mnProfils.add(mnListeDesProfils);

		mnProfils = addListProfileToMenu(mnProfils, settings);

		JSeparator separator_1 = new JSeparator();
		mnProfils.add(separator_1);

		JMenuItem mntmNouveauProfil = new JMenuItem("Nouveau profil");
		mnProfils.add(mntmNouveauProfil);

		JMenuItem mntmSupprimerUnProfil = new JMenuItem("Supprimer un profil");
		mnProfils.add(mntmSupprimerUnProfil);

		JSeparator separator_2 = new JSeparator();
		mnProfils.add(separator_2);

		JMenuItem mntmCurrentprofile = new JMenuItem(settings.getProfilName());
		mntmCurrentprofile.setEnabled(false);
		mnProfils.add(mntmCurrentprofile);

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

		JMenu mnPropos = new JMenu("A propos");
		menuBar.add(mnPropos);

		JMenuItem mntmProposDu = new JMenuItem("\u00C1 propos du d\u00E9veloppeur");
		mntmProposDu.setAction(action_1);
		mnPropos.add(mntmProposDu);

		JMenuItem mntmChangelog = new JMenuItem("Changelog");
		mntmChangelog.setAction(action_2);
		mnPropos.add(mntmChangelog);

		JSeparator separator = new JSeparator();
		mnPropos.add(separator);

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
			frmMenu.dispose();
			LaunchGame game = new LaunchGame();
			game.newGame(gridSize);
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
			SettingPopup parametre = new SettingPopup(null, "Paramètre", true, settings);
			String[] settingData = parametre.showSettingPopup();
			updateSettingData(settingData);

		}
	}

	protected void updateSettingData(String[] settingData) {
		profilName = settingData[0];
		gridSize = settingData[1];
	}

	protected JMenu addListProfileToMenu(JMenu menuToUpdate, SettingReader settings) {
		File profileDirectory = new File(Path.PROFIL_PATH);
		File[] profilList = profileDirectory.listFiles();

		if (profilList.length != 0) {
			for (int i = 0; i < profilList.length; i++) {
				if (!(profilList[i].getName().equals(settings.getProfilName()))) {
					JMenuItem menuItem = new JMenuItem(profilList[i].getName());
					menuToUpdate.add(menuItem);
				}
			}
		} else {
			JMenuItem menuItem = new JMenuItem("Aucun profil enregistré");
			menuItem.setEnabled(false);
			menuToUpdate.add(menuItem);
		}

		return menuToUpdate;
	}
}