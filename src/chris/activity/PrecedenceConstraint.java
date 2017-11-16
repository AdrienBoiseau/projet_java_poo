package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static chris.activity.Main.sdf;

public class PrecedenceConstraint extends BinaryConstraint implements Constraint {

    public PrecedenceConstraint(Activity f, Activity s) {
        super(f, s);
    }

    static boolean is_equal_or_less(GregorianCalendar date,
                                    GregorianCalendar limit) {

        return date.compareTo(limit) <= 0;
    }

    @Override
    public boolean isSatisfied(GregorianCalendar date1,
                               GregorianCalendar date2) {
        GregorianCalendar d = (GregorianCalendar) date1.clone();
        d.add(Calendar.MINUTE, this.first.duration);
        System.out.println(sdf.format(d.getTime()) + "<=" + sdf.format(date2.getTime()) + "?");

        return is_equal_or_less(d, date2);
    }


}
