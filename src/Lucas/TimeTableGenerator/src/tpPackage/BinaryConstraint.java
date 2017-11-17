package tpPackage;

abstract class BinaryConstraint {
	
	protected Activity first;
	protected Activity second;
	
	abstract boolean isSatisfied(int date1,int date2);
	
	public void isSatisfied(Schedule schedule){
		int date1 = schedule.hmap.get(this.first);
		int date2 = schedule.hmap.get(this.second);
		isSatisfied(date1,date2);
	}
}
