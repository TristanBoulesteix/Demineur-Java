package game.demineur.items;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

@SuppressWarnings("serial")
public class Case extends CaseItem {
	public Case(int status) {
		super(status);
		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.DEFAULT_CUBE_PICTURE, 25, 25);
	}

}
