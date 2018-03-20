package game.demineur.data;

import java.util.ArrayList;

import game.demineur.items.Case;
import game.library.Coordonnees;
import game.library.MineUtils;

public class BooleanChangeTest {
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

	}
}
