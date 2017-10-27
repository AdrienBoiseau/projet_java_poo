package classes;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PrecedenceConstraintWithDuration extends PrecedenceConstraint {

	private int pause_min;
	private int pause_max;
	
	public PrecedenceConstraintWithDuration(Activity prem, Activity deux, int min, int max) {
		super(prem, deux);
		pause_min=min;
		pause_max=max;
	}
	

	public int getPause_min() {
		return pause_min;
	}


	public int getPause_max() {
		return pause_max;
	}


	public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2) {
		int time= this.first.getTime();
		time=time+(date1.get(Calendar.HOUR_OF_DAY))*60+date1.get(Calendar.MINUTE);
		int time2=(date2.get(Calendar.HOUR_OF_DAY))*60+date2.get(Calendar.MINUTE);
		
		int pause = time2-time;
		
		if(time<=time2){
			if(pause>=this.getPause_min() && pause<=this.getPause_max()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}

}
