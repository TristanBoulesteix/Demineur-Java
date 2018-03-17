package game.demineur.items;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

@SuppressWarnings("serial")
public class CarreEmpty extends Case {
	public CarreEmpty(int status) {
		super(status);
		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.DEFAULT_CUBE_PICTURE, 25, 25);
	}

}
