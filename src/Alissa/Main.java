import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Activity options = new Activity ("Choisir mes options", 120);
		Activity ip = new Activity ("Inscription pédagogique", 90);
		Activity poo = new Activity ("Programmation Orientée Objet", 180);
		Activity python = new Activity ("Python", 120);	
		
	    HashMap<Activity, Integer> map = new HashMap<>();
	    map.put(options, 8);
	    map.put(ip, 9);
	    map.put(poo, 10);
	    map.put(python, 13);

	    Schedule schedule = new Schedule(map);
	    	    
	    ArrayList<PrecedenceConstraint> listOfConstraints = new ArrayList<>();
	    
	    Map<String, Activity> activities = new HashMap<>();
	    
		System.out.println("------------------------------------------------------------------------------");

	    System.out.println("Création d'un emploi du temps selon des contraintes données");
	    
		System.out.println("------------------------------------------------------------------------------");

	    System.out.println(schedule.computeSchedule(schedule.getSortedActivities(), listOfConstraints));
	    
		ScheduleReader scheduleReader = new ScheduleReader();
		
		activities = scheduleReader.readActivities("data/activities");
		listOfConstraints = scheduleReader.readPrecedenceConstraint("data/precedence", scheduleReader.readActivities("data/activities"));
		
		System.out.println("------------------------------------------------------------------------------");
		
		System.out.println("Contraintes de précédence");
		
		System.out.println("------------------------------------------------------------------------------");
		
		for(int i = 0;i< listOfConstraints.size();i++){
			System.out.println(listOfConstraints.get(i).first.description + " avant de " + listOfConstraints.get(i).second.description);
		}
		
		System.out.println("------------------------------------------------------------------------------");
		
		System.out.println("Activités");
		
		System.out.println("------------------------------------------------------------------------------");
		
		for(String act : activities.keySet()){
			System.out.println(act + " = " + activities.get(act).description + " pendant " + activities.get(act).duree + " minutes ");
		}
		
		System.out.println("------------------------------------------------------------------------------");
		
		System.out.println("Création d'un emploi du temps avec les activités précédentes");

		System.out.println("------------------------------------------------------------------------------");
		
		HashMap<Activity, Integer> map2 = new HashMap<>();
		for(String act : activities.keySet()){
			map2.put(activities.get(act), activities.get(act).duree);
		}
	    Schedule scheduleBis = new Schedule(map2);
	    System.out.println(scheduleBis.computeSchedule(scheduleBis.getSortedActivities(), listOfConstraints));

	}
}
