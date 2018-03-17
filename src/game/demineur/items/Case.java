package game.demineur.items;

import java.awt.Dimension;

import javax.swing.JLabel;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

public class Case {
	protected JLabel cube = new JLabel();

	public static final int BOMB = 1;
	public static final int FLAG = 2;
	public static final int CARRE = 3;

	private final Dimension size = new Dimension(25, 25);

	public Case(int status) {
		cube.setPreferredSize(size);
		addCarreIcon();

	}

	private void addIcon(int status) {
		if (status == Case.BOMB) {
			addBombIcon();
		} else if (status == Case.FLAG) {
			addFlagIcon();
		}
	}

	private void addBombIcon() {
		ImagesSettings resizePicture = new ImagesSettings();
		cube = resizePicture.displayImage(cube, Path.BOMB_PICTURE);
	}

	private void addFlagIcon() {
		ImagesSettings resizePicture = new ImagesSettings();
		cube = resizePicture.displayImage(cube, Path.FLAG_PICTURE);
	}

	private void addCarreIcon() {
		ImagesSettings resizePicture = new ImagesSettings();
		cube = resizePicture.displayImage(cube, Path.FLAG_PICTURE);
	}
}
