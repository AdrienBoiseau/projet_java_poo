package chris.activity;

import java.util.Calendar;
import java.util.GregorianCalendar;

class TimeSlot {
    private GregorianCalendar begin, end;
    private String label;

    TimeSlot(GregorianCalendar begin, GregorianCalendar end, String label) {
        setBegin(begin);
        setEnd(end);
        setLabel(label);
    }

    static private String get_date_repr(GregorianCalendar date) {
        return String.format("%d/%d/%d à %dh",
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH) + 1,
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY));
    }

    static boolean overlaps(TimeSlot t1,
                            TimeSlot t2) {
        return (is_date_between(t2.getBegin(), t1.getBegin(), t1.getEnd()) |
                is_date_between(t2.getEnd(), t1.getBegin(), t1.getEnd()) |
                is_date_between(t1.getBegin(), t2.getBegin(), t2.getEnd()) |
                is_date_between(t1.getEnd(), t2.getBegin(), t2.getEnd()));
    }

    static private boolean is_equal_or_less(GregorianCalendar date,
                                            GregorianCalendar limit) {
        return (date.get(Calendar.YEAR) <= limit.get(Calendar.YEAR) &
                date.get(Calendar.DAY_OF_YEAR) <= limit.get(Calendar.DAY_OF_YEAR) &
                date.get(Calendar.HOUR_OF_DAY) <= limit.get(Calendar.HOUR_OF_DAY));
    }

    static private boolean is_date_between(GregorianCalendar date,
                                           GregorianCalendar inf_lim,
                                           GregorianCalendar sup_lim) {
        return is_equal_or_less(inf_lim, date) & is_equal_or_less(date, sup_lim);
    }

    String get_repr() {
        return String.format("%s - %s : %s",
                get_date_repr(this.begin),
                get_date_repr(this.end),
                this.label);
    }

    private GregorianCalendar getBegin() {
        return begin;
    }

    private void setBegin(GregorianCalendar begin) {
        this.begin = begin;
    }

    private GregorianCalendar getEnd() {
        return end;
    }

    private void setEnd(GregorianCalendar end) {
        this.end = end;
    }

    private void setLabel(String label) {
        this.label = label;
    }
}
