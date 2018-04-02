package game.library;

public class Coordonnees {
	private int ordonnee;
	private int abscisse;

	private boolean negative = false;

	/**
	 * @return if abscisse or ordonnees are negative
	 */
	public boolean isNegative() {
		return negative;
	}

	public Coordonnees(int i, int j) {
		setAbscisse(i);
		setOrdonnees(j);

		if (getAbscisse() < 0 || getOrdonnees() < 0) {
			negative = true;
		}
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

		if (isNegative()) {
			sb.append(", negative");
		}

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
