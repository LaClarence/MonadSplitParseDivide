package dev.lego.app;

public interface Operator {

  static Result<Double> divide(Pair<Double> d) {
    if (d._2() == 0d) {
      return Result.failure(new ArithmeticException("Division by zero"));
    }
    return Result.success(d._1() / d._2());
  }

}
