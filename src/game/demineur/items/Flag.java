package game.demineur.items;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

@SuppressWarnings("serial")
public class Flag extends CaseItem {
	public Flag(int newState) {
		super(newState);

		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.FLAG_PICTURE, 25, 25);
	}

}
