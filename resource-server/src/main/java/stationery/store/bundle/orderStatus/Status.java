package stationery.store.bundle.orderStatus;

import java.util.stream.Stream;

public enum Status {
    SUBMITTED(1), DELIVERED(2), RETURNED(3);

    private int stage;

    Status(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return this.stage;
    }

    public static Status of(int stage) {
        return Stream.of(Status.values())
                .filter(p -> p.getStage() == stage)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
