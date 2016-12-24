package com.chess.engine.move.pawn;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;
import lombok.Data;

@Data
public class PawnEnPassantAttack extends PawnAttackMove {
    public PawnEnPassantAttack(final Board board, final Piece movedPiece, final Coordiantes destinationCoordinates, final Piece attackedPiece) {
        super(board, movedPiece, destinationCoordinates, attackedPiece);
    }
}
