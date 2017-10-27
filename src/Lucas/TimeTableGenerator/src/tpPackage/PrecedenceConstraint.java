package tpPackage;

public class PrecedenceConstraint {
	
	protected Activity first;
	protected Activity second;
	
	public PrecedenceConstraint(Activity first, Activity second) {
		this.first = first;
		this.second = second;
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
