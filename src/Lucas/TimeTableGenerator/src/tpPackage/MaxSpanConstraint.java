package tpPackage;

import java.util.ArrayList;
import java.util.Collections;

public class MaxSpanConstraint implements Constraint{
	
	protected ArrayList<Activity> activities;
	int maxduration;
	
	public MaxSpanConstraint(ArrayList<Activity> activities, int maxduration) {
		this.activities = activities;
		this.maxduration = maxduration;
	}
	
	@Override
	public boolean isSatisfied(Schedule sch) {
		ArrayList<Integer> hour = new ArrayList<>();
		for(Activity act : activities) {
			hour.add(sch.hmap.get(act));
		}
		
		int min = Collections.max(hour);
		int max = Collections.min(hour);
		
		return ((max-min) <= this.maxduration);
	}

}
