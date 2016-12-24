package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.move.Move;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode
public abstract class Piece {

    protected final PieceType pieceType;
    protected final Coordiantes coordiantes;
    protected final Alliance alliance;
    protected final boolean isFirstMove;

    Piece(final PieceType pieceType, final Coordiantes coordiantes, final Alliance alliance, final boolean isFirstMove) {
        this.pieceType = pieceType;
        this.coordiantes = coordiantes;
        this.alliance = alliance;
        this.isFirstMove = isFirstMove;
    }

    public abstract Piece movePiece(final Move move);
    public abstract Collection<Move> calculateLegalMoves(final Board board);
}
