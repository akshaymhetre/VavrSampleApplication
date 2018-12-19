package com.akshay.learning;

import io.vavr.*;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;
import java.util.function.DoubleToIntFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.vavr.API.*;
import static io.vavr.Predicates.isIn;
import static org.junit.jupiter.api.Assertions.*;

class SampleApplicationTest {
    @Test
    void sampleTest(){
        System.out.println("123");
    }

    @Test
    public void givenValue_whenCreatesOption_thenCorrect() {
        Option<Object> noneOption = Option.of(null);
        Option<Object> someOption = Option.of("val");

        assertEquals("None", noneOption.toString());
        assertEquals("Some(val)", someOption.toString());
    }

    @Test
    public void givenNull_whenCreatesOption_thenCorrect() {
        String name = null;
        Option<String> nameOption = Option.of(name);

        assertEquals("akshay", nameOption.getOrElse("akshay"));
    }

    @Test
    public void whenCreatesTuple_thenCorrect1() {
        Tuple2<String, Integer> java8 = Tuple.of("Java", 8);
        String element1 = java8._1;
        int element2 = java8._2();

        assertEquals("Java", element1);
        assertEquals(8, element2);
    }

    @Test
    public void whenCreatesTuple_thenCorrect2() {
        Tuple3<String, Integer, Double> java8 = Tuple.of("Java", 8, 1.8);
        String element1 = java8._1;
        int element2 = java8._2();
        double element3 = java8._3();

        assertEquals("Java", element1);
        assertEquals(8, element2);
        assertEquals(1.8, element3, 0.1);
    }

    @Test
    public void givenBadCode_whenThrowsException_thenCorrect() {
        assertThrows(ArithmeticException.class, () -> {
            int i = 1/0;
        });
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect() {
        Try<Integer> result = Try.of(() -> 1 / 0);

        assertTrue(result.isFailure());
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect2() {
        Try<Integer> computation = Try.of(() -> 1 / 0);
        int errorSentinel = computation.getOrElse(-1);

        assertEquals(-1, errorSentinel);
    }

    @Test
    public void givenBadCode_whenTryHandles_thenCorrect3() {
        Try<Integer> result = Try.of(() -> 1 / 0);
        assertThrows(ArithmeticException.class, () -> {
            result.getOrElseThrow((Supplier<ArithmeticException>) ArithmeticException::new);
        });
    }

    @Test
    public void givenJava8Function_whenWorks_thenCorrect() {
        Function<Integer, Integer> square = (num) -> num * num;
        int result = square.apply(2);

        assertEquals(4, result);
    }

    @Test
    public void givenJava8BiFunction_whenWorks_thenCorrect() {
        BiFunction<Integer, Integer, Integer> sum =
                (num1, num2) -> num1 + num2;
        int result = sum.apply(5, 7);

        assertEquals(12, result);
    }

    @Test
    public void givenVavrFunction_whenWorks_thenCorrect() {
        Function1<Integer, Integer> square = (num) -> num * num;
        int result = square.apply(2);

        assertEquals(4, result);
    }

    @Test
    public void givenVavrBiFunction_whenWorks_thenCorrect() {
        Function2<Integer, Integer, Integer> sum =
                (num1, num2) -> num1 + num2;
        int result = sum.apply(5, 7);

        assertEquals(12, result);
    }

    @Test
    public void whenCreatesFunction_thenCorrect0() {
        Function0<String> clazzName = () -> this.getClass().getName();
        String clazz = clazzName.apply();
        assertEquals("com.akshay.learning.SampleApplicationTest", clazz);
    }

    public int sum(int a, int b){
        return a+b;
    }

    @Test
    public void whenCreatesFunctionFromMethodRef_thenCorrect() {
        Function2<Integer, Integer, Integer> sum = Function2.of(this::sum);
        int summed = sum.apply(5, 6);

        assertEquals(11, summed);
    }

    @Test
    public void whenCreatesVavrList_thenCorrect() {
        List<Integer> intList = List.of(1, 2, 3);

        assertEquals(3, intList.length());
        assertEquals(new Integer(1), intList.get(0));
        assertEquals(new Integer(2), intList.get(1));
        assertEquals(new Integer(3), intList.get(2));
    }

    @Test
    public void whenSumsVavrList_thenCorrect() {
        int sum = List.of(1, 2, 3).sum().intValue();

        assertEquals(6, sum);
    }

    @Test
    public void givenFunction_whenEvaluatesWithLazy_thenCorrect() {
        Lazy<Double> lazy = Lazy.of(Math::random);
        assertFalse(lazy.isEvaluated());

        double val1 = lazy.get();
        assertTrue(lazy.isEvaluated());

        double val2 = lazy.get();
        assertEquals(val1, val2, 0.1);
    }

    @Test
    public void whenMatchworks_thenCorrect() {
        int input = 2;
        String output = Match(input).of(
                Case($(1), "one"),
                Case($(2), "two"),
                Case($(3), "three"),
                Case($(), "?"));

        assertEquals("two", output);
    }

    @Test
    public void whenMatchworks1_thenCorrect() {
        String arg = "-v";
        Match(arg).of(
                Case($(isIn("-h", "--help")), o -> run(this::displayHelp)),
                Case($(isIn("-v", "--version")), o -> run(this::displayVersion)),
                Case($(), o -> run(() -> {
                    throw new IllegalArgumentException(arg);
                }))
        );

    }

    private void displayVersion() {
        System.out.println("Displaying version");
    }

    private void displayHelp() {
        System.out.println("Displaying help");
    }

    @Test
    public void testFunctionComposition(){
        Function1<Integer, Integer> incrFunc = a -> a + 1;
        Function1<Integer, Integer> doubleFunc = a -> a * 2;
        Function1<Integer, Integer> incrAndDoubleFunc = incrFunc.andThen(doubleFunc);

        assertEquals(Integer.valueOf(6), incrAndDoubleFunc.apply(2));
    }

    @Test
    public void testFunctionLifting(){
        Function2<Integer, Integer, Integer> divide = (a, b) -> a / b;
        Function2<Integer, Integer, Option<Integer>> safeDivide = Function2.lift(divide);

        assertEquals(Option.of(null), safeDivide.apply(1,0));
        assertEquals(Option.of(2), safeDivide.apply(4,2));
    }

    public int sumWithException(int first, int second){
        if (first < 0 || second < 0) {
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        return first + second;
    }

    @Test
    public void testFunctionLiftingWithException(){
        Function2<Integer, Integer, Option<Integer>> sumFunc = Function2.lift(this::sumWithException);

        assertEquals(Option.of(null), sumFunc.apply(-1,2));
        assertEquals(Option.of(6), sumFunc.apply(4,2));
    }

    @Test
    public void memoization(){
        Function0<Double> hashCache =
                Function0.of(Math::random).memoized();

        double randomValue1 = hashCache.apply();
        double randomValue2 = hashCache.apply();

        assertEquals(randomValue1, randomValue2);
    }


}