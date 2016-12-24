package com.chess.engine.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;
import com.chess.engine.piece.Rook;
import lombok.Data;

@Data
public class QueenSideCastleMove extends CastleMove {
    public QueenSideCastleMove(final Board board,
                               final Piece movedKing,
                               final Coordiantes kingDestinationCoordinates,
                               final Rook movedRook,
                               final Coordiantes rookDestinationCoordinate) {
        super(board, movedKing, kingDestinationCoordinates, movedRook, rookDestinationCoordinate);
    }
}
