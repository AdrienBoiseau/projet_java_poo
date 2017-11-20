package tpPackage;

import java.util.GregorianCalendar;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Test de timeSlot" + "\n");
		testTimeSlot();
		System.out.println("\n" + "Test de PrecedenceConstraint" + "\n");
		testPrecedenceConstraint();
		System.out.println("\n" + "Test de PrecedenceConstraintWithDuration" + "\n");
		testPrecedenceConstraintWithDuration();
		System.out.println("\n" + "Test de Schedule" + "\n");
		testSchedule();
		System.out.println("\n" + "Test de compteSchedule" + "\n");
		testSchedulepart2();
		System.out.println("\n" + "Test de classe abstraite et interface" + "\n");
		testBinaryConstraint();
		System.out.println("\n" + "Test de ScheduleReader" + "\n");
		testScheduleReader();
    }
	
	public static void testTimeSlot() {
		
		// test sans calendrier

		TimeSlot test1 = new TimeSlot(9,12,"poo");
		TimeSlot test2 = new TimeSlot(13,14,"math");
		TimeSlot test3 = new TimeSlot(10,11,"te");
		Boolean a = test1.overslaps(test2);
		Boolean b = test1.overslaps(test3);
		Boolean c = test2.overslaps(test3);
		
		if (a) {
			System.out.print("Premier test sans calendrier reussi" + "\n");
		}
		else {
			System.out.print("Premier test sans calendrier raté" + "\n");
		}
		if (!b) {
			System.out.print("Second test sans calendrier reussi" + "\n");
		}
		else {
			System.out.print("Premier test sans calendrier raté" + "\n");
		}
		if (c) {
			System.out.print("Dernier test sans calendrier reussi" + "\n");
		}
		else {
			System.out.print("Premier test sans calendrier raté" + "\n");
		}
		
		System.out.println("\n");
		// test avec calendrier
				
		TimeSlotGregorien test4 = new TimeSlotGregorien("POO");
		test4.setDebut(9, 00);
		test4.setFin(12, 00);
		TimeSlotGregorien test5 = new TimeSlotGregorien("math");
		test5.setDebut(13,00);
		test5.setFin(14,00);
		TimeSlotGregorien test6 = new TimeSlotGregorien("te");
		test6.setDebut(10,00);
		test6.setFin(11,00);
		Boolean d = test4.overslaps(test5);
		Boolean e = test4.overslaps(test6);
		Boolean f = test5.overslaps(test6);
		
		if (d) {
			System.out.print("Premier test avec calendrier reussi" + "\n");
		}
		else {
			System.out.print("Premier test avec calendrier raté" + "\n");
		}
		if (!e) {
			System.out.print("Second test avec calendrier reussi" + "\n");
		}
		else {
			System.out.print("Premier test avec calendrier raté" + "\n");
		}
		if (f) {
			System.out.print("Dernier test avec calendrier reussi" + "\n");
		}
		else {
			System.out.print("Premier test avec calendrier raté" + "\n");
		}
	}
	
	public static void testPrecedenceConstraint(){
		// test sans calendrier
		
		// Objets
		Activity options = new Activity ("Choisir mes options", 70);
		Activity ip = new Activity ("Inscription pédagogique", 30);
				
		PrecedenceConstraint contrainte = new PrecedenceConstraint (options, ip);
		int neufHeures = 9;
		int dixHeures = 10;
		int onzeHeures = 11;
		
		System.out.println("avec des integer" + "\n");
		// Test avec une programmation censée satisfaire la contrainte
		if ( ! contrainte.isSatisfied(neufHeures, onzeHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait du trouver que la contrainte est satisfaite.");
		} else {
		    System.out.println("Mon programme passe le premier test avec succés.");
		}

		// Test avec une programmation censée ne pas satisfaire la contrainte
		if ( contrainte.isSatisfied(dixHeures, neufHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait du trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le deuxième test avec succés.");
		}

		// Test avec une programmation censée ne pas satisfaire la contrainte (car la première
		// activité finirait après le début de la seconde)
		if ( contrainte.isSatisfied(neufHeures, dixHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait du trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le troisième test avec succés.");
		}
				
		System.out.println("\n");
		
		//test avec calendrier
		System.out.println("avec des calendrier" + "\n");
		GregorianCalendar neuf = new GregorianCalendar();
		GregorianCalendar dix = new GregorianCalendar();
		GregorianCalendar onze = new GregorianCalendar();
				
		neuf.set(Calendar.HOUR, 9);
		neuf.set(Calendar.MINUTE, 0);
		dix.set(Calendar.HOUR, 10);
		dix.set(Calendar.MINUTE, 0);
		onze.set(Calendar.HOUR, 11);
		onze.set(Calendar.MINUTE, 0);
						
		PrecedenceConstraintGregorien greg1 = new PrecedenceConstraintGregorien(options, ip);
		
		if ( ! greg1.isSatisfied(neuf, onze) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait du trouver que la contrainte est satisfaite.");
		} else {
		    System.out.println("Mon programme passe le premier test avec succés.");
		}

		// Test avec une programmation censée ne pas satisfaire la contrainte
		if ( greg1.isSatisfied(dix, neuf) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait du trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le deuxième test avec succés.");
		}

		// Test avec une programmation censée ne pas satisfaire la contrainte (car la première
		// activité finirait après le début de la seconde)
		if ( greg1.isSatisfied(neuf, dix) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait du trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le troisième test avec succés.");
		}
	}
	
	public static void testPrecedenceConstraintWithDuration() {
		
		// Sans Calendrier
		System.out.println("avec des integer" + "\n");
		Activity math = new Activity ("math", 50);
		Activity poo = new Activity ("poo", 120);
		
		PrecedenceConstraintWithDuration test7 = new PrecedenceConstraintWithDuration(math, poo, 120, 240);
		PrecedenceConstraintWithDuration test8 = new PrecedenceConstraintWithDuration(poo, math, 120, 240);
		
		boolean prem = test7.isSatisfied(9, 14);
		boolean sec = test8.isSatisfied(9, 14);
		
		if (!prem) {
			System.out.println("Mon programme passe le premier test avec succés.");
		}else {
			System.out.println("Mon programme ne marche pas");
			System.out.println("Il aurait du trouver que la contrainte est satisfaite");
		}
		if (sec) {
			System.out.println("Mon programme passe le second test avec succés.");
		}else {
			System.out.println("Mon programme ne marche pas");
			System.out.println("Il aurait du trouver que la contrainte n'est pas satisfaite");
		}
		
		// Avec Calendrier
		System.out.println("\n" + "avec des calendrier" + "\n");
		GregorianCalendar cal1 = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();
		
		cal1.set(Calendar.HOUR, 9);
		cal1.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.HOUR, 14);
		cal2.set(Calendar.MINUTE, 0);
		
		PrecedenceConstraintWithDurationGregorien greg3 = new PrecedenceConstraintWithDurationGregorien(math,poo,120,240);
		PrecedenceConstraintWithDurationGregorien greg4 = new PrecedenceConstraintWithDurationGregorien(poo,math,120,240);
		boolean testgre3 = greg3.isSatisfied(cal1, cal2);
		boolean testgre4 = greg4.isSatisfied(cal1, cal2);
		
		if (!testgre3) {
			System.out.println("Mon programme passe le premier test avec succés.");
		}else {
			System.out.println("Mon programme ne marche pas");
			System.out.println("Il aurait du trouver que la contrainte est satisfaite");
		}
		if (testgre4) {
			System.out.println("Mon programme passe le second test avec succés.");
		}else {
			System.out.println("Mon programme ne marche pas");
			System.out.println("Il aurait du trouver que la contrainte n'est pas satisfaite");
		}
	}
	
	public static void testSchedule() {
		
		Activity math = new Activity ("math", 50);
		Activity poo = new Activity ("poo", 120);
		Activity te = new Activity ("te", 60);
		Activity ang = new Activity ("ang",120);
		
		PrecedenceConstraint contrainte1 = new PrecedenceConstraint (math, poo);
		PrecedenceConstraint contrainte2 = new PrecedenceConstraint (poo,te);
		PrecedenceConstraint contrainte3 = new PrecedenceConstraint(te,ang);
		
		ArrayList<BinaryConstraint> touteslescontraintes = new ArrayList<>();
		
		touteslescontraintes.add(contrainte1);
		touteslescontraintes.add(contrainte2);
		touteslescontraintes.add(contrainte3);
		
		int neuf = 9;
		int dix = 10;
		int midi = 12;
		int quatorze = 14;
		
		Schedule emploidutemps = new Schedule();
		
		emploidutemps.add(math,neuf);
		emploidutemps.add(poo,dix);
		emploidutemps.add(te,midi);
		emploidutemps.add(ang,quatorze);
		
		if (emploidutemps.satisfies(touteslescontraintes)) {
			System.out.println("Mon programme passe le test");
		}else {
			System.out.println("Mon programme ne passe pas le test");
		}
		System.out.println("Mon emploi du temps : " + emploidutemps);
	}
	
	public static void testSchedulepart2() {
		Schedule schedule = new Schedule();
		ArrayList<Activity> activities = new ArrayList<>();
		
		for (int i = 0; i<10; i++) {
			activities.add(new Activity("activity"+i,1));
		}
		
		ArrayList<BinaryConstraint> constraints = new ArrayList<>();
		
		for (int i = 10 ; i>1 ; i--) {
			constraints.add(new PrecedenceConstraint(activities.get(i-2), activities.get(i-1)));
		}
		
		schedule = schedule.computeSchedule(activities, constraints);
		System.out.println("Emploi du temps obtenu avec compteSchedule : " + schedule);
	}
	
	public static void testBinaryConstraint() {
		ArrayList<BinaryConstraint> contraintes = new ArrayList<> ();
		ArrayList<Activity> activities = new ArrayList<>();
		Activity activity1 = new Activity("first",1);
		Activity activity2 = new Activity("second",1);
		Activity activity3 = new Activity("third",1);
		Activity activity4 = new Activity("fourth",1);
		activities.add(activity1);
		activities.add(activity2);
		activities.add(activity3);
		activities.add(activity4);
		

		// Contrainte : activité1 doit être planifiée avant activité2
		contraintes.add(new PrecedenceConstraint(activity1, activity2));

		// Contrainte : activité4 doit commencer précisément quand activité3 se termine
		contraintes.add(new MeetConstraint(activity3, activity4));

		// Contrainte : activité1, activité2 et activité3 doivent toutes
		// être exécutées en au plus 90 minutes
		ArrayList<Activity> ensemble = new ArrayList<> ();
		ensemble.add(activity1);
		ensemble.add(activity2);
		ensemble.add(activity3);
		//MaxSpanConstraint contrainteEnsemble = new MaxSpanConstraint(ensemble, 90);
		//contraintes.add(contrainteEnsemble);

		Schedule emploiDuTemps = new Schedule ();
		
		emploiDuTemps = emploiDuTemps.computeSchedule(activities,contraintes);

		System.out.println("L'emploi du temps satisfait-il toutes les contraintes ? ");
		System.out.println(emploiDuTemps.satisfies(contraintes));
	}
	
	public static void testScheduleReader() throws IOException {
		ScheduleReader read = new ScheduleReader();
		String ACTIVITY_PATH = "testreader1.txt";
		String PRECEDENCE_PATH = "testreader2.txt";
		Map<String, Activity> activities = read.readActivities(ACTIVITY_PATH);
		ArrayList<PrecedenceConstraint> constraints = read.readPrecedenceConstraint(PRECEDENCE_PATH, activities);
		Schedule sch = new Schedule();
		sch = sch.computeSchedule(activities.values(), constraints);
		System.out.println(sch);
	}
}
