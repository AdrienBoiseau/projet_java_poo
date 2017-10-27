package classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PrecedenceConstraint {
	protected Activity first;
	protected Activity second;

	public PrecedenceConstraint(Activity prem, Activity deux){
		first=prem;
		second=deux;
	}

	public Activity getFirst() {
		return first;
	}

	public Activity getSecond() {
		return second;
	}

	
	public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2) {
		
		int time= this.first.getTime();
		time=time+(date1.get(Calendar.HOUR_OF_DAY))*60+date2.get(Calendar.MINUTE);
		int time2=(date2.get(Calendar.HOUR_OF_DAY))*60+date2.get(Calendar.MINUTE);
		
		if(time<=time2){
			return true;
		}else{
			return false;
		}
	}
}
