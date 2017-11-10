package chris.testing;

import chris.activity.Activity;
import chris.activity.PrecedenceConstraint;
import chris.activity.Schedule;

import java.util.*;
import java.util.stream.IntStream;

public class BasicTests {


    public void schedule_tests() {
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

        System.out.println("Schedule test: no constraints => " +
                ((no_constraints_schedule(sched)) ? "PASSED" : "FAILED"));

        System.out.println("Schedule test: no solutions => " +
                ((no_solution_schedule(sched)) ? "PASSED" : "FAILED"));

        System.out.println("Schedule test: random ok constraints => " +
                ((random_ok_constraints_schedule(sched)) ? "PASSED" : "FAILED"));


    }

    private boolean random_ok_constraints_schedule(Schedule sched) {

        ArrayList<PrecedenceConstraint> constraints = new ArrayList<>();
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

    private boolean no_solution_schedule(Schedule sched) {

        ArrayList<PrecedenceConstraint> constraints = new ArrayList<>();
        ArrayList<Activity> activities = sched.get_sorted_activities();
        int n = activities.size();
        for (int i = 0; i < n; i++) {
            constraints.add(new PrecedenceConstraint(activities.get(i),
                    activities.get((i + 1) % n)));
        }

        return !sched.satisfies(constraints);
    }

    private boolean no_constraints_schedule(Schedule sched) {
        ArrayList<PrecedenceConstraint> constraints = new ArrayList<>();

        return sched.satisfies(constraints);
    }
}
