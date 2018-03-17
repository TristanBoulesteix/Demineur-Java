package game.demineur.ingame;

public class LaunchGame {
	public void newGame(String gridSize) {
		int size;

		if (gridSize.equals("9x9")) {
			size = 9;
		} else if (gridSize.equals("16x16")) {
			size = 16;
		} else if (gridSize.equals("30x16")) {
			size = 30;
		}

		GameWindow window = new GameWindow();
		window.getFrame().setVisible(true);
	}
}
