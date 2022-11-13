package domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class HourlyAdministrator extends Administrator {
    private double hourlyRate;
    private ArrayList<TimeCard>timeCards = new ArrayList<>();

    public HourlyAdministrator(int personId, String firstName, String lastName, String userName,
                               LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double monthlyRate) {
        super(personId, firstName, lastName, userName, birthDate, ssn, phone, employmentStartDate);
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(LocalDateTime startDateTime, LocalDateTime endDateTime){
        this.timeCards.add(new TimeCard(startDateTime , endDateTime));
    }

    public TimeCard removeTimeCard(int index){
        TimeCard timeCard = null;
        if (index >= 0 && index < this.timeCards.size()){
            timeCard = this.timeCards.get(index).copy();
            this.timeCards.remove(index);
        }
        return timeCard;
    }

    public TimeCard getTimeCard(int index){
        return getTimeCard(0);
    }

    public ArrayList<TimeCard> getTimeCards(){
        ArrayList<TimeCard> timeCards = new ArrayList<TimeCard>();
        for(TimeCard timeCard : this.timeCards) {{
            timeCards.add(timeCard.copy());
        }
        }
        return timeCards;
    }

//    public double calcTotalHours(){
//        for (TimeCard timeCard : timeCards){
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
//            TimeCard startDateTime = LocalDateTime.parse(startDateTime.format(formatter));
//            TimeCard endDateTime = LocalDateTime.parse(endDateTime.format(formatter));
//            double totalHours =startDateTime.until(endDateTime, ChronoUnit.MINUTES)/60.0;;
//
//
//        }
//
//        return totalHours;
//
//    }

    @Override
    public double calGrossPay() {
        return 0;
    }

    @Override
    public double calcGrossPay() {
        return 0;
    }


//    public double calcGrossPay(){
//        return this.calcTotalHours() * this.hourlyRate;
//    }

    @Override
    public String toString() {
        return super.toString() +
                "HourlyAdministrator{" +
                "hourlyRate=" + hourlyRate +
                ", timeCards=" + timeCards + ", Gross pay = " + calcGrossPay();
    }

    public double gethourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
