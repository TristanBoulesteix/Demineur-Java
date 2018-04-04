package game.library;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveAs {
	public File SaveFile() {
		File fileToSave = null;

		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Choisissez ou sauvegarder votre fichier");
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("*.png", "png");
		fileChooser.addChoosableFileFilter(filter);

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			fileToSave = fileChooser.getSelectedFile();
		}

		if (!fileToSave.getPath().toLowerCase().endsWith(".png")) {
			fileToSave = new File(fileToSave.getPath() + ".png");
		}

		return fileToSave;
	}
}
