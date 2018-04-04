package game.demineur.ingame;

import javax.swing.JFrame;

import game.demineur.menu.settings.SettingReader;

public class LaunchGame {
	public void newGame(String[] settingData, SettingReader settings, JFrame menuFrame) {
		int size;

		switch (settingData[1]) {
		case "9x9":
			size = 9;
			break;

		case "16x16":
			size = 16;
			break;

		case "30x16":
			size = 30;
			break;

		default:
			size = 9;
			break;
		}

		GameWindow window = new GameWindow(settingData[0], size, settingData[2], settings, menuFrame);
		window.getFrame().setVisible(true);
	}
}
