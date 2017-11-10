package chris.testing;

import chris.activity.PrecedenceConstraint;
import chris.activity.Schedule;

import java.util.ArrayList;

public class ScheduleConstraintTestCase {

    private final boolean expectedTo;
    private final Schedule schedule;
    private final ArrayList<PrecedenceConstraint> constraints;


    public ScheduleConstraintTestCase(Schedule schedule, ArrayList<PrecedenceConstraint> constraints, boolean result_expected) {
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
