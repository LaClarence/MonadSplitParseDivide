package dev.lego.app;

import java.util.Arrays;

public class App {
    record Pair<T>(T _1, T _2) {

    }

    record Result<T>(T value, Exception error) {

        public static <T> Result<T> success(T value) {
            return new Result<>(value, null);
        }

        public static <T> Result<T> failure(Exception error) {
            return new Result<>(null, error);
        }

        public boolean isSuccess() {
            return error == null;
        }

        public boolean isFailure() {
            return error != null;
        }

        public T getValue() {
            if (isFailure()) {
                throw new IllegalStateException("Cannot get value from a failed result: " + error);
            }
            return value;
        }

        public Exception getError() {
            return error;
        }

        public <U> Result<U> flatMap(ResultMapper<T, U> mapper) {
            if (isFailure()) {
                return Result.failure(error);
            }
            try {
                return mapper.apply(value);
            } catch (Exception e) {
                return Result.failure(e);
            }
        }

        @FunctionalInterface
        interface ResultMapper<T, U> {
            Result<U> apply(T t) throws Exception;
        }
    }

    static Result<Pair<String>> split(String s) {
        if (s == null || !s.contains(",")) {
            return Result.failure(new IllegalArgumentException("Input is null or does not contain a comma"));
        }
        var parts = s.split(",");
        if (parts.length != 2) {
            return Result.failure(new IllegalArgumentException("Input does not contain exactly two parts"));
        }
        return Result.success(new Pair<>(parts[0], parts[1]));
    }

    static Result<Double> parseDouble(String p) {
        try {
            return Result.success(Double.valueOf(p));
        } catch (NullPointerException | NumberFormatException ex) {
            return Result.failure(new NumberFormatException("Invalid number: " + p));
        }
    }

    static Result<Pair<Double>> parse(Pair<String> p) {
        return parseDouble(p._1())
                .flatMap(d1 -> parseDouble(p._2())
                        .flatMap(d2 -> Result.success(new Pair<>(d1, d2))));
    }

    static Result<Double> divide(Pair<Double> d) {
        if (d._2() == 0d) {
            return Result.failure(new ArithmeticException("Division by zero"));
        }
        return Result.success(d._1() / d._2());
    }

    static Result<Double> splitParseDivide(String s) {
        return split(s)
                .flatMap(App::parse)
                .flatMap(App::divide);
    }

    public static void main(String[] a) {
        var input = "126,3";
        System.out.println("Composition function result: " + splitParseDivide(input));

        var inputs = Arrays.asList("126,3", "126.3", "4,0", "", ",", "ABC", null);
        var results = inputs.stream().map(App::splitParseDivide).toList();
        results.forEach(System.out::println);
    }

    private App() {
    }
}
