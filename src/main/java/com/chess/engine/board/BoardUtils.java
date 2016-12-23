package com.chess.engine.board;

public final class BoardUtils {

    public static final int MAX_X_COORDINATES = 8;

    public static final int MAX_Y_COORDINATES = 8;

    public static final int MAX_TILES_ON_BOARD = MAX_X_COORDINATES * MAX_Y_COORDINATES;

    private BoardUtils() {
        throw new RuntimeException("Cannot initialise BoardUtils");
    }

    public static boolean isValidTileCoordinates(final Coordiantes offsetCoordinates) {
        return offsetCoordinates.getXCoordinate() < 8 &&
                offsetCoordinates.getXCoordinate() >= 0 &&
                offsetCoordinates.getYCoordinate() < 8 &&
                offsetCoordinates.getYCoordinate() >= 0;
    }
}
