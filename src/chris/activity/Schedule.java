package chris.activity;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Schedule {
    HashMap<Activity, GregorianCalendar> sched = new HashMap<Activity, GregorianCalendar>();

    Schedule(HashMap<Activity, GregorianCalendar> s) {
        this.sched = s;
    }

    boolean satisfies(ArrayList<PrecedenceConstraint> constraints) {

        for (PrecedenceConstraint contraint : constraints) {
            if (this.sched.get(contraint.first) != null & this.sched.get(contraint.second) != null)
                if (!contraint.isSatisfied(this.sched.get(contraint.first), this.sched.get(contraint.second))) {
                    return false;
                }
        }
        return true;

    }
}
