package game.demineur.menu;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import game.demineur.utils.Path;

public class SettingPopupInfo {
	private String nomJoueur, defaultGridSize, score;

	public SettingPopupInfo() {
		readDefaultSetttingsPath();
	}

	public void readDefaultSetttingsPath() {
		try {
			Properties pathProperties = new Properties();
			pathProperties.load(new FileInputStream(Path.DEFAULT_PATH_INI));
			System.out.print(pathProperties.getProperty("Current_path"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
