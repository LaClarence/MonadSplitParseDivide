package dev.lego.app;

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

  @Override
  public String toString() {
    if (isSuccess()) {
      return "Result{value=" + value + "}";
    } else {
      return "Result{error=" + error.getMessage() + "}";
    }
  }
}