package game.demineur.menu.settings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import game.demineur.popup.Popup;
import game.demineur.utils.Path;
import game.library.Restart;

public class SettingsUtils {
	public static JComboBox<String> updateProfilBox(JComboBox<String> box, SettingReader settings) {
		File userFolder = new File(Path.PROFIL_PATH);

		if (!(userFolder.exists()) && !(userFolder.isDirectory())) {
			userFolder.mkdir();
		}

		File[] listFiles = userFolder.listFiles();

		if (listFiles.length != 0) {
			for (int i = 0; i < listFiles.length; i++) {
				box.addItem(listFiles[i].getName());
			}
		}

		box.addItem("Ajouter un profil");

		if (settings.getProfilName().equals("Guest")) {
			box.setSelectedItem("Ajouter un profil");
		} else {
			box.setSelectedItem(settings.getProfilName());
		}

		return box;
	}

	public static ButtonGroup selectDefaultElement(JRadioButton a, JRadioButton b, JRadioButton c,
			SettingReader settings, ButtonGroup group) {
		String gridSize = settings.getDefaultGridSize();

		if (gridSize.equals("9x9")) {
			c.setSelected(true);
		} else if (gridSize.equals("16x16")) {
			b.setSelected(true);
		} else {
			a.setSelected(true);
		}

		return group;
	}

	public static void addNewProfile(String newProfileName, SettingReader settings, boolean needConfirmPopup) {
		File profileFile = new File(Path.PROFIL_PATH + newProfileName);

		if (profileFile.exists() && needConfirmPopup) {
			Popup.profileAlreadyExisting(newProfileName);
			return;
		}

		FileWriter writer;
		try {
			writer = new FileWriter(profileFile);
			BufferedWriter buffer = new BufferedWriter(writer);
			buffer.write("/* Fichier de configuration du profil. NE PAS MODIFIER !");
			buffer.flush();
			buffer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		settings.updateProfilNameINI(newProfileName);
	}

	public static void changeProfile(String newProfileName, SettingReader settings) {
		if (settings.getProfilName().equals(newProfileName)) {
			return;
		} else if (Popup.askForRestartFromPopup()) {
			if (newProfileName.equals("Ajouter un profil")) {
				addNewProfile(newProfileName, settings, false);
			} else {
				settings.updateProfilNameINI(newProfileName);
				Restart restart = new Restart();
				restart.restartApplication();
			}
		}
	}

	public static void changeGridSize(String size, SettingReader settings) {
		if (settings.getDefaultGridSize().equals(size)) {
			return;
		} else {
			switch (size) {
			case "9x9":
				settings.updateGridSizeINI(size);
				break;

			case "16x16":
				settings.updateGridSizeINI(size);
				break;

			case "30x16":
				settings.updateGridSizeINI(size);
				break;

			default:
				String errorMessage = "Une erreur critique s'est produite. Impossible de sélectionner cette taille de grille.";
				Popup.errorSettingPopup(errorMessage);
				break;
			}
		}
	}

	public static void changeColorGrid(String colorName, SettingReader settings) {
		if (settings.getDefaultColorGrid().equals(colorName)) {
			return;
		} else {
			switch (colorName) {
			case "Gris":
				settings.updateColorINI(colorName);
				break;

			case "Bleu":
				settings.updateColorINI(colorName);
				break;

			case "Rouge":
				settings.updateColorINI(colorName);
				break;

			case "Vert":
				settings.updateColorINI(colorName);
				break;

			default:
				String errorMessage = "Une erreur critique s'est produite. Impossible de trouver la couleur choisie.";
				Popup.errorSettingPopup(errorMessage);
				break;
			}
		}
	}
}
