package game.demineur.endIt;

import game.demineur.items.Chrono;

public class DetectVictory {
	private static int numberOfBombsFlagged = 0;
	private static int totalOfBombs;

	/**
	 * @return the totalOfBombs
	 */
	private static int getTotalOfBombs() {
		return totalOfBombs;
	}

	/**
	 * @param totalOfBombs
	 *            the totalOfBombs to set
	 */
	private static void setTotalOfBombs(int totalOfBombs) {
		DetectVictory.totalOfBombs = totalOfBombs;
	}

	/**
	 * @return the numberOfBombsFlagged
	 */
	private static int getNumberOfBombsFlagged() {
		return numberOfBombsFlagged;
	}

	/**
	 * @param numberOfBombsFlagged
	 *            the numberOfBombsFlagged to set
	 */
	private static void setNumberOfBombsFlagged(int numberOfBombsFlagged) {
		DetectVictory.numberOfBombsFlagged = numberOfBombsFlagged;
	}

	public static void initialize(int numberOfBombs) {
		setNumberOfBombsFlagged(0);
		setTotalOfBombs(numberOfBombs);
	}

	public static void addAFlaggedBomb(Chrono timer) {
		setNumberOfBombsFlagged(getNumberOfBombsFlagged() + 1);
		checkVictory(timer);
	}

	public static void removeAFlaggedBomb() {
		setNumberOfBombsFlagged(getNumberOfBombsFlagged() - 1);
	}

	private static void checkVictory(Chrono timer) {
		if (getNumberOfBombsFlagged() >= getTotalOfBombs()) {
			endTheGame.victory(timer);
		}
	}
}
