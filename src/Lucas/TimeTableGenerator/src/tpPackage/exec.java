package tpPackage;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class exec {
	public static void main(String[] args) {
		
		/*
		
		// test sans calendrier

		TimeSlot test1 = new TimeSlot(9,12,"poo");
		TimeSlot test2 = new TimeSlot(13,14,"math");
		TimeSlot test3 = new TimeSlot(10,11,"te");
		Boolean a = test1.overslaps(test2);
		Boolean b = test1.overslaps(test3);
		Boolean c = test2.overslaps(test3);
		System.out.println(a + " " + b + " " + c);

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
		System.out.println(d + " " + e + " " + f);
		
		*/
		
		
		
		// test sans calendrier
		
		// Objets
		Activity options = new Activity ("Choisir mes options", 70);
		Activity ip = new Activity ("Inscription p�dagogique", 30);
		
		/*
		 *(GregorianCalendar)
		PrecedenceConstraint contrainte = new PrecedenceConstraint (options, ip);
		int neufHeures = 9;
		int dixHeures = 10;
		int onzeHeures = 11;

		// Test avec une programmation cens�e satisfaire la contrainte
		if ( ! contrainte.isSatisfied(neufHeures, onzeHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait d� trouver que la contrainte est satisfaite.");
		} else {
		    System.out.println("Mon programme passe le premier test avec succ�s.");
		}

		// Test avec une programmation cens�e ne pas satisfaire la contrainte
		if ( contrainte.isSatisfied(dixHeures, neufHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait d� trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le deuxi�me test avec succ�s.");
		}

		// Test avec une programmation cens�e ne pas satisfaire la contrainte (car la premi�re
		// activit� finirait apr�s le d�but de la seconde)
		if ( contrainte.isSatisfied(neufHeures, dixHeures) ) {
		    System.out.println("Mon programme ne marche pas.");
		    System.out.println("Il aurait d� trouver que la contrainte n'est pas satisfaite.");
		} else {
		    System.out.println("Mon programme passe le troisi�me test avec succ�s.");
		}
		*/
		
		Activity math = new Activity ("math", 50);
		Activity poo = new Activity ("poo", 120);
		
		
		PrecedenceConstraintWithDuration test7 = new PrecedenceConstraintWithDuration(math, poo, 120, 240);
		PrecedenceConstraintWithDuration test8 = new PrecedenceConstraintWithDuration(poo, math, 120, 240);
		boolean prem = test7.isSatisfied(9, 14);
		boolean sec = test8.isSatisfied(9, 14);
		System.out.println(prem + " " + sec);
		
		
		//test avec calendrier
		
		GregorianCalendar cal1 = new GregorianCalendar();
		GregorianCalendar cal2 = new GregorianCalendar();
		
		cal1.set(Calendar.HOUR, 9);
		cal1.set(Calendar.MINUTE, 0);
		cal2.set(Calendar.HOUR, 14);
		cal2.set(Calendar.MINUTE, 0);
		
		/*
		PrecedenceConstraintGregorien greg1 = new PrecedenceConstraintGregorien(options, ip);
		PrecedenceConstraintGregorien greg2 = new PrecedenceConstraintGregorien(ip, options);
		boolean testgre1 = greg1.isSatisfied(cal1,cal2);
		System.out.println(cal1.get(cal1.HOUR)+ " " + cal1.get(cal1.MINUTE));
		boolean testgre2 = greg2.isSatisfied(cal1,cal2);
		System.out.println(testgre1);
		System.out.println(testgre2);
		*/
		
		PrecedenceConstraintWithDurationGregorien greg3 = new PrecedenceConstraintWithDurationGregorien(math,poo,120,240);
		boolean testgre3 = greg3.isSatisfied(cal1, cal2);
		System.out.println(testgre3);
		PrecedenceConstraintWithDurationGregorien greg4 = new PrecedenceConstraintWithDurationGregorien(poo,math,120,240);
		boolean testgre4 = greg4.isSatisfied(cal1, cal2);
		System.out.println(testgre4);
	}
}
