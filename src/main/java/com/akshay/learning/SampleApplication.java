package com.akshay.learning;

import java.time.LocalDate;

import static io.vavr.API.*;
import static io.vavr.API.*;
import static java.time.LocalDate.now;

public class SampleApplication {
    public static void main(String[] args) {
        System.out.println("Hello World");

        /*LocalDate localDate = now();

        LocalDate of = LocalDate.of(2016, 2, 13);
        String result3 = Match(localDate).of(
                Case(of, () -> "2016-02-13"),
                Case(LocalDate(2016, $(), $()), m -> "month " + m + " in 2016"),
                Case(LocalDate($(2016), $(), $()), (y, m) -> "month " + m + " in " + y),
                Case($(), () -> "(catch all)")
        );
        System.out.println(result3);*/
    }
}
