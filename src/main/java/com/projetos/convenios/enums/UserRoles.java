package com.projetos.convenios.enums;

import lombok.Getter;

@Getter
public enum UserRoles {

    ADMIN(1),
    COMPANY(2);

    private final int value;

    UserRoles(int value) {
        this.value = value;
    }

    public static UserRoles fromValue(int value) {
        for (UserRoles type : UserRoles.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value " + value);
    }
}
