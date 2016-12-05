package com.chess.engine.board.tile;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;

public final class EmptyTile extends Tile {
    protected EmptyTile(final Coordiantes coordiantes) {
        super(coordiantes);
    }

    public boolean isTileOccupied() {
        return false;
    }

    public Piece getPiece() {
        return null;
    }
}
