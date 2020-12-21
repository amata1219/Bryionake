package amata1219.bryionake.type;

import java.util.function.Supplier;

public class SafeCaster<T, U extends T, E> {

    private final Supplier<E> castFailedErrorMessage;

    public SafeCaster(Supplier<E> castFailedErrorMessage) {
        this.castFailedErrorMessage = castFailedErrorMessage;
    }

    public Either<E, U> tryCast(T value) {
        try {
            return Either.success((U) value);
        } catch (ClassCastException e) {
            return Either.failure(castFailedErrorMessage.get());
        }
    }

}
