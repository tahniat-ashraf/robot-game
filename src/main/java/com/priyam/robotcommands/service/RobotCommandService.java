package com.priyam.robotcommands.service;

import com.priyam.robotcommands.model.Request;
import com.priyam.robotcommands.model.Response;

public interface RobotCommandService {

    Response controlRobot(Request request);

}
