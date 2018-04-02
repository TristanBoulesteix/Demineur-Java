package game.demineur.endIt;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.demineur.items.Case;
import game.demineur.items.Chrono;
import game.demineur.popup.Popup;
import game.demineur.utils.Path;
import game.library.MineUtils;

public class endTheGame {
	public void defeat(Chrono timer) {
		timer.stopTimer();
		playExplosion();
		discoverEverything();
		Popup.defeatPopup();

	}

	public static void victory(Chrono timer) {
		timer.stopTimer();
		discoverEverything();
		Popup.victoryPopup();
	}

	public static void discoverEverything() {
		Case[][] list = MineUtils.getCaseList();

		for (int i = 0; i < list.length; i++) {
			for (int j = 0; j < list[i].length; j++) {
				Case c = list[i][j];

				if (!c.isDiscovered()) {
					if (c.getState() == Case.EXPLOSIVE) {
						c.changeToBomb(true);
					} else if (c.getState() == Case.SAFE) {
						c.changeToNumber(c.getNumberOfExplosiveNeighboor());
					}
				} else if (c.getStatus() == Case.FLAG && c.getState() == Case.EXPLOSIVE) {
					c.changeToBomb(false);
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
