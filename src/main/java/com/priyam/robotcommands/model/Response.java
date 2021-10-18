package com.priyam.robotcommands.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.priyam.robotcommands.constant.Status;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Response {

    private Position position;
    private Status status;
    private String errorMessage;
}
