package com.chess.engine.board;

public final class BoardUtils {

    public static final int MAX_X_COORDINATES = 8;

    public static final int MAX_Y_COORDINATES = 8;

    public static final int MAX_TILES_ON_BOARD = MAX_X_COORDINATES * MAX_Y_COORDINATES - 1;

    private BoardUtils() {
        throw new RuntimeException("Cannot initialise BoardUtils");
    }

    public static boolean isValidTileCoordinates(final Coordiantes currentCoordiantes, final Coordiantes offsetCoordinates) {
        return currentCoordiantes.getXCoordinate() + offsetCoordinates.getXCoordinate() < 8 ||
                currentCoordiantes.getXCoordinate() + offsetCoordinates.getXCoordinate() >= 0 ||
                currentCoordiantes.getYCoordinate() + offsetCoordinates.getYCoordinate() < 8 ||
                currentCoordiantes.getYCoordinate() + offsetCoordinates.getYCoordinate() >= 0;
    }
}
