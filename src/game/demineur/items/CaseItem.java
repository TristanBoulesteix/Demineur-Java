package game.demineur.items;

import java.awt.Dimension;

import javax.swing.JLabel;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

@SuppressWarnings("serial")
abstract class CaseItem extends JLabel {
	public static final int EXPLOSIVE = 100;
	public static final int SAFE = 200;

	public static final int CASE = -100;
	public static final int BOMB = -200;
	public static final int FLAG = -300;

	/**
	 * The current state of the item. It could be an {@code this.EXPLOSIVE} or a
	 * {@code this.SAFE} state.
	 * 
	 * @author Tristan BOULESTEIX
	 */
	protected int state;

	/**
	 * The current status of the item. It could be a {@code this.CASE}, a
	 * {@code this.BOMB} or a {@code this.FLAG}.
	 * 
	 * @author Tristan BOULESTEIX
	 */
	protected int status;

	private final Dimension DIMENSION = new Dimension(25, 25);

	public CaseItem(int state) {
		this.setPreferredSize(DIMENSION);
		setState(state);
	}

	protected void changeToBomb() {
		ImagesSettings setImage = new ImagesSettings();
		setImage.displayImage(this, Path.BOMB_PICTURE, 25, 25);
	}

	/**
	 * The current state of the item. It could be an {@code this.EXPLOSIVE} or a
	 * {@code this.SAFE} state.
	 * 
	 * @author Tristan BOULESTEIX
	 */
	public int getState() {
		return state;
	}

	/**
	 * The current state of the item. It could be an {@code this.EXPLOSIVE} or a
	 * {@code this.SAFE} state.
	 * 
	 * @author Tristan BOULESTEIX
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * The current status of the item. It could be a {@code this.CASE}, a
	 * {@code this.BOMB} or a {@code this.FLAG}.
	 * 
	 * @author Tristan BOULESTEIX
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * The current status of the item. It could be a {@code this.CASE}, a
	 * {@code this.BOMB} or a {@code this.FLAG}.
	 * 
	 * This method is private.
	 * 
	 * @see status
	 * @author Tristan BOULESTEIX
	 */
	protected void setStatus(int status) {
		this.status = status;
	}
}
