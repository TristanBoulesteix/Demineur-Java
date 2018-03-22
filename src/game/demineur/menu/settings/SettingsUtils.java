package game.demineur.menu.settings;

import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import game.demineur.utils.Path;

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
}
