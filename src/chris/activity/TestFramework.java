package chris.activity;

class TestFramework {

    static private boolean print_test_result(Boolean result) {
        if (result)
            System.out.println("TEST PASSED");
        else
            System.out.println("TEST FAILED");
        return result;
    }

    static boolean run_schedule_constraints_test(ScheduleConstraintTestCase test) {
        boolean constraints_met = test.run_test_case();
        if (constraints_met)
            System.out.println("SCHEDULES SATISFIES CONDITIONS");
        else
            System.out.println("SCHEDULES DOES NOT SATISFIES CONDITIONS");

        return print_test_result(constraints_met == test.isExpectedTo());
    }

    static boolean run_activity_test(ActivityTestCase test) {
        boolean constraints_met = test.run_test_case();
        if (constraints_met)
            System.out.println("ACTIVITY SATISFIES DATES CONSTRAINTS");
        else
            System.out.println("ACTIVITY DOES NOT SATISFIES DATES CONSTRAINTS");
        return print_test_result(constraints_met == test.isExpectedTo());
    }

    static boolean run_constraint_test(ConstraintTestCase test) {
        boolean constraints_met = test.run_test_case();
        if (constraints_met)
            System.out.println("CONSTRAINT SATISFIES DATES CONSTRAINTS");
        else
            System.out.println("CONSTRAINT DOES NOT SATISFIES DATES CONSTRAINTS");
        return print_test_result(constraints_met == test.isExpectedTo());
    }

}
