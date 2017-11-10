package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static chris.activity.Main.sdf;

public class PrecedenceConstraintWithDuration extends PrecedenceConstraint {

    private int pause_min = 0;
    private int pause_max = 0;


    PrecedenceConstraintWithDuration(Activity a1, Activity a2, int pause_min, int pause_max) {
        super(a1, a2);
        //pause en minutes
        this.pause_min = pause_min;
        this.pause_max = pause_max;
    }


    public boolean isSatisfied(GregorianCalendar d,
                               GregorianCalendar next_date) {
        GregorianCalendar date = (GregorianCalendar) d.clone();
        date.add(Calendar.MINUTE, first.duration);
        GregorianCalendar lim_min = (GregorianCalendar) next_date.clone();
        lim_min.add(Calendar.MINUTE, -this.pause_min);

        GregorianCalendar lim_max = (GregorianCalendar) next_date.clone();
        lim_max.add(Calendar.MINUTE, -this.pause_max);

        System.out.println(sdf.format(
                lim_max.getTime()) +
                " <= " +
                sdf.format(date.getTime()) +
                " <= " +
                sdf.format(lim_min.getTime()) +
                " ? ");

        return is_equal_or_less(lim_max, date) & is_equal_or_less(date, lim_min);
    }
}
