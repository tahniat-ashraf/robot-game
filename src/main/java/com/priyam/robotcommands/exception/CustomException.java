package com.priyam.robotcommands.exception;

public class CustomException extends Exception {

    public static final String INVALID_INSTRUCTION = "Invalid instruction";
    public static final String INSTRUCTION_SET_EMPTY = "Instruction set cannot be empty";

    public CustomException(String invalidInstruction) {
        super(invalidInstruction);
    }
}
