package chris.activity;

import java.util.GregorianCalendar;

public abstract class BinaryConstraint {
    Activity first;
    Activity second;

    public abstract boolean isSatisfied(GregorianCalendar date1,
                                        GregorianCalendar date2);

    boolean isSatisfied(Schedule s) {
        return isSatisfied(s.get_date(first), s.get_date(second));
    }
}
