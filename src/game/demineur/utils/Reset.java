package game.demineur.utils;

import java.io.File;

import game.demineur.menu.settings.SettingReader;
import game.demineur.popup.Popup;

public class Reset {
	private boolean success = true;

	public Reset() {
		deleteProfilesFiles();
		checkForSuccess();
	}

	public Reset(SettingReader settings) {
		deleteProfilesFiles();
		deleteSettings(settings);
		checkForSuccess();
	}

	private void deleteProfilesFiles() {
		String path = Path.PROFIL_PATH;
		File directory = new File(path);
		File[] listFiles = directory.listFiles();

		for (int i = 0; i < listFiles.length; i++) {
			success = listFiles[i].delete();
		}
	}

	private void deleteSettings(SettingReader settings) {
		String path = Path.SETTINGS_PATH + "settings.ini";
		File file = new File(path);

		file.delete();

		success = settings.setDefaultSettingsPath();
	}

	private void checkForSuccess() {
		if (!success) {
			Popup.errorWhenResetPopup();
		}
	}
}
