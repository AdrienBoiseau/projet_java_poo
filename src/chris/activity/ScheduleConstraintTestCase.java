package chris.activity;

import java.util.ArrayList;

class ScheduleConstraintTestCase {

    private final boolean expectedTo;
    private final Schedule schedule;
    private final ArrayList<PrecedenceConstraint> constraints;


    ScheduleConstraintTestCase(Schedule schedule, ArrayList<PrecedenceConstraint> constraints, boolean result_expected) {
        this.expectedTo = result_expected;
        this.schedule = schedule;
        this.constraints = constraints;
    }

    boolean run_test_case() {
        return schedule.satisfies(constraints);
    }

    boolean isExpectedTo() {
        return expectedTo;
    }
}
