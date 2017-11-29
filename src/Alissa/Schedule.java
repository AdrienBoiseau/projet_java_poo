import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Schedule {
	/**
	 * Permet de créer un dictionnaire permettant de stocker les activités sous forme d'un couple, date/description
	 */
	private HashMap<Activity, Integer> edt = new HashMap<>();
	/**
	 * Constructeur d'un emplois du temps
	 * @param edt L'emplois du temps
	 */
	public Schedule(HashMap<Activity, Integer> edt) {
		this.edt = edt;
	}
	
	private Schedule(){
	}
	/**
	 * Permet de retourner l'emplois du temps
	 * @return edt (L'emplois du temps
	 */
	public HashMap<Activity, Integer> getEdt() {
		return edt;
	}
	/**
	 * Méthode permet de vérifier si l'emplois du temps vérifie la liste de contraintes
	 * @param ListOfConstraints Listes des contraintes
	 * @return Retourne si vrai ou faux si l'emplois du temps satisfait toute les contraintes
	 */
	public boolean satisfies(final Collection<PrecedenceConstraint> ListOfConstraints) {
		for (PrecedenceConstraint constraint : ListOfConstraints){
			if(!constraint.isSatisfied(this)){
					return false;
				
			}
		}
		return true;
	}
	/**
	 * Méthode qui retourne une liste des activités triées par ordre croissant de l'heure
	 * @return arl (liste d'activités triées)
	 */
	public ArrayList<Activity> getSortedActivities() {
		ArrayList<Activity> arl = new ArrayList <> ();
		
		for(Activity test : this.edt.keySet()) {
			int date = edt.get(test);
			if (arl.size() == 0) arl.add(test);
			else{
				boolean inserted = false;
				for (int i = 0;i< arl.size();i++){
					if(date <= this.edt.get(arl.get(i))) { 
						arl.add(i,test);
						inserted = true;
						break;
					}
				}
				if(!inserted) arl.add(test);
			}		
		}
		return arl;
	}
	/**
	 * Retourne une chaîne de caractère bien détaillé pour présenter une activité
	 * @return res (chaîne de caractère)
	 */
	public String toString() {
		ArrayList<Activity> activities = getSortedActivities();
		String res="";
		for (Activity act : activities){
			res += act.description + " à " + this.edt.get(act)/60 + "h" + this.edt.get(act)%60 + " durant " + (act.duree) + " minutes \n" ;
		}
		return res;
	}
	/**
	 * Méthode permettant de retourner une activité qui peut être planifiée en fonction de contrainte et d'une liste d'activités déjà planifiées
	 * @param activities Activité déjà planifié
	 * @param constraints Contraintes entre les activités
	 * @param set Activité passé en paramètre
	 * @return activity (retourne une activité qui peut être planifiée)
	 */
	private Activity next(final Collection<Activity> activities, final Collection<PrecedenceConstraint> constraints, final Collection<Activity> set){
		for(Activity activity : activities) {
			if(!set.contains(activity)){
				boolean isOk = true;
				for(PrecedenceConstraint prec : constraints) {
					if(prec.getSecond().equals(activity) && !set.contains(prec.getFirst())){
						isOk = false;
					}
				}
				if(isOk){
					return activity;
				}
			}
		}
		return null;
	}
	/**
	 * Méthode permettant de retourner un emplois du temps comprenant toute les activités et respectant les contraintes
	 * @param activities Listes d'acitivtés
	 * @param constraints Contraintes
	 * @return planning (emplois du temps)
	 */
	public Schedule computeSchedule(final Collection<Activity> activities, final Collection<PrecedenceConstraint> constraints){
		int hour = 8*60;
		Schedule planning = new Schedule();
		ArrayList<Activity> tmp = new ArrayList<>(activities); 
		for(int i = 0; i < activities.size(); i++) {
			Activity act = next(tmp, constraints, planning.edt.keySet());
			int indexAct = tmp.indexOf(act);			
			planning.edt.put(act, hour);
			hour+=act.duree;
			tmp.remove(indexAct);		
		}
		return planning;
	}
}
