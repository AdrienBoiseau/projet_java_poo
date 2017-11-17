import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

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

	public boolean satisfies(ArrayList<PrecedenceConstraint> ListOfConstraints) {
		for (PrecedenceConstraint constraint : ListOfConstraints){
			if(this.edt.get(constraint.first) != null && this.edt.get(constraint.second) != null){
				if (!constraint.isSatisfied(this.edt.get(constraint.first), this.edt.get(constraint.second))){
					return false;
				}
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
			res += act.description + " à " + this.edt.get(act) + "h durant " + (act.duree) + " minutes \n" ;
		}
		return res;
	}
	
	private Activity next(ArrayList<Activity> activities, ArrayList<PrecedenceConstraint> constraints, Set<Activity> scheduled){
		for(Activity activity : activities) {
			if(!scheduled.contains(activity)){
				boolean isOk = true;
				for(PrecedenceConstraint prec : constraints) {
					if(prec.second.equals(activity) && !scheduled.contains(prec.first)){
						isOk = false;
						break;
					}
				}
				if(isOk){
					return activity;
				}
			}
		}
		return null;
	}
	
	public Schedule computeSchedule(ArrayList<Activity> activities, ArrayList<PrecedenceConstraint> constraints){
		int hour = 8;
		Schedule planning = new Schedule();
		@SuppressWarnings("unchecked")
		ArrayList<Activity> tmp = (ArrayList<Activity>) activities.clone();
		for(int i = 0; i < activities.size(); i++) {
			Activity act = next(tmp, constraints, planning.edt.keySet());
			int indexAct = tmp.indexOf(act);			
			planning.edt.put(act, hour);
			hour+=act.duree/60;
			tmp.remove(indexAct);		
		}
		return planning;
	}
}
