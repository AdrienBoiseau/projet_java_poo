package chris.testing;

import chris.activity.*;

import java.util.*;
import java.util.stream.IntStream;

class BasicTests {


    static void run_schedule_tests() {
        List<Activity> act_span = new ArrayList<>();

        Activity act_a = new Activity("a", 60);
        GregorianCalendar date_a = new GregorianCalendar(
                2012,
                11,
                31,
                9,
                0,
                0);

        Activity act_b = new Activity("b", 60);
        GregorianCalendar date_b = new GregorianCalendar(
                2012,
                11,
                31,
                10,
                0,
                0);

        Activity act_c = new Activity("c", 120);
        GregorianCalendar date_c = new GregorianCalendar(2012,
                11,
                31,
                11,
                0,
                0);

        HashMap<Activity, GregorianCalendar> horaires = new HashMap<>();
        horaires.put(act_a, date_a);
        horaires.put(act_b, date_b);
        horaires.put(act_c, date_c);

        act_span.add(act_a);
        act_span.add(act_b);
        act_span.add(act_c);

        Schedule sched = new Schedule(horaires);

        System.out.println("Schedule test: no constraints => " +
                ((no_constraints_schedule(sched)) ? "PASSED" : "FAILED"));

        System.out.println("Schedule test: no solutions => " +
                ((no_solution_schedule(sched)) ? "PASSED" : "FAILED"));

        System.out.println("Schedule test: random ok constraints => " +
                ((random_ok_constraints_schedule(sched)) ? "PASSED" : "FAILED"));

        System.out.println("Schedule test: meet constraints => " +
                ((meet_constraints_schedule(sched)) ? "PASSED" : "FAILED"));

        System.out.println("Schedule test: don't meet constraints => " +
                ((no_meet_constraints_schedule(sched)) ? "PASSED" : "FAILED"));

        System.out.println("Schedule test: 0 mins max span constraint => " +
                ((no_max_duration_schedule(act_span, sched)) ? "PASSED" :
                        "FAILED"));

        System.out.println("Schedule test: 1 year max span constraint => " +
                ((max_duration_schedule(act_span, sched)) ? "PASSED" :
                        "FAILED"));


    }

    private static boolean random_ok_constraints_schedule(Schedule sched) {

        Collection<Constraint> constraints = new ArrayList<>();
        ArrayList<Activity> activities = sched.get_sorted_activities();
        int n = activities.size();
        Random rand = new Random();
        IntStream first_indexes = rand.ints(n / 2, 0, n / 2);
        IntStream second_indexes = rand.ints(n / 2, n / 2, n);

        Iterator<Integer> first_iterator = first_indexes.iterator();
        Iterator<Integer> second_iterator = second_indexes
                .iterator();
        while (first_iterator.hasNext() && second_iterator.hasNext()) {
            constraints.add(
                    new PrecedenceConstraint(
                            activities.get(first_iterator.next()),
                            activities.get(second_iterator.next())));
        }

        return sched.satisfies(constraints);
    }

    private static boolean no_solution_schedule(Schedule sched) {

        Collection<Constraint> constraints = new ArrayList<>();
        ArrayList<Activity> activities = sched.get_sorted_activities();
        int n = activities.size();
        for (int i = 0; i < n; i++) {
            constraints.add(new PrecedenceConstraint(activities.get(i),
                    activities.get((i + 1) % n)));
        }

        return !sched.satisfies(constraints);
    }

    private static boolean no_constraints_schedule(Schedule sched) {
        Collection<Constraint> constraints = new ArrayList<>();

        return sched.satisfies(constraints);
    }

    private static boolean meet_constraints_schedule(Schedule sched) {

        Collection<Constraint> constraints = new ArrayList<>();
        ArrayList<Activity> activities = sched.get_sorted_activities();

        constraints.add(
                new MeetConstraint(
                        activities.get(0),
                        activities.get(1)));
        constraints.add(
                new MeetConstraint(
                        activities.get(1),
                        activities.get(2)));

        return sched.satisfies(constraints);
    }

    private static boolean no_meet_constraints_schedule(Schedule sched) {

        Collection<Constraint> constraints = new ArrayList<>();
        ArrayList<Activity> activities = sched.get_sorted_activities();

        constraints.add(
                new MeetConstraint(
                        activities.get(0),
                        activities.get(2)));
        constraints.add(
                new MeetConstraint(
                        activities.get(1),
                        activities.get(2)));

        return !sched.satisfies(constraints);
    }

    private static boolean no_max_duration_schedule(List<Activity> act,
                                                    Schedule sched) {
        return !new MaxSpanConstraint(act, 0).isSatisfied(sched);
    }

    private static boolean max_duration_schedule(List<Activity> act,
                                                 Schedule sched) {
        return new MaxSpanConstraint(act, 60 * 24 * 365).isSatisfied(sched);
    }
}
