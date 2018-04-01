package game.library;

import java.util.ArrayList;

import game.demineur.items.Case;

public class MineUtils {
	private static Case[][] casesList = new Case[9][9];

	public static void casePositionArray(Case caseToAdd) {
		Case[][] PlaceCase = getCaseList();

		PlaceCase[caseToAdd.getPosition().getAbscisse()][caseToAdd.getPosition().getOrdonnees()] = caseToAdd;

		setCaseList(PlaceCase);
	}

	public static Case[][] getCaseList() {
		return casesList;
	}

	private static void setCaseList(Case[][] caseGrid) {
		MineUtils.casesList = caseGrid;
	}

	public static Coordonnees[] generateCoordonneesVoisines(int x, int y) {
		Coordonnees[] tableauCoor = new Coordonnees[8];

		Coordonnees topLeft = new Coordonnees(x - 1, y - 1);
		tableauCoor[0] = topLeft;

		Coordonnees topMiddle = new Coordonnees(x, y - 1);
		tableauCoor[1] = topMiddle;

		Coordonnees topRight = new Coordonnees(x + 1, y - 1);
		tableauCoor[2] = topRight;

		Coordonnees middleLeft = new Coordonnees(x - 1, y);
		tableauCoor[3] = middleLeft;

		Coordonnees middleRight = new Coordonnees(x + 1, y);
		tableauCoor[4] = middleRight;

		Coordonnees bottomLeft = new Coordonnees(x - 1, y + 1);
		tableauCoor[5] = bottomLeft;

		Coordonnees bottomMiddle = new Coordonnees(x, y + 1);
		tableauCoor[6] = bottomMiddle;

		Coordonnees bottomRight = new Coordonnees(x + 1, y + 1);
		tableauCoor[7] = bottomRight;

		return tableauCoor;
	}

	public static boolean isABomb(int dizaines, int unites, ArrayList<Coordonnees> arrayCoordonnes) {
		boolean isBomb = false;

		Coordonnees coordonnees = new Coordonnees(unites, dizaines);

		for (int i = 0; i < arrayCoordonnes.size(); i++) {
			if (arrayCoordonnes.get(i).equals(coordonnees)) {
				isBomb = true;
				return isBomb;
			}
		}

		return isBomb;
	}

	public static int giveNeighbourNumber(Coordonnees[] arrayCoor, ArrayList<Coordonnees> arrayCases) {
		int numberOfNeighboor = 0;
		arrayCoor = removeNegativeCoordonnees(arrayCoor);

		for (int i = 0; i < arrayCoor.length; i++) {
			if (isABomb(arrayCoor[i].getOrdonnees(), arrayCoor[i].getAbscisse(), arrayCases)) {
				numberOfNeighboor++;
			}
		}

		return numberOfNeighboor;
	}

	public static Coordonnees[] removeNegativeCoordonnees(Coordonnees[] coor) {
		for (int i = 0; i < coor.length; i++) {
			if (coor[i].getAbscisse() < 0 || coor[i].getOrdonnees() < 0) {
				coor[i] = new Coordonnees(10, 10);
			}
		}

		return coor;
	}

	public static void revealEmptyCaseAdjacent(Coordonnees position) {
		Case[][] listeDesCases = getCaseList();
		Coordonnees[] neighboor = generateCoordonneesVoisines(position.getAbscisse(), position.getOrdonnees());
		neighboor = removeNegativeCoordonnees(neighboor);

		for (int i = 0; i < neighboor.length; i++) {
			System.out.println(listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()]);

			if ((listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()].getState() == Case.SAFE)
					&& !(listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()].isDiscovered())
					&& (listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()]
							.getNumberOfExplosiveNeighboor() == 0)) {
				listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()].changeToNumber(0);
			}
		}

	}
}
