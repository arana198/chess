package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.move.AttackMove;
import com.chess.engine.move.MajorMove;
import com.chess.engine.move.Move;
import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class King extends Piece {

    private static final List<Coordiantes> CANDIDATE_MOVE_COORDINATES = initialiseCandidateMove();

    private static final List<Coordiantes> initialiseCandidateMove() {
        final List<Coordiantes> coordiantesList = new ArrayList<>();
        coordiantesList.add(new Coordiantes(0, 1));
        coordiantesList.add(new Coordiantes(1, 1));
        coordiantesList.add(new Coordiantes(1, 0));
        coordiantesList.add(new Coordiantes(1, -1));
        coordiantesList.add(new Coordiantes(0, -1));
        coordiantesList.add(new Coordiantes(-1, -1));
        coordiantesList.add(new Coordiantes(-1, 0));
        coordiantesList.add(new Coordiantes(-1, 1));
        return ImmutableList.copyOf(coordiantesList);
    }

    public King(final Coordiantes coordiantes, final Alliance alliance, final boolean isFirstMove) {
        super(PieceType.KING, coordiantes, alliance, isFirstMove);
    }

    @Override
    public Piece movePiece(final Move move) {
        return new King(move.getDestinationCoordinates(), move.getMovedPiece().getAlliance(), false);
    }

    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> moveList = new ArrayList<>();
        for (Coordiantes offsetCoordinates : CANDIDATE_MOVE_COORDINATES) {
            final int xCoordinates = this.coordiantes.getXCoordinate() + offsetCoordinates.getXCoordinate();
            final int yCoordinates = this.coordiantes.getYCoordinate() + offsetCoordinates.getYCoordinate();
            final Coordiantes candidateCoordinates = new Coordiantes(xCoordinates, yCoordinates);

            if (!BoardUtils.isValidTileCoordinates(candidateCoordinates)) {
                continue;
            }

            final Tile candidateTile = board.getTile(candidateCoordinates);

            if (!candidateTile.isTileOccupied()) {
                moveList.add(new MajorMove(board, this, candidateCoordinates));
            } else {
                final Piece pieceAtDestination = candidateTile.getPiece();
                final Alliance alliance = pieceAtDestination.getAlliance();
                if (this.alliance != alliance) {
                    moveList.add(new AttackMove(board, this, candidateCoordinates, pieceAtDestination));
                }
            }
        }

        return ImmutableList.copyOf(moveList);
    }

    @Override
    public String toString() {
        return PieceType.KING.getPieceName();
    }
}
