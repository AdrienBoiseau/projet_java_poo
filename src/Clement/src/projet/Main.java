package projet;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        long debut = System.currentTimeMillis();
        //testTimeSlot();
        //testPrecedenceConstraint();
        //testSchedule();
        //testSchedule2(5000);
        testAbstract();
        System.out.println("Fin. Durée : " + (System.currentTimeMillis()-debut));

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

    private static void testSchedule() {
        // Activités
        Activity activite1 = new Activity("CM",60);
        Activity activite2 = new Activity("TP",60);
        Activity activite3 = new Activity("TD",60);

        // Contraintes
        //PrecedenceConstraintWithDuration contrainte1 = ...; // Not implement
        PrecedenceConstraint contrainte1 = new PrecedenceConstraint(activite3,activite2);
        PrecedenceConstraint contrainte2 = new PrecedenceConstraint(activite1,activite3);

        ArrayList toutesLesContraintes = new ArrayList ();
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
        System.out.println("Mon emploi du temps : " + emploiDuTemps);
    }

    private static void testSchedule2(int n) {
        ArrayList<Activity> activities = new ArrayList<>();
        for (int i=0 ; i<n ; i++) {
            activities.add(new Activity("Activity"+i,1));
        }
        
        ArrayList<PrecedenceConstraint> constraints = new ArrayList<>();
        for (int i=n ; i>1 ; i--) {
            //System.out.println(i);
            constraints.add(new PrecedenceConstraint(activities.get(i-2),activities.get(i-1)));
        }

        Schedule test = new Schedule();
        test = test.computeSchedule(activities,constraints);

    }

    private static void testAbstract() {
        ArrayList<Activity> activities = new ArrayList<>();
        Activity activity1 = new Activity("First",1);
        Activity activity2 = new Activity("Second",1);
        Activity activity3 = new Activity("Third",1);
        Activity activity4 = new Activity("Fourth",1);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
        activities.add(activity4);

        List<Constraint> constraints = new ArrayList<>();

        // Constraint : Activity1 must be plan before Activity2.
        constraints.add(new PrecedenceConstraint(activity1,activity2));

        // Constraint : Activity4 must be plan just when Activity3 finished.
        constraints.add(new MeetConstraint(activity3,activity4));

        // Constraint : Activity1, Activity2 and Activity3 must be all finished in 90 minutes or less.
        ArrayList<Activity> set = new ArrayList<>();
        set.add(activity1);
        set.add(activity2);
        set.add(activity3);
        MaxSpanConstraint setConstraint = new MaxSpanConstraint(set,90);
        constraints.add(setConstraint);

        Schedule emploiDuTemps = new Schedule();
        emploiDuTemps = emploiDuTemps.computeSchedule(activities,constraints);
    }
}
