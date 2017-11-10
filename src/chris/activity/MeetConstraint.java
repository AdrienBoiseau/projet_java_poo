package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MeetConstraint extends BinaryConstraint {

    public MeetConstraint(Activity f, Activity s) {
        this.first = f;
        this.second = s;
    }

    @Override
    public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2) {
        GregorianCalendar end_date1 = (GregorianCalendar) date1.clone();
        end_date1.add(Calendar.MINUTE, first.duration);
        return date2.compareTo(end_date1) == 0;
    }
}
