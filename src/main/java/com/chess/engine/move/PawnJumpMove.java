package com.chess.engine.move;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;
import lombok.Data;

@Data
public class PawnJumpMove extends Move {
    public PawnJumpMove(final Piece movedPiece, final Coordiantes destinationCoordinates) {
        super(movedPiece, destinationCoordinates);
    }
}
