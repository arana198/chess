package com.chess.engine.move.castle;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Piece;
import com.chess.engine.piece.Rook;
import lombok.Data;

@Data
public class CastleMove extends Move {

    private Rook movedRook;
    private Coordiantes rookDestinationCoordinate;

    public CastleMove(final Board board,
                      final Piece movedKing,
                      final Coordiantes kingDestinationCoordinates,
                      final Rook movedRook,
                      final Coordiantes rookDestinationCoordinate) {
        super(board, movedKing, kingDestinationCoordinates);
        this.movedRook = movedRook;
        this.rookDestinationCoordinate = rookDestinationCoordinate;
    }
}
