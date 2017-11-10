package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static chris.activity.Main.sdf;

public class PrecedenceConstraint {

    Activity first;
    Activity second;

    public PrecedenceConstraint(Activity f, Activity s) {
        this.first = f;
        this.second = s;
    }

    static boolean is_equal_or_less(GregorianCalendar date,
                                    GregorianCalendar limit) {

        return date.compareTo(limit) <= 0;
    }

    public boolean isSatisfied(GregorianCalendar date1,
                               GregorianCalendar date2) {
        GregorianCalendar d = (GregorianCalendar) date1.clone();
        d.add(Calendar.MINUTE, this.first.duration);
        System.out.println(sdf.format(d.getTime()) + "<=" + sdf.format(date2.getTime()) + "?");

        return is_equal_or_less(d, date2);
    }


}
