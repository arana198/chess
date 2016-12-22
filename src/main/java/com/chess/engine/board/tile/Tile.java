package com.chess.engine.board.tile;

import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class Tile {

    private static final Map<Coordiantes, Tile> EMPTY_TILE_CACHE = createAllPossibleEmptyTiles();
    protected final Coordiantes coordiantes;

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

    public static Tile createTile(final Coordiantes coordiantes, final Optional<Piece> piece) {
        return piece
                .map(p -> new OccupiedTile(coordiantes, p))
                .map(Tile.class::cast)
                .orElse(EMPTY_TILE_CACHE.get(coordiantes));
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();
}
