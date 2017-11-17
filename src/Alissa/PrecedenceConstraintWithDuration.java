public class PrecedenceConstraintWithDuration extends PrecedenceConstraint implements Constraint{

	private int min;
	private int max;
	
	public PrecedenceConstraintWithDuration(Activity first, Activity second, int min, int max) {
		super(first, second);
		this.min = min;
		this.max = max;
	}
	
	public boolean isSatisfied(int date1, int date2) {
		if(date1*60 + first.getDuree() + max >= date2*60  && date1*60 + first.getDuree() + min <= date2*60 ){
			return true;
		}
		return false;	
	}
}
