package chess;

import java.util.Arrays;
import java.util.Objects;

/**
 * A chessboard that can hold and rearrange chess pieces.
 * <p>
 * Note: You can add to this class, but you may not alter
 * signature of the existing methods.
 */
public class ChessBoard {
    public ChessPiece [][] _board;

    public ChessBoard() {
        _board = new ChessPiece[8][8];
    }

    /**
     * Adds a chess piece to the chessboard
     *
     * @param position where to add the piece to
     * @param piece    the piece to add
     */
    public void addPiece(ChessPosition position, ChessPiece piece) {
        int row = position.getRow();
        int col = position.getColumn();
        _board[row-1][col-1] = piece;
    }

    /**
     * Gets a chess piece on the chessboard
     *
     * @param position The position to get the piece from
     * @return Either the piece at the position, or null if no piece is at that
     * position
     */
    public ChessPiece getPiece(ChessPosition position) {
        int row = position.getRow();
        int col = position.getColumn();
        return _board[row-1][col-1];
    }

    /**
     * Sets the board to the default starting board
     * (How the game of chess normally starts)
     */
    public void resetBoard() {
        String board_layout = ("|r|n|b|q|k|b|n|r|\n" +
                "|p|p|p|p|p|p|p|p|\n" +
                "| | | | | | | | |\n" +
                "| | | | | | | | |\n" +
                "| | | | | | | | |\n" +
                "| | | | | | | | |\n" +
                "|P|P|P|P|P|P|P|P|\n" +
                "|R|N|B|Q|K|B|N|R|");
        int curr_row = 8;
        int curr_col = 1;
        ChessGame.TeamColor color;
        ChessPiece.PieceType type = null;
        for (var c : board_layout.toCharArray())
        {
            switch (c) {
                case '|' -> {

                }
                case ' ' -> {
                    curr_col ++;
                }
                case '\n' -> {
                    curr_row --;
                    curr_col = 1;
                }
                default -> {
                    if (Character.isLowerCase(c)) {color = ChessGame.TeamColor.BLACK;}
                    else {color = ChessGame.TeamColor.WHITE;}
                    switch (Character.toLowerCase(c)) {
                        case 'k' -> {
                            type = ChessPiece.PieceType.KING;
                        }
                        case 'q' -> {
                            type = ChessPiece.PieceType.QUEEN;
                        }
                        case 'b' -> {
                            type = ChessPiece.PieceType.BISHOP;
                        }
                        case 'n' -> {
                            type = ChessPiece.PieceType.KNIGHT;
                        }
                        case 'r' -> {
                            type = ChessPiece.PieceType.ROOK;
                        }
                        case 'p' -> {
                            type = ChessPiece.PieceType.PAWN;
                        }
                    }
                    ChessPosition new_pos = new ChessPosition(curr_row, curr_col);
                    ChessPiece new_piece = new ChessPiece(color, type);
                    addPiece(new_pos, new_piece);
                    curr_col ++;
                }
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ChessBoard that = (ChessBoard) o;
        return Objects.deepEquals(_board, that._board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(_board);
    }

    @Override
    public String toString() {
        String output_str = "";
        for (ChessPiece [] row : _board)
        {
            for (var piece : row)
            {
                if (piece == null) {
                    output_str += " ";
                }
                else {
                    if (piece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                        output_str += "W";
                    } else {
                        output_str += "B";
                    }
                    switch (piece.getPieceType()) {
                        case KING -> {
                            output_str += "k";
                        }
                        case QUEEN -> {
                            output_str += "q";
                        }
                        case BISHOP -> {
                            output_str += "b";
                        }
                        case KNIGHT -> {
                            output_str += "n";
                        }
                        case ROOK -> {
                            output_str += "r";
                        }
                        case PAWN -> {
                            output_str += "p";
                        }
                    }
                    output_str += ", ";
                }
            }
            output_str += "\n";
        }
        return "ChessBoard{" + output_str + '}';
    }
}
