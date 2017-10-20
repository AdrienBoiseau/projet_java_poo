package chris.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import static chris.activity.Main.sdf;

public class Schedule {
    private HashMap<Activity, GregorianCalendar> sched = new HashMap<Activity, GregorianCalendar>();

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

    private ArrayList<Activity> get_sorted_activities() {
        ArrayList<Activity> a_ar = new ArrayList<>();


        for (Activity act : this.sched.keySet()) {
            GregorianCalendar date = sched.get(act);

            if (a_ar.size() == 0) {
                a_ar.add(act);
            } else {
                boolean inserted = false;
                for (int i = 0; i < a_ar.size(); i++) {
                    if (date.compareTo(this.sched.get(a_ar.get(i))) <= 0) {
                        a_ar.add(i, act);
                        inserted = true;
                        break;
                    }
                }
                if (!inserted)
                    a_ar.add(act);
            }
        }

        return a_ar;
    }

    public String toString() {
        ArrayList<Activity> activities = get_sorted_activities();
        String res = "";
        for (Activity act : activities) {
            GregorianCalendar debut = (GregorianCalendar) this.sched.get(act).clone();
            GregorianCalendar fin = (GregorianCalendar) this.sched.get(act).clone();
            fin.add(Calendar.MINUTE, act.duration);

            res = res.concat(sdf.format(debut.getTime()) + " -> " + sdf.format(fin.getTime()) + ": " + act.description + "\n");
        }
        return res;
    }
}
