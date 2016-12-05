package com.chess.engine.move;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;
import lombok.Data;

@Data
public abstract class Move {
    protected final Piece movedPiece;
    protected final Coordiantes destinationCoordinates;

    protected Move(final Piece movedPiece, final Coordiantes destinationCoordinates) {
        this.movedPiece = movedPiece;
        this.destinationCoordinates = destinationCoordinates;
    }
}
