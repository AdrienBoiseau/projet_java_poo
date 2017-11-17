package tpPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.lang.Math;

public class Schedule {
	
	protected HashMap<Activity,Integer> hmap;
	
	public Schedule() {
		this.hmap = new HashMap<>();
	}
	
	public void add(Activity activity, int a){
		this.hmap.put(activity, a);
	}
	
	public boolean satisfies(ArrayList<PrecedenceConstraint> contrainte) {
		for (PrecedenceConstraint cont : contrainte) {
			if (!(cont.isSatisfied(this.hmap.get(cont.first),this.hmap.get(cont.second)))) {
				return false;
			}
		}
		return true;
	}
	
	private ArrayList<Activity> get_sorted_activity(){
		ArrayList<Activity> res = new ArrayList<>();
		for (Activity act : this.hmap.keySet()) {
			if (res.isEmpty()) {
				res.add(act);
			}
			else {
				boolean inserted = false;
				for (int i = 0; i < res.size();i++) {
					if (hmap.get(res.get(i))>hmap.get(act)) {
						res.add(i,act);
						inserted = true;
						break;
					}
				}
				if(inserted==false) {
					res.add(act);
				}
				
			}
		
		}
		return res;
	}
	
	private Activity next(ArrayList<Activity> activities, ArrayList<PrecedenceConstraint> constraints, ArrayList<Activity> scheduled){
		for (Activity act : activities){
			if (!scheduled.contains(act)){
				for(PrecedenceConstraint cons : constraints){
					if (cons.second == act){
						if (scheduled.contains(cons.first)){
							return act;
						}
						break;
					}
				}
			}
		}
		return null;
	}
	
	public Schedule compteSchedule(ArrayList<Activity> activities, ArrayList<PrecedenceConstraint> constraints){
		ArrayList<Activity> scheduled = new ArrayList<>();
		Schedule sch = new Schedule();
		int hour = 8;
		while(next(activities,constraints,scheduled)!=null){
			Activity act = next(activities,constraints,scheduled);
			sch.add(act, hour);
			hour += Math.round(act.duree / 60)+1;
		}
		return sch;
	}
	
	@Override
	public String toString(){
		ArrayList<Activity> liste = get_sorted_activity();
		String res = "";
		for (int i = 0; i<liste.size(); i++) {
			res =res + " " + liste.get(i).description + " : " + this.hmap.get(liste.get(i)) + " h ; ";
		}
		res = "[ " + res + " ]";
		return res;
	}
}
