package com.chess.engine.board.tile;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;

public final class OccupiedTile extends Tile {

    protected final Piece piece;

    public OccupiedTile(final Coordiantes coordiantes, final Piece piece) {
        super(coordiantes);
        this.piece = piece;
    }

    public boolean isTileOccupied() {
        return true;
    }

    public Piece getPiece() {
        return this.piece;
    }
}
