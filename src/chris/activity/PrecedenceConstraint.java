package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static chris.activity.Main.sdf;

public class PrecedenceConstraint {

    Activity first;
    Activity second;

    PrecedenceConstraint(Activity f, Activity s){
        this.first = f;
        this.second = s;
    }

    static boolean is_equal_or_less(GregorianCalendar date,
                                    GregorianCalendar limit) {

        return date.compareTo(limit) <= 0;
    }

    protected boolean isSatisfied(GregorianCalendar date1,
                                  GregorianCalendar date2) {
        date1.add(Calendar.MINUTE, this.first.duration);
        System.out.println(sdf.format(date1.getTime()) + "<=" + sdf.format(date2.getTime()) + "?");

        return is_equal_or_less(date1, date2);
    }


}
