package com.chess.engine.piece;

public enum PieceType {
    KING("K") {
        @Override
        public boolean isKing() {
            return true;
        }
    },
    QUEEN("Q") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    ROOK("R") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    KNIGHT("N") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    BISHOP("B") {
        @Override
        public boolean isKing() {
            return false;
        }
    },
    PAWN("P") {
        @Override
        public boolean isKing() {
            return false;
        }
    };

    private String pieceName;

    PieceType(final String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceName() {
        return pieceName;
    }

    public abstract boolean isKing();
}
