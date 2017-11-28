public class PrecedenceConstraintWithDuration extends PrecedenceConstraint implements Constraint{

	private int min;
	private int max;

	/**
	 * Permet de créer une constrainte de précédence avec durée dans le but de vérifier les bornes sont respecté entre l'acitivé first et seconde
	 * @param first Première activité
	 * @param second Seconde activité
	 * @param min Borne minimum
	 * @param max Borne maximum
	 */
	public PrecedenceConstraintWithDuration(Activity first, Activity second, int min, int max) {
		super(first, second);
		this.min = min;
		this.max = max;
	}

	/**
	 * Méthode fille de "PrecedenceConstraint" permettant de vérifier si les bornes sont respecté
	 * @param date1 Date de la première activité
	 * @param date2 Date de la seconde activité
	 * @return Retourne si la contrainte est respecté ou non
	 */
	public boolean isSatisfied(int date1, int date2) {
		if(date1*60 + first.getDuree() + max >= date2*60  && date1*60 + first.getDuree() + min <= date2*60 ){
			return true;
		}
		return false;	
	}
}
