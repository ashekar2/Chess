package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

/**
 * King Class
 */
public class King extends Piece{
	
	/**
     * Constructor for King
     *
     * @param color Sets color of King object
     */
	public King(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.KING;
		this.pieceColor = pieceColor;
	}
	
	public King(King copy)
	{
		super();
	}
	
	/**
     * Function to determine if King can move in the desired path between start and end
     *
     * @param start starting position of King
     * @param end ending position of King
     * @param board current board setting where King resides
     *  
     */
	public boolean validMoveFashion(Position start, Position end, ChessBoard board)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		int moveX = Math.abs(startX - endX);
		int moveY = Math.abs(startY - endY);
		if((moveX == 1 && moveY == 1) || (moveX == 1 && moveY == 0) || (moveX == 0 && moveY == 1))
		{
			return true;
		}
		return false;
	}
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     *
     * @param start starting position of King
     * @param end ending position of King
     * @param board current board setting where King resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     * 
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{
		return false;
	}

}
