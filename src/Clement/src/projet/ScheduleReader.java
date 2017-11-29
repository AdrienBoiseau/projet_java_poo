package projet;

import scheduleio.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ScheduleReader {
    public static Map<String,Activity> readActivity(String filename) throws IOException {
        IdStringStringReader activityReader = new IdStringStringReader(
                new BufferedReader(new FileReader(filename)),
                " = ",
                "_lasting_");

        Map<String,Activity> activities = new HashMap<String, Activity>();

        for (Map.Entry<String, OrderedPair<String, String>> strings: activityReader.readAll().entrySet()) {
            String id = strings.getKey();
            OrderedPair<String, String> activityStrings = strings.getValue();
            String description = activityStrings.getFirst();
            int duration = Integer.parseInt(activityStrings.getSecond());
            activities.put(id,new Activity(description,duration));
        }

        return activities;
    }

    public static Collection<BinaryConstraint> readConstraint(String filename, Map<String,Activity> referred) throws IOException {
        OrderedPairReader constraintReader = new OrderedPairReader(
                new BufferedReader(new FileReader(filename)),
                referred.keySet(),
                "_before_"
        );
        Collection<BinaryConstraint> constraints;
        constraints = new HashSet<BinaryConstraint>();
        for (OrderedPair p : constraintReader.readAll()) {
            Activity first = referred.get(p.getFirst());
            Activity second = referred.get(p.getSecond());
            constraints.add(new PrecedenceConstraint(first,second));
        }
        return constraints;
    }
}
