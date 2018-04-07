package game.demineur.popup;

import javax.swing.JOptionPane;

import game.demineur.endIt.EndGameText;

public class Popup {
	public static boolean defeatPopup(EndGameText text) {
		boolean confirm = false;

		int choice = JOptionPane.showConfirmDialog(null, text.getScrollBar(), "D�faite", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (choice == JOptionPane.OK_OPTION) {
			confirm = true;
		}

		return confirm;
	}

	public static boolean victoryPopup(EndGameText text) {
		boolean confirm = false;

		int choice = JOptionPane.showConfirmDialog(null, text.getScrollBar(), "Victoire !", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);

		if (choice == JOptionPane.OK_OPTION) {
			confirm = true;
		}

		return confirm;
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

	public static boolean askForRestartFromPopup() {
		int choice = JOptionPane.showConfirmDialog(null,
				"Pour changer de profil, l'application de se red�marrer. Voulez-vous continuer ?",
				"Red�marrage n�cessaire", JOptionPane.OK_CANCEL_OPTION);

		if (choice == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static void errorSettingPopup(String errorMessage) {
		JOptionPane.showMessageDialog(null, "Erreur de mise � jour des param�tres", errorMessage,
				JOptionPane.ERROR_MESSAGE);
	}

	public static String selectProfileToDelete(Object[] profileList) {
		String toDelete = (String) JOptionPane.showInputDialog(null, "Veuillez s�lectionner un profil � supprimer.",
				"Supprimer un profil", JOptionPane.QUESTION_MESSAGE, null, profileList, profileList[0]);
		return toDelete;
	}

	public static void errorWhenResetPopup() {
		JOptionPane.showMessageDialog(null,
				"Une erreur inconnue s'est produite. Certains fichiers n'ont pas �t� supprim�s", "Erreur !",
				JOptionPane.ERROR_MESSAGE);
	}

	public static boolean confirmReset() {
		int choice = JOptionPane.showConfirmDialog(null,
				"Attention ! Cette action va supprimer TOUS les fichiers de TOUS les profils.\nCela r�initialisera �galement les param�tres par d�faut.",
				"R�initialiser", JOptionPane.OK_CANCEL_OPTION);

		if (choice == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean confirmGoBackToMenu() {
		int choice = JOptionPane.showConfirmDialog(null,
				"Attention ! Pour retourner au menu principal, la partie en cours va �tre consid�r�e comme une d�faite.\n�tes-vous s�r de continuer ?",
				"Retour au menu", JOptionPane.OK_CANCEL_OPTION);

		if (choice == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean confirmReplaceFile(String path) {
		int choice = JOptionPane.showConfirmDialog(null,
				"Le fichier " + path + " existe d�j�. Voulez-vous le remplacer ?", "Enregistrer sous...",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
}
