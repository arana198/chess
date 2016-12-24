package com.chess.engine.player;

import com.chess.engine.board.Board;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.move.Move;
import com.chess.engine.move.MoveStatus;
import com.chess.engine.move.MoveTransition;
import com.chess.engine.piece.Alliance;
import com.chess.engine.piece.King;
import com.chess.engine.piece.Piece;
import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.stream.Collectors;

public abstract class Player {

    protected final Board board;
    protected final King playerKing;
    protected final Collection<Move> legalMoves;
    protected final boolean isInCheck;

    protected Player(final Board board, final Collection<Move> legalMoves, final Collection<Move> opponentLegalMoves) {
        this.board = board;
        this.legalMoves = legalMoves;
        this.playerKing = this.establishKing();
        this.isInCheck = !this.calculateAttacksOnTile(playerKing.getCoordiantes(), opponentLegalMoves).isEmpty();
    }

    public King getPlayerKing() {
        return this.playerKing;
    }

    public Collection<Move> getLegalMoves() {
        return legalMoves;
    }

    public boolean isMoveLegal(final Move move) {
        return this.legalMoves.contains(move);
    }

    public boolean isInCheck() {
        return isInCheck;
    }

    public boolean isInCheckMate() {
        return isInCheck && !this.hasEscapeMoves();
    }

    public boolean isInStalemate() {
        return !isInCheck && !this.hasEscapeMoves();
    }

    public MoveTransition makeMove(final Move move) {
        if (!this.isMoveLegal(move)) {
            return new MoveTransition(this.board, move, MoveStatus.ILLEGAL);
        }

        final Board transitionBoard = move.execute();
        if (!this.calculateAttacksOnTile(transitionBoard.getCurrentPlayer().getOpponent().getPlayerKing().getCoordiantes(),
                transitionBoard.getCurrentPlayer().getLegalMoves()).isEmpty()) {
            return new MoveTransition(this.board, move, MoveStatus.PLAYER_IN_CHECK);
        }

        return new MoveTransition(this.board, move, MoveStatus.DONE);
    }

    public Collection<Move> calculateAttacksOnTile(final Coordiantes tileCoordinate, final Collection<Move> moves) {
        return moves.parallelStream()
                .filter(move -> move.getDestinationCoordinates().equals(tileCoordinate))
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
    }

    private boolean hasEscapeMoves() {
        for (Move move : this.legalMoves) {
            MoveTransition moveTransition = this.makeMove(move);
            if (moveTransition.getMoveStatus().isDone()) {
                return true;
            }
        }

        return false;
    }

    private King establishKing() {
        return this.getActivePieces()
                .parallelStream()
                .filter(p -> p.getPieceType().isKing())
                .findFirst()
                .map(King.class::cast)
                .orElseThrow(() -> new RuntimeException(this.getAlliance() + " player does not have a King"));
    }

    public abstract Collection<Piece> getActivePieces();

    public abstract Alliance getAlliance();

    public abstract Player getOpponent();
}
