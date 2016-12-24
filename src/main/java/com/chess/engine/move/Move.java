package com.chess.engine.move;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.piece.Piece;
import lombok.Data;

import static com.chess.engine.board.Board.BoardBuilder;

@Data
public abstract class Move {

    protected final Board board;
    protected final Piece movedPiece;
    protected final Coordiantes destinationCoordinates;

    protected Move(final Board board, final Piece movedPiece, final Coordiantes destinationCoordinates) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinates = destinationCoordinates;
    }

    public Board execute() {
        final BoardBuilder boardBuilder = new BoardBuilder();
        for (Piece piece : board.getCurrentPlayer().getActivePieces()) {
            if (!movedPiece.equals(piece.getAlliance())) {
                boardBuilder.setPiece(piece);
            }
        }

        for (Piece piece : board.getCurrentPlayer().getOpponent().getActivePieces()) {
            boardBuilder.setPiece(piece);
        }

        boardBuilder.setPiece(this.movedPiece.movePiece(this));
        boardBuilder.setMoveMaker(board.getCurrentPlayer().getOpponent().getAlliance());

        return boardBuilder.build();
    }
}
