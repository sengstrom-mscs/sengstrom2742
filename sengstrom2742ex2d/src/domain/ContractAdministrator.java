package domain;

import java.time.LocalDateTime;

public class ContractAdministrator extends Administrator {
    private double monthlyRate;

    public ContractAdministrator(int personId, String firstName, String lastName, String userName,
                                 LocalDateTime birthDate, String ssn, String phone, LocalDateTime employmentStartDate, double monthlyRate) {
        super(personId, firstName, lastName, userName, birthDate, ssn, phone, employmentStartDate);
        this.monthlyRate = monthlyRate;
    }

    @Override
    public double calGrossPay() {
        return monthlyRate;
    }

    public double calcGrossPay(){
        return monthlyRate;
    }

    @Override
    public String toString() {
        return super.toString() +
                "ContractAdministrator{" +
                "monthlyRate=" + monthlyRate +
                '}';
    }

    public double getMonthlyRate() {
        return monthlyRate;
    }

    public void setMonthlyRate(double monthlyRate) {
        this.monthlyRate = monthlyRate;
    }
}