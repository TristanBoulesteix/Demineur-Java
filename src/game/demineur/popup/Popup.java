package game.demineur.popup;

import javax.swing.JOptionPane;

public class Popup {
	public static String askForGridSize() {
		String[] choix = { "9x9", "16x16", "30x16" };

		String choice = (String) JOptionPane.showInputDialog(null, "Choisissez une taille de grille :",
				"Nouvelle partie", JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);

		return choice;
	}

	public static void defeatPopup() {
		JOptionPane.showMessageDialog(null, "Défaite", "Défaite", JOptionPane.INFORMATION_MESSAGE);
	}

	public static String needToCreateNewProfil() {
		String nom = JOptionPane.showInputDialog(null,
				"Vous n'avez pas encore de profil enregistré. Écrivez votre nom.", "Nouveau profil",
				JOptionPane.QUESTION_MESSAGE);

		return nom;
	}
}
