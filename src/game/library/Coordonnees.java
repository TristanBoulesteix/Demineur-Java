package game.library;

public class Coordonnees {
	private int ordonnee;
	private int abscisse;

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
	 * @return the ordonnées
	 */
	public int getOrdonnees() {
		return ordonnee;
	}

	/**
	 * @param ordonnees
	 *            the ordonnées to set
	 */
	protected void setOrdonnees(int ordonnees) {
		this.ordonnee = ordonnees;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("x = ").append(abscisse);
		sb.append(", y = ").append(ordonnee);
		return sb.toString();
	}

	@Override
	public boolean equals(Object anObject) {
		if (super.equals(anObject)) {
			return true;
		}
		if (anObject instanceof Coordonnees) {
			Coordonnees aCoordonnee = (Coordonnees) anObject;
			return this.getAbscisse() == aCoordonnee.getAbscisse() && this.getOrdonnees() == aCoordonnee.getOrdonnees();
		}
		return false;
	}
}
