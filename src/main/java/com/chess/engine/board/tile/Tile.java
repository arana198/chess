package com.chess.engine.board.tile;

import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final Coordiantes coordiantes;

    private static final Map<Coordiantes, Tile> EMPTY_TILE_CACHE = createAllPossibleEmptyTiles();

    Tile(final Coordiantes coordiantes) {
        this.coordiantes = coordiantes;
    }

    private static Map<Coordiantes, Tile> createAllPossibleEmptyTiles() {
        final Map<Coordiantes, Tile> coordiantesTileMap = new HashMap<Coordiantes, Tile>();
        for (int i = 0; i < BoardUtils.MAX_X_COORDINATES; i++) {
            for (int j = 0; i < BoardUtils.MAX_Y_COORDINATES; j++) {
                final Coordiantes coordiantes = new Coordiantes(i, j);
                coordiantesTileMap.put(coordiantes, new EmptyTile(coordiantes));
            }
        }

        return Collections.unmodifiableMap(coordiantesTileMap);
    }

    public static Tile createTile(final Coordiantes coordiantes, final Piece piece) {
        return piece != null ? new OccupiedTile(coordiantes, piece) : EMPTY_TILE_CACHE.get(coordiantes);
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();
}
