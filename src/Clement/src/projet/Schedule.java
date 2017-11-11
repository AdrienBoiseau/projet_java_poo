package projet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Schedule {
    Map<Activity, Integer> set_of_activity;

    public Schedule() {
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
}
