package game.demineur.data;

import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.demineur.items.Case;
import game.demineur.popup.Popup;
import game.demineur.utils.Path;
import game.library.Coordonnees;
import game.library.MineUtils;

public class endTheGame {
	public void DestroyAllBombs(Coordonnees firstBomb, ArrayList<Integer> listOfAllBombs) {
		ArrayList<Case> list = MineUtils.getListDesCases();

		for (int i = 0; i < list.size(); i++) {
			StringBuilder position = new StringBuilder();
			position.append(list.get(i).getPosition().getAbscisse());
			position.append(list.get(i).getPosition().getOrdonnees());

			int positionOfCase = Integer.parseInt(position.toString());

			for (int j = 0; j < listOfAllBombs.size(); j++) {
				if (positionOfCase == listOfAllBombs.get(j)) {
					list.get(i).changeToBomb();
				}

			}
		}

		playExplosion();
		discoverEverything();
		Popup.defeatPopup();

	}

	public void discoverEverything() {
		ArrayList<Case> list = MineUtils.getListDesCases();

		for (int i = 0; i < list.size(); i++) {
			Case c = list.get(i);

			if (!c.discovered) {
				if (c.getState() == Case.EXPLOSIVE) {
					c.changeToBomb();
				} else if (c.getState() == Case.SAFE) {
					c.changeToNumber(c.getNumberOfExplosiveNeighboor());
				}
			}
		}
	}

	public void playExplosion() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(this.getClass().getResourceAsStream(Path.EXPLOSION_SOUND));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();

		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}
