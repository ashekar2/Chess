package boardAndLogic;
import java.util.Vector;

import chess.allPieces.Bishop;
import chess.allPieces.King;
import chess.allPieces.Knight;
import chess.allPieces.KnightOfBhutra;
import chess.allPieces.Pawn;
import chess.allPieces.Piece;
import chess.allPieces.Queen;
import chess.allPieces.Rook;
import chess.allPieces.Viku;
/***
 * Main class that defines the board structure.
 * @author ajayshekar
 *
 */
public class ChessBoard {
	private final int width = 8;
	private final int height = 8;
	/***
	 * board represents the 2D array for the chessBoard
	 */
	Vector<Vector<BoardSquare>> board = new Vector<Vector<BoardSquare>>();
	
	Vector<Position> whitePieces = new Vector<Position>();
	Vector<Position> blackPieces = new Vector<Position>();
	
	private Position blackKing, whiteKing;
	
	/***
	 * Helper function to put pieces onto the board.
	 */
	private void initializePieces()
	{
        board.get(0).get(0).setPiece(new Rook(color.WHITE));
        board.get(0).get(1).setPiece(new Knight(color.WHITE));
        board.get(0).get(2).setPiece(new Bishop(color.WHITE));
        board.get(0).get(3).setPiece(new King(color.WHITE));
        board.get(0).get(4).setPiece(new Queen(color.WHITE));
        board.get(0).get(5).setPiece(new Bishop(color.WHITE));
        board.get(0).get(6).setPiece(new Knight(color.WHITE));
        board.get(0).get(7).setPiece(new Rook(color.WHITE));
        
        board.get(7).get(0).setPiece(new Rook(color.BLACK));
        board.get(7).get(1).setPiece(new Knight(color.BLACK));
        board.get(7).get(2).setPiece(new Bishop(color.BLACK));
        board.get(7).get(3).setPiece(new King(color.BLACK));
        board.get(7).get(4).setPiece(new Queen(color.BLACK));
        board.get(7).get(5).setPiece(new Bishop(color.BLACK));
        board.get(7).get(6).setPiece(new Knight(color.BLACK));
        board.get(7).get(7).setPiece(new Rook(color.BLACK));

        for (int i = 0; i < width; i++) {
            board.get(1).get(i).setPiece(new Pawn(color.WHITE));
            board.get(6).get(i).setPiece(new Pawn(color.BLACK));
            whitePieces.add(new Position(i,1));
            whitePieces.add(new Position(i,0));
            blackPieces.add(new Position(i,6));
        	blackPieces.add(new Position(i,7));
        }
	}
	
	/***
	 * Helper function to create the layout for the board.
	 */
	private void initializeBoard()
	{
		int colorFlag = 0;
		for(int i = 0; i<height; i++)
		{
			Vector<BoardSquare> row = new Vector<BoardSquare>();
			for(int j = 0; j<width; j++)
			{
				if(colorFlag == 0)
				{
					row.add(new BoardSquare(color.BLACK));
					colorFlag = 1;
				}
				else
				{
					row.add(new BoardSquare(color.WHITE));
					colorFlag = 0;
				}
			}
			board.add(row);
		}
	}
	
	/***
	 * Default constructor for the chessBoard
	 */
	public ChessBoard()
	{
		initializeBoard();
		initializePieces();
		blackKing = new Position(3,7);
		whiteKing = new Position(3,0);
	}
	
	/**
	 * Copy Constructor
	 * @param copy Board to copy
	 */
	public ChessBoard (ChessBoard copy)
	{
		for(int i = 0; i<height; i++)
		{
			Vector<BoardSquare> row = new Vector<BoardSquare>();
			for(int j = 0; j<width; j++)
			{
				row.add(new BoardSquare(copy.getSquareAtIndex(i,j)));
			}
			board.add(row);
		}
		
		Vector<Position> blackCopy = copy.getAllPiecesWithColor(color.BLACK);
		Vector<Position> whiteCopy = copy.getAllPiecesWithColor(color.WHITE);
		
		copyVector(blackCopy, blackPieces);
		copyVector(whiteCopy, whitePieces);
		
		this.blackKing = new Position(copy.getKingPosition(color.BLACK));
		this.whiteKing = new Position(copy.getKingPosition(color.WHITE));
	}
	
