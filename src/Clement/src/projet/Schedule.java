package projet;


import java.lang.Math;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *  Represent a timetable.
 */
public class Schedule {

    HashMap<Activity, Integer> schedule;

    public Schedule() {
        /**
         * Constructor of Schedule class.
         *
         * Create the set of activities represanted by the HashMap.
         * The keys are activities and the value the date the activity begins.
         */
        this.schedule = new HashMap();
    }

    public boolean satisfies(ArrayList<BinaryConstraint> listeContrainte) {
        System.out.println(listeContrainte);
        for (BinaryConstraint constraint : listeContrainte) {
            System.out.println(constraint.second);
            Activity before = constraint.first;
            Activity after = constraint.second;
            if (! constraint.isSatisfied(this.schedule.get(before),
                    this.schedule.get(after))){
                return false;
            }
        }
        return true;

    }

    public void add(Activity activity, int hour) {
        this.schedule.put(activity,hour);
    }

    private ArrayList<Activity> getSortedActivities() {
        ArrayList<Activity> sorted = new ArrayList<>();
        for (Activity activity : this.schedule.keySet()) {
            if (sorted.isEmpty()) {
                sorted.add(activity);
            } else {
                boolean lastIn = false;
                for (int i = 0; i < sorted.size(); i++) {
                    if (schedule.get(sorted.get(i)) > schedule.get(activity)) {
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
            str += a.name + " : " + this.schedule.get(a) + "h";
            if (it.hasNext()) {
                str += " ; ";
            }

        }
        return str;
    }

    private Activity next(ArrayList<Activity> activities, ArrayList<BinaryConstraint> constraints, ArrayList<Activity> scheduled) {
        for (Activity activity : activities) {
            if (! scheduled.contains(activity)) {
                boolean underConstraint = false;
                label:if(!underConstraint) {
                    for (BinaryConstraint constraint : constraints) {
                        underConstraint = (activity==constraint.second);
                        if (underConstraint) {
                            if (! scheduled.contains(constraint.first)) {
                                break label;
                            }
                            else {
                                underConstraint = false;
                            }
                        }
                    }
                }
                if (! underConstraint) {
                    return activity;
                }
            }
        }
        return null;

    }

    public Schedule computeSchedule(ArrayList<Activity> activities, ArrayList<BinaryConstraint> constraints) {
        Schedule res = new Schedule();
        ArrayList<Activity> scheduled = new ArrayList<>();
        Activity nextActivity;
        nextActivity = next(activities, constraints, scheduled);
        int h = 8;
        while (nextActivity != null) {
            res.add(nextActivity,h);
            scheduled.add(nextActivity);
            h += Math.round(nextActivity.duration/60)+1;
            nextActivity = next(activities,constraints,scheduled);
        }
        return res;
    }

}

