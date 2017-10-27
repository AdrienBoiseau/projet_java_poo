package tpPackage;

public class TimeSlot {
	
	private int HorDeb;
	private int HorFin;
	private String lib;
	
	public TimeSlot(int HorDeb,int HorFin,String lib){
		this.HorDeb = HorDeb;
		this.HorFin = HorFin;
		this.lib = lib;
	}
	
	public void repr() {
		System.out.println("Cours de "+ this.lib + " de " + this.HorDeb + " à " + this.HorFin);
	}
	
	public boolean overslaps(TimeSlot arg){
		if (((arg.HorDeb < this.HorDeb) && (arg.HorFin < this.HorDeb))||((arg.HorFin > this.HorFin) && (arg.HorFin > this.HorFin))){
			return true;
		}
		return false;
	}
}
