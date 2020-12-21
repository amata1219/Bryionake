package amata1219.bryionake.dsl.parser;

import amata1219.bryionake.type.Either;

public interface FailableParser<T> {

    Either<String, T> tryParse(String arg);

}
