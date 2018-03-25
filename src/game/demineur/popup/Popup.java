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

		if (nom == null) {
			System.exit(0);
		} else if (nom.equals("")) {
			emptyNameFormat();
			nom = needToCreateNewProfil();
		} else if (!(nom.matches("[a-zA-Z]+"))) {
			wrongNameFormat();
			nom = needToCreateNewProfil();
		}

		return nom;
	}

	public static void emptyNameFormat() {
		JOptionPane.showMessageDialog(null, "Désolé, votre nom ne peut pas être vide", "Nouveau profil : Erreur",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void wrongNameFormat() {
		JOptionPane.showMessageDialog(null,
				"Désolé, un nom de profil ne peut contenir que des caractères de A à Z en majuscules et minuscules.",
				"Nouveau profil : Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public static String addNewProfile() {
		String nom = JOptionPane.showInputDialog(null, "Écrivez votre pseudo", "Nouveau profil",
				JOptionPane.QUESTION_MESSAGE);

		if (nom == null) {
			return null;
		} else if (nom.equals("")) {
			emptyNameFormat();
			nom = addNewProfile();
		} else if (!(nom.matches("[a-zA-Z]+"))) {
			wrongNameFormat();
			nom = addNewProfile();
		}

		return nom;
	}

	public static void profileAlreadyExisting(String profilName) {
		JOptionPane.showMessageDialog(null, "Impossible de créer le profil. Le pseudo " + profilName + " existe déjà.",
				"Erreur", JOptionPane.WARNING_MESSAGE);
	}

	public static boolean askForRestart() {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Cette opération va nécessiter le redémarrage du programme.\n Voulez-vous continuer ?",
				"Confirmation de redémnarrage", JOptionPane.OK_CANCEL_OPTION);

		if (confirm == JOptionPane.CLOSED_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
			return false;
		} else {
			return true;
		}
	}
}
