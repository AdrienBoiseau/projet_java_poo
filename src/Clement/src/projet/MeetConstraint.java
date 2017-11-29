package projet;

public class MeetConstraint extends BinaryConstraint implements Constraint{
    public MeetConstraint(Activity first, Activity second) {
        this.first = first;
        this.second = second;
    }

    @Override
    boolean isSatisfied(int date1, int date2) {
        return (date1 + (first.duration / 60) == date2);
    }
}
