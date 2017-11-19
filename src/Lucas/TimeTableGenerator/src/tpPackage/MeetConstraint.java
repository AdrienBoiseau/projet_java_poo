package tpPackage;

public class MeetConstraint extends BinaryConstraint implements Constraint{
	
	public MeetConstraint(Activity first, Activity second) {
		super(first,second);
	}
	
	@Override
	public boolean isSatisfied(int date1,int date2) {
		if (date1 + (first.duree / 60) == date2) {
			return true;
		}
		return false;
	}
}
