package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

public class Viku extends Piece{
	/**
     * Constructor for Viku
     *
     * @param color Sets color of Viku object
     */
	public Viku(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.VIKU;
		this.pieceColor = pieceColor;
	}
	
	public Viku(Viku copy)
	{
		super();
	}
	
	/**
     * Function to determine if Viku can move in the desired path between start and end.
     * Can either move as a Rook or a Knight. But not both.
     * @param start starting position of Viku
     * @param end ending position of Viku
     * @param board current board setting where Viku resides
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
		if(moveX !=0 && moveY != 0)
		{
			if((moveX == 1 && moveY == 2) || (moveX == 2 && moveY == 1))
			{
				return true;
			}
			return false;
		}
		return true;
	}
	
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     * Trivial in case of Viku as it jumps over all object in its path
     *
     * @param start starting position of Viku
     * @param end ending position of Viku
     * @param board current board setting where Viku resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     * 
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		int moveX = Math.abs(startX - endX);
		int moveY = Math.abs(startY - endY);
		if(moveX == 0 && moveY == 0)
		{
			return false;
		}
		if(moveX !=0 && moveY != 0)
		{
			return false;
		}
		return board.checkLinearObstruction(start, end);
	}
}
