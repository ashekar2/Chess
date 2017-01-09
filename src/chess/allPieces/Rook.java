package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

/**
 * Rook Class
 */
public class Rook extends Piece{
	
	/**
     * Constructor for Rook
     *
     * @param color Sets color of Rook object
     */
	public Rook(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.ROOK;
		this.pieceColor = pieceColor;
	}
	
	public Rook(Rook copy)
	{
		super();
	}
	
	/**
     * Function to determine if Rook can move in the desired path between start and end
     *
     * @param start starting position of Rook
     * @param end ending position of Rook
     * @param board current board setting where Rook resides
     *  
     */
	public boolean validMoveFashion(Position start, Position end, ChessBoard board)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		int xMove = startX - endX;
		int yMove = startY - endY;
		if(xMove == 0 && yMove == 0)
		{
			return false;
		}
		if(xMove != 0 && yMove != 0)
		{
			return false;
		}
		return true;
	}
	
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     * In the case of Rook, we consider all situations in checkPieceMoveFasion function.
     *
     * @param start starting position of Rook
     * @param end ending position of Rook
     * @param board current board setting where Rook resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{	
		return board.checkLinearObstruction(start, end);
	}
}
