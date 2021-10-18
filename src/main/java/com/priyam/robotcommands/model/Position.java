package com.priyam.robotcommands.model;

import com.priyam.robotcommands.constant.Direction;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Position {

    private int xAxisPosition;
    private int yAxisPosition;
    private Direction direction;
}
