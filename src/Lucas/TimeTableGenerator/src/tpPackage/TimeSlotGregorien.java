package tpPackage;

import java.util.GregorianCalendar;
import java.util.Calendar;



public class TimeSlotGregorien {
	
	private GregorianCalendar Deb = new GregorianCalendar();
	private GregorianCalendar Fin = new GregorianCalendar();
	private String lib;
	
	public TimeSlotGregorien(String lib){
		this.lib = lib;
	}
	
	public void setDebut(int heure, int minute){
		Deb.set(Calendar.HOUR, heure);
		Deb.set(Calendar.MINUTE, minute);
	}
	
	public void setFin(int heure, int minute){
		Fin.set(Calendar.HOUR, heure);
		Fin.set(Calendar.MINUTE, minute);
	}

	public void repr(){
		System.out.println("Cours de " + this.lib +
				" de " + Deb.get(Calendar.HOUR) + "h" + Deb.get(Calendar.MINUTE) + "m" +
				" à " + Fin.get(Calendar.HOUR) + "h" + Fin.get(Calendar.MINUTE) + "m");
	}
	
	public boolean overslaps(TimeSlotGregorien tsg){
		if (((tsg.Deb.compareTo(Deb) < 0) && (tsg.Deb.compareTo(Deb) < 0)) || ((tsg.Deb.compareTo(Fin)>0) && (tsg.Fin.compareTo(Fin)>0))){
			return true;
		}
		return false;
	}
}
