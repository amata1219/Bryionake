package amata1219.bryionake.dsl.parser;

import amata1219.bryionake.type.Either;

import java.util.function.Function;

public interface FailableParser<T> {

    Either<String, T> tryParse(String arg);

    default <U> FailableParser<U> append(Function<T, Either<String, U>> mapper) {
        return arg -> tryParse(arg).flatMap(mapper);
    }

}
