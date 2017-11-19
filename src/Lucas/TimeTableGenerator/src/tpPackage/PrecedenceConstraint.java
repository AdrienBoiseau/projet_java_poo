package tpPackage;

public class PrecedenceConstraint extends BinaryConstraint implements Constraint{
	
	
	public PrecedenceConstraint(Activity first, Activity second) {
		super(first, second);
	}
	
	public boolean isSatisfied(int date1, int date2) {
		float entre = date2 - date1;
		float dureeheure = (float)this.first.duree * 1/60;
		if (dureeheure>entre) {
			return false;
		}
		else {
			return true;
		}
	}

}
