package dev.lego.app;

public interface App {

    static Result<Double> splitParseDivide(String s) {
        return Spliter.splitIfValid(s)
                .flatMap(Parser::parse)
                .flatMap(Operator::divide);
    }
}
