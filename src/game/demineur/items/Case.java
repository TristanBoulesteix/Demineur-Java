package game.demineur.items;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.demineur.utils.ImagesSettings;
import game.demineur.utils.Path;

@SuppressWarnings("serial")
public class Case extends CaseItem {
	public Case(int status) {
		super(status);
		setState(CaseItem.CASE);
		ImagesSettings resize = new ImagesSettings();
		resize.displayImage(this, Path.DEFAULT_CUBE_PICTURE, 25, 25);

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (getState() == CaseItem.EXPLOSIVE) {
					setState(CaseItem.BOMB);
					changeToBomb();
				}
			}
		});
	}

}
