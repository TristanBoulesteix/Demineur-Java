package game.demineur.utils;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImagesSettings {
	public JLabel displayImage(JLabel lblNewLabel, String imageName) {
		ImageIcon imageToResize1 = new ImageIcon(getClass().getResource(imageName));
		Image img1 = imageToResize1.getImage();
		Image resizedImg1 = img1.getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon resizedFinal1 = new ImageIcon(resizedImg1);
		lblNewLabel.setIcon(resizedFinal1);
		return lblNewLabel;
	}

	public JLabel displayImage(JLabel lblNewLabel, String imageName, int width, int height) {
		ImageIcon imageToResize1 = new ImageIcon(getClass().getResource(imageName));
		Image img1 = imageToResize1.getImage();
		Image resizedImg1 = img1.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon resizedFinal1 = new ImageIcon(resizedImg1);
		lblNewLabel.setIcon(resizedFinal1);
		return lblNewLabel;
	}
}
