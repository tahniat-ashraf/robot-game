package com.priyam.robotcommands.service;

import com.priyam.robotcommands.constant.Direction;
import com.priyam.robotcommands.constant.Instruction;
import com.priyam.robotcommands.constant.Status;
import com.priyam.robotcommands.exception.CustomException;
import com.priyam.robotcommands.model.Position;
import com.priyam.robotcommands.model.Request;
import com.priyam.robotcommands.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Slf4j
public class RobotCommandServiceImpl implements RobotCommandService {


    private final static int X_AXIS_GRID_SIZE = 5;
    private final static int Y_AXIS_GRID_SIZE = 5;
    private final Direction[][] gridArray;

    public RobotCommandServiceImpl() {
        this.gridArray = new Direction[X_AXIS_GRID_SIZE][Y_AXIS_GRID_SIZE];
    }

    private void setCurrentPositionInGrid(Direction[][] gridArray, Position position) throws CustomException {

        if (0 <= position.getXAxisPosition() && position.getXAxisPosition() < X_AXIS_GRID_SIZE &&
                0 <= position.getYAxisPosition() && position.getYAxisPosition() < Y_AXIS_GRID_SIZE &&
                position.getDirection() != null
        ) {
            gridArray[position.getXAxisPosition()][position.getYAxisPosition()] = position.getDirection();

        } else {
            throw new CustomException(CustomException.INVALID_INSTRUCTION);
        }

    }

    private void processPositionInstruction(String[] instructionArray, Direction[][] gridArray, Position position) throws CustomException {

        if (instructionArray.length != 4) {
            throw new CustomException(CustomException.INVALID_INSTRUCTION);
        }

        removeLastPositionFootprint(gridArray, position.getXAxisPosition(), position.getYAxisPosition());
        position.setXAxisPosition(Integer.parseInt(instructionArray[1]));
        position.setYAxisPosition(Integer.parseInt(instructionArray[2]));
        position.setDirection(Direction.valueOf(instructionArray[3]));
        setCurrentPositionInGrid(gridArray, position);
        printGrid(gridArray);
    }

    private void processForwardInstruction(String[] instructionArray, Direction[][] gridArray, Position position) throws CustomException {

        if (instructionArray.length != 2) {
            throw new CustomException(CustomException.INVALID_INSTRUCTION);
        }

        removeLastPositionFootprint(gridArray, position.getXAxisPosition(), position.getYAxisPosition());
        var moves = Integer.parseInt(instructionArray[1]);
        switch (position.getDirection()) {
            case EAST:
                position.setXAxisPosition(position.getXAxisPosition() + moves);
                break;
            case WEST:
                position.setXAxisPosition(position.getXAxisPosition() - moves);
                break;
            case NORTH:
                position.setYAxisPosition(position.getYAxisPosition() - moves);
                break;
            case SOUTH:
                position.setYAxisPosition(position.getYAxisPosition() + moves);
        }
        setCurrentPositionInGrid(gridArray, position);
        printGrid(gridArray);

    }

    private void processDirectionChangeInstruction(Direction[][] gridArray, Position position, Instruction instruction) throws CustomException {

        switch (instruction) {
            case LEFT:
                position.setDirection(Direction.valueOf(position.getDirection().getLeftDirection()));
                break;
            case RIGHT:
                position.setDirection(Direction.valueOf(position.getDirection().getRightDirection()));
                break;
            case TURNAROUND:
                position.setDirection(Direction.valueOf(position.getDirection().getOppositeDirection()));
        }
        setCurrentPositionInGrid(gridArray, position);
        printGrid(gridArray);

    }

    private void checkIfInstructionsEmpty(String[] instructions) throws CustomException {
        if (instructions.length == 0) {
            throw new CustomException(CustomException.INSTRUCTION_SET_EMPTY);
        }

    }

    @Override
    public Response controlRobot(Request request) {

        log.info("controlRobot :: instructions {}", request);

        var position = Position.builder().build();
        var instructions = request.getCommands().trim().split("\\n");
        try {
            checkIfInstructionsEmpty(instructions);
            for (String detailedInstruction : instructions) {
                var instructionArray = detailedInstruction.split(" ");
                processInstruction(position, instructionArray, instructionArray[0]);
            }
            log.info("controlRobot :: instructions {} :: finalPosition ({},{}) facing {} direction", instructions, position.getXAxisPosition(), position.getYAxisPosition(), position.getDirection());
        } catch (CustomException exception) {
            log.error("controlRobot :: instructions {} :: errorMessage {}", instructions, exception.getMessage());
            return createErrorResponse(exception);
        }

        return Response.builder().position(position).status(Status.SUCCESS).build();

    }

    private Response createErrorResponse(CustomException exception) {
        return Response.builder()
                .status(Status.FAIL)
                .errorMessage(exception.getMessage())
                .build();
    }

    private void processInstruction(Position position, String[] instructionArray, String instructionText) throws CustomException {

        validateInstruction(instructionText);
        log.info("instruction = {}", Arrays.toString(instructionArray));
        var instruction = Instruction.valueOf(instructionText);
        switch (instruction) {
            case POSITION:
                processPositionInstruction(instructionArray, gridArray, position);
                break;
            case FORWARD:
                processForwardInstruction(instructionArray, gridArray, position);
                break;
            case WAIT:
                //do nothing
                printGrid(gridArray);
                break;
            case TURNAROUND:
            case RIGHT:
            case LEFT:
                processDirectionChangeInstruction(gridArray, position, instruction);
        }
    }

    private void validateInstruction(String instructionText) throws CustomException {

        if (!Instruction.contains(instructionText)) {
            throw new CustomException(CustomException.INVALID_INSTRUCTION);
        }
    }

    private void removeLastPositionFootprint(Direction[][] gridArray, int xAxis, int yAxis) {
        gridArray[xAxis][yAxis] = null;
    }

    private void printGrid(Direction[][] gridArray) {

        for (int i = 0; i < gridArray.length; i++) {
            log.info("i = {} {}", i, Arrays.toString(gridArray[i]));
        }
    }
}