	private void copyVector(Vector<Position> colorCopy, Vector<Position> colorPieces)
	{
		for(int i = 0 ; i < colorCopy.size(); i++)
		{
			colorPieces.addElement(new Position(colorCopy.get(i).getXPosition(), colorCopy.get(i).getYPosition()));
		}
	}
	
	public void createCustomGame()
	{
		board.get(0).get(1).setPiece(new Viku(color.WHITE));
		board.get(7).get(1).setPiece(new Viku(color.BLACK));
		
		board.get(0).get(7).setPiece(new KnightOfBhutra(color.WHITE));
		board.get(7).get(7).setPiece(new KnightOfBhutra(color.BLACK));
	}
	
	/**
	 * Function to get the square at index x,y
	 * @param x X location
	 * @param y Y location
	 * @return BoardSquare object of that location
	 */
	private BoardSquare getSquareAtIndex(int x, int y)
	{
		return board.get(y).get(x);
	}
	
	/**
	 * Function to get width of board
	 * @return width of board
	 */
	public int getBoardWidth()
	{
		return width;
	}
	
	/**
	 * Function to get height of board
	 * @return height of board
	 */
	public int getBoardHeight()
	{
		return height;
	}
	
	/**
	 * Function to get the position of the piece at index in the vector containing all the pieces
	 * @param index Index of item in the list
	 * @param pieceColor Color of player
	 * @return position of the piece on the board
	 */
	public Position getPiecePostion(int index, color pieceColor)
	{
		if(pieceColor == color.WHITE)
		{
			return whitePieces.get(index);
		}
		else
		{
			return blackPieces.get(index);
		}
	}
	
	/**
	 * Function to get number of active pieces for the player with playerColor
	 * @param playerColor color of Player
	 * @return Returns number of active pieces for the player with playerColor
	 */
	public int getNumberOfPieces(color playerColor)
	{
		if(playerColor == color.BLACK)
		{
			return blackPieces.size();
		}
		else
		{
			return whitePieces.size();
		}
	}
	
	/***
	 * Returns the position of king for player defined by playerColor
	 * @param playerColor color of Player
	 * @return position of king
	 */
	public Position getKingPosition(color playerColor)
	{
		if(playerColor == color.BLACK)
		{
			return blackKing;
		}
		else
		{
			return whiteKing;
		}
	}
	
	/**
	 * Function to get the piece at the board on location x,y
	 * @param x X coordinate on board
	 * @param y y coordinate on board
	 * @return piece at the location x,y
	 */
	public Piece getPiece(int x, int y)
	{
		return board.get(y).get(x).getCurrentPiece();
	}
	
	/***
	 * Function to ensure that move is within bounds of board and that there is no piece in the end point or
	 * there is a piece of enemy color.
	 * @param endX X position of end move
	 * @param endY Y position of end move
	 * @param currentPiece current piece being moved
	 * @return True if move within board bounds and no conflict with end position
	 */
	public boolean isMoveWithinBounds(int endX, int endY, Piece currentPiece)
	{
		if(endX >= width || endY >= height || endX < 0 || endY < 0)
		{
			return false;
		}
		if(getPiece(endX, endY) != null && getPiece(endX, endY).getPieceColor() == currentPiece.getPieceColor())
		{
			return false;
		}
		return true;
	}
	
