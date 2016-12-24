package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;
import lombok.Data;

@Data
public class MajorMove extends Move {
    public MajorMove(final Board board, final Piece movedPiece, final Coordiantes destinationCoordinates) {
        super(board, movedPiece, destinationCoordinates);
    }
}
