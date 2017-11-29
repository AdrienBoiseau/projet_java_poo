package projet;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            Map<String,Activity> activities = new HashMap<>();
            activities = ScheduleReader.readActivity(args[0]);

            ArrayList<Activity> listActivities = new ArrayList<>();
            listActivities.addAll(activities.values());

            Collection<BinaryConstraint> constraints = new HashSet<>();
            constraints = ScheduleReader.readConstraint(args[1],activities);

            Schedule sch = new Schedule();
            sch = sch.computeSchedule(listActivities,constraints);

            if (sch.satisfies(constraints)) {
                System.out.println(sch);
                System.out.println("\nL'emploi du temps ne satisfait pas toutes les contraintes.");
            } else {
                System.out.println("Aucun emploi du temps ne peut satisfaire toutes les contraintes de précédence fournies.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
            System.out.println("Usage :\n" +
                    "java -jar Schedule.jar path/to/activities.txt path/to/precedenceConstraints.txt");
        }

    }
}
