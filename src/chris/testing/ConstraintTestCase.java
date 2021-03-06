package chris.testing;

import chris.activity.BinaryConstraint;

import java.util.GregorianCalendar;

public class ConstraintTestCase {

    private final BinaryConstraint constraint;
    private final GregorianCalendar date_1;
    private final GregorianCalendar date_2;
    private final boolean expectedTo;

    public ConstraintTestCase(BinaryConstraint c, GregorianCalendar d1, GregorianCalendar d2, Boolean expectedTo) {
        this.constraint = c;
        this.date_1 = d1;
        this.date_2 = d2;
        this.expectedTo = expectedTo;
    }

    boolean run_test_case() {
        return constraint.isSatisfied(date_1, date_2);
    }

    boolean isExpectedTo() {
        return expectedTo;
    }
}
