package ui;

import domain.GDate;

public class Main {
    public static void main(String[] args){
        //constructors
        GDate date1 = new GDate();
        System.out.println("GDate():\t\t" + date1);
        GDate date2 = new GDate(2000,1,1);
        System.out.println("GDate(2000,1,1):" + date2);
        GDate date3 = new GDate(date1);
        System.out.println("GDate(date1):\t" + date3);
        GDate date4 = new GDate(2451545);
        System.out.println("GDate():" + date4);
        GDate date5 = date1.copy();
        System.out.println("date1.copy:" + date5);
        GDate date6 = new GDate(2000,1,2);
        GDate date7 = new GDate(2001,1,1);
        GDate date8 = new GDate(2002,1,1);




        //comparisons
        System.out.print(date1);
        System.out.print(date1.equals(date3) ? " = " : " != ");
        System.out.println(date3);

        System.out.print(date3);
        System.out.print(date1.equals(date3) ? " != " : " = ");
        System.out.println(date6);

        System.out.print(date3);
        System.out.print(date1.greaterThan(date3) ? " > " : " <= ");
        System.out.println(date6);

        System.out.print(date6);
        System.out.print(date6.greaterThan(date3) ? " > " : " <= ");
        System.out.println(date3);

        System.out.print(date7);
        System.out.print(" - ");
        System.out.print(date3);
        System.out.print(" = ");
        System.out.println(date7.diff(date3) + " days ");

        System.out.print(date8);
        System.out.print(" - ");
        System.out.print(date7);
        System.out.print(" = ");
        System.out.println(date8.diff(date7) + " days ");

        System.out.print(date1);
        System.out.print(" + 60 days");
        System.out.print(" = ");
        System.out.println(date1.add(60));

        System.out.print(date7);
        System.out.print(" + 60 days");
        System.out.print(" = ");
        System.out.println(date7.add(60));

        System.out.print("Year of ");
        System.out.print(date1);
        System.out.print(" = ");
        System.out.println(date1.year());

        System.out.print("Month of ");
        System.out.print(date1);
        System.out.print(" = ");
        System.out.println(date1.month());

        System.out.print("Day of ");
        System.out.print(date1);
        System.out.print(" = ");
        System.out.println(date1.day());

        System.out.print("JDN of ");
        System.out.print(date1);
        System.out.print(" = ");
        System.out.println(date1.julianDay());

    }
}
