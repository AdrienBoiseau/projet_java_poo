package timetable_generator;

import java.util.GregorianCalendar;

public class Timeslot {
    private GregorianCalendar start = new GregorianCalendar();
    private GregorianCalendar end = new GregorianCalendar();
    private String wording;

    public Timeslot(String wording) {
        this.wording = wording;
    }

    public void setStart(int hour, int minute,int day,int month, int year) {
        this.start.set(start.HOUR,hour);
        this.start.set(start.MINUTE,minute);
        this.start.set(start.DAY_OF_MONTH,day);
        this.start.set(start.MONTH,month);
        this.start.set(start.YEAR,year);
    }

    public void setEnd(int hour, int minute, int day,int month, int year) {
        this.end.set(end.HOUR,hour);
        this.end.set(end.MINUTE,minute);
        this.end.set(end.DAY_OF_MONTH,day);
        this.end.set(end.MONTH,month);
        this.end.set(end.YEAR,year);
    }

    public String AM_PM(int b) {
        if (b == 0){
            return "AM";
        }
        else {
            return "PM";
        }
    }

    public Boolean overlaps(Timeslot date2){
        /*
        * Méthode toCompare(GregorianCalendar)
        * 0 : deux dates égales
        * -1 : dates2 après cet objet
        * 1 : date2 avant cet objet
        * Fonction d'origine :
        *
        if (this.start < t.start){
            return (this.end >= t.start);
        } else {
            return (t.end >= this.start);
        }
        */
        if (this.start.compareTo(date2.start)<0) {
            return (this.end.compareTo(date2.start)>=0);
        } else {
            return (date2.end.compareTo(this.start) >= 0);
        }


    }

    public GregorianCalendar getStart() {
        return start;
    }

    public GregorianCalendar getEnd() {
        return end;
    }

    @Override
    public String toString() {

        return  wording +
                " du " +
                this.start.get(this.start.DAY_OF_MONTH) + "." +
                this.start.get(this.start.MONTH) + "." +
                this.start.get(this.start.YEAR) + " " +
                this.start.get(this.start.HOUR) + ":" +
                this.start.get(this.start.MINUTE) + AM_PM(this.start.get(this.start.AM)) +
                " au " +
                this.end.get(this.end.DAY_OF_MONTH) + "." +
                this.end.get(this.end.MONTH) + "." +
                this.end.get(this.end.YEAR) + " " +
                this.end.get(this.end.HOUR) + ":" +
                this.end.get(this.end.MINUTE) + AM_PM(this.start.get(this.start.AM_PM));
    }
}
