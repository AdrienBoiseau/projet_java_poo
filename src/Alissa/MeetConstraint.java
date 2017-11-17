public class MeetConstraint extends BinaryConstraint implements Constraint{

	public MeetConstraint(Activity first, Activity second) {
		super(first, second);
	}

	protected boolean isSatisfied(int date1, int date2) {
		return (date2*60 == date1*60 + first.getDuree());
	}
}
