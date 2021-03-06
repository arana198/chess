package com.chess.engine.move.pawn;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Piece;
import lombok.Data;

@Data
public class PawnJumpMove extends Move {
    public PawnJumpMove(final Board board, final Piece movedPiece, final Coordiantes destinationCoordinates) {
        super(board, movedPiece, destinationCoordinates);
    }
}
