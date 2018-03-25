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
		JOptionPane.showMessageDialog(null, "D�faite", "D�faite", JOptionPane.INFORMATION_MESSAGE);
	}

	public static String needToCreateNewProfil() {
		String nom = JOptionPane.showInputDialog(null,
				"Vous n'avez pas encore de profil enregistr�. �crivez votre nom.", "Nouveau profil",
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
		JOptionPane.showMessageDialog(null, "D�sol�, votre nom ne peut pas �tre vide", "Nouveau profil : Erreur",
				JOptionPane.ERROR_MESSAGE);
	}

	public static void wrongNameFormat() {
		JOptionPane.showMessageDialog(null,
				"D�sol�, un nom de profil ne peut contenir que des caract�res de A � Z en majuscules et minuscules.",
				"Nouveau profil : Erreur", JOptionPane.ERROR_MESSAGE);
	}

	public static String addNewProfile() {
		String nom = JOptionPane.showInputDialog(null, "�crivez votre pseudo", "Nouveau profil",
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
		JOptionPane.showMessageDialog(null, "Impossible de cr�er le profil. Le pseudo " + profilName + " existe d�j�.",
				"Erreur", JOptionPane.WARNING_MESSAGE);
	}

	public static boolean askForRestart() {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Cette op�ration va n�cessiter le red�marrage du programme.\n Voulez-vous continuer ?",
				"Confirmation de red�mnarrage", JOptionPane.OK_CANCEL_OPTION);

		if (confirm == JOptionPane.CLOSED_OPTION || confirm == JOptionPane.CANCEL_OPTION) {
			return false;
		} else {
			return true;
		}
	}
}
