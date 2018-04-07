package game.demineur.ingame;

import javax.swing.JFrame;

import game.demineur.menu.settings.SettingReader;

public class RestartGame {
	private String profileName, GridColor;
	private int GridSize;
	private SettingReader settings;
	private JFrame currentWindow, menuWindow;

	public RestartGame(String profileName, SettingReader settings, JFrame currentWindow, JFrame menuWindow) {
		this.profileName = profileName;
		this.GridSize = getGridSize(settings);
		this.GridColor = settings.getDefaultColorGrid();
		this.settings = settings;
		this.currentWindow = currentWindow;
		this.menuWindow = menuWindow;
	}

	public void startANewGame() {
		currentWindow.dispose();
		GameWindow restart = new GameWindow(profileName, GridSize, GridColor, settings, menuWindow);
		restart.getFrame().setVisible(true);
	}

	private int getGridSize(SettingReader settings) {
		String size = settings.getDefaultGridSize();

		switch (size) {
		case "9x9":
			return 9;

		case "16x16":
			return 16;

		case "30x16":
			return 30;

		default:
			return 9;
		}
	}
}
