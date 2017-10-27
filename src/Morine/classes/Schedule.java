package classes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;



public class Schedule {
	HashMap<Activity, GregorianCalendar> sched = new HashMap<Activity, GregorianCalendar>();
	

	public Schedule(HashMap<Activity, GregorianCalendar> s) {
		this.sched = s;
	}

	public boolean satisfies(ArrayList<PrecedenceConstraint> constraints) {
		for (PrecedenceConstraint contraint : constraints) {
			if (this.sched.get(contraint.getFirst()) != null & this.sched.get(contraint.getSecond()) != null)
                if (!contraint.isSatisfied(this.sched.get(contraint.getFirst()), this.sched.get(contraint.getSecond()))) {
                    return false;
                }
            
        }
        return true;
	}
	
	
	
	private ArrayList<Activity> getSortedActivities() {
		ArrayList<Activity> time = new ArrayList<>();
		Activity tab[] = new Activity[100];
		int j=0;
		for(Activity act : this.sched.keySet()){
			tab[j]=act;
			j++;
		}
		boolean verifi = false;
		Activity temp;
		while(verifi==false){
			verifi=true;
			for(int i=0 ; i<=j-2; i++){
				if(this.sched.get(tab[i]).get(Calendar.HOUR_OF_DAY)>this.sched.get(tab[i+1]).get(Calendar.HOUR_OF_DAY)){
					temp=tab[i];
					tab[i]=tab[i+1];
					tab[i+1]=temp;				
					verifi=false;
				}
			}
		}
		for(int i=0 ; i<=j-1; i++){
			time.add(tab[i]);
		}
		return time;
	}
	
	public String toString(){
		ArrayList<Activity> time = this.getSortedActivities();
		
		String text = "\n";
		for(Activity act : time){
			if(act!=null){
				text+=" - ";
				text+=act.getLibel()+" à ";
				text+=this.sched.get(act).get(Calendar.HOUR_OF_DAY)+"h "+this.sched.get(act).get(Calendar.MINUTE)
						+ "min (durée: "
						+act.getTime()
						+"min)\n";
			}
		}
		
		
		return text;
	}

	
	private Activity next(ArrayList<Activity> activities, 
						  ArrayList<PrecedenceConstraint> constraintes,
						  ArrayList<Activity> planning){
		for(Activity act : activities) {
			boolean first_constraint_only=false;
			
			if(!planning.contains(act)){
				first_constraint_only=true;
				
				for(PrecedenceConstraint cons : constraintes){
					
					if(cons.getSecond().equals(act)){
						if(!planning.contains(cons.getFirst())){
							first_constraint_only=false;
							break;
						}
					}
				}
				if(first_constraint_only){
					return act;
				}
			}
		}
		return null;
	}
	
	public Schedule computeSchedule(ArrayList<Activity> activities, 
									ArrayList<PrecedenceConstraint> constraintes){

		HashMap<Activity, GregorianCalendar> horaires = new HashMap<>();
		GregorianCalendar date = new GregorianCalendar(2017,9,8,8,0,0);
		ArrayList<Activity> plannifie = new ArrayList();
		ArrayList<Activity> a_plannifie = (ArrayList<Activity>) activities.clone();
		
		for(int i=0;i<activities.size();i++){
			Activity activite_place = next(a_plannifie,constraintes,plannifie);
			horaires.put(activite_place,(GregorianCalendar) date.clone());
			if(activite_place!=null){
				date.add(Calendar.MINUTE, activite_place.getTime());
			}
			plannifie.add(activite_place);
			a_plannifie.remove(activite_place);
		}
		Schedule emploi_du_temp= new Schedule(horaires);
		
		return emploi_du_temp;
	}


}
