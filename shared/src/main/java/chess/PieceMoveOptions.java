package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PieceMoveOptions {

    public Collection<ChessMove> kingPieceMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor col) {
        Collection<ChessMove> moves = new ArrayList<>();
        int start_row = myPosition.getRow();
        int start_col = myPosition.getColumn();
        ChessGame.TeamColor color = col;
        List<List<Integer>> move_masks= List.of(
                List.of(0, 1),
                List.of(1, 0),
                List.of(0, -1),
                List.of(-1, 0),
                List.of(1, 1),
                List.of(1, -1),
                List.of(-1, -1),
                List.of(-1, 1)
        );
        for (var mask : move_masks)
        {
            int row_adj = mask.getFirst();
            int col_adj = mask.getLast();
            int new_row = start_row + row_adj;
            int new_col = start_col + col_adj;
            ChessPosition newPos = new ChessPosition(new_row, new_col);
            if (isMoveValid(board, newPos, color)) {
                ChessMove newMove = new ChessMove(myPosition, newPos);
                moves.add(newMove);
            }
        }
        return moves;
    }

    public Collection<ChessMove> knightPieceMoves(ChessBoard board, ChessPosition myPosition, ChessGame.TeamColor col) {
        Collection<ChessMove> moves = new ArrayList<>();
        int start_row = myPosition.getRow();
        int start_col = myPosition.getColumn();
        ChessGame.TeamColor color = col;
        List<List<Integer>> move_masks= List.of(
                List.of(2, 1),
                List.of(2, -1),
                List.of(-2, 1),
                List.of(-2, -1),
                List.of(1, 2),
                List.of(-1, 2),
                List.of(1, -2),
                List.of(-1, -2)
        );
        for (var mask : move_masks)
        {
            int row_adj = mask.getFirst();
            int col_adj = mask.getLast();
            int new_row = start_row + row_adj;
            int new_col = start_col + col_adj;
            ChessPosition newPos = new ChessPosition(new_row, new_col);
            if (isMoveValid(board, newPos, color)) {
                ChessMove newMove = new ChessMove(myPosition, newPos);
                moves.add(newMove);
            }
        }
        return moves;
    }

    public boolean isMoveInBounds(ChessPosition newPos)
    {
        int row = newPos.getRow();;
        int col = newPos.getColumn();
        if (row > 8 || row < 1) {return false;}
        if (col > 8 || col < 1) {return false;}
        return true;
    }

    public boolean isMoveValid(ChessBoard board, ChessPosition newPos, ChessGame.TeamColor color){
        // in bounds?
        if (!isMoveInBounds(newPos)) {return false;}

        ChessPiece newPiece = board.getPiece(newPos);
        // empty spot?
        if (newPiece == null) {return true;}
        // enemy spot?
        else if (newPiece.getTeamColor() != color) {return true;}
        else {return false;}
    }
}
