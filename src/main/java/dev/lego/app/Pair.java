package dev.lego.app;

record Pair<T>(T _1, T _2) {
  Pair {
    if (_1 == null || _2 == null) {
      throw new IllegalArgumentException("Pair elements cannot be null");
    }
  }
}