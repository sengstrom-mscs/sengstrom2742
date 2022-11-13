package ui;

import domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Main {
    public static void main (String[] args){
        Person person = new Person(101, "Bob", "Shield", "bob.shield");
//        System.out.println(person);
        Tenant tenant = new Tenant(102, "Bob", "Shield", "bob.shield", LocalDateTime.now(),
                "111-111-1111", "111-111-1111","Bob's Manufacturing",
                "Owner",15000.00, LocalDateTime.now());
//        System.out.println(tenant);

//        Administrator administrator = new Administrator(103, "Greg", "Field", "greg.field",
//                LocalDateTime.now(), "333-333-3333", "444-444-4444", LocalDateTime.now());
//        System.out.println(administrator);

        ContractAdministrator contractAdministrator = new ContractAdministrator(104,"Carol", "Sugar", "carol.sugar",
                LocalDateTime.now(), "555-555-5555", "666-666-6666", LocalDateTime.now(), 9000.00);
        System.out.println(contractAdministrator);

        HourlyAdministrator hourlyAdministrator = new HourlyAdministrator(105, "Jon", "Carter", "jon.carter",
                LocalDateTime.now(), "777-777-7777", "888-888-8888", LocalDateTime.now(), 50.00);


        hourlyAdministrator.addTimeCard(LocalDateTime.of(2022, 10,22, 8,0),
                LocalDateTime.of(2022, 10,22, 18,0));
        hourlyAdministrator.addTimeCard(LocalDateTime.of(2022, 10,23, 8,0),
                LocalDateTime.of(2022, 10,23, 18,0));
        hourlyAdministrator.addTimeCard(LocalDateTime.of(2022, 10,24, 8,0),
                LocalDateTime.of(2022, 10,24, 18,0));
        hourlyAdministrator.addTimeCard(LocalDateTime.of(2022, 10,25, 8,0),
                LocalDateTime.of(2022, 10,25, 18,0));
        hourlyAdministrator.addTimeCard(LocalDateTime.of(2022, 10,26, 8,0),
                LocalDateTime.of(2022, 10,26, 18,0));


        ArrayList<Person> people = new ArrayList<Person>();
        people.add(person);
        people.add(tenant);
        people.add(contractAdministrator);
        people.add(hourlyAdministrator);

        double totalGrossPay = 0.0;
        for (Person p :people){
            System.out.println(p);
            if (p instanceof Administrator)
                totalGrossPay += ((Administrator)p).calcGrossPay();
        }

        ArrayList<TimeCard> timeCards = hourlyAdministrator.getTimeCards();
        for (TimeCard timeCard: timeCards){
            System.out.println("\t" + timeCard);
        }
        System.out.println("\nTotal payroll:" + String.format("2f", totalGrossPay));

        ArrayList<Administrator>administrators = new ArrayList<Administrator>();
        administrators.add(contractAdministrator);
        administrators.add(hourlyAdministrator);

        for (Administrator a : administrators){
            totalGrossPay += a.calcGrossPay();
        }
        System.out.println("Total payroll: " + String.format("0.2f", totalGrossPay));
    }
}