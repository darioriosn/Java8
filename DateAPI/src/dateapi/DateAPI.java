/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dateapi;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Dario
 */
public class DateAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // TODO code application logic here
        //Fecha actual
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.toString());

        System.out.println(LocalDate.of(2015, 02, 20));

        System.out.println(LocalDate.parse("2015-02-20"));

        LocalDate tomorrow = LocalDate.now().plusDays(1);
        System.out.println(tomorrow.toString());

        //Año bisiesto
        boolean leapYear = LocalDate.now().isLeapYear();
        System.out.println(leapYear ? "Ese año fue bisiesto" : "Ese año no fue bisiesto");

        LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
        System.out.println(previousMonthSameDay.toString());

        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalTime sixThirty = LocalTime.parse("06:30");
        System.out.println(sixThirty.toString());

        System.out.println(LocalDateTime.now());

        System.out.println(LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30));
        System.out.println(LocalDateTime.parse("2015-02-20T06:30:00"));

        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime.plusDays(1));
        System.out.println(localDateTime.minusHours(2));

        System.out.println(localDateTime.getMonth());

        Instant start = Instant.now();
        //Manejo de tiempos     
        for (int i = 0; i < 100; i++) {
            System.out.println("Hola a todos");
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        long seconds = (timeElapsed / 1000) % 60;
        System.out.println("Tiempo:" + seconds);

    }

}
