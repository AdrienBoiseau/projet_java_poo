import timetable_generator.Activity;
import timetable_generator.PrecedenceConstraint;
import timetable_generator.Timeslot;
import timetable_generator.PrecedenceConstraintWithDuration;

import java.util.GregorianCalendar;


public class exec {
    public static void main(String[] tab) {
        
        Timeslot test = new Timeslot("TP POO");
        test.setStart(13,00,20,10,2017);
        test.setEnd(16,45,20,10,2017);
        System.out.println(test);



        // Objets
        Activity options = new Activity ("Choisir mes options", 70);
        Activity ip = new Activity ("Inscription pédagogique", 30);
        PrecedenceConstraint contrainte = new PrecedenceConstraint (options, ip);
        int neufHeures = 9;
        int dixHeures = 10;
        int onzeHeures = 11;

        // Test avec une programmation censée satisfaire la contrainte
        if ( ! contrainte.isSatisfied(neufHeures, onzeHeures) ) {
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
        if ( contrainte.isSatisfied(neufHeures, dixHeures) ) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
        } else {
            System.out.println("Mon programme passe le troisième test avec succès.");
        }

        Activity dejeuner = new Activity("Déjeuner",30);
        Activity cours = new Activity("Cours",120);

        GregorianCalendar debut = new GregorianCalendar()
        PrecedenceConstraintWithDuration contrainteDeTemps = new PrecedenceConstraintWithDuration(dejeuner,cours,60,15);
        Boolean test = contrainteDeTemps.isSatisfied();
        System.out.println("Résultat du test = ");

    }
}
