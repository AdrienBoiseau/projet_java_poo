package projet;

import java.util.ArrayList;

abstract class BinaryConstraint{
    Activity first;
    Activity second;

    abstract boolean isSatisfied(int date1, int date2);

    public boolean isSatisfied(Schedule sch) {
        return isSatisfied(sch.schedule.get(first),sch.schedule.get(second));
    }
}
