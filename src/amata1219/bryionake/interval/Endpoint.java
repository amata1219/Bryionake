package amata1219.bryionake.interval;

import java.util.function.Supplier;

public class Endpoint<N extends Number & Comparable<Number>> {

    private final boolean containsEqualElement;
    private final Supplier<N> boundaryValue;

    public static <N extends Number & Comparable<Number>> Endpoint<N> openEndpoint(Supplier<N> boundaryValue) {
        return new Endpoint<>(false, boundaryValue);
    }

    public static <N extends Number & Comparable<Number>> Endpoint<N> closedEndpoint(Supplier<N> boundaryValue) {
        return new Endpoint<>(true, boundaryValue);
    }

    private Endpoint(boolean containsEqualElement, Supplier<N> boundaryValue) {
        this.containsEqualElement = containsEqualElement;
        this.boundaryValue = boundaryValue;
    }

    public boolean hasBoundaryValueAbove(N value) {
        int result = value.compareTo(boundaryValue.get());
        return containsEqualElement ?  result >= 0 : result > 0;
    }

    public boolean hasBoundaryValueBelow(N value) {
        int result = value.compareTo(boundaryValue.get());
        return containsEqualElement ? result <= 0 : result < 0;
    }

}
