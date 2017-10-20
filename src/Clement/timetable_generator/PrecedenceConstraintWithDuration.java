package timetable_generator;

import java.util.GregorianCalendar;
import java.util.Calendar;

public class PrecedenceConstraintWithDuration extends PrecedenceConstraint{
    protected int notBefore;
    protected int notAfter;

    public PrecedenceConstraintWithDuration(Activity first, Activity second, int notBefore, int notAfter) {
        super(first, second);
        this.notBefore = notBefore;
        this.notAfter = notAfter;
    }

    @Override
    public boolean isSatisfied(GregorianCalendar firstDate, GregorianCalendar secondDate) {
        GregorianCalendar borneInf;
        GregorianCalendar borneSup;
        GregorianCalendar test;
        /*
        * Méthode toCompare(GregorianCalendar)
        * 0 : deux dates égales
        * -1 : dates2 après cet objet
        * 1 : date2 avant cet objet
        */

        borneInf = secondDate;
        borneInf.add(Calendar.MINUTE,-this.notBefore);

        borneSup = secondDate;
        borneSup.add(Calendar.MINUTE,-this.notAfter);

        test = firstDate;
        test.add(Calendar.MINUTE,this.first.duration);

        if (firstDate.compareTo(secondDate) < 0) {
            return ((test.compareTo(borneInf)>=0) && (test.compareTo(borneSup) <= 0));
        }
        return false;
    }
}
