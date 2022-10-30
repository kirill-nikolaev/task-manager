package com.example.taskmanager.models;

public enum Role {
    ROLE_GUEST("Guest"),
    ROLE_DEVELOPER("Developer"),
    ROLE_MANAGER("Manager"),
    ROLE_DIRECTOR("Director"),
    ROLE_ADMIN("Admin");

    final String value;
    Role(String role) {
        this.value = role;
    }

    public String getValue() {
        return this.value;
    }
    public static Role fromString(String value) {
        for (Role role: Role.values()) {
            if (role.getValue().equals(value))
                return role;
        }

        return null;
    }
}
