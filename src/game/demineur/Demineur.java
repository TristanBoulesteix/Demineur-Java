package game.demineur;

import game.demineur.menu.MainMenuWindow;

public class Demineur {
	public static void main(String[] args) {
		openMenu();
	}

	public static void openMenu() {
		MainMenuWindow window = new MainMenuWindow();
		window.getFrmMenu().setVisible(true);
	}

}
