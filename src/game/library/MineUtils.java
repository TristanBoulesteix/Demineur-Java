package game.library;

import java.util.ArrayList;

public class MineUtils {
	public static Coordonnees[] generateCoordonneesVoisines(int i, int j) {
		Coordonnees[] tableauCoor = new Coordonnees[8];

		Coordonnees topLeft = new Coordonnees(i - 1, j - 1);
		tableauCoor[0] = topLeft;

		Coordonnees topMiddle = new Coordonnees(i, j - 1);
		tableauCoor[1] = topMiddle;

		Coordonnees topRight = new Coordonnees(i + 1, j - 1);
		tableauCoor[2] = topRight;

		Coordonnees middleLeft = new Coordonnees(i - 1, j);
		tableauCoor[3] = middleLeft;

		Coordonnees middleRight = new Coordonnees(i + 1, j);
		tableauCoor[4] = middleRight;

		Coordonnees bottomLeft = new Coordonnees(i - 1, j + 1);
		tableauCoor[5] = bottomLeft;

		Coordonnees bottomMiddle = new Coordonnees(i, j + 1);
		tableauCoor[6] = bottomMiddle;

		Coordonnees bottomRight = new Coordonnees(i + 1, j + 1);
		tableauCoor[7] = bottomRight;

		return tableauCoor;
	}

	public static boolean isABomb(int dizaine, int unites, ArrayList<Integer> arrayToCheck) {
		boolean isBomb = false;

		StringBuilder temp = new StringBuilder();
		temp.append(dizaine);
		temp.append(unites);

		String temporaryString = temp.toString();
		int coordonnees = Integer.parseInt(temporaryString);

		for (int i = 0; i < arrayToCheck.size(); i++) {
			if (arrayToCheck.get(i) == coordonnees) {
				isBomb = true;
				return isBomb;
			}
		}

		return isBomb;
	}

	public static int giveNeighbourNumber(Coordonnees[] arrayCoor, ArrayList<Integer> list) {
		int numberOfNeighboor = 0;
		arrayCoor = removeNegativeCoordonnees(arrayCoor);

		for (int i = 0; i < arrayCoor.length; i++) {
			if (isABomb(arrayCoor[i].abscisse, arrayCoor[i].ordonnees, list)) {
				numberOfNeighboor++;
			}
		}

		return numberOfNeighboor;
	}

	public static Coordonnees[] removeNegativeCoordonnees(Coordonnees[] coor) {
		for (int i = 0; i < coor.length; i++) {
			if (coor[i].abscisse < 0 || coor[i].ordonnees < 0) {
				coor[i] = new Coordonnees(10, 10);
			}
		}

		return coor;
	}
}
