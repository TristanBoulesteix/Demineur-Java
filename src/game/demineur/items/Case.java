package game.demineur.items;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

@SuppressWarnings("serial")
public class Case extends CaseItem {
	public Case(int state, int neighboor) {
		super(state, neighboor);
		setStatus(CaseItem.CASE);
		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.DEFAULT_CUBE_PICTURE, 25, 25);

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(getState());

				if (getState() == CaseItem.EXPLOSIVE) {
					setStatus(CaseItem.BOMB);
					changeToBomb();
				} else if (getState() == CaseItem.SAFE) {
					changeToNumber(neighboor);
				}
			}
		});
	}

}
