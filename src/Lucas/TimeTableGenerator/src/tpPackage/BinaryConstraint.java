package tpPackage;

abstract class BinaryConstraint implements Constraint{
	
	protected Activity first;
	protected Activity second;
	
	public BinaryConstraint(Activity first,Activity second) {
		this.first = first;
		this.second = second;
	}
	
	abstract boolean isSatisfied(int date1,int date2);

	
	public boolean isSatisfied(Schedule schedule){
		int date1 = schedule.hmap.get(this.first);
		int date2 = schedule.hmap.get(this.second);
		return isSatisfied(date1,date2);
	}
	
	
}
