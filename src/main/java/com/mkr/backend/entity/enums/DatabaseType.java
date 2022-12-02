package com.mkr.backend.entity.enums;

public enum DatabaseType {

    SPANNER("Spanner"),
    POSTGRES("Postgres");

    private final String value;

    DatabaseType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static DatabaseType fromKringe(String value) {
        DatabaseType[] values = values();

        for (DatabaseType type : values) {
            if (type.value.equals(value)) {
                return type;
            }
        }

        return null;
    }

}
