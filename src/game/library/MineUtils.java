package game.library;

import java.util.ArrayList;

import game.demineur.items.Case;

public class MineUtils {
	private static Case[][] casesList;

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

	public static int giveNeighbourNumber(Coordonnees[] arrayCoor, ArrayList<Coordonnees> arrayCases, int xMax,
			int yMax) {
		int numberOfNeighboor = 0;

		for (int i = 0; i < arrayCoor.length; i++) {
			if (!isOutOfBound(arrayCoor[i], xMax, yMax)) {
				if (isABomb(arrayCoor[i].getOrdonnees(), arrayCoor[i].getAbscisse(), arrayCases)) {
					numberOfNeighboor++;
				}
			}
		}

		return numberOfNeighboor;
	}

	public static void revealEmptyCaseAdjacent(Coordonnees position, int xMax, int yMax) {
		Case[][] listeDesCases = getCaseList();
		Coordonnees[] neighboor = generateCoordonneesVoisines(position.getAbscisse(), position.getOrdonnees());

		for (int i = 0; i < neighboor.length; i++) {
			if (!isOutOfBound(neighboor[i], xMax, yMax)) {
				if ((listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()].getState() == Case.SAFE)
						&& !(listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()].isDiscovered())) {

					// Action of If
					listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()]
							.changeToNumber(listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()]
									.getNumberOfExplosiveNeighboor());

					if (listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()]
							.getNumberOfExplosiveNeighboor() == 0) {
						revealEmptyCaseAdjacent(
								listeDesCases[neighboor[i].getAbscisse()][neighboor[i].getOrdonnees()].getPosition(),
								xMax, yMax);
					}
				}
			}
		}
	}

	public static boolean isOutOfBound(Coordonnees coor, int xMax, int yMax) {
		if (coor.isNegative() || coor.getAbscisse() >= xMax || coor.getOrdonnees() >= yMax) {
			return true;
		}

		return false;
	}

	public static void initialize(int size) {
		int xMax, yMax;

		switch (size) {
		case 9:
			xMax = 9;
			yMax = 9;
			break;
		case 16:
			xMax = 16;
			yMax = 16;
			break;
		case 30:
			xMax = 30;
			yMax = 16;
			break;
		default:
			xMax = 9;
			yMax = 9;
			break;
		}

		casesList = new Case[xMax][yMax];
	}
}
