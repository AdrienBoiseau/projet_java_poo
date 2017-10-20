package chris.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Main {

    static SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy hh:mm aaa");

    public static void main(String[] args){

        //TP2();
        TP3();

    }

    static private void print(String m) {
        System.out.println(m);
    }

    static private void TP2() {
        print("Hello world!");
        Point a = new Point(3,4);
        print(a.get_representation());
        print(a.get_symetry().get_representation());
        TimeSlot ts1 = new TimeSlot(new GregorianCalendar(2012, 11, 31, 20, 0, 0),
                new GregorianCalendar(2013, 0, 1, 6, 0, 0),
                "bitch");

        TimeSlot ts2 = new TimeSlot(new GregorianCalendar( 2013, 0, 1, 7, 0, 0),
                new GregorianCalendar( 2013, 0, 1, 9, 0, 0),
                "salaauuud");

        TimeSlot ts3 = new TimeSlot(new GregorianCalendar( 2013, 0, 1, 8, 0, 0),
                new GregorianCalendar( 2013, 0, 1, 11, 0, 0),
                "salaauuud");
        print(ts1.get_repr());
        print(ts2.get_repr());
        print(ts3.get_repr());
        print(String.valueOf(TimeSlot.overlaps(ts1, ts2)));
        print(String.valueOf(TimeSlot.overlaps(ts1, ts3)));
        print(String.valueOf(TimeSlot.overlaps(ts2, ts3)));
    }

    static private void TP3() {
        // Objets
        Activity options = new Activity ("Choisir mes options", 70);
        Activity ip = new Activity ("Inscription pédagogique", 30);
        PrecedenceConstraint contrainte = new PrecedenceConstraint(options, ip);

        PrecedenceConstraintWithDuration contrainte_with_duration = new PrecedenceConstraintWithDuration(options,
                ip,
                0,
                120);
        GregorianCalendar date_1 = new GregorianCalendar(2012, 11, 31, 9, 0, 0);
        GregorianCalendar date_2 = new GregorianCalendar(2012, 11, 31, 10, 0, 0);
        GregorianCalendar date_3 = new GregorianCalendar(2012, 11, 31, 11, 0, 0);

// Test avec une programmation censée satisfaire la contrainte
        if ( ! contrainte.isSatisfied(date_1, date_3) ) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte est satisfaite.");
        } else {
            System.out.println("Mon programme passe le premier test avec succès.");
        }

// Test avec une programmation censée ne pas satisfaire la contrainte
        if ( contrainte.isSatisfied(date_2, date_1) ) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
        } else {
            System.out.println("Mon programme passe le deuxième test avec succès.");
        }

// Test avec une programmation censée ne pas satisfaire la contrainte (car la première
// activité finirait après le début de la seconde)
        if ( contrainte.isSatisfied(date_1, date_2) ) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
        } else {
            System.out.println("Mon programme passe le troisième test avec succès.");
        }

        if (!contrainte_with_duration.isSatisfied(date_1, date_3)) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte est satisfaite.");
        } else {
            System.out.println("Mon programme passe le premier test avec succès.");
        }

// Test avec une programmation censée ne pas satisfaire la contrainte
        if (contrainte_with_duration.isSatisfied(date_2, date_1)) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
        } else {
            System.out.println("Mon programme passe le deuxième test avec succès.");
        }

// Test avec une programmation censée ne pas satisfaire la contrainte (car la première
// activité finirait après le début de la seconde)
        if (contrainte_with_duration.isSatisfied(date_1, date_2)) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
        } else {
            System.out.println("Mon programme passe le troisième test avec succès.");
        }

        //b SCHEDULE

        Activity act_a = new Activity("a", 60);
        Activity act_b = new Activity("b", 60);
        Activity act_c = new Activity("c", 120);

        GregorianCalendar date_a = new GregorianCalendar(2012, 11, 31, 9, 0, 0);
        GregorianCalendar date_b = new GregorianCalendar(2012, 11, 31, 10, 0, 0);
        GregorianCalendar date_c = new GregorianCalendar(2012, 11, 31, 11, 0, 0);

        HashMap<Activity, GregorianCalendar> horaires = new HashMap<>();
        horaires.put(act_a, date_a);
        horaires.put(act_b, date_b);
        horaires.put(act_c, date_c);

        Schedule sched = new Schedule(horaires);

        ArrayList<PrecedenceConstraint> cons_true = new ArrayList<>();

        cons_true.add(new PrecedenceConstraint(act_a, act_b));
        cons_true.add(new PrecedenceConstraint(act_a, act_c));
        cons_true.add(new PrecedenceConstraint(act_b, act_c));

        System.out.println(sched.satisfies(cons_true));
        System.out.println(sched.toString());


        ArrayList<PrecedenceConstraint> cons_false = new ArrayList<>();

        cons_false.add(new PrecedenceConstraint(act_a, act_b));
        cons_false.add(new PrecedenceConstraint(act_a, act_c));
        cons_false.add(new PrecedenceConstraint(act_c, act_b));

        System.out.println(sched.satisfies(cons_false));
        System.out.println(sched.toString());

        ArrayList<Activity> activities = new ArrayList<>();
        activities.add(act_a);
        activities.add(act_b);
        activities.add(act_c);

        print(Schedule.computeSchedule(activities, cons_true).toString());


    }

}
