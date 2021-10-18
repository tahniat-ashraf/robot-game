package com.priyam.robotcommands.constant;

public enum Instruction {
    POSITION,
    FORWARD,
    WAIT,
    TURNAROUND,
    RIGHT,
    LEFT;

    public static boolean contains(String test) {

        for (Instruction instruction : Instruction.values()) {
            if (instruction.name().equals(test)) {
                return true;
            }
        }
        return false;
    }
}
