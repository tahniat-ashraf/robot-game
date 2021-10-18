package com.priyam.robotcommands.controller;

import com.priyam.robotcommands.constant.Status;
import com.priyam.robotcommands.model.Request;
import com.priyam.robotcommands.model.Response;
import com.priyam.robotcommands.service.RobotCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/robot-game")
@RestController
@RequiredArgsConstructor
public class RobotCommandController {

    private final RobotCommandService robotCommandService;


    @PostMapping("/move")
    public ResponseEntity<Response> moveRobotUsingCommands(@RequestBody Request request) {

        var response = robotCommandService.controlRobot(request);

        if (response.getStatus() == Status.SUCCESS) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
