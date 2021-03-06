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
public class Queen extends Piece {

    private static final List<Coordiantes> CANDIDATE_MOVE_COORDINATES = initialiseCandidateMove();

    private static final List<Coordiantes> initialiseCandidateMove() {
        final List<Coordiantes> coordiantesList = new ArrayList<>();
        coordiantesList.add(new Coordiantes(0, -1));
        coordiantesList.add(new Coordiantes(0, 1));
        coordiantesList.add(new Coordiantes(1, 0));
        coordiantesList.add(new Coordiantes(-1, 0));
        coordiantesList.add(new Coordiantes(-1, -1));
        coordiantesList.add(new Coordiantes(-1, 1));
        coordiantesList.add(new Coordiantes(1, -1));
        coordiantesList.add(new Coordiantes(1, 1));
        return ImmutableList.copyOf(coordiantesList);
    }

    public Queen(final Coordiantes coordiantes, final Alliance alliance, final boolean isFirstMove) {
        super(PieceType.QUEEN, coordiantes, alliance, isFirstMove);
    }

    @Override
    public Piece movePiece(final Move move) {
        return new Queen(move.getDestinationCoordinates(), move.getMovedPiece().getAlliance(), false);
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> moveList = new ArrayList<>();
        for (Coordiantes offsetCoordinates : CANDIDATE_MOVE_COORDINATES) {
            int xCoordinates = this.coordiantes.getXCoordinate();
            int yCoordinates = this.coordiantes.getYCoordinate();
            Coordiantes candidateDestinationCoordinate = new Coordiantes(xCoordinates, yCoordinates);

            while (BoardUtils.isValidTileCoordinates(candidateDestinationCoordinate)) {
                xCoordinates += offsetCoordinates.getXCoordinate();
                yCoordinates += offsetCoordinates.getYCoordinate();
                candidateDestinationCoordinate = new Coordiantes(xCoordinates, yCoordinates);

                if (BoardUtils.isValidTileCoordinates(candidateDestinationCoordinate)) {
                    final Tile candidateTile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateTile.isTileOccupied()) {
                        moveList.add(new MajorMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateTile.getPiece();
                        final Alliance alliance = pieceAtDestination.getAlliance();
                        if (this.alliance != alliance) {
                            moveList.add(new AttackMove(board, this, candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }

        return ImmutableList.copyOf(moveList);
    }

    @Override
    public String toString() {
        return PieceType.QUEEN.getPieceName();
    }
}
