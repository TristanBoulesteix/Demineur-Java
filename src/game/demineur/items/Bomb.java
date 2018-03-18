package game.demineur.items;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

@SuppressWarnings("serial")
public class Bomb extends CaseItem {
	public Bomb(int newState) {
		super(newState);

		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.BOMB_PICTURE, 25, 25);
	}

}
