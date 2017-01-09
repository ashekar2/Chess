package chess.allPieces;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;

/**
 * Class that defines properties of all pieces. This class is used as a base class by all types of pieces.
 * @author ajayshekar
 *
 */

public abstract class Piece {
	color pieceColor;
	chessPiecesTypes pieceType;
	
	/***
	 * Defualt constructor for the piece class
	 */
	public Piece()
	{
		pieceColor = color.BLACK;
	}
	
	/***
	 * Constructor that sets the color for the associated piece
	 */
	public Piece(color pieceColor){
		this.pieceColor = pieceColor;
	}
	
	public Piece(Piece copy)
	{
		pieceColor = copy.getPieceColor();
		pieceType = copy.getPieceType();
	}
	
	/***
	 * Getter for getting piece type
	 */
	public chessPiecesTypes getPieceType()
	{
		return this.pieceType;
	}
	
	/***
	 * Getter for getting piece color
	 */
	public color getPieceColor()
	{
		return this.pieceColor;
	}
	
	/***
	 * Function to ensure for that Piece moving to position end, there is no piece in the position end
	 * OR there is a piece in position end but belongs to enemy color.
	 * 
	 * @param end Position where Piece is attempting to move to
	 * @param board Current board where piece resides
	 * @return True if Piece can move to position end. False otherwise
	 */
	public boolean validateMoveEndPosition(Position end, ChessBoard board)
	{
		Piece endPiece = board.getPiece(end.getXPosition(), end.getYPosition());
		if(endPiece == null)
		{
			return true;
		}
		if(endPiece.getPieceColor() != this.pieceColor)
		{
			return true;
		}
		return false;
	}
	
	/***
	 * Function to check whether piece can move in the direction specified by the vector connecting start
	 * and end.
	 * @param start Starting position for piece
	 * @param end Position where piece is attempting to move to
	 * @param board current board where piece resides
	 * @return True if piece can move shape specified between start and end. False otherwise. 
	 */
	public abstract boolean validMoveFashion(Position start, Position end, ChessBoard board);
	
	/**
     * Function to determine if any object occludes the path between start and end (not including end)
     *
     * @param start starting position of Bishop
     * @param end ending position of Bishop
     * @param board current board setting where Bishop resides
     * @return Returns true if there is not piece between start and end (not including end). False otherwise
     */
	public abstract boolean isMoveObstructed(Position start, Position end, ChessBoard board);
}