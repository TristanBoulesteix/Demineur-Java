package game.demineur.items;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Case extends JLabel {
	public static final int BOMB = 100;
	public static final int FLAG = 200;
	public static final int CARRE = 300;

	protected int state = CARRE;
	protected int statusGiven;

	private final Dimension size = new Dimension(25, 25);

	public Case(int status) {
		this.setPreferredSize(size);
		setState(status);
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getStatusGiven() {
		return statusGiven;
	}

	public void setStatusGiven(int statusGiven) {
		this.statusGiven = statusGiven;
	}

}
