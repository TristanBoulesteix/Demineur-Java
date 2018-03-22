package game.demineur.menu.settings;

import java.io.File;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import game.demineur.popup.Popup;
import game.demineur.utils.Path;

public class SettingReader {
	private final File settingPathFile = new File(Path.DEFAULT_PATH_INI);

	private String pathOfSettings;
	private String profilName, score, defaultGridSize;

	private Wini iniPath = null;
	private Wini iniSetting;

	public SettingReader() {
		getCurrentSettingPath();
		setCurrentSettingSate();
	}

	private void setCurrentSettingSate() {
		try {
			iniSetting = new Wini(new File(pathOfSettings));
			String tempProfilName = iniSetting.get("User informations", "Nom");

			if (tempProfilName == "Guest") {
				profilName = Popup.needToCreateNewProfil();
			} else {
				profilName = tempProfilName;
			}

			score = iniSetting.get("User informations", "Best score");
			defaultGridSize = iniSetting.get("Game settings", "Default grid");

		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCurrentSettingPath() {
		String temporaryString = null;

		try {
			iniPath = new Wini(settingPathFile);
			temporaryString = iniPath.get("Relative path", "Current_path");

		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (temporaryString.equals("Default path")) {
			pathOfSettings = iniPath.get("Relative path", "Default_path");
		} else {
			pathOfSettings = temporaryString;
		}
	}

	/**
	 * @return the pathOfSettings
	 */
	public String getPathOfSettings() {
		return pathOfSettings;
	}

	/**
	 * @return the profilName
	 */
	public String getProfilName() {
		return profilName;
	}

	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	/**
	 * @return the defaultGridSize
	 */
	public String getDefaultGridSize() {
		return defaultGridSize;
	}
}
