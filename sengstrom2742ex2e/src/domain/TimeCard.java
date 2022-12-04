package domain;

import exceptions.TimeCardIllegalArgumentException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class TimeCard implements JSONStringifiable{
    private int timeCardId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public TimeCard (LocalDateTime startDateTime, LocalDateTime endDateTime){
        this.timeCardId = DbContext.getNextTimeCardId();
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public TimeCard(int timeCardId, LocalDateTime startDateTime, LocalDateTime endDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
        this.timeCardId = timeCardId;
        if(startDateTime.isEqual(endDateTime) || startDateTime.isAfter(endDateTime))
            throw new TimeCardIllegalArgumentException(
                    "Start: " + formatter.format(startDateTime)
                    + ", End: " + formatter.format(endDateTime)
            );
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;

    }
    public TimeCard(TimeCard timeCard){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
        this.timeCardId = timeCard.timeCardId;
        if(startDateTime.isEqual(endDateTime) || startDateTime.isAfter(endDateTime))
            throw new TimeCardIllegalArgumentException(
                    "Start: " + formatter.format(startDateTime)
                            + ", End: " + formatter.format(endDateTime)
            );
        this.startDateTime = timeCard.startDateTime;
        this.endDateTime = timeCard.endDateTime;

    }


    public int getTimeCardId() {
        return timeCardId;
    }

    public void setTimeCardId(int timeCardId) {
        this.timeCardId = timeCardId;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }



    public TimeCard copy(){return new TimeCard(this.timeCardId, this.startDateTime, this.endDateTime);}

    public double calcHours(){return this.startDateTime.until(this.endDateTime, ChronoUnit.MINUTES)/60.0;}


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
        return "Id= " + timeCardId +
                ", startDateTime= " + startDateTime.format(formatter) +
                ", endDateTime= " + endDateTime.format(formatter) +
                ", hours= " +  this.calcHours();
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof TimeCard)) return false;
        TimeCard timeCard = (TimeCard) o;
        return timeCardId == timeCard.timeCardId &&
                Objects.equals(startDateTime, timeCard.startDateTime) &&
                Objects.equals(endDateTime, timeCard.endDateTime);
    }

    @Override
    public int hashCode(){return Objects.hash(timeCardId, startDateTime, endDateTime);}

    public String jsonStringify(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mma");
        StringBuilder sb = new StringBuilder(200);

        sb.append("{\"id\":");
        sb.append(this.timeCardId);
        sb.append(",\"startDateTime\":\"");
        sb.append(formatter.format(this.startDateTime));
        sb.append("\",\"endDateTime\":\"");
        sb.append(formatter.format(this.endDateTime));
        sb.append("\"}");

        return sb.toString();
    }

}