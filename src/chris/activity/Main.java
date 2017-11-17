package chris.activity;

import chris.schedule.ScheduleReader;
import chris.testing.TestFramework;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Map;

public class Main {

    static SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy hh:mm aaa");

    public static void main(String[] args) throws IOException {
        // Get the data files in memory
        String ACTIVITY_PATH = "data/activities.txt";
        String PRECEDENCE_PATH = "data/precedence.txt";
        String MEET_PATH = "data/meet_precedence.txt";
        String MAX_SPAN_PATH = "data/max_span_precedence.txt";

        // get the activities

        ScheduleReader reader = new ScheduleReader();
        Map<String, Activity> activities = reader.readActivities(ACTIVITY_PATH);

        // get the constraints

        Collection<PrecedenceConstraint> constraints = reader.readPrecedence
                (PRECEDENCE_PATH, activities);

        // Calculate a schedule

        Schedule sched = Schedule.computeSchedule(activities.values(), constraints);

        // Show the schedule

        System.out.println(sched.toString());


        //TP2();
        //TP3();

    }

    static private void print(String m) {
        System.out.println(m);
    }

    static private void TP2() {
        print("Hello world!");
        Point a = new Point(3, 4);
        print(a.get_representation());
        print(a.get_symetry().get_representation());
        TimeSlot ts1 = new TimeSlot(new GregorianCalendar(2012, 11, 31, 20, 0, 0),
                new GregorianCalendar(2013, 0, 1, 6, 0, 0),
                "bitch");

        TimeSlot ts2 = new TimeSlot(new GregorianCalendar(2013, 0, 1, 7, 0, 0),
                new GregorianCalendar(2013, 0, 1, 9, 0, 0),
                "salaauuud");

        TimeSlot ts3 = new TimeSlot(new GregorianCalendar(2013, 0, 1, 8, 0, 0),
                new GregorianCalendar(2013, 0, 1, 11, 0, 0),
                "salaauuud");
        print(ts1.get_repr());
        print(ts2.get_repr());
        print(ts3.get_repr());
        print(String.valueOf(TimeSlot.overlaps(ts1, ts2)));
        print(String.valueOf(TimeSlot.overlaps(ts1, ts3)));
        print(String.valueOf(TimeSlot.overlaps(ts2, ts3)));
    }

    static private void TP3() {

        TestFramework.run_basic_tests();

    }

}
