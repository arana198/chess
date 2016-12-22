package com.chess.engine.board;

import com.chess.engine.board.tile.Tile;
import com.chess.engine.move.Move;
import com.chess.engine.piece.Alliance;
import com.chess.engine.piece.Bishop;
import com.chess.engine.piece.King;
import com.chess.engine.piece.Knight;
import com.chess.engine.piece.Pawn;
import com.chess.engine.piece.Piece;
import com.chess.engine.piece.Queen;
import com.chess.engine.piece.Rook;
import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Board {

    private final List<Tile> boardTiles;
    private final Collection<Piece> activeWhitePieces;
    private final Collection<Piece> activeBlackPieces;
    private final Collection<Move> whiteLegalMoves;
    private final Collection<Move> blackLegalMoves;

    private Board(final BoardBuilder boardBuilder) {
        this.boardTiles = this.createGameBoard(boardBuilder);
        this.activeWhitePieces = this.calculateActivePieces(boardBuilder, Alliance.WHITE);
        this.activeBlackPieces = this.calculateActivePieces(boardBuilder, Alliance.BLACK);
        this.whiteLegalMoves = this.calculateLegalMoves(this.activeWhitePieces);
        this.blackLegalMoves = this.calculateLegalMoves(this.activeBlackPieces);
    }

    private static List<Tile> createGameBoard(final BoardBuilder boardBuilder) {
        final List<Tile> tiles = new ArrayList<>(BoardUtils.MAX_TILES_ON_BOARD);
        for (int i = 0; i < BoardUtils.MAX_X_COORDINATES; i++) {
            for (int j = 0; j < BoardUtils.MAX_Y_COORDINATES; j++) {
                final Coordiantes coordiantes = new Coordiantes(i, j);
                tiles.add(Tile.createTile(coordiantes, Optional.ofNullable(boardBuilder.getBoardPiece(coordiantes))));
            }
        }

        return ImmutableList.copyOf(tiles);
    }

    public Tile getTile(final Coordiantes coordiantes) {
        return boardTiles.parallelStream()
                .filter(c -> c.equals(coordiantes))
                .findFirst()
                .get();
    }

    private Collection<Piece> calculateActivePieces(final BoardBuilder boardBuilder, final Alliance alliance) {
        return boardBuilder.boardConfig.values()
                .parallelStream()
                .filter(p -> p.getAlliance() == alliance)
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
    }

    private Collection<Move> calculateLegalMoves(final Collection<Piece> pieces) {
        return pieces.parallelStream()
                .map(p -> p.calculateLegalMoves(this))
                .flatMap(c -> c.stream())
                .collect(Collectors.collectingAndThen(Collectors.toList(), ImmutableList::copyOf));
    }

    public static Board createStandardBoard() {
        final BoardBuilder builder = new BoardBuilder();
        // Black Layout
        builder.setPiece(new Rook(new Coordiantes(0, 7), Alliance.BLACK));
        builder.setPiece(new Knight(new Coordiantes(1, 7), Alliance.BLACK));
        builder.setPiece(new Bishop(new Coordiantes(2, 7), Alliance.BLACK));
        builder.setPiece(new Queen(new Coordiantes(3, 7), Alliance.BLACK));
        builder.setPiece(new King(new Coordiantes(4, 7), Alliance.BLACK, true));
        builder.setPiece(new Bishop(new Coordiantes(5, 7), Alliance.BLACK));
        builder.setPiece(new Knight(new Coordiantes(6, 7), Alliance.BLACK));
        builder.setPiece(new Rook(new Coordiantes(7, 7), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordiantes(0, 6), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(1, 6), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(2, 6), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(3, 6), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(4, 6), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(5, 6), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(6, 6), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(7, 6), Alliance.BLACK, true));

        // White Layout
        builder.setPiece(new Rook(new Coordiantes(0, 0), Alliance.BLACK));
        builder.setPiece(new Knight(new Coordiantes(1, 0), Alliance.BLACK));
        builder.setPiece(new Bishop(new Coordiantes(2, 0), Alliance.BLACK));
        builder.setPiece(new Queen(new Coordiantes(3, 0), Alliance.BLACK));
        builder.setPiece(new King(new Coordiantes(4, 0), Alliance.BLACK, true));
        builder.setPiece(new Bishop(new Coordiantes(5, 0), Alliance.BLACK));
        builder.setPiece(new Knight(new Coordiantes(6, 0), Alliance.BLACK));
        builder.setPiece(new Rook(new Coordiantes(7, 0), Alliance.BLACK));
        builder.setPiece(new Pawn(new Coordiantes(0, 1), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(1, 1), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(2, 1), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(3, 1), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(4, 1), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(5, 1), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(6, 1), Alliance.BLACK, true));
        builder.setPiece(new Pawn(new Coordiantes(7, 1), Alliance.BLACK, true));

        //white to move
        builder.setMoveMaker(Alliance.WHITE);
        return builder.build();
    }

    public static final class BoardBuilder {

        private Map<Coordiantes, Piece> boardConfig;
        private Alliance nextMoveMaker;

        public BoardBuilder() {
            boardConfig = new HashMap<>(33, 1.0f);
        }

        public BoardBuilder setPiece(final Piece piece) {
            boardConfig.put(piece.getCoordiantes(), piece);
            return this;
        }

        public BoardBuilder setMoveMaker(final Alliance nextMoveMaker) {
            this.nextMoveMaker = nextMoveMaker;
            return this;
        }

        public Piece getBoardPiece(final Coordiantes coordiantes) {
            return boardConfig.get(coordiantes);
        }

        public Board build() {
            return new Board(this);
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < BoardUtils.MAX_TILES_ON_BOARD; i++) {
            final String tileText = prettyPrint(this.boardTiles.get(i));
            builder.append(String.format("%3s", tileText));
            if ((i + 1) % 8 == 0) {
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private static String prettyPrint(final Tile tile) {
        if (tile.isTileOccupied()) {
            return tile.getPiece().getAlliance().isBlack() ?
                    tile.toString().toLowerCase() : tile.toString();
        }
        return tile.toString();
    }
}
