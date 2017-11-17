package chris.testing;

import chris.activity.Constraint;
import chris.activity.Schedule;

import java.util.Collection;

public class ScheduleConstraintTestCase {

    private final boolean expectedTo;
    private final Schedule schedule;
    private final Collection<Constraint> constraints;


    public ScheduleConstraintTestCase(Schedule schedule,
                                      Collection<Constraint>
                                              constraints, boolean result_expected) {
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
