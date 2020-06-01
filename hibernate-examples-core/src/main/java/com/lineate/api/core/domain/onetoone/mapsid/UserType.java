package com.lineate.api.core.domain.onetoone.mapsid;

public enum UserType {
    ADMIN("ADMIN"),
    USER("USER");

    private String typeString;

    UserType(String typeString) {
        this.typeString = typeString;
    }

    public String getTypeString() {
        return typeString;
    }
}
