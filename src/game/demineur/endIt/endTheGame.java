package game.demineur.endIt;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import game.demineur.ingame.RestartGame;
import game.demineur.items.Case;
import game.demineur.items.Chrono;
import game.demineur.menu.settings.SettingReader;
import game.demineur.popup.Popup;
import game.demineur.utils.Path;
import game.library.MineUtils;

public class endTheGame {
	private static SettingReader settings;
	private static String profileName;

	public static void initialize(SettingReader settings, String currentProfile) {
		endTheGame.settings = settings;
		endTheGame.profileName = currentProfile;
	}

	public void defeat(Chrono timer, JFrame currentWindow, JFrame menu) {
		timer.stopTimer();
		playExplosion();
		discoverEverything();

		CalculTime calcTime = new CalculTime(profileName, timer.getHeuresMinutesAndSecondes(), false);
		EndGameText text = new EndGameText(calcTime.getFiveShortTimes(), calcTime.getAllTimes(),
				timer.getHeuresMinutesAndSecondes(), false);

		boolean restart = Popup.defeatPopup(text);

		if (restart) {
			RestartGame newGame = new RestartGame(profileName, settings, currentWindow, menu);
			newGame.startANewGame();
		} else {
			System.exit(0);
		}

	}

	public void finishGame(Chrono timer) {
		timer.stopTimer();
		discoverEverything();
	}

	public static void victory(Chrono timer, JFrame currentWindow, JFrame menu) {
		timer.stopTimer();

		CalculTime calcTime = new CalculTime(profileName, timer.getHeuresMinutesAndSecondes(), true);
		EndGameText text = new EndGameText(calcTime.getFiveShortTimes(), calcTime.getAllTimes(),
				timer.getHeuresMinutesAndSecondes(), true);

		discoverEverything();
		boolean restart = Popup.victoryPopup(text);

		if (restart) {
			RestartGame newGame = new RestartGame(profileName, settings, currentWindow, menu);
			newGame.startANewGame();
		} else {
			System.exit(0);
		}
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
