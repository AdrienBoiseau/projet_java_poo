package projet;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PrecedenceConstraint {
    Activity first,second;

    public PrecedenceConstraint(Activity first, Activity second) {
        this.first = first;
        this.second = second;
    }

    public boolean isSatisfied(GregorianCalendar date1, GregorianCalendar date2) {
        GregorianCalendar test = date1;
        test.add(Calendar.MINUTE,this.first.duration);
        /*
        * Méthode compareTo :
        *   -1 si l'objet est avant l'argument
        *   0 si les deux dates sont égales
        *   1 si l'objet est après l'argument
        */

        if (test.compareTo(date2) <= 0) {
            return true;
        } return false;
    }

    public boolean isSatisfied(int start, int end) {
        if (start < end) {
            return (start+first.duration<=end);
        } return false;
    }
}
