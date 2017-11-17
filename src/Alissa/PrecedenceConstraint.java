import java.util.ArrayList;

public class PrecedenceConstraint extends BinaryConstraint implements Constraint {
	
	public PrecedenceConstraint(Activity first, Activity second) {
		super(first, second);
	}
	
	public boolean isSatisfied(int date1, int date2) {
		System.out.println(date1);
		System.out.println(date2);
		if(date1*60 + first.getDuree() <= date2*60){
			return true;
		}
		return false;	
	}
}
