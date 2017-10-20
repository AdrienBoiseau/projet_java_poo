package timetable_generator;

public class Activity {
    String naming;
    int duration;

    public Activity(String naming, int duration) {
        this.naming = naming;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "timetable_generator.Activity{" +
                "naming='" + naming + '\'' +
                ", duration=" + duration +
                '}';
    }
}
