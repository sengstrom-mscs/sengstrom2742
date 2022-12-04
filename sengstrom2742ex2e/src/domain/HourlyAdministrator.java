package domain;

import exceptions.TimeCardIllegalArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class HourlyAdministrator extends Administrator implements Printable, JSONStringifiable{
    private double hourlyRate;
    private ArrayList<TimeCard>timeCards = new ArrayList<>();

    @Override
    public void print() {
        System.out.println("");
    }

    public HourlyAdministrator(int personId, String firstName, String lastName, String userName,
                               LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double hourlyRate) {
        super(personId, firstName, lastName, userName, birthDate, ssn, phone, employmentStartDate);
        this.hourlyRate = hourlyRate;
    }

    public void addTimeCard(LocalDateTime startDateTime, LocalDateTime endDateTime){
       try{
        this.timeCards.add(new TimeCard(startDateTime , endDateTime));}
       catch (TimeCardIllegalArgumentException e){
           throw new TimeCardIllegalArgumentException(
                   "Invalid start/end in TimeCard for personId=" + this.getPersonId() + ", " +e.getMessage());
       }
    }

    public void addTimeCard(int id, LocalDateTime startDateTime, LocalDateTime endDateTime){
        try {
            this.timeCards.add(new TimeCard(id, startDateTime, endDateTime));
        }
        catch (TimeCardIllegalArgumentException e){
            throw new TimeCardIllegalArgumentException(
                    "Invalid start/end in TimeCard for personId=" + this.getPersonId() + ", " +e.getMessage());
        }
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

    public double calcTotalHours() {
        double totalHours = 0.0;
        for (TimeCard timeCard : timeCards) {
            totalHours += timeCard.calcHours();
        }
        return totalHours;
    }

    @Override
    public double calcGrossPay(){return this.calcTotalHours() * this.hourlyRate;}

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

    public String jsonStringify(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        StringBuilder sb = new StringBuilder(200);

        sb.append("{\n");
        sb.append("\"subclass\":\"hourlyAdministrator\",\n");
        sb.append("\"personId\":");
        sb.append(this.getPersonId());
        sb.append(",\n");
        sb.append("\"lastName\":\"");
        sb.append(this.getLastName());
        sb.append("\",\n");
        sb.append("\"firstName\":\"");
        sb.append(this.getFirstName());
        sb.append("\",\n");
        sb.append("\"userName\":\"");
        sb.append(this.getUserName());
        sb.append("\",\n");
        sb.append("\"birthDate\":\"");
        sb.append(formatter.format(this.getBirthDate()));
        sb.append("\",\n");
        sb.append("\"ssn\":\"");
        sb.append(this.getSsn());
        sb.append("\",\n");
        sb.append("\"phone\":\"");
        sb.append(this.getPhone());
        sb.append("\",\n");
        sb.append("\"employmentStartDate\":\"");
        sb.append(formatter.format(this.getEmploymentStartDate()));
        sb.append("\",\n");
        sb.append("\"hourlyRate\":");
        sb.append(this.gethourlyRate());
        sb.append(",\n");
        sb.append("\"timeCards\":[\n");
        for (TimeCard timeCard: timeCards){
            sb.append(timeCard.jsonStringify());
            sb.append(",\n");
        }
        int commaIndex = sb.lastIndexOf(",");
        sb.deleteCharAt(commaIndex);
        sb.append("]\n");
        sb.append("}");

        return sb.toString();

    }
}
