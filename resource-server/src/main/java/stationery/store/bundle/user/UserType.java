package stationery.store.bundle.user;

import java.util.stream.Stream;

public enum UserType {
    ADMIN(1), CUSTOMER(2), SHIPPER(3);

    private int type;

    UserType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    public static UserType of(int type) {
        return Stream.of(UserType.values())
                .filter(u -> u.getType() == type)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
