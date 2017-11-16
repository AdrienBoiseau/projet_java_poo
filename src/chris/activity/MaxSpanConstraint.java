package chris.activity;

public class MaxSpanConstraint implements Constraint {
    // CODE COMME DEMANDE mais serieusement, c'est mal propre de faire des
    // objets et stocker de telles valeurs (duree max) juste pour une condition.
    // Instancier des trucs pareils c'est de la perte de mémoire inutile.
    private int max_duration;

    public MaxSpanConstraint(int max_dur) {
        max_duration = max_dur;
    }

    @Override
    public boolean isSatisfied(Schedule sched) {
        int duration = 0;
        for (Activity act : sched.get_sorted_activities())
            duration += act.duration;
        return duration <= max_duration;
    }
}
