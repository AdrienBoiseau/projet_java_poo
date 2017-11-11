package projet;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        //testTimeSlot();
        //testPrecedenceConstraint();
        testShedule();
    }

    private static void testTimeSlot() {
        System.out.println("Début des tests sur la classe TimeSlot");
        TimeSlot t1 = new TimeSlot(new GregorianCalendar(2017,10,27,8,0),
                new GregorianCalendar(2017,10,27,10,0),
                "Cours 1");
        TimeSlot t2 = new TimeSlot(new GregorianCalendar(2017,10,27,9,0),
                new GregorianCalendar(2017,10,27,11,0),
                "Cours 1");
        TimeSlot t3 = new TimeSlot(new GregorianCalendar(2017,10,27,11,30),
                new GregorianCalendar(2017,10,27,12,0),
                "Cours 1");

        System.out.println(t1.getRepresentation());
        System.out.println(t2.getRepresentation());
        System.out.println(t3.getRepresentation());

        boolean test1 = (t1.overlaps(t2)); // True
        boolean test2 = (t2.overlaps(t3)); // False
        boolean test3 = (t1.overlaps(t3)); // False

        if (test1) {
            System.out.println("Premier test réussi");
        } else {
            System.out.println("Premier test raté");
        }

        if (!test2) {
            System.out.println("Deuxième test réussi");
        } else {
            System.out.println("Deuxième test raté");
        }

        if (!test3) {
            System.out.println("Troisième test réussi");
        } else {
            System.out.println("Troisième test raté");
        }

    }

    private static void testPrecedenceConstraint(){
        // Objets
        Activity options = new Activity ("Choisir mes options", 70);
        Activity ip = new Activity ("Inscription pédagogique", 30);
        PrecedenceConstraint contrainte = new PrecedenceConstraint (options, ip);
        GregorianCalendar neufHeures = new GregorianCalendar(2017,10,27,9,0);
        GregorianCalendar dixHeures = new GregorianCalendar(2017,10,27,10,0);
        GregorianCalendar onzeHeures = new GregorianCalendar(2017,10,27,11,0);

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
    }

    private static void testPrecedenceConstraintWithDuration() {

    }

    private static void testShedule() {
        // Activités
        Activity activite1 = new Activity("Cours1",60);
        Activity activite2 = new Activity("Cours2",60);
        Activity activite3 = new Activity("Cours3",60);

        // Contraintes
        //PrecedenceConstraintWithDuration contrainte1 = ...; // Not implement
        PrecedenceConstraint contrainte1 = new PrecedenceConstraint(activite3,activite2);
        PrecedenceConstraint contrainte2 = new PrecedenceConstraint(activite1,activite3);

        ArrayList<PrecedenceConstraint> toutesLesContraintes = new ArrayList ();
        toutesLesContraintes.add(contrainte1);
        toutesLesContraintes.add(contrainte2);


        // Emploi du temps
        int neufHeures = 9;
        int dixHeures = 10;
        int midi = 12;
        Schedule emploiDuTemps = new Schedule ();
        emploiDuTemps.add(activite1, neufHeures);
        emploiDuTemps.add(activite2, midi);
        emploiDuTemps.add(activite3, dixHeures);


        // Tests
        if ( emploiDuTemps.satisfies(toutesLesContraintes) ) {
            System.out.println("Mon programme passe le premier test");
        } else {
            System.out.println("Mon programme ne passe pas le premier test");
        }
    }
}
