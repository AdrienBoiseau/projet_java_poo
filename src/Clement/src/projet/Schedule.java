package projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    /**
     *  Represent a timetable.
     *
     * @TODO : method toString
     */
    Map<Activity, Integer> set_of_activity;

    public Schedule() {
        /**
         * Constructor of Schedule class.
         *
         * Create the set of activities represanted by the HashMap set_of_activity.
         * The keys are activities and the value the date the activity begins.
         */
        this.set_of_activity = new HashMap();
    }

    public boolean satisfies(ArrayList<PrecedenceConstraint> listeContrainte) {
        for (PrecedenceConstraint constraint : listeContrainte) {
            Activity before = constraint.first;
            Activity after = constraint.second;
            if (! constraint.isSatisfied(this.set_of_activity.get(before),
                    this.set_of_activity.get(after))){
                return false;
            }
        } return true;
    }

    public void add(Activity activity, int hour) {
        this.set_of_activity.put(activity,hour);
    }

    @Override
    public String toString() {
        return "Not implemented yet.";
    }
}
