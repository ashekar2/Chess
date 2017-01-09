package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

/**
 * Pawn Class
 */
public class Pawn extends Piece {
	
	/**
     * Constructor for Pawn
     *
     * @param color Sets color of Pawn object
     */
	private int firstMove = 0; 
	
	public Pawn(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.PAWN;
		this.pieceColor = pieceColor;
	}
	
	
	/**
     * Function to determine if Pawn can move in the desired path between start and end
     *
     * @param start starting position of Pawn
     * @param end ending position of Pawn
     * @param board current board setting where Pawn resides
     *  
     */
	public boolean validMoveFashion(Position start, Position end, ChessBoard board){
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		
		//make sure movement is only forward with respect to color
		
		int diffY = endY -startY;
        int diffX = startX - endX;
        int direction;
        int startingY;

        if (this.pieceColor == color.BLACK) {
            direction = -1;
            startingY = 6;
        } else {
            direction = 1;
            startingY = 1;
        }
        if (Math.abs(diffX) == 1 && diffY == direction) {
            return board.getPiece(endX, endY) != null;
        }

        if (startY == startingY) 
        {
            if (diffX == 0 && diffY == 2 * direction) 
            {
                return true;
            } 
            else 
            {
                return (diffX == 0 && diffY == direction);
            }
        } 
        if(Math.abs(diffX) == 0 && diffY == direction)
        {
        	return board.getPiece(endX, endY) == null;
        }
        return false;
	}
	
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     * In the case of Pawn, we consider all situations in checkPieceMoveFasion function.
     *
     * @param start starting position of Pawn
     * @param end ending position of Pawn
     * @param board current board setting where Pawn resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{
		return false;
	}
}
