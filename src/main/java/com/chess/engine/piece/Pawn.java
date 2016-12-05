package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.move.Move;
import lombok.Data;

import java.util.Collection;

@Data
public class Pawn extends Piece {

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        return null;
    }
}
