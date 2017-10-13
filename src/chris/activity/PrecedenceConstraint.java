package hello;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class PrecedenceConstraint {

    Activity first;
    Activity second;

    PrecedenceConstraint(Activity f, Activity s){
        this.first = f;
        this.second = s;
    }

    static private boolean is_equal_or_less(GregorianCalendar date,
                                            GregorianCalendar limit){
        return (date.get(Calendar.YEAR) <= limit.get(Calendar.YEAR) &
                date.get(Calendar.DAY_OF_YEAR) <= limit.get(Calendar.DAY_OF_YEAR)&
                date.get(Calendar.HOUR_OF_DAY) <= limit.get(Calendar.HOUR_OF_DAY));
    }
    boolean isSatisfied(GregorianCalendar date1,
                        GregorianCalendar date2){
        return is_equal_or_less(date1, date2);
    }


}
