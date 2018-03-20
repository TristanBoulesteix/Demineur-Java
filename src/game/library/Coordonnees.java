package game.library;

public class Coordonnees {
	protected int ordonnee;
	protected int abscisse;

	public Coordonnees(int i, int j) {
		setAbscisse(i);
		setOrdonnees(j);
	}

	/**
	 * @return the abscisse
	 */
	public int getAbscisse() {
		return ordonnee;
	}

	/**
	 * @param abscisse
	 *            the abscisse to set
	 */
	protected void setAbscisse(int abscisse) {
		this.ordonnee = abscisse;
	}

	/**
	 * @return the ordonneés
	 */
	public int getOrdonnees() {
		return abscisse;
	}

	/**
	 * @param ordonnees
	 *            the ordonneés to set
	 */
	protected void setOrdonnees(int ordonnees) {
		this.abscisse = ordonnees;
	}
}
