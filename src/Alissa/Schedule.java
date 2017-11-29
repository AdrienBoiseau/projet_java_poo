import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Schedule {

	private HashMap<Activity, Integer> edt = new HashMap<>();
	
	public Schedule(HashMap<Activity, Integer> edt) {
		this.edt = edt;
	}
	
	private Schedule(){
	}

	public HashMap<Activity, Integer> getEdt() {
		return edt;
	}

	public boolean satisfies(final Collection<PrecedenceConstraint> ListOfConstraints) {
		for (PrecedenceConstraint constraint : ListOfConstraints){
			if(!constraint.isSatisfied(this)){
					return false;
				
			}
		}
		return true;
	}

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
	
	public String toString() {
		ArrayList<Activity> activities = getSortedActivities();
		String res="";
		for (Activity act : activities){
			res += act.description + " à " + this.edt.get(act)/60 + "h" + this.edt.get(act)%60 + " durant " + (act.duree) + " minutes \n" ;
		}
		return res;
	}
	
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
