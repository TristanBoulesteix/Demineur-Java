package game.demineur.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class SettingPopup extends JDialog {
	private SettingPopupInfo info = new SettingPopupInfo();
	private JLabel profilLabel, generalSettingsLabel, gameSizeLabel;
	private JRadioButton profilListBox1, profilListBox2, profilListBox3;
	private JComboBox<String> globalSettingsChoice, profilChoiceList;
	private JTextField taille;

	public SettingPopup(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(550, 270);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
	}

	public SettingPopupInfo showSettingPopup() {
		this.setVisible(true);
		return this.info;
	}

	private void initComponent() {

		JPanel profilPan = new JPanel();
		profilPan.setBackground(Color.white);
		profilPan.setPreferredSize(new Dimension(220, 90));
		profilChoiceList = new JComboBox<String>();
		profilChoiceList.setEditable(true);
		profilChoiceList.setPreferredSize(new Dimension(100, 25));
		profilPan.setBorder(BorderFactory.createTitledBorder("Profil"));
		profilLabel = new JLabel("Sélectionnez un profil :");
		profilPan.add(profilLabel);
		profilPan.add(profilChoiceList);

		JPanel gridSize = new JPanel();
		gridSize.setBackground(Color.white);
		gridSize.setBorder(BorderFactory.createTitledBorder("Paramètre du jeu"));
		gridSize.setPreferredSize(new Dimension(220, 90));
		gameSizeLabel = new JLabel("Taille de la grille :");
		profilListBox1 = new JRadioButton("9x9");
		profilListBox1.setSelected(true);
		profilListBox2 = new JRadioButton("16x16");
		profilListBox3 = new JRadioButton("16x32");
		ButtonGroup group = new ButtonGroup();
		group.add(profilListBox1);
		group.add(profilListBox2);
		group.add(profilListBox3);
		gridSize.add(gameSizeLabel);
		gridSize.add(profilListBox1);
		gridSize.add(profilListBox2);
		gridSize.add(profilListBox3);

		JPanel othersSettings = new JPanel();
		othersSettings.setBackground(Color.white);
		othersSettings.setPreferredSize(new Dimension(440, 60));
		othersSettings.setBorder(BorderFactory.createTitledBorder("Paramètre du jeu"));
		globalSettingsChoice = new JComboBox<String>();
		globalSettingsChoice.addItem("Masculin");
		globalSettingsChoice.addItem("Féminin");
		globalSettingsChoice.addItem("Indéterminé");
		generalSettingsLabel = new JLabel("");
		othersSettings.add(generalSettingsLabel);
		othersSettings.add(globalSettingsChoice);

		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(profilPan);
		content.add(gridSize);
		content.add(othersSettings);

		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");

		okBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				info = new SettingPopupInfo((String) globalSettingsChoice.getSelectedItem(),
						(String) profilChoiceList.getSelectedItem(), getGridSize());
				setVisible(false);
			}

			public String getGridSize() {
				return (profilListBox1.isSelected()) ? profilListBox1.getText()
						: (profilListBox2.isSelected()) ? profilListBox2.getText()
								: (profilListBox3.isSelected()) ? profilListBox3.getText() : profilListBox1.getText();
			}
		});

		JButton cancelBouton = new JButton("Annuler");
		cancelBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});

		control.add(okBouton);
		control.add(cancelBouton);

		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
}
