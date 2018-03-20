package game.demineur.items;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.demineur.data.BooleanChangeTest;
import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;
import game.library.Coordonnees;

@SuppressWarnings("serial")
abstract class CaseItem extends JLabel {
	public static final int EXPLOSIVE = 100;
	public static final int SAFE = 200;

	public static final int CASE = -100;
	public static final int BOMB = -200;
	public static final int FLAG = -300;
	public static final int NUMBER = -400;

	private final Dimension DIMENSION = new Dimension(25, 25);

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

	protected int numberOfExplosiveNeighboor;

	protected Coordonnees position;

	protected ArrayList<Integer> listOfAllBombs;

	protected BooleanChangeTest booom = new BooleanChangeTest();

	/**
	 * @param state
	 * @param listOfAllBombs
	 * @param nombre
	 *            de voisins
	 */
	public CaseItem(int state, int neighboors, Coordonnees position, ArrayList<Integer> listOfAllBombs) {
		this.setPreferredSize(DIMENSION);
		this.state = state;
		this.position = position;
		this.listOfAllBombs = listOfAllBombs;
		setNumberOfExplosiveNeighboor(neighboors);
	}

	/**
	 * Change a case to a bomb
	 */
	public void changeToBomb() {
		ImagesSettings setImage = new ImagesSettings();
		setImage.displayImage(this, Path.BOMB_PICTURE, 25, 25);
	}

	public void changeToFlag() {
		ImagesSettings setImage = new ImagesSettings();
		setImage.displayImage(this, Path.FLAG_PICTURE, 25, 25);
	}

	/**
	 * Change a case to a number with number of neighboor
	 * 
	 * @param numberOfNeighboor
	 */
	public void changeToNumber(int numberOfNeighboor) {
		this.setIcon(null);

		if (numberOfNeighboor == 0) {
			this.setOpaque(true);
			this.setBackground(new Color(224, 224, 224));
		} else if (numberOfNeighboor > 0) {
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
			String number = String.valueOf(numberOfNeighboor);
			this.setText(number);
		}
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

	/**
	 * The number of bomb next to the case
	 * 
	 * @author Tristan BOULESTEIX
	 */
	public int getNumberOfExplosiveNeighboor() {
		return numberOfExplosiveNeighboor;
	}

	/**
	 * This method is private. Do NOT change manually this value !
	 * 
	 * @param number
	 *            of bombs
	 * @author Tristan BOULESTEIX
	 */
	protected void setNumberOfExplosiveNeighboor(int numberOfExplosiveNeighboor) {
		this.numberOfExplosiveNeighboor = numberOfExplosiveNeighboor;
	}

	/**
	 * The number of bomb next to the case
	 * 
	 * @return current position
	 * @author Tristan BOULESTEIX
	 */
	public Coordonnees getPosition() {
		return position;
	}

	/**
	 * The list of bombs
	 * 
	 * @return An ArrayList of all bombs
	 * @author Tristan BOULESTEIX
	 */
	public ArrayList<Integer> getListOfAllBombs() {
		return listOfAllBombs;
	}
}
