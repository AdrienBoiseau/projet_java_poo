package exec;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import classes.TimeSlot;
import classes.Activity;
import classes.PrecedenceConstraint;
import classes.PrecedenceConstraintWithDuration;
import classes.Schedule;



public class Main {
	
	public static void verify(boolean contrainte){
		if (contrainte) {
		    System.out.println("La contrainte des activités est satisfaite");
		} else {
		    System.out.println("La contrainte des activités n'est pas satisfaite");
		}
	}
	

	public static void main(String[] args){
		
		
		// Activités
		Activity activite1 = new Activity("TD Maths",150);
		Activity activite2 = new Activity("CM Calcul scientifique",60);
		Activity activite3 = new Activity("CM Système",75);
		Activity activite4 = new Activity("TD Algo",75);
		Activity activite5 = new Activity("TP Algo",120);

		// Contraintes
		PrecedenceConstraint contrainte1 = new PrecedenceConstraint(activite1,activite2);
		PrecedenceConstraint contrainte2 = new PrecedenceConstraint(activite2,activite3);
		PrecedenceConstraint contrainte3 = new PrecedenceConstraint(activite4,activite5);
		PrecedenceConstraint contrainte4 = new PrecedenceConstraint(activite1,activite4);

		ArrayList<PrecedenceConstraint> toutesLesContraintes = new ArrayList<> ();
		toutesLesContraintes.add(contrainte1);
		toutesLesContraintes.add(contrainte2);
		toutesLesContraintes.add(contrainte3);
		toutesLesContraintes.add(contrainte4);

		ArrayList<Activity> toutesLesActivites = new ArrayList<> ();
		toutesLesActivites.add(activite1);
		toutesLesActivites.add(activite2);
		toutesLesActivites.add(activite3);
		toutesLesActivites.add(activite4);
		toutesLesActivites.add(activite5);
		
		
		HashMap<Activity, GregorianCalendar> horaires = new HashMap<>();
		Schedule sch = new Schedule(horaires);
		
        System.out.println("\nEmploi du temps : " + sch.computeSchedule(toutesLesActivites, toutesLesContraintes).toString());
		
		
	}

}
