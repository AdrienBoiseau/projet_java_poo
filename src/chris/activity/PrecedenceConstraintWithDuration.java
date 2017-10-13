package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static chris.activity.Main.sdf;

class PrecedenceConstraintWithDuration extends PrecedenceConstraint {

    int pause_min = 0;
    int pause_max = 0;


    PrecedenceConstraintWithDuration(Activity a1, Activity a2, int pause_min, int pause_max) {
        super(a1, a2);
        //pause en minutes
        this.pause_min = pause_min;
        this.pause_max = pause_max;
    }


    protected boolean isSatisfied(GregorianCalendar date,
                                  GregorianCalendar next_date) {
        System.out.println(sdf.format(date.getTime()));
        date.add(Calendar.MINUTE, first.duration);
        System.out.println(sdf.format(date.getTime()));
        GregorianCalendar lim_min = (GregorianCalendar) next_date.clone();
        lim_min.add(Calendar.MINUTE, -this.pause_min);

        GregorianCalendar lim_max = (GregorianCalendar) next_date.clone();
        lim_max.add(Calendar.MINUTE, -this.pause_max);

        System.out.println(sdf.format(lim_min.getTime()) + " <= " + sdf.format(date.getTime()) + " <= " + sdf.format(lim_max.getTime()) + " ? ");

        return is_equal_or_less(lim_max, date) & is_equal_or_less(date, lim_min);
    }



}
