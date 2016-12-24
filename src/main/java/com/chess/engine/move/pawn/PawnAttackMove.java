package com.chess.engine.move.pawn;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Piece;
import lombok.Data;

@Data
public class PawnAttackMove extends Move {

    private Piece attackedPiece;

    public PawnAttackMove(final Board board, final Piece movedPiece, final Coordiantes destinationCoordinates, final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinates);
        this.attackedPiece = attackedPiece;
    }
}
