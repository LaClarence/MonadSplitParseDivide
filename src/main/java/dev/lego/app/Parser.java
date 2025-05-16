package dev.lego.app;

public interface Parser {

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

}
