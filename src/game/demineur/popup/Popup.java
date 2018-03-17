package game.demineur.popup;

import javax.swing.JOptionPane;

public class Popup {
	public static String askForGridSize() {
		String[] choix = { "9x9", "16x16", "30x16" };

		String choice = (String) JOptionPane.showInputDialog(null, "Choisissez une taille de grille :",
				"Nouvelle partie", JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);

		return choice;
	}
}
