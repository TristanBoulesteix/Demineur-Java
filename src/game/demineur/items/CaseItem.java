package game.demineur.items;

import java.awt.Dimension;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class CaseItem extends JLabel {
	public static final int EXPLOSIVE = 100;
	public static final int SAFE = 200;

	public static final int CASE = -100;
	public static final int BOMB = -200;
	public static final int FLAG = -300;

	protected int state;

	private final Dimension size = new Dimension(25, 25);

	public CaseItem(int newState) {
		this.setPreferredSize(size);
		state = newState;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}
}
