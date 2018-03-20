package game.demineur.menu;

import javax.swing.JDialog;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class SettingPopup extends JDialog {
	public SettingPopup(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);

		this.setSize(200, 200);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		showSettingPopup();
	}

	public void showSettingPopup() {
		this.setVisible(true);
	}
}
