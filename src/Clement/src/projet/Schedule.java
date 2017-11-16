package projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Schedule {
    /**
     *  Represent a timetable.
     *
     * @TODO : nothing.
     */
    HashMap<Activity, Integer> set_of_activity;

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

    private ArrayList<Activity> getSortedActivities() {
        ArrayList<Activity> sorted = new ArrayList<>();
        for (Activity activity : this.set_of_activity.keySet()) {
            if (sorted.isEmpty()) {
                sorted.add(activity);
            } else {
                boolean lastIn = false;
                for (int i = 0; i < sorted.size(); i++) {
                    if (set_of_activity.get(sorted.get(i)) > set_of_activity.get(activity)) {
                        sorted.add(i, activity);
                        lastIn = true;
                        break;
                    }
                }
                if (lastIn == false) {
                    sorted.add(activity);
                }
            }
        }
        return sorted;
    }

    @Override
    public String toString() {
        ArrayList<Activity> sorted = this.getSortedActivities();
        String str = "";
        for (Iterator it = sorted.iterator() ; it.hasNext(); ) {
            Activity a = (Activity) it.next();
            str += a.name + " : " + this.set_of_activity.get(a) + "h";
            if (it.hasNext()) {
                str += " ; ";
            }

        }
        return str;
    }
}
