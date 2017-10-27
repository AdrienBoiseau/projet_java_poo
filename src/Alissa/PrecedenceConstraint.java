public class PrecedenceConstraint {

	protected Activity first;
	protected Activity second;
	
	public PrecedenceConstraint(Activity first, Activity second) {
		this.first = first;
		this.second = second;
	}

	public Activity getFirst() {
		return first;
	}

	public void setFirst(Activity first) {
		this.first = first;
	}

	public Activity getSecond() {
		return second;
	}

	public void setSecond(Activity second) {
		this.second = second;
	}
	
	public boolean isSatisfied(int date1, int date2) {
		if(date1*60 + first.getDuree() <= date2*60){
			System.out.println(date1*60 + first.getDuree());
			return true;
		}
		return false;	
	}
}
