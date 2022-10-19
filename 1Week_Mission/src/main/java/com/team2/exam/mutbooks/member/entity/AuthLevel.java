package com.team2.exam.mutbooks.member.entity;

import lombok.Getter;

@Getter
public enum AuthLevel {
    NORMAL(3), AUTHOR(4), ADMIN(7);
    private final int authLevel;

    AuthLevel(int authLevel) {
        this.authLevel = authLevel;
    }
}
