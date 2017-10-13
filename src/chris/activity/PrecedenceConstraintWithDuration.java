package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

class PrecedenceConstraintWithDuration extends PrecedenceConstraint {

    int pause_min = 0;
    int pause_max = 0;


    PrecedenceConstraintWithDuration(Activity a1, Activity a2, int pause_max, int pause_min) {
        super(a1, a2);
        //pause en minutes
        this.pause_min = pause_min;
        this.pause_max = pause_max;
    }

    static private boolean is_equal_or_less(GregorianCalendar date,
                                            GregorianCalendar limit) {
        return date.compareTo(limit) <= 0;
    }

    protected boolean isSatisfied(GregorianCalendar date,
                                  GregorianCalendar next_date) {
        date.add(Calendar.MINUTE, first.duration);

        int min_min_shift = this.pause_min % 60;
        int min_hour_shift = this.pause_min / 60 % 24;
        int min_day_shift = this.pause_min / 60 / 24;

        int max_min_shift = this.pause_min % 60;
        int max_hour_shift = this.pause_min / 60 % 24;
        int max_day_shift = this.pause_min / 60 / 24;

        GregorianCalendar lim_min = (GregorianCalendar) next_date.clone();
        lim_min.add(Calendar.DAY_OF_YEAR, -min_day_shift);
        lim_min.add(Calendar.HOUR_OF_DAY, -min_hour_shift);
        lim_min.add(Calendar.MINUTE, -min_min_shift);

        GregorianCalendar lim_max = (GregorianCalendar) next_date.clone();
        lim_max.add(Calendar.DAY_OF_YEAR, max_day_shift);
        lim_max.add(Calendar.HOUR_OF_DAY, max_hour_shift);
        lim_max.add(Calendar.MINUTE, max_min_shift);


        return is_equal_or_less(date, lim_max) & is_equal_or_less(lim_min, date);
    }



}
