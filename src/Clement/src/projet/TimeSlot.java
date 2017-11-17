package projet;

import java.util.GregorianCalendar;

public class TimeSlot {
    GregorianCalendar startDate,endDate;
    String name;

    public TimeSlot(GregorianCalendar startDate, GregorianCalendar endDate, String name) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
    }

    public Boolean overlaps(TimeSlot arg){
        if (this.startDate.compareTo(arg.startDate)<0) {
            return (this.endDate.compareTo(arg.startDate)>=0);
        } else {
            return (arg.endDate.compareTo(this.startDate) >= 0);
        }
    }

    public String AM_PM(int b) {
        if (b == 0){
            return "AM";
        }
        else {
            return "PM";
        }
    }

    public String getRepresentation() {
        return  name +
                " du " +
                this.startDate.get(this.startDate.DAY_OF_MONTH) + "." +
                this.startDate.get(this.startDate.MONTH) + "." +
                this.startDate.get(this.startDate.YEAR) + " " +
                this.startDate.get(this.startDate.HOUR) + ":" +
                this.startDate.get(this.startDate.MINUTE) + AM_PM(this.startDate.get(this.startDate.AM)) +
                " au " +
                this.endDate.get(this.endDate.DAY_OF_MONTH) + "." +
                this.endDate.get(this.endDate.MONTH) + "." +
                this.endDate.get(this.endDate.YEAR) + " " +
                this.endDate.get(this.endDate.HOUR) + ":" +
                this.endDate.get(this.endDate.MINUTE) + AM_PM(this.endDate.get(this.endDate.AM_PM));
    }


}
