public class MaxSpanConstraint implements Constraint{

	private int timer;
	
	public MaxSpanConstraint(int timer) {
		this.timer = timer;
	}

	@Override
	public boolean isSatisfied(Schedule schedule) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*public boolean isSatisfied(Schedule schedule) {
		if(first.getDuree() + min <= date2*60){
			return true;
		}
		return false;
	}*/
}