	/**
	 * Function to simulate iterating and searching through a vector
	 * @param x - X coordinate of the piece index being searched for.
	 * @param y - Y coordinate of the piece index being searched for.
	 * @param V - Vector for either black or white player containing all the pieces for that player
	 * @return index in the vector matching the x,y coordinate. -1 if not found.
	 */
	public int getIndexInVector(int x, int y, Vector<Position> V)
	{
		for(int i = 0; i<V.size(); i++)
		{
			Position curr = V.get(i);
			if(curr.getXPosition() == x && curr.getYPosition() == y)
			{
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Move piece from position start to position end
	 * @param start - Initial position of the piece
	 * @param end - Final position of the piece
	 */
	public void changePieceLocation(Position start, Position end)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		
		Piece currPiece = getPiece(startX, startY);
		//change in board, change in vector && change king position if need be
		
		board.get(startY).get(startX).setPieceNull();
		board.get(endY).get(endX).setPiece(currPiece);
		
		if(currPiece.getPieceType() == chessPiecesTypes.KING)
		{
			if(currPiece.getPieceColor() == color.BLACK)
			{
				blackKing = end;
			}
			else
			{
				whiteKing = end;
			}
		}
		
		if(currPiece.getPieceColor() == color.BLACK)
		{
			int index = getIndexInVector(startX, startY, blackPieces);
			blackPieces.set(index, end);
		}
		else
		{
			int index = getIndexInVector(startX, startY, whitePieces);
			whitePieces.set(index, end);
		}
	}
	
	/**
	 * Get all active pieces for the player with playerColor
	 * @param playerColor color of player
	 * @return Vector containing all the pieces for the playerColor
	 */
	public Vector<Position> getAllPiecesWithColor(color playerColor)
	{
		if(playerColor == color.BLACK)
		{
			return blackPieces;
		}
		else
		{
			return whitePieces;
		}
	}
	
	/**
	 * Function that gets enemy color for the player defined by playerColor.
	 * @param playerColor - color of the player
	 * @return color of enemy player
	 */
	public color getEnemyColor(color playerColor)
	{
		if(playerColor == color.BLACK)
		{
			return color.WHITE;
		}
		else
		{
			return color.BLACK;
		}
	}
	
	/**
	 * Function to check if there is an obstruction between start and end where
	 * the vector from start to end is either vertical or horizontal.
	 * @param start Starting position of piece
	 * @param end Ending position of piece
	 * @return boolean true if there is an obstruction. False if not.
	 */
	public boolean checkLinearObstruction(Position start, Position end)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		if(startX == endX)
		{
			if(endY > startY)
			{
				for(int i=startY+1; i<endY; i++)
				{
					if(getPiece(startX, i) != null)
						return true;
				}
			}
			else
			{
				for(int i=endY + 1; i<startY; i++)
				{
					if(getPiece(startX, i) != null)
						return true;
				}
			}
		}
		else if(startY == endY)
		{
			if(endX > startX)
			{
				for(int i=startX + 1; i<endX; i++)
				{
					if(getPiece(i,startY) != null)
						return true;
				}
			}
			else
			{
				for(int i=endX + 1; i<startX; i++)
				{
					if(getPiece(i, startY) != null)
						return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Function to check if there is an obstruction between start and end where
	 * the vector from start to end is of diagonol fashion.
	 * @param start Starting position of piece
	 * @param end Ending position of piece
	 * @return boolean true if there is an obstruction. False if not.
	 */
	public boolean checkDiagnolObstruction(Position start, Position end)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		boolean diagnolObstruction = false;
		if(startX < endX && startY > endY) //right down
		{
			for(int i = startX+1, j = startY - 1; i<endX && j>endY; i++, j--)
			{
				if(getPiece(i, j) != null)
				{
					diagnolObstruction = true;
					break;
				}
			}

		}
		else if(startX > endX && startY > endY) //left down
		{
			for(int i = startX-1, j = startY - 1; i>endX && j>endY; i--, j--)
			{
				if(getPiece(i, j) != null)
				{
					diagnolObstruction = true;
					break;
				}
			}
		}
		else if(startX > endX && startY < endY) //left up
		{
			for(int i = startX-1, j = startY + 1; i>endX && j<endY; i--, j++)
			{
				if(getPiece(i, j) != null)
				{
					diagnolObstruction = true;
					break;
				}
			}
		}
		else if(startX < endX && startY < endY) //right up
		{
			for(int i = startX + 1, j = startY + 1; i < endX && j < endY; i++, j++)
			{
				if(getPiece(i, j) != null)
				{
					diagnolObstruction = true;
					break;
				}
			}
		}
		return diagnolObstruction;
	}
	
	/**
	 * Function to delete the piece at X,Y from the board
	 * @param x - X coordinate of the piece
	 * @param y - Y coordinate of the piece
	 */
	public void deletePieceFromBoard(int x, int y)
	{
		Piece currPiece = getPiece(x,y);
		if(currPiece == null)
		{
			return;
		}
		color pieceColor = currPiece.getPieceColor();
		if(pieceColor == color.BLACK)
		{
			int index = getIndexInVector(x, y, blackPieces);
			blackPieces.removeElementAt(index);
		}
		else
		{
			int index = getIndexInVector(x, y, whitePieces);
			whitePieces.removeElementAt(index);
		}
		board.get(y).get(x).setPieceNull();
	}
	
	public void setPiece(int i, int j, Piece piece)
	{
		board.get(j).get(i).setPiece(piece);
	}
	
}
