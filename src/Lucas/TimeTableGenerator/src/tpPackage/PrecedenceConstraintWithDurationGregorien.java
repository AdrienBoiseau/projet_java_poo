package tpPackage;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class PrecedenceConstraintWithDurationGregorien extends PrecedenceConstraintGregorien{
	
	protected int avantMin;
	protected int avantMax;
	
	public PrecedenceConstraintWithDurationGregorien(Activity first, Activity second, int avantMin, int avantMax){
		super(first,second);
		this.avantMin = avantMin;
		this.avantMax = avantMax;
	}
	
	@Override
	public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2){
		GregorianCalendar cale = new GregorianCalendar();
		GregorianCalendar cale2 = new GregorianCalendar();
		GregorianCalendar cale3 = new GregorianCalendar();
		cale = (GregorianCalendar)date2.clone();
		cale2 = (GregorianCalendar)date2.clone();
		cale3 = (GregorianCalendar)date1.clone();
		cale.add(Calendar.MINUTE, -(this.avantMin));
		cale2.add(Calendar.MINUTE, -(this.avantMax));
		cale3.add(Calendar.MINUTE, this.first.duree);
		if ((cale3.compareTo(cale)<=0)&&(cale3.compareTo(cale2)>=0)){
			return true;
		}
		else{
			return false;
		}
		
		
	}
	

}
