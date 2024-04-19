package com.example.user.member.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

@Getter
@RequiredArgsConstructor
public enum SortCheck {

    CREATED_AT("createdAt"),
    NAME("name");

    private final String symbol;

    @JsonCreator
    public static SortCheck parsing(String inputValue) {
        for (SortCheck sortCheck : SortCheck.values()) {
            if (sortCheck.getSymbol().equalsIgnoreCase(inputValue)) {
                return sortCheck;
            }
        }
        return null;
    }
}
