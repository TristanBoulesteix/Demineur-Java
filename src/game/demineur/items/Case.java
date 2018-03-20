package game.demineur.items;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;
import game.library.Coordonnees;

@SuppressWarnings("serial")
public class Case extends CaseItem {
	public Case(int state, int neighboor, Coordonnees position, ArrayList<Integer> listOfAllBombs) {
		super(state, neighboor, position, listOfAllBombs);
		setStatus(CaseItem.CASE);
		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.DEFAULT_CUBE_PICTURE, 25, 25);

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) && getStatus() == CaseItem.CASE) {
					setStatus(CaseItem.FLAG);
					changeToFlag();

				} else if (SwingUtilities.isLeftMouseButton(e) && getStatus() == CaseItem.CASE) {
					System.out.println(getState());

					if (getState() == CaseItem.EXPLOSIVE) {
						setStatus(CaseItem.BOMB);
						changeToBomb();
						booom.DestroyAllBombs(getPosition(), getListOfAllBombs());
					} else if (getState() == CaseItem.SAFE) {
						setStatus(CaseItem.NUMBER);
						changeToNumber(neighboor);
					}
				}

			}
		});
	}

}
