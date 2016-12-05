package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.move.Move;
import lombok.Data;

import java.util.Collection;

@Data
public abstract class Piece {

    protected final Coordiantes coordiantes;
    protected final Alliance alliance;

    Piece(final Coordiantes coordiantes, final Alliance alliance) {
        this.coordiantes = coordiantes;
        this.alliance = alliance;
    }

    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
