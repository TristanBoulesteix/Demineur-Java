package game.demineur;

import game.demineur.menu.MainMenuWindow;
import game.demineur.menu.SettingPopupInfo;

public class Demineur {
	public static void main(String[] args) {
		openMenu();
		SettingPopupInfo test = new SettingPopupInfo();
		test.readDefaultSetttingsPath();
	}

	public static void openMenu() {
		MainMenuWindow window = new MainMenuWindow();
		window.getFrmMenu().setVisible(true);
	}

}
