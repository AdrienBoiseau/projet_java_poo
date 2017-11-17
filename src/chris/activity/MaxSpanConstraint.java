package chris.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class MaxSpanConstraint implements Constraint {
    // CODE COMME DEMANDE mais serieusement, c'est mal propre de faire des
    // objets et stocker de telles valeurs (duree max) juste pour une condition.
    // Instancier des trucs pareils c'est de la perte de mémoire inutile.
    private List<Activity> activities;
    private int max_duration;

    public MaxSpanConstraint(final List<Activity> act, final int max_dur) {
        activities = act;
        max_duration = max_dur; // minutes max
    }

    @Override
    public boolean isSatisfied(final Schedule sched) {
        if (activities.size() == 0)
            return true;

        GregorianCalendar start;
        GregorianCalendar end;
        ArrayList<Activity> sorted_acts = sched.get_sorted_activities();
        sorted_acts.retainAll(activities);

        start = sched.get_date(sorted_acts.get(0));
        end = sched.get_date(sorted_acts.get(sorted_acts.size() - 1));
        end.add(Calendar.MINUTE, sorted_acts.get(sorted_acts.size() - 1).duration);

        return (end.getTimeInMillis() - start.getTimeInMillis()) * 1000 * 60 <= max_duration;
    }
}
