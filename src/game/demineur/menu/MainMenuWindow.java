package game.demineur.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JTextPane;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

public class MainMenuWindow {

	private JFrame frmMenu;

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

		JMenu mnAide = new JMenu("Aide");
		menuBar.add(mnAide);

		JMenu mnPropos = new JMenu("\u00C1 propos");
		menuBar.add(mnPropos);
		frmMenu.getContentPane().setLayout(new BorderLayout(0, 0));

		JButton btnDmarrerLaPartie = new JButton("D\u00E9marrer la partie");
		frmMenu.getContentPane().add(btnDmarrerLaPartie, BorderLayout.SOUTH);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(120, 300));
		ImagesSettings resizePicture = new ImagesSettings();
		lblNewLabel = resizePicture.displayImage(lblNewLabel, Path.MENU_PICTURE);
		frmMenu.getContentPane().add(lblNewLabel, BorderLayout.EAST);

		JEditorPane txtpnPane = new JEditorPane();
		txtpnPane.setDisabledTextColor(Color.BLACK);
		txtpnPane.setEnabled(false);
		txtpnPane.setEditable(false);
		txtpnPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txtpnPane.setText(
				"Bienvenue dans le jeu du démineur !\n\nPour commencer une partie cliquez sur le bouton ci-dessous.");
		frmMenu.getContentPane().add(txtpnPane, BorderLayout.WEST);

		JTextPane txtpnJeuDuDmineur = new JTextPane();
		txtpnJeuDuDmineur.setEditable(false);
		txtpnJeuDuDmineur.setEnabled(false);
		txtpnJeuDuDmineur.setText("Jeu du démineur");
		frmMenu.getContentPane().add(txtpnJeuDuDmineur, BorderLayout.NORTH);
	}

}
