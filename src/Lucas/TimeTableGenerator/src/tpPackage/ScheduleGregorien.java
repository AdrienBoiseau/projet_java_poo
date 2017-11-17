package tpPackage;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

public class ScheduleGregorien {
	
	protected HashMap<Activity,GregorianCalendar> hmap = new HashMap<>();
	
	public ScheduleGregorien(HashMap<Activity,GregorianCalendar> hmap) {
		this.hmap = hmap;
	}
	
	public boolean satisfies(ArrayList<PrecedenceConstraintGregorien> contrainte) {
		for (PrecedenceConstraintGregorien cont : contrainte) {
			if (!(cont.isSatisfied(this.hmap.get(cont.first),this.hmap.get(cont.second)))) {
				return false;
			}
		}
		return true;
	}

}
