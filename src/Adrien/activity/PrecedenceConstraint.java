import java.util.GregorianCalendar;
import java.util.Calendar;
public class PrecedenceConstraint {
    Activity first;
    Activity second;

    PrecedenceConstraint(Activity first, Activity second){
        this.first = first;
        this.second = second;
    }

    static private boolean is_equal_or_less(GregorianCalendar date, GregorianCalendar limit){
        return (date.compareTo(limit))<= 0;
    }
    boolean isSatisfied(GregorianCalendar date1,
                        GregorianCalendar date2){
        return is_equal_or_less(date1, date2);
    }
}
