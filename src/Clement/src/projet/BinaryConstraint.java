package projet;

import java.util.ArrayList;

abstract class BinaryConstraint {
    Activity first;
    Activity second;

    abstract boolean isSatisfied(int date1,int date2);

    public boolean isSatisfied(Schedule sch) {
        ArrayList<Integer> dates = new ArrayList<>();
        dates.add(sch.schedule.get(this.first));
        dates.add(sch.schedule.get(this.second));
        return isSatisfied(dates.get(0),dates.get(1));
    }
}
