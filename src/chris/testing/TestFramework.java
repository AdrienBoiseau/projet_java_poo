package chris.testing;

public class TestFramework {

    public static void run_basic_tests() {
        System.out.println("===Running Schedule tests===");
        BasicTests.run_schedule_tests();

    }

    static private void print_test_result(final Boolean result) {
        if (result)
            System.out.println("TEST PASSED");
        else
            System.out.println("TEST FAILED");
    }

    public static void run_schedule_constraints_test
            (final ScheduleConstraintTestCase test) {
        boolean constraints_met = test.run_test_case();
        if (constraints_met)
            System.out.println("SCHEDULES SATISFIES CONDITIONS");
        else
            System.out.println("SCHEDULES DOES NOT SATISFIES CONDITIONS");

        print_test_result(constraints_met == test.isExpectedTo());
    }

    public static void run_activity_test(final ActivityTestCase test) {
        boolean constraints_met = test.run_test_case();
        if (constraints_met)
            System.out.println("ACTIVITY SATISFIES DATES CONSTRAINTS");
        else
            System.out.println("ACTIVITY DOES NOT SATISFIES DATES CONSTRAINTS");
        print_test_result(constraints_met == test.isExpectedTo());
    }

    public static void run_constraint_test(final ConstraintTestCase test) {
        boolean constraints_met = test.run_test_case();
        if (constraints_met)
            System.out.println("CONSTRAINT SATISFIES DATES CONSTRAINTS");
        else
            System.out.println("CONSTRAINT DOES NOT SATISFIES DATES CONSTRAINTS");
        print_test_result(constraints_met == test.isExpectedTo());
    }

}
