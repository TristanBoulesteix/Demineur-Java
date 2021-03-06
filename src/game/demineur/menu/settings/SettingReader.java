package game.demineur.menu.settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import game.demineur.popup.Popup;
import game.demineur.utils.Path;
import game.library.Restart;

public class SettingReader {
	private final File settingPathFile = new File(Path.DEFAULT_PATH_INI);

	private String pathOfSettings;
	private String profilName, score, defaultGridSize, defaultColorGrid;

	private Wini iniPath = null;
	@SuppressWarnings("unused")
	private Wini iniDefaultSetting;
	private Wini iniSettings;

	private File settingFile;

	public SettingReader() {
		getCurrentSettingPath();
		setCurrentSettingSate();
	}

	private void setCurrentSettingSate() {
		try {
			settingFile = new File(Path.SETTINGS_PATH + "setting.ini");
			pathOfSettings = iniPath.get("Relative path", "Default_path");
			iniDefaultSetting = new Wini(new File(pathOfSettings));
			iniSettings = new Wini(settingFile);
			String tempProfilName = iniSettings.get("User informations", "Nom");

			if (tempProfilName.equals("Guest")) {
				profilName = Popup.needToCreateNewProfil();
				SettingsUtils.addNewProfile(profilName, this, true);
			} else {
				profilName = tempProfilName;
			}

			score = iniSettings.get("User informations", "Best score");
			defaultGridSize = iniSettings.get("Game settings", "Default grid");
			defaultColorGrid = iniSettings.get("Game settings", "Color grid");

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
			createNewSettingsFile();

		} else {
			pathOfSettings = temporaryString;
		}
	}

	public void updateProfilNameINI(String profilName) {
		try {
			iniSettings = new Wini(settingFile);
			iniSettings.put("User informations", "Nom", profilName);
			iniSettings.store();

			this.profilName = profilName;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateGridSizeINI(String gridSize) {
		try {
			iniSettings = new Wini(settingFile);
			iniSettings.put("Game settings", "Default grid", gridSize);
			iniSettings.store();

			this.defaultGridSize = gridSize;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateColorINI(String colorName) {
		try {
			iniSettings = new Wini(settingFile);
			iniSettings.put("Game settings", "Color grid", colorName);
			iniSettings.store();

			setDefaultColorGrid(colorName);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createNewSettingsFile() {
		String settingName = Path.SETTINGS_PATH + "setting.ini";
		File settingModel = new File(pathOfSettings);
		InputStream streamed;
		String line;

		try {
			FileWriter writer = new FileWriter(settingName, true);
			BufferedWriter bufferW = new BufferedWriter(writer);
			streamed = new FileInputStream(settingModel);
			InputStreamReader reader = new InputStreamReader(streamed);
			BufferedReader bufferR = new BufferedReader(reader);

			while ((line = bufferR.readLine()) != null) {
				bufferW.write(line + "\n");
			}

			bufferW.close();
			bufferR.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		updatePathOfSetting(settingName);

		Restart restart = new Restart();
		restart.restartApplication();
	}

	private void updatePathOfSetting(String path) {
		File file = new File(path);
		String absolutePath = file.getAbsoluteFile().getParentFile().getAbsolutePath();

		try {
			iniPath = new Wini(settingPathFile);
			iniPath.put("Relative path", "Current_path", path);
			iniPath.put("Absolute path", "Current_absolute path", absolutePath);
			iniPath.store();

		} catch (IOException e) {
			e.printStackTrace();
		}

		settingFile = file;

		try {
			iniSettings = new Wini(file);
		} catch (InvalidFileFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean setDefaultSettingsPath() {
		boolean success = true;

		try {
			iniPath.put("Relative path", "Current_path", "Default path");
			iniPath.put("Absolute path", "Current_absolute path", "null");
			iniPath.store();
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}

		return success;
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

	/**
	 * @return the defaultColorGrid
	 */
	public String getDefaultColorGrid() {
		return defaultColorGrid;
	}

	/**
	 * @param defaultColorGrid
	 *            the defaultColorGrid to set
	 */
	public void setDefaultColorGrid(String defaultColorGrid) {
		this.defaultColorGrid = defaultColorGrid;
	}
}
