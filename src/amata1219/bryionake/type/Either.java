package amata1219.bryionake.type;

public class Either<L, R> {

    public static <L, R> Either<L, R> success(R value) {
        return new Success<>(value);
    }

    public static <L, R> Either<L, R> failure(L error) {
        return new Failure<>(error);
    }

    public static class Success<L, R> extends Either<L, R> {

        public final R value;

        private Success(R value) {
            this.value = value;
        }

    }

    public static class Failure<L, R> extends Either<L, R> {

        public final L error;

        private Failure(L error) {
            this.error = error;
        }

    }

}
