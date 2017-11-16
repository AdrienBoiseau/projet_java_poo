package tpPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class Schedule {
	
	protected HashMap<Activity,Integer> hmap = new HashMap<>();
	
	public Schedule(HashMap<Activity,Integer> hmap) {
		this.hmap = hmap;
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
	
	@Override
	public String toString(){
		ArrayList<Activity> liste = get_sorted_activity();
		String res = "";
		for (int i = 0; i<liste.size(); i++) {
			System.out.println(liste.get(i));
			res =res + " " + liste.get(i).description + " : " + this.hmap.get(liste.get(i)) + " h ; ";
		}
		res = "[ " + res + " ]";
		return res;
	}
}
