package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

/**
 * Bishop Class
 */
public class Bishop extends Piece{
	
	/**
     * Constructor for Bishop
     *
     * @param color Sets color of Bishop object
     */
	public Bishop(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.BISHOP;
		this.pieceColor = pieceColor;
	}
	
	public Bishop(Bishop copy)
	{
		super();
	}
	
	/**
     * Function to determine if Bishop can move in the desired path between start and end
     *
     * @param start starting position of Bishop
     * @param end ending position of Bishop
     * @param board current board setting where Bishop resides
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
		if((moveX != 0 && moveY == 0) || (moveX == 0 && moveY != 0))
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
     *
     * @param start starting position of Bishop
     * @param end ending position of Bishop
     * @param board current board setting where Bishop resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{
		return board.checkDiagnolObstruction(start,end);
	}
}
