package chris.activity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PrecedenceConstraint {

    Activity first;
    Activity second;

    PrecedenceConstraint(Activity f, Activity s){
        this.first = f;
        this.second = s;
    }

    static private boolean is_equal_or_less(GregorianCalendar date,
                                            GregorianCalendar limit){
        return date.compareTo(limit) <= 0;
    }

    protected boolean isSatisfied(GregorianCalendar date1,
                                  GregorianCalendar date2) {
        SimpleDateFormat sdf = new SimpleDateFormat("d MMM yyyy hh:mm aaa");
        System.out.println(sdf.format(date1.getTime()));
        date1.add(Calendar.MINUTE, this.first.duration);
        System.out.println(sdf.format(date1.getTime()));

        System.out.println(sdf.format(date2.getTime()));
        return is_equal_or_less(date1, date2);
    }


}
