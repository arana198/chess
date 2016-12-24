package com.chess.engine.piece;

import com.chess.engine.board.Coordiantes;
import com.chess.engine.player.Player;

import static com.chess.engine.board.BoardUtils.MAX_Y_COORDINATES;

public enum Alliance {
    BLACK {
        @Override
        public int getDirection() {
            return -1;
        }

        @Override
        public Player choosePlayerByAlliance(final Player whitePlayer, final Player blackPlayer) {
            return blackPlayer;
        }

        @Override
        public boolean isPawnPromotionSquare(final Coordiantes coordiantes) {
            return coordiantes.getYCoordinate() == 0;
        }

        @Override
        public boolean isBlack() {
            return true;
        }

        @Override
        public boolean isWhite() {
            return false;
        }
    },
    WHITE {
        @Override
        public int getDirection() {
            return 1;
        }

        @Override
        public Player choosePlayerByAlliance(final Player whitePlayer, final Player blackPlayer) {
            return whitePlayer;
        }

        @Override
        public boolean isPawnPromotionSquare(final Coordiantes coordiantes) {
            return coordiantes.getYCoordinate() == MAX_Y_COORDINATES;
        }

        @Override
        public boolean isBlack() {
            return false;
        }

        @Override
        public boolean isWhite() {
            return true;
        }
    };

    public abstract int getDirection();

    public abstract Player choosePlayerByAlliance(Player whitePlayer, Player blackPlayer);

    public abstract boolean isPawnPromotionSquare(Coordiantes coordiantes);

    public abstract boolean isBlack();

    public abstract boolean isWhite();
}
