package game.demineur;

import game.demineur.menu.MainMenuWindow;
import game.demineur.menu.settings.SettingReader;

public class Demineur {
	public static void main(String[] args) {
		SettingReader settings = new SettingReader();
		openMenu(settings);
	}

	public static void openMenu(SettingReader settings) {
		MainMenuWindow window = new MainMenuWindow(settings);
		window.getFrmMenu().setVisible(true);
	}

}
