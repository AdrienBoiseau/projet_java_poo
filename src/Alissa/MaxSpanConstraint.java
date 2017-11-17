import java.util.ArrayList;

public class MaxSpanConstraint implements Constraint{

	private int timer;
	private ArrayList<Activity> activity;
	
	public MaxSpanConstraint(int timer, ArrayList<Activity> activity) {
		this.timer = timer;
		this.activity = activity;
	}
	
	public boolean isSatisfied(Schedule schedule) {
		int duree = 0;
		for(Activity act : activity){
			duree += act.duree;
		}
		return duree <= timer;
	}
}
