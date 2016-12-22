package com.chess.engine;

import com.chess.engine.board.Board;

public class JChess {

    public static void main(final String[] args) {
        Board board = Board.createStandardBoard();
        System.out.print("Hello board is: /n" + board);
    }
}
