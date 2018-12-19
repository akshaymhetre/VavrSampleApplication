package com.akshay.learning;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.Tuple3;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;

import java.time.LocalDate;

@Patterns
public class PatternsForUDC {

    @Unapply
    static Tuple2<String, Integer> Person(Person person) {
        return Tuple.of(person.getName(), person.getAge());
    }

    @Unapply
    static Tuple3<Integer, Integer, Integer> LocalDate(LocalDate date) {
        return Tuple.of(
                date.getYear(), date.getMonthValue(), date.getDayOfMonth());

    }
}