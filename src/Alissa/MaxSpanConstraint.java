import java.util.ArrayList;

public class MaxSpanConstraint implements Constraint{

	private int timer;
	private ArrayList<Activity> activity;

	/**
	 * Permet de créer une contrainte de temps entre chaque acitivtés
	 * @param timer Temps entre chaque acitivités
	 * @param activity Activité
	 */
	public MaxSpanConstraint(int timer, ArrayList<Activity> activity) {
		this.timer = timer;
		this.activity = activity;
	}

	/**
	 * Méthode implémentant l'interface "Constraint" permet de représenter des contraintes permettant de stipuler un temps maximum entre chaque activités
	 * @param schedule Emplois du temps
	 * @return duree (En vérifiant si elle est inférieur au temps entre chaque activités)
	 */
	public boolean isSatisfied(Schedule schedule) {
		int duree = 0;
		for(Activity act : activity){
			duree += act.duree;
		}
		return duree <= timer;
	}
}
