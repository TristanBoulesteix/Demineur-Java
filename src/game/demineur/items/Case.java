package game.demineur.items;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Case extends JLabel {
	public static final int BOMB = 100;
	public static final int FLAG = 200;
	public static final int CARRE = 300;

	protected int state = CARRE;
	protected int originalStatus;
	protected int currentStatus;

	private final Dimension size = new Dimension(25, 25);

	public Case(int status) {
		this.setPreferredSize(size);
		originalStatus = status;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getOriginalStatus() {
		return originalStatus;
	}

	public int getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(int currentStatus) {
		this.currentStatus = currentStatus;
	}

}
