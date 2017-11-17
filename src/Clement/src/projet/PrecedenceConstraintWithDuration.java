package projet;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PrecedenceConstraintWithDuration extends PrecedenceConstraint {
    int notBefore,notAfter;

    public PrecedenceConstraintWithDuration(Activity first, Activity second, int notBefore, int notAfter) {
        super(first, second);
        this.notBefore = notBefore;
        this.notAfter = notAfter;
    }

    @Override
    public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2) {
        GregorianCalendar borneInf  = date2;
        GregorianCalendar borneSup  = date2;

        GregorianCalendar test = date1;

        borneInf.add(Calendar.MINUTE,-this.notBefore);
        borneSup.add(Calendar.MINUTE,-this.notAfter);

        test.add(Calendar.MINUTE,this.first.duration);
        if ((borneInf.compareTo(test) <= 0) && (borneSup.compareTo(test)>=0)) {
            return true;
        }
        return false;
    }
}
