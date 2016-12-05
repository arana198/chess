package com.chess.engine.move;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;
import lombok.Data;

@Data
public class AttackMove extends Move {
    private final Piece attackedPiece;

    public AttackMove(final Piece movedPiece, final Coordiantes destinationCoordinates, final Piece attackedPiece) {
        super(movedPiece, destinationCoordinates);
        this.attackedPiece = attackedPiece;
    }
}
