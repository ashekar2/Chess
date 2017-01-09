package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

/**
 * Knight Class
 */
public class Knight extends Piece {

	/**
     * Constructor for Knight
     *
     * @param color Sets color of Knight object
     */
	public Knight(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.KNIGHT;
		this.pieceColor = pieceColor;
	}
	
	public Knight(Knight copy)
	{
		super();
	}
	
	/**
     * Function to determine if Knight can move in the desired path between start and end
     *
     * @param start starting position of Knight
     * @param end ending position of Knight
     * @param board current board setting where Knight resides
     *  
     */
	public boolean validMoveFashion(Position start, Position end, ChessBoard board)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		int moveX = Math.abs(startX - endX);
		int moveY = Math.abs(startY - endY);
		if((moveX == 1 && moveY == 2) || (moveX == 2 && moveY == 1))
		{
			return true;
		}
		return false;
	}
	
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     * Trivial in case of Knight as it jumps over all object in its path
     *
     * @param start starting position of Knight
     * @param end ending position of Knight
     * @param board current board setting where Knight resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     * 
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{
		return false;
	}
}
