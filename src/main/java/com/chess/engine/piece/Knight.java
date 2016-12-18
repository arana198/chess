package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.move.AttackMove;
import com.chess.engine.move.MajorMove;
import com.chess.engine.move.Move;
import com.chess.engine.board.tile.Tile;
import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class Knight extends Piece {

    private static final List<Coordiantes> CANDIDATE_MOVE_COORDINATES = initialiseCandidateMove();

    private static final List<Coordiantes> initialiseCandidateMove() {
        final List<Coordiantes> coordiantesList = new ArrayList<>();
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(-3, -2));
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(-3, 2));
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(3, 2));
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(3, -2));
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(-2, -3));
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(-2, 3));
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(2, 3));
        CANDIDATE_MOVE_COORDINATES.add(new Coordiantes(2, -3));
        return ImmutableList.copyOf(coordiantesList);
    }

    public Knight(final Coordiantes coordiantes, final Alliance alliance) {
        super(coordiantes, alliance, isFirstMove);
    }

    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> moveList = new ArrayList<>();
        for (Coordiantes offsetCoordinates : CANDIDATE_MOVE_COORDINATES) {
            if (!BoardUtils.isValidTileCoordinates(this.coordiantes, offsetCoordinates)) {
                continue;
            }

            final int xCoordinates = this.coordiantes.getXCoordinate() + offsetCoordinates.getXCoordinate();
            final int yCoordinates = this.coordiantes.getYCoordinate() + offsetCoordinates.getYCoordinate();
            final Coordiantes candidateCoordinates = new Coordiantes(xCoordinates, yCoordinates);
            final Tile candidateTile = board.getTile(candidateCoordinates);

            if (!candidateTile.isTileOccupied()) {
                moveList.add(new MajorMove(this, candidateCoordinates));
            } else {
                final Piece pieceAtDestination = candidateTile.getPiece();
                final Alliance alliance = pieceAtDestination.getAlliance();
                if (this.alliance != alliance) {
                    moveList.add(new AttackMove(this, candidateCoordinates, pieceAtDestination));
                }
            }
        }

        return ImmutableList.copyOf(moveList);
    }
}
