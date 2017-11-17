package projet;

import java.util.ArrayList;

public class MaxSpanConstraint extends BinaryConstraint {
    ArrayList<Activity> activities;
    int maxDuration;

    public MaxSpanConstraint(ArrayList<Activity> arg1, int arg2) {
        this.activities = arg1;
        this.maxDuration = arg2;
    }

    @Override
    boolean isSatisfied(int date1, int date2) {
        return false;
    }

    @Override
    public boolean isSatisfied(Schedule sch) {
        ArrayList<Integer> dates = new ArrayList<>();
        for (Activity a : activities) {
            dates.add(sch.schedule.get(a));
        }

        int min = dates.get(0);
        int max = dates.get(0);

        for (int i : dates) {
            if (i < min) min=i;
            if (i > max) max=i;
        }
        return ((max-min) <= this.maxDuration);
    }

}
