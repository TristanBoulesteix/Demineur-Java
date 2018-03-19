package game.library;

public class Coordonnees {
	protected int abscisse;
	protected int ordonnees;

	public Coordonnees(int i, int j) {
		setAbscisse(i);
		setOrdonnees(j);
	}

	/**
	 * @return the abscisse
	 */
	public int getAbscisse() {
		return abscisse;
	}

	/**
	 * @param abscisse
	 *            the abscisse to set
	 */
	protected void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}

	/**
	 * @return the ordonneés
	 */
	public int getOrdonnees() {
		return ordonnees;
	}

	/**
	 * @param ordonnees
	 *            the ordonneés to set
	 */
	protected void setOrdonnees(int ordonnees) {
		this.ordonnees = ordonnees;
	}
}
