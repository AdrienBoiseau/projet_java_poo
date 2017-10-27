package tpPackage;

public class PrecedenceConstraintWithDuration extends PrecedenceConstraint{
	
	protected int avantMin;
	protected int avantMax;
	
	public PrecedenceConstraintWithDuration(Activity first, Activity second, int avantMin, int avantMax) {
		super(first,second);
		this.avantMin = avantMin;
		this.avantMax = avantMax;
	}
	
	@Override
	public boolean isSatisfied(int date1,int date2){
		int date1min = (date1 * 60) + this.first.duree;
		int date2min = date2 * 60;
		if ((date1min >= (date2min - this.avantMax)) && (date1min <= (date2min - this.avantMin))){
			return true;
		}
		else {
			return false;
		}
	}

}
