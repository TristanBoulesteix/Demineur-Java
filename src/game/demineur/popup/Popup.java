package game.demineur.popup;

import javax.swing.JOptionPane;

import game.demineur.endIt.EndGameText;

public class Popup {
	public static boolean defeatPopup(EndGameText text) {
		boolean confirm = false;

		int choice = JOptionPane.showConfirmDialog(null, text.getScrollBar(), "Défaite", JOptionPane.YES_NO_OPTION,
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

	public static boolean askForRestartFromPopup() {
		int choice = JOptionPane.showConfirmDialog(null,
				"Pour changer de profil, l'application de se redémarrer. Voulez-vous continuer ?",
				"Redémarrage nécessaire", JOptionPane.OK_CANCEL_OPTION);

		if (choice == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static void errorSettingPopup(String errorMessage) {
		JOptionPane.showMessageDialog(null, "Erreur de mise à jour des paramètres", errorMessage,
				JOptionPane.ERROR_MESSAGE);
	}

	public static String selectProfileToDelete(Object[] profileList) {
		String toDelete = (String) JOptionPane.showInputDialog(null, "Veuillez sélectionner un profil à supprimer.",
				"Supprimer un profil", JOptionPane.QUESTION_MESSAGE, null, profileList, profileList[0]);
		return toDelete;
	}

	public static void errorWhenResetPopup() {
		JOptionPane.showMessageDialog(null,
				"Une erreur inconnue s'est produite. Certains fichiers n'ont pas été supprimés", "Erreur !",
				JOptionPane.ERROR_MESSAGE);
	}

	public static boolean confirmReset() {
		int choice = JOptionPane.showConfirmDialog(null,
				"Attention ! Cette action va supprimer TOUS les fichiers de TOUS les profils.\nCela réinitialisera également les paramètres par défaut.",
				"Réinitialiser", JOptionPane.OK_CANCEL_OPTION);

		if (choice == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean confirmGoBackToMenu() {
		int choice = JOptionPane.showConfirmDialog(null,
				"Attention ! Pour retourner au menu principal, la partie en cours va être considérée comme une défaite.\nÊtes-vous sûr de continuer ?",
				"Retour au menu", JOptionPane.OK_CANCEL_OPTION);

		if (choice == JOptionPane.OK_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean confirmReplaceFile(String path) {
		int choice = JOptionPane.showConfirmDialog(null,
				"Le fichier " + path + " existe déjà. Voulez-vous le remplacer ?", "Enregistrer sous...",
				JOptionPane.YES_NO_OPTION);

		if (choice == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}
}
