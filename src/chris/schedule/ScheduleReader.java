package chris.schedule;


import chris.activity.Activity;
import chris.activity.PrecedenceConstraint;
import scheduleio.IdStringStringReader;
import scheduleio.OrderedPair;
import scheduleio.OrderedPairReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScheduleReader {
    ScheduleReader() {
    }

    public Map<String, Activity> readActivities(String filename) throws IOException {
        BufferedReader file = new BufferedReader(new FileReader(filename));
        IdStringStringReader activityReader = new IdStringStringReader(file,
                "=",
                "_lasting_");

        HashMap<String, Activity> activities = new HashMap<>();

        for (Map.Entry<String, OrderedPair<String, String>> strings : activityReader.readAll().entrySet()) {
            String id = strings.getKey();
            OrderedPair<String, String> activityStrings = strings.getValue();
            String description = activityStrings.getFirst();
            int duration = Integer.parseInt(activityStrings.getSecond());
            activities.put(id, new Activity(description, duration));
        }
        return activities;
    }

    public ArrayList<PrecedenceConstraint> readPrecedence(String filename,
                                                          Map<String,
                                                                  Activity>
                                                                  act
    ) throws
            IOException {
        BufferedReader file = new BufferedReader(new FileReader(filename));
        OrderedPairReader precedReader = new OrderedPairReader(
                file,
                act.keySet(),
                "_before_");

        ArrayList<PrecedenceConstraint> precedence = new ArrayList<>();

        for (OrderedPair<String, String> pair : precedReader.readAll()) {

            precedence.add(
                    new PrecedenceConstraint(
                            act.get(pair.getFirst()),
                            act.get(pair.getSecond())
                    )
            );
        }
        return precedence;
    }
}
