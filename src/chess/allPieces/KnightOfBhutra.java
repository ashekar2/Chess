package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

public class KnightOfBhutra extends Piece {
	/**
     * Constructor for KnightOfBhutra
     *
     * @param color Sets color of KnightOfBhutra object
     */
	public KnightOfBhutra(color pieceColor)
	{
		this.pieceType = chessPiecesTypes.BHUTRA;
		this.pieceColor = pieceColor;
	}
	
	public KnightOfBhutra(KnightOfBhutra copy)
	{
		super();
	}
	
	/**
     * Function to determine if Bhutra can move in the desired path between start and end
     *
     * @param start starting position of Bhutra
     * @param end ending position of Bhutra
     * @param board current board setting where Bhutra resides
     *  
     */
	public boolean validMoveFashion(Position start, Position end, ChessBoard board)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		int moveX = Math.abs(startX - endX);
		int moveY = Math.abs(startY - endY);
		if((moveX == (2*moveY)) || (moveY == (2*moveX)))
		{
			return true;
		}
		return false;
	}
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     *
     * @param start starting position of Bhutra
     * @param end ending position of Bhutra
     * @param board current board setting where Bhutra resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     * 
     */
	public boolean isMoveObstructed(Position start, Position end, ChessBoard board)
	{
		return false;
	}
}
