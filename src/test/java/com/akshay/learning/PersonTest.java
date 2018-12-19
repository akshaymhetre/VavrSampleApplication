package com.akshay.learning;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.vavr.API.*;
import static io.vavr.API.Match.*;
import static java.time.LocalDate.now;
import static org.junit.jupiter.api.Assertions.*;
import static com.akshay.learning.PatternsForUDC.*;

class PersonTest {
    /*@Test
    public void unapplyPerson(){
        Person akshay = new Person("akshay", 31);
        Match(akshay).of(
                Case(Person($(""), $(2)), () -> ""),
                Case($(), () -> "")
        );

    }

    @Test
    public void givenObject_whenDecomposesVavrWay_thenCorrect2() {
        LocalDate date = LocalDate.of(2017, 2, 13);
        LocalDate localDate = now();

        String result = Match(localDate).of(
                Case(LocalDate(2016, 2, 13), () -> "2016-02-13"),
                Case(LocalDate(2016, $(), $_), m -> "month " + m + " in 2016"),
                Case(LocalDate($(2016), $(), $_), (y, m) ->
                        "month " + m + " in " + y
                ),
                Case($_, () -> "(catch all)")
        );

        assertEquals("month 2 in 2017",result);
    }
*/
}