package com.priyam.robotcommands.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Direction {
    EAST("WEST", "NORTH", "SOUTH"),
    WEST("EAST", "SOUTH", "NORTH"),
    NORTH("SOUTH", "WEST", "EAST"),
    SOUTH("NORTH", "EAST", "WEST");

    @Getter
    private final String oppositeDirection;
    @Getter
    private final String leftDirection;
    @Getter
    private final String rightDirection;

}
