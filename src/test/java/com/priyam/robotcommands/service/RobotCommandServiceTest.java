package com.priyam.robotcommands.service;

import com.priyam.robotcommands.constant.Direction;
import com.priyam.robotcommands.constant.Status;
import com.priyam.robotcommands.model.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RobotCommandServiceTest {

    @Autowired
    private RobotCommandService robotCommandService;

    @Test
    public void testCommand1() {

        var commands = "POSITION 1 3 EAST\n" +
                "FORWARD 3\n" +
                "WAIT\n" +
                "TURNAROUND\n" +
                "FORWARD 1\n" +
                "RIGHT\n" +
                "FORWARD 2";

        var response = robotCommandService.controlRobot(
                Request.builder()
                        .commands(commands)
                        .build());

        Assertions.assertEquals(Status.SUCCESS, response.getStatus());
        Assertions.assertEquals(3, response.getPosition().getXAxisPosition());
        Assertions.assertEquals(1, response.getPosition().getYAxisPosition());
        Assertions.assertEquals(Direction.NORTH, response.getPosition().getDirection());

    }

    @Test
    public void testCommand2() {

        var commands = "POSITION 0 4 NORTH\n" +
                "FORWARD 1\n" +
                "WAIT\n" +
                "RIGHT\n" +
                "FORWARD 3\n" +
                "TURNAROUND\n" +
                "FORWARD 1\n" +
                "RIGHT\n" +
                "FORWARD 3\n" +
                "LEFT\n" +
                "FORWARD 2\n" +
                "WAIT\n" +
                "RIGHT\n" +
                "TURNAROUND";

        var response = robotCommandService.controlRobot(
                Request.builder()
                        .commands(commands)
                        .build());

        Assertions.assertEquals(Status.SUCCESS, response.getStatus());
        Assertions.assertEquals(0, response.getPosition().getXAxisPosition());
        Assertions.assertEquals(0, response.getPosition().getYAxisPosition());
        Assertions.assertEquals(Direction.SOUTH, response.getPosition().getDirection());

    }

    @Test
    public void testCommand3() {
        var commands = "POSITION 3 1 NORTH\n" +
                "FORWARD 1\n" +
                "WAIT\n" +
                "LEFT\n" +
                "TURNAROUND\n" +
                "FORWARD 2\n" +
                "WAIT";

        var response = robotCommandService.controlRobot(
                Request.builder()
                        .commands(commands)
                        .build());

        Assertions.assertEquals(Status.FAIL, response.getStatus());
    }

    @Test
    public void testCommand4() {

        var commands = "POSITION 0 4 NORTH\n" +
                "FORWARD 1\n" +
                "WAIT\n" +
                "RIGHT\n" +
                "FORWARD 3\n" +
                "TURNAROUND\n" +
                "FORWARD 1\n" +
                "RIGHT\n" +
                "FORWARD 3\n" +
                "LEFT\n" +
                "FORWARD 2\n" +
                "WAIT\n" +
                "RIGHT\n" +
                "FORWARD 1";

        var response = robotCommandService.controlRobot(
                Request.builder()
                        .commands(commands)
                        .build());

        Assertions.assertEquals(Status.FAIL, response.getStatus());
    }

    @Test
    public void testCommand5() {

        var commands = "POSITION 1 3 EAST\n" +
                "FORWARD 3\n" +
                "WAIT\n" +
                "TURNAROUND\n" +
                "FORWARD 1\n" +
                "RIGHT\n" +
                "FORWARD 2\n" +
                "RIGHT\n" +
                "FORWARD 2";

        var response = robotCommandService.controlRobot(
                Request.builder()
                        .commands(commands)
                        .build());

        Assertions.assertEquals(Status.FAIL, response.getStatus());

    }

}
