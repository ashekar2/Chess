
package boardAndLogic;

import chess.allPieces.Piece;

/**
 * Class that maintains individual tiles of chess Board.
 * @author ajayshekar
 *
 */

public class BoardSquare {
	private Piece currentPiece;
	private color pieceColor;
	
	/***
	 * Default Constructor
	 * @param pieceColor Color of Square
	 */
	public BoardSquare(color pieceColor)
	{
		this.pieceColor = pieceColor;
		currentPiece = null;
	}
	
	public BoardSquare(BoardSquare copy)
	{
		this.currentPiece = copy.getCurrentPiece();
		this.pieceColor = copy.getBoardPieceColor();
	}
	
	/***
	 * Accessor function to get Piece Type at that Square
	 * @return Piece Type
	 */
	public chessPiecesTypes getCurrentPieceType(){
		return this.currentPiece.getPieceType();
	}
	
	/**
	 * Accessor function to get Piece at that Square
	 * @return Get Piece at that location
	 */
	public Piece getCurrentPiece()
	{
		return this.currentPiece;
	}
	
	/**
	 * Set current Square Location to null
	 */
	public void setPieceNull()
	{
		currentPiece = null;
	}
	
	/***
	 * Function to set piece at the square.
	 * @param newPiece Piece at the square
	 */
	public void setPiece(Piece newPiece)
	{
		this.currentPiece = newPiece;
	}
	
	public color getBoardPieceColor()
	{
		return this.pieceColor;
	}
}
