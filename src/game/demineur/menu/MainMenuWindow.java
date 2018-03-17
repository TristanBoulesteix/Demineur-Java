package game.demineur.menu;

import java.awt.EventQueue;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

public class MainMenuWindow {

	private JFrame frmMenu;

	public JFrame getFrmMenu() {
		return frmMenu;
	}

	public void setFrmMenu(JFrame frmMenu) {
		this.frmMenu = frmMenu;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainMenuWindow window = new MainMenuWindow();
					window.getFrmMenu().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainMenuWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrmMenu(new JFrame());
		getFrmMenu().setTitle("Menu");
		getFrmMenu().setBounds(100, 100, 600, 500);
		getFrmMenu().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE };
		frmMenu.getContentPane().setLayout(gridBagLayout);

		JMenuBar menuBar = new JMenuBar();
		frmMenu.setJMenuBar(menuBar);
	}

}
