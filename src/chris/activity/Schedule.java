package chris.activity;

import java.util.*;

import static chris.activity.Main.sdf;

public class Schedule {
    private HashMap<Activity, GregorianCalendar> sched = new HashMap<>();

    public Schedule(HashMap<Activity, GregorianCalendar> s) {
        this.sched = s;
    }

    private Schedule() {
    }

    static Schedule computeSchedule(ArrayList<Activity> activities, ArrayList<PrecedenceConstraint> constraints) {
        GregorianCalendar dummy_cal = new GregorianCalendar(2017, 5, 30, 8, 0, 0);
        Schedule planning = new Schedule();
        ArrayList<Activity> to_plannify = (ArrayList<Activity>) activities.clone();
        for (int i = 0; i < activities.size(); i++) {
            // get next planifiable act
            Activity act = next(to_plannify, constraints, planning.sched.keySet());
            if (act == null) {
                System.out.println("EMPLOI DU TEMPS IMPOSSIBLE, CONSTRAINTES CIRCULAIRES");
                return null;
            }
            // add it to the schedule at the next planiffiable moment
            planning.sched.put(act, (GregorianCalendar) dummy_cal.clone());
            // on ajoute la durée de l'activité au compteur de temps possible pour avancer dans le temps suffisamment
            dummy_cal.add(Calendar.MINUTE, act.duration);
            // on vire l 'eactivité de la liste des trucs a planiffier.
            to_plannify.remove(act);
        }
        return planning;
    }

    static private Activity next(ArrayList<Activity> activities, ArrayList<PrecedenceConstraint> constraints, Set<Activity> planified) {
        for (Activity act : activities) {
            // Act !== planned activities
            if (!planified.contains(act)) {
                Activity next_act = act;
                for (PrecedenceConstraint con : constraints) {
                    // no constraints telles que X => act
                    // et tel que X !== planned activities
                    if (con.second.equals(act) && !planified.contains(con.first)) {
                        next_act = null;
                        break;
                    }
                }
                if (next_act != null) {
                    return act;//
                }
            }
        }
        // si jamais le tri est impossible, conditions circulaires
        return null;
    }

    public boolean satisfies(ArrayList<PrecedenceConstraint> constraints) {

        for (PrecedenceConstraint contraint : constraints) {
            if (this.sched.get(contraint.first) != null & this.sched.get(contraint.second) != null)
                if (!contraint.isSatisfied(this.sched.get(contraint.first), this.sched.get(contraint.second))) {
                    return false;
                }
        }
        return true;
    }

    public ArrayList<Activity> get_sorted_activities() {
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
