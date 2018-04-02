package game.demineur.items;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;
import game.library.Coordonnees;
import game.library.MineUtils;

@SuppressWarnings("serial")
public class Case extends CaseItem {
	public Case(int neighboor, Coordonnees position, ArrayList<Coordonnees> arrayCases) {
		super(neighboor, position, arrayCases);
		resize();

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					if (getStatus() == CaseItem.CASE && !isDiscovered()) {
						setStatus(CaseItem.FLAG);
						changeToFlag();
					} else if (getStatus() == CaseItem.FLAG) {
						resize();
						setDiscovered(false);
					}

				} else if (SwingUtilities.isLeftMouseButton(e) && getStatus() == CaseItem.CASE && !isDiscovered()) {
					System.out.println(getState());

					if (getState() == CaseItem.EXPLOSIVE) {
						setStatus(CaseItem.BOMB);
						changeToBomb(true);
						booom.DestroyAllBombs(getPosition());
					} else if (getState() == CaseItem.SAFE) {
						setStatus(CaseItem.NUMBER);
						changeToNumber(neighboor);
						if (getNumberOfExplosiveNeighboor() == 0) {
							MineUtils.revealEmptyCaseAdjacent(getPosition());
						}
					}
				}

			}
		});
	}

	public void resize() {
		setStatus(CaseItem.CASE);
		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.DEFAULT_CUBE_PICTURE, 25, 25);
	}

}
