public class MeetConstraint extends BinaryConstraint implements Constraint{

	/**
	 * Permet de créer une contrainte de temps entre la première et la deuxième acitivité
	 * @param first Première activité
	 * @param second Deuxième acitivité
	 */
	public MeetConstraint(Activity first, Activity second) {
		super(first, second);
	}

	/**
	 * Méthode héritant de "BinaryConstraint" et permettant de s'arrurer que la deuxième activité commence exactement à la fin de la première
	 * @param date1 Date de la première activité
	 * @param date2 Date de la seconde activité
	 * @return Retourne si la contrainte est respecté
	 */
	protected boolean isSatisfied(int date1, int date2) {
		return (date2*60 == date1*60 + first.getDuree());
	}
}
