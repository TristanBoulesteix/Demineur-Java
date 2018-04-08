package game.demineur.items;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import game.demineur.endIt.DetectVictory;
import game.demineur.utils.ImagesSettings;
import game.library.Coordonnees;
import game.library.MineUtils;

@SuppressWarnings("serial")
public class Case extends CaseItem {
	public Case(int neighboor, Coordonnees position, ArrayList<Coordonnees> arrayCases, Chrono timer, String colorPath,
			JFrame currentWindow, JFrame menuWindow, int xMax, int yMax) {
		super(neighboor, position, arrayCases, colorPath);
		resize();

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					if (getStatus() == CaseItem.CASE && !isDiscovered()) {
						setStatus(CaseItem.FLAG);
						changeToFlag();

						if (getState() == CaseItem.EXPLOSIVE) {
							DetectVictory.addAFlaggedBomb(timer, currentWindow, menuWindow);
						} else {
							DetectVictory.removeAFlaggedBomb();
						}

					} else if (getStatus() == CaseItem.FLAG) {
						resize();
						setDiscovered(false);

						if (getState() == CaseItem.EXPLOSIVE) {
							DetectVictory.removeAFlaggedBomb();
						} else {
							DetectVictory.addAFlaggedBomb(timer, currentWindow, menuWindow);
						}
					}

				} else if (SwingUtilities.isLeftMouseButton(e) && getStatus() == CaseItem.CASE && !isDiscovered()) {
					if (getState() == CaseItem.EXPLOSIVE) {
						setStatus(CaseItem.BOMB);
						changeToBomb(true);
						booom.defeat(timer, currentWindow, menuWindow);
					} else if (getState() == CaseItem.SAFE) {
						setStatus(CaseItem.NUMBER);
						changeToNumber(neighboor);
						if (getNumberOfExplosiveNeighboor() == 0) {
							MineUtils.revealEmptyCaseAdjacent(getPosition(), xMax, yMax);
						}
					}
				}

			}
		});
	}

	public void resize() {
		setStatus(CaseItem.CASE);
		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, path, 25, 25);
	}

}
