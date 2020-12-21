package amata1219.bryionake.type;

import java.util.function.Supplier;

public class SafeCaster<T, U extends T> {

    private final Supplier<String> castFailedErrorMessage;

    public SafeCaster(Supplier<String> castFailedErrorMessage) {
        this.castFailedErrorMessage = castFailedErrorMessage;
    }

    public Either<String, U> tryCast(T value) {
        try {
            return Either.success((U) value);
        } catch (ClassCastException e) {
            return Either.failure(castFailedErrorMessage.get());
        }
    }

}
