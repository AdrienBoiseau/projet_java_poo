package timetable_generator;

import java.util.GregorianCalendar;

public class PrecedenceConstraint {
    protected Activity first;
    protected Activity second;

    public PrecedenceConstraint(Activity first, Activity second) {
        this.first = first;
        this.second = second;
    }


    public boolean isSatisfied(GregorianCalendar firstDate,GregorianCalendar secondDate) {
        GregorianCalendar test;
        /*
        * Méthode toCompare(GregorianCalendar)
        * 0 : deux dates égales
        * -1 : dates2 après cet objet
        * 1 : date2 avant cet objet
        */
        if (firstDate.compareTo(secondDate) < 0) {
            test = firstDate;
            test.add(test.MINUTE,this.first.duration);
            return (test.compareTo(secondDate) < 0);
        }
        return false;
    }

    public boolean isSatisfied(int firstDate, int secondDate) {
        if (firstDate<secondDate) {
            return (firstDate + (first.duration / 60) < secondDate);
        }
        return false;
    }

    public Activity getFirst() {
        return first;
    }

    public Activity getSecond() {
        return second;
    }
}
