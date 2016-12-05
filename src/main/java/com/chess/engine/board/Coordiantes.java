package com.chess.engine.board;

import lombok.Data;

@Data
public class Coordiantes {
    private final int xCoordinate;
    private final int yCoordinate;

    public Coordiantes(final int xCoordinate, final int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
}
