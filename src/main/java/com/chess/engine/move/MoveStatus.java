package com.chess.engine.move;

public enum MoveStatus {
    DONE {
        @Override
        public boolean isDone() {
            return true;
        }
    },
    PLAYER_IN_CHECK {
        @Override
        public boolean isDone() {
            return false;
        }
    },
    ILLEGAL {
        @Override
        public boolean isDone() {
            return false;
        }
    };

    public abstract boolean isDone();
}
