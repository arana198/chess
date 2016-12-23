package com.chess.engine.board.tile;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;

final class OccupiedTile extends Tile {

    private final Piece piece;

    OccupiedTile(final Coordiantes coordiantes, final Piece piece) {
        super(coordiantes);
        this.piece = piece;
    }

    public boolean isTileOccupied() {
        return true;
    }

    public Piece getPiece() {
        return this.piece;
    }

    @Override
    public String toString() {
        return piece.toString();
    }
}
