import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		Activity options = new Activity ("Choisir mes options", 120);
		Activity ip = new Activity ("Inscription pédagogique", 60);
		Activity poo = new Activity ("Programmation Orientée Objet", 180);
		Activity python = new Activity ("Python", 120);

		PrecedenceConstraint contrainte = new PrecedenceConstraint (options, ip);
		MeetConstraint meetConstraint = new MeetConstraint(poo, python);
		int neufHeures = 9;
		int dixHeures = 7;
		int onzeHeures = 11;
		
		// Test avec une programmation censée satisfaire la contrainte
		if (!contrainte.isSatisfied(neufHeures, onzeHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait dû trouver que la contrainte est satisfaite.");
		} else {
		    System.out.println("Mon programme passe le premier test avec succès.");
		}
		
		// Test avec une programmation censée ne pas satisfaire la contrainte
		if ( contrainte.isSatisfied(dixHeures, neufHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le deuxième test avec succès.");
		}
		
		// Test avec une programmation censée ne pas satisfaire la contrainte (car la première
		// activité finirait après le début de la seconde)
		if (contrainte.isSatisfied(neufHeures, dixHeures)) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le troisième test avec succès.");
		}
		
		int test1 = 8;
		int test2 = 12;
		int test3 = 19;
		int test4 = 10;
		PrecedenceConstraintWithDuration contrainte1 = new PrecedenceConstraintWithDuration(options,ip,120,240);
		
		if (!contrainte1.isSatisfied(test1, test2)) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait dû trouver que la contrainte est satisfaite.");
		} else {
		    System.out.println("Mon programme passe le premier test avec succès.");
		}

		if (contrainte1.isSatisfied(test2, test3)) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le deuxième test avec succès.");
		}
		
		if (contrainte1.isSatisfied(test1, test3)) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le troisième test avec succès.");
		}
		
		
		if (contrainte1.isSatisfied(test1, test4)) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait dû trouver que la contrainte est satisfaite.");
		} else {
		    System.out.println("Mon programme passe le dernier test avec succès.");
		}
		
	    HashMap<Activity, Integer> map = new HashMap<>();
	    map.put(options, 8);
	    map.put(ip, 9);
	    map.put(poo, 10);
	    map.put(python, 13);

	    Schedule schedule = new Schedule(map);
	    
	    HashMap<Activity, Integer> map2 = new HashMap<>();
	    //Schedule schedule2 = new Schedule(map2);

	    //System.out.println(map.values());
	    
	    ArrayList<PrecedenceConstraint> listOfConstraints = new ArrayList<>();
	    listOfConstraints.add(new PrecedenceConstraint(options, poo));
	    //System.out.println(schedule.satisfies(listOfConstraints));

	    System.out.println(schedule.getSortedActivities());
	    //System.out.println(schedule.toString());
	    ArrayList<Activity> planified = new ArrayList<>();

	    //System.out.println(schedule.next(schedule.getSortedActivities(), listOfConstraints, planified));
	    System.out.println(schedule.computeSchedule(schedule.getSortedActivities(), listOfConstraints));
	    //System.out.println(contrainte.isSatisfied(schedule));
	    //System.out.println(meetConstraint.isSatisfied(schedule));
		MaxSpanConstraint maxSpanConstraint = new MaxSpanConstraint(620, schedule.getSortedActivities());
	    //System.out.println(maxSpanConstraint.isSatisfied(schedule));
		ScheduleReader scheduleReader = new ScheduleReader();
		//System.out.println(scheduleReader.readActivities("data/activities"));
		System.out.println(scheduleReader.readPrecedenceConstraint("data/precedence", scheduleReader.readActivities("data/activities")));

	}
}
