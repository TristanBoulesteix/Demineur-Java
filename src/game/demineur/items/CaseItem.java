package game.demineur.items;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import game.demineur.data.endTheGame;
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

	private boolean discovered = false;

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

	protected ArrayList<Coordonnees> listOfAllBombs;

	protected endTheGame booom = new endTheGame();

	/**
	 * @param nombre
	 *            de voisins
	 * @param position
	 * @param arrayCases
	 */
	public CaseItem(int neighboors, Coordonnees position, ArrayList<Coordonnees> arrayCases) {
		this.setPreferredSize(DIMENSION);
		this.state = SAFE;
		this.position = position;
		this.listOfAllBombs = arrayCases;
		setNumberOfExplosiveNeighboor(neighboors);
	}

	/**
	 * Change a case to a bomb
	 * 
	 * @param needTo
	 */
	public void changeToBomb(boolean bombIsNotDefused) {
		String pathPicture;

		if (bombIsNotDefused) {
			pathPicture = Path.BOMB_PICTURE;
		} else {
			pathPicture = Path.DEFUSED_BOMB_PICTURE;
		}

		ImagesSettings setImage = new ImagesSettings();
		setImage.displayImage(this, pathPicture, 25, 25);
		setDiscovered(true);
	}

	public void changeToFlag() {
		ImagesSettings setImage = new ImagesSettings();
		setImage.displayImage(this, Path.FLAG_PICTURE, 25, 25);
		setDiscovered(true);
	}

	/**
	 * Change a case to a number with number of neighboor
	 * 
	 * @param numberOfNeighboor
	 */
	public void changeToNumber(int numberOfNeighboor) {
		this.setIcon(null);
		this.setMinimumSize(DIMENSION);

		if (numberOfNeighboor == 0) {
			this.setOpaque(true);
			this.setBackground(new Color(224, 224, 224));
		} else if (numberOfNeighboor > 0) {
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setVerticalAlignment(SwingConstants.CENTER);
			String number = String.valueOf(numberOfNeighboor);
			this.setText(number);

			switch (numberOfNeighboor) {
			case 1:
				this.setForeground(new Color(0, 156, 253));
				break;

			case 2:
				this.setForeground(new Color(0, 192, 0));
				break;

			case 3:
				this.setForeground(new Color(254, 162, 1));
				break;

			default:
				this.setForeground(new Color(255, 0, 0));
				break;
			}

		}

		setDiscovered(true);
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
	 * {@code this.BOMB}, a {@code this.NUMBER} or a {@code this.FLAG}.
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
	public ArrayList<Coordonnees> getListOfAllBombs() {
		return listOfAllBombs;
	}

	/**
	 * Change the case to "discovered" status: It means that the case state's is
	 * known by the player or flagged.
	 * 
	 * @param boolean
	 *            discovered
	 * @author Tristan BOULESTEIX
	 */
	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	/**
	 * Check if the current case is discovered or not
	 * 
	 * @return boolean discovered
	 * @author Tristan BOULESTEIX
	 */
	public boolean isDiscovered() {
		return this.discovered;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("state=").append(state);
		sb.append(", status=").append(status);
		sb.append(", position=").append(position);
		sb.append(", bombsAround=").append(numberOfExplosiveNeighboor);
		return sb.toString();
	}
}
