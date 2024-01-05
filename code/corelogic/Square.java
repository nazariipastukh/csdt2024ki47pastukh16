package corelogic;

import java.io.Serializable;

public final class Square
        implements Serializable {
    public static Square EmptySquare = new Square(null, corelogic.Piece.Empty, 0);
    public static Square[] StandardMan = new Square[]{new Square(corelogic.Player.Black, corelogic.Piece.StandardMan, 1), new Square(corelogic.Player.White, corelogic.Piece.StandardMan, 2)};
    public static Square[] King = new Square[]{new Square(corelogic.Player.Black, corelogic.Piece.King, 3), new Square(corelogic.Player.White, corelogic.Piece.King, 4)};
    public static Square[] All = new Square[]{EmptySquare, StandardMan[0], StandardMan[1], King[0], King[1]};
    private corelogic.Player player;
    private corelogic.Piece piece;
    private int value;

    private Square(corelogic.Player player, corelogic.Piece piece, int value) {
        this.player = player;
        this.piece = piece;
        this.value = value;
    }

    public corelogic.Player getPlayer() {
        return this.player;
    }

    public corelogic.Piece getPiece() {
        return this.piece;
    }

    public int getValue() {
        return this.value;
    }
}
