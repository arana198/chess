package com.chess.engine.board;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Coordiantes {
    private final int xCoordinate;
    private final int yCoordinate;

    public Coordiantes(final int xCoordinate, final int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
