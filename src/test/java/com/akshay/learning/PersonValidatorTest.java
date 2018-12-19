package com.akshay.learning;

import io.vavr.collection.List;
import io.vavr.collection.Seq;
import io.vavr.control.Validation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonValidatorTest {
    @Test
    public void whenValidationWorks_thenCorrect() {
        PersonValidator personValidator = new PersonValidator();

        Validation<Seq<String>, Person> valid =
                personValidator.validatePerson("John Doe", 30);

        Validation<Seq<String>, Person> invalid =
                personValidator.validatePerson("John? Doe!4", -1);


        assertEquals(
                "Valid(Person{name='John Doe', age=30})",
                valid.toString());

        assertEquals(
                "Invalid(List(Invalid characters in name: ?!4, Age must be at least 0))",
                invalid.toString()
        );
    }

}