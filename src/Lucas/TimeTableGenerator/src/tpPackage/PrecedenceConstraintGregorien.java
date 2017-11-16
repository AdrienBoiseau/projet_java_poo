package tpPackage;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class PrecedenceConstraintGregorien {
	
	protected Activity first;
	protected Activity second;
	
	public PrecedenceConstraintGregorien(Activity first, Activity second) {
		this.first = first;
		this.second = second;
	}
	
	public boolean isSatisfied(GregorianCalendar date1,GregorianCalendar date2) {
		GregorianCalendar prem = new GregorianCalendar();
		prem = (GregorianCalendar)date1.clone();
		prem.add(Calendar.MINUTE, this.first.duree);
		if (prem.compareTo(date2)<=0) {
			return true;
		}
		else {
			return false;
		}

	}
	
	
}
