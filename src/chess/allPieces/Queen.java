package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

/**
 * Queen Class
 */
public class Queen extends Piece {
	
	/**
     * Constructor for Queen
     *
     * @param color Sets color of Queen object
     */
	public Queen(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.QUEEN;
		this.pieceColor = pieceColor;
	}
	
	public Queen(Queen copy)
	{
		super();
	}
	
	/**
     * Function to determine if Queen can move in the desired path between start and end
     *
     * @param start starting position of Queen
     * @param end ending position of Queen
     * @param board current board setting where Queen resides
     *  
     */
	public boolean validMoveFashion(Position start, Position end, ChessBoard board)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		int moveX = Math.abs(startX - endX);
		int moveY = Math.abs(startY - endY);
		if(moveX == 0 && moveY == 0)
		{
			return false;
		}
		if(moveX != 0 && moveY != 0 && moveX != moveY)
		{
			return false;
		}
		return true;
	}
	
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     * In the case of Queen, we consider all situations in checkPieceMoveFasion function.
     *
     * @param start starting position of Queen
     * @param end ending position of Queen
     * @param board current board setting where Queen resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		if(startX == endX || startY == endY)
		{
			return board.checkLinearObstruction(start, end);
		}
		else
		{
			return board.checkDiagnolObstruction (start, end);
		}
		
	}
		
		
}
