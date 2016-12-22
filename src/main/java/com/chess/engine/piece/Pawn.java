package com.chess.engine.piece;

import com.chess.engine.board.Board;
import com.chess.engine.board.BoardUtils;
import com.chess.engine.board.Coordiantes;
import com.chess.engine.board.tile.Tile;
import com.chess.engine.move.Move;
import com.chess.engine.move.PawnAttackMove;
import com.chess.engine.move.PawnJumpMove;
import com.chess.engine.move.PawnMove;
import com.chess.engine.move.PawnPromotion;
import com.google.common.collect.ImmutableList;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class Pawn extends Piece {

    private static final List<Coordiantes> CANDIDATE_MOVE_COORDINATES = initialiseCandidateMove();

    private final boolean isFirstMove;

    private static final List<Coordiantes> initialiseCandidateMove() {
        final List<Coordiantes> coordiantesList = new ArrayList<>();
        coordiantesList.add(new Coordiantes(0, 1));
        coordiantesList.add(new Coordiantes(0, 2));
        coordiantesList.add(new Coordiantes(1, 1));
        coordiantesList.add(new Coordiantes(-1, 1));
        return ImmutableList.copyOf(coordiantesList);
    }

    public Pawn(final Coordiantes coordiantes, final Alliance alliance, final Boolean isFirstMove) {
        super(coordiantes, alliance);
        this.isFirstMove = isFirstMove;
    }

    @Override
    public Collection<Move> calculateLegalMoves(final Board board) {
        final List<Move> moveList = new ArrayList<>();
        for (Coordiantes offsetCoordinates : CANDIDATE_MOVE_COORDINATES) {
            final int xCoordinates = this.coordiantes.getXCoordinate() + (this.alliance.getDirection() * offsetCoordinates.getXCoordinate());
            final int yCoordinates = this.coordiantes.getYCoordinate() + (this.alliance.getDirection() * offsetCoordinates.getYCoordinate());
            final Coordiantes candidateDestinationCoordinate = new Coordiantes(xCoordinates, yCoordinates);

            if (!BoardUtils.isValidTileCoordinates(this.coordiantes, candidateDestinationCoordinate)) {
                continue;
            }

            final Tile candidateTile = board.getTile(candidateDestinationCoordinate);
            if (offsetCoordinates.getXCoordinate() == 0 && offsetCoordinates.getYCoordinate() == 1 && !candidateTile.isTileOccupied()) { //single move
                if (this.alliance.isPawnPromotionSquare(candidateDestinationCoordinate)) {
                    moveList.add(new PawnPromotion(this, candidateDestinationCoordinate));
                } else {
                    moveList.add(new PawnMove(this, candidateDestinationCoordinate));
                }
            } else if (this.isFirstMove && offsetCoordinates.getXCoordinate() == 0 && offsetCoordinates.getYCoordinate() == 2
                    && (this.alliance.isWhite() && this.coordiantes.getXCoordinate() == 2)
                    || (this.alliance.isBlack() && this.coordiantes.getXCoordinate() == -7)) { //first move - jump
                final Coordiantes behindCandidateDestinationCoordinate = new Coordiantes(xCoordinates, yCoordinates + (1 * this.alliance.getDirection()));
                final Tile behindCandidateTile = board.getTile(behindCandidateDestinationCoordinate);
                if (!candidateTile.isTileOccupied() && !behindCandidateTile.isTileOccupied()) {
                    moveList.add(new PawnJumpMove(this, candidateDestinationCoordinate));
                }
            } else if ((offsetCoordinates.getXCoordinate() == 1 || offsetCoordinates.getXCoordinate() == -1) && offsetCoordinates.getYCoordinate() == 1) {
                if(candidateTile.isTileOccupied()) {
                    final Piece pieceAtDestination = candidateTile.getPiece();
                    final Alliance pieceAtDestinationAlliance = pieceAtDestination.getAlliance();
                    if(this.alliance != pieceAtDestinationAlliance) {
                        moveList.add(new PawnAttackMove(this, candidateDestinationCoordinate, pieceAtDestination));
                    }
                }
            } //consider enPassant move


            //otherwise single move
            //but need to check if tile in front is not occupied
            //kill - opponent piece
        }

        return ImmutableList.copyOf(moveList);
    }

    @Override
    public String toString() {
        return PieceType.PAWN.name();
    }
}
