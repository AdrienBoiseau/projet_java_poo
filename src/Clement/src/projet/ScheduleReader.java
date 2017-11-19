package projet;

import scheduleio.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScheduleReader {
    public ScheduleReader() {
    }

    public Map<String,String> readActivity(String filename) throws IOException {
        for (Map.Entry<String, OrderedPair<String, String>> strings: activityReader.readAll().entrySet()) {
            String id = strings.getKey();
            OrderedPair<String, String> activityStrings = strings.getValue();
            String description = activityStrings.getFirst();
            int duration = Integer.parseInt(activityStrings.getSecond());
        }
    }
}
