package classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TimeSlot {
	private Calendar h_debut;
	private Calendar h_fin;
	private String lib;
	
	public TimeSlot(Calendar deb,Calendar fin,String libel){
		h_debut=deb;
		h_fin=fin;
		lib=libel;
	}
	
	public String getRepresentation(){
		return "Créneaux horaires : \n"
				+ "> Début : \n - "
						+this.h_debut.get(Calendar.DAY_OF_MONTH)+"/"
						+(this.h_debut.get(Calendar.MONTH)+1)+"/"
						+this.h_debut.get(Calendar.YEAR)+"\n - "
						+this.h_debut.get(Calendar.HOUR_OF_DAY)+"h "
						+this.h_debut.get(Calendar.MINUTE)+"min "
						+this.h_debut.get(Calendar.SECOND)+"sec "
						+" \n"
				+ "> Fin   : \n - "
						+this.h_fin.get(Calendar.DAY_OF_MONTH)+"/"
						+(this.h_fin.get(Calendar.MONTH)+1)+"/"
						+this.h_fin.get(Calendar.YEAR)+"\n - "
						+this.h_fin.get(Calendar.HOUR_OF_DAY)+"h "
						+this.h_fin.get(Calendar.MINUTE)+"min "
						+this.h_fin.get(Calendar.SECOND)+"sec "
						+" \n"
				+ "> Libellé : "+this.lib+"\n";

	}
}
