package ui;

import java.util.ArrayList;

import ui.dataaccess.SensorReadingJSONParser;
import domain.Sensor;
import domain.SensorReading;

public class Main {
    public static void main(String[] args) {
        ArrayList<SensorReading> sensorReadings = new ArrayList<SensorReading>();
        Sensor sensor = new Sensor(2,1,1,"Heat Register");

        try {
            SensorReadingJSONParser.readFile("sengstrom2742ex2f/resources/readings.json");
            sensor.setSensorReadings(SensorReadingJSONParser.getSensorReadings());
        } catch (Exception e) {
            System.out.println(e);
        }

        int index = sensor.findMinReadingIndex();
        System.out.println("\nMin: index = " + index +", "+
                sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex();
        System.out.println("\nMax: index = " + index +", "+
                sensor.getSensorReadings().get(index).toString());

        index = sensor.findMinReadingIndex(84,95);
        System.out.println("\nMin: index = " + index +", "+
                sensor.getSensorReadings().get(index).toString());
        index = sensor.findMaxReadingIndex(8,18);
        System.out.println("\nMax: index = " + index +", "+
                sensor.getSensorReadings().get(index).toString());

        //Invalid indexes
        try {
            index = sensor.findMinReadingIndex(-1, 95);
        }catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }
        try {
            index = sensor.findMaxReadingIndex(8,1000);
        }
        catch (IndexOutOfBoundsException e){
            System.out.println(e);
        }

        int prevIndex = 0;
        index = sensor.findNextCycleMaxIndex(0);
        while (index > prevIndex){
            prevIndex = index;
            System.out.println("Index: " + ", Max: " + sensor.getSensorReading(index));
            index = sensor.findNextCycleMinIndex(index);
            if(index > prevIndex)
                System.out.println("Index: " + index + ", Min: " + sensor.getSensorReading(index));
            index = sensor.findNextCycleMaxIndex(index);
        }
    }
}
