package tpPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collection;

public class Schedule {
	
	protected HashMap<Activity,Integer> hmap;
	
	public Schedule() {
		this.hmap = new HashMap<>();
	}
	
	public void add(Activity activity, int a){
		this.hmap.put(activity, a);
	}
	
	public boolean satisfies(Collection<? extends Constraint> contrainte) {
		for (Constraint cont : contrainte) {
			if (!cont.isSatisfied(this)) {
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
	
	private Activity next(Collection<Activity> activities, Collection<? extends BinaryConstraint> constraints, Collection<Activity> scheduled){
		for (Activity act : activities){
			if (!scheduled.contains(act)){
				Activity next = act;
				for(BinaryConstraint cons : constraints){
					if (cons.second == act){
						if (!scheduled.contains(cons.first)){
							next = null;
							break;
						}
					}
				}
				if (next != null) {
					return act;
				}
			}
		}
		return null;
	}
	
	public Schedule computeSchedule(Collection<Activity> activities, Collection<? extends BinaryConstraint> constraints){
		ArrayList<Activity> scheduled = new ArrayList<>();
		Schedule sch = new Schedule();
		Activity act = next(activities,constraints,scheduled);
		int hour = 8;
		while(act!=null){
			scheduled.add(act);
			sch.add(act, hour);
			hour += Math.round(act.duree / 60)+1;
			act = next(activities,constraints,scheduled);
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
