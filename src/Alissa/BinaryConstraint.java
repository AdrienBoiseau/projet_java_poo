public abstract class BinaryConstraint implements Constraint{

	protected Activity first;
	protected Activity second;

	/**
	 * Permet de créer une contrainte binaire portant sur deux activités
	 * @param first Première contrainte
	 * @param second Seconde contrainte
	 */
	public BinaryConstraint(Activity first, Activity second){
		this.first = first;
		this.second = second;
	}

	/**
	 * Test si les dates satisfait les conditions de l'emploi du temps
	 * @param date1 date de l'activité 1
	 * @param date2 date de l'acitivité 2
	 */
	protected abstract boolean isSatisfied(int date1, int date2);

	/**
	 * Récupère les dates des activités dans l'emploi du temps considérer puis à appele la méthode abstraite "isSatisfied" avec ces dates
	 * @param schedule l'emploi du temps
	 * @return Retourne "isSatisfied" avec la date et la première et deuxième activité
	 */
	public boolean isSatisfied(Schedule schedule){
		int date1 = schedule.getEdt().get(this.first);
		int date2 = schedule.getEdt().get(this.second);
		return this.isSatisfied(date1, date2);	
	}
}
