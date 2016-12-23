package com.chess.engine.board.tile;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;

final class EmptyTile extends Tile {
    EmptyTile(final Coordiantes coordiantes) {
        super(coordiantes);
    }

    public boolean isTileOccupied() {
        return false;
    }

    public Piece getPiece() {
        return null;
    }

    @Override
    public String toString() {
        return "-";
    }
}
