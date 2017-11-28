import java.util.ArrayList;

public class PrecedenceConstraint extends BinaryConstraint implements Constraint {

	/**
	 * Permet de représenter des contraintes de précédence tel que l'activité first commence avant la seconde
	 * @param first Première activité
	 * @param second Deuxième acitivité
	 */
	public PrecedenceConstraint(Activity first, Activity second) {
		super(first, second);
	}

	/**
	 * Méthode permettant de vérifier si l'activité 1 commence bien avant la 2
	 * @param date1 Date de la première activité
	 * @param date2 Date de la seconde activité
	 * @return Retourne si la contrainte est respecté ou non
	 */
	public boolean isSatisfied(int date1, int date2) {
		System.out.println(date1);
		System.out.println(date2);
		if(date1*60 + first.getDuree() <= date2*60){
			return true;
		}
		return false;	
	}
}
