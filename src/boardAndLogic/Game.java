package boardAndLogic;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;
import java.util.Vector;

import chess.allPieces.*;

/***
 * Class that controls game logic and everything involved in the game
 * @author ajayshekar
 */

public class Game {
	
	public ChessBoard board = new ChessBoard();
	Stack<ChessBoard> listOfMoves = new Stack<ChessBoard>();
	
	/***
	 * Function to determine if the player of playerColor is currently in check given the board configuration.
	 * @param playerColor color of player
	 * @return True if in check. False if not.
	 */
	public boolean isPlayerInCheck(color playerColor)
	{
		color enemyColor = board.getEnemyColor(playerColor);
		Position kingLocation = board.getKingPosition(playerColor);
		int numPieces = board.getNumberOfPieces(enemyColor);
		for(int i = 0; i < numPieces; i++)
		{
			Position pieceLocation = board.getPiecePostion(i, enemyColor);
			Piece enemyPiece = board.getPiece(pieceLocation.getXPosition(), pieceLocation.getYPosition());
			boolean isValid = enemyPiece.validMoveFashion(pieceLocation, kingLocation, board) &&
							  !enemyPiece.isMoveObstructed(pieceLocation, kingLocation, board);
			if(isValid)
			{
				return true;
			}
		}
		return false;
	}
	
	/***
	 * Function to determine if the player specified by playerColor is in check mate
	 * @param playerColor color of player
	 * @return True if in checkmate. False otherwise
	 */
	public boolean isPlayerInCheckMate(color playerColor)
	{
		Position kingLocation = board.getKingPosition(playerColor);
		Piece king = board.getPiece(kingLocation.getXPosition(), kingLocation.getYPosition());
		//check whether King of player can move to any of its 8 positions in order to avoid check
		//loop from -1 to 1
		for(int x = -1; x <= 1; x++)
		{
			for(int y = -1; y <= 1; y++)
			{
				boolean checkMove = moveKing(king, kingLocation, x, y);
				if(checkMove)
				{
					return false;
				}
			}
		}
		
		//check whether any of the player's pieces can make a move in order to obstruct the check.
		Vector<Position> playerPiecePositions = board.getAllPiecesWithColor(playerColor);
		for(int i = 0; i<playerPiecePositions.size(); i++)
		{
			Position currPieceLocation = playerPiecePositions.get(i);
			Piece currPiece = board.getPiece(currPieceLocation.getXPosition(), currPieceLocation.getYPosition());
			boolean canPreventCheck = movePiece(currPiece, currPieceLocation, playerColor);
			if(canPreventCheck)
			{
				return false; // not in check mate
			}
		}
		
		return true;
		
	}
	
	/***
	 * Helper Function to determine if the king can make a given move specified by the offset of x and y
	 * in order to get out of check
	 * @param king Reference to king piece
	 * @param start Position where king is
	 * @param x Offset in the x direction
	 * @param y Offset in the y direction
	 * @return True if the given combination of offset results in the king being out of check. False otherwise
	 */
	private boolean moveKing(Piece king, Position start, int x, int y)
	{
		Position end = new Position(start.getXPosition()+x, start.getYPosition()+y);
		int endX = end.getXPosition(), endY = end.getYPosition();
		boolean stillInCheck = false;
		if(board.isMoveWithinBounds(endX, endY, king))
		{
			board.changePieceLocation(start, end);
			stillInCheck = isPlayerInCheck(king.getPieceColor());
			board.changePieceLocation(end, start);
			return !stillInCheck;
		}
		return false;
	}
	
	/***
	 * Function to explore explore the possibility of a piece making a move to avoid check.
	 * @param currentPiece piece which is being tested
	 * @param start starting position of piece
	 * @param playerColor color of player
	 * @return true if currPiece can prevent check. False if peice cannot prevent check for playerColor.
	 */
	private boolean movePiece(Piece currentPiece, Position start, color playerColor)
	{
		int width = board.getBoardWidth();
		int height = board.getBoardHeight();
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				Position end = new Position(j,i);
				if(end == start)
				{
					continue;
				}
				boolean validMove = board.isMoveWithinBounds(j, i, currentPiece) && 
						currentPiece.validMoveFashion(start, end, board) &&
						!currentPiece.isMoveObstructed(start, end, board);
				if(validMove)
				{
					boolean moveResultsInCheck = ifMoveResultsInCheck(start, end);
					if(!moveResultsInCheck)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/***
	 * Function to check stale mate
	 * @param playerColor color of player
	 * @return True if player is in stale mate
	 */
	public boolean staleMate(color playerColor)
	{
		Vector<Position> playerPiecePositions = board.getAllPiecesWithColor(playerColor);
		for(int i = 0; i<playerPiecePositions.size(); i++)
		{
			int x = playerPiecePositions.get(i).getXPosition();
			int y = playerPiecePositions.get(i).getYPosition();
			Piece playerPiece = board.getPiece(x, y);
			if(pieceAvoidStaleMate(playerPiece, playerPiecePositions.get(i), playerColor))
				return false;
		}
		return true;
	}
	
	/***
	 * Function to check if piece can make move that does not result in check
	 * @param currentPiece Current piece in order to break stale mate
	 * @param start Starting position of piece
	 * @param playerColor color of player
	 * @return true if piece can prevent stale mate. False otherwise
	 */
	private boolean pieceAvoidStaleMate(Piece currentPiece, Position start, color playerColor)
	{
		int width = board.getBoardWidth();
		int height = board.getBoardHeight();
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				Position end = new Position(j,i);
				if(end == start)
				{
					continue;
				}
				boolean validMove = board.isMoveWithinBounds(j, i, currentPiece) &&
						currentPiece.validMoveFashion(start, end, board) &&
						!currentPiece.isMoveObstructed(start, end, board);
				if(validMove)
				{
					boolean moveResultsInCheck = ifMoveResultsInCheck(start, end);
					if(!moveResultsInCheck)
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/***
	 * Function to check if piece moved from start to end results in a check
	 * @param start - Starting position
	 * @param end - Ending position
	 * @return true if move results in check
	 */
	private boolean ifMoveResultsInCheck(Position start, Position end)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		color playerColor = board.getPiece(startX, startY).getPieceColor();
		executeMove(start, end);
		boolean playerInCheck = isPlayerInCheck(playerColor);
		undo();
		return playerInCheck;
	}
	
	/***
	 * Main function to validate move from player 
	 * @param start Starting position input by player
	 * @param end Position where piece at starting position is attempting to move
	 * @return True if valid move. False otherwise.
	 */
	public boolean validateMove(Player currentPlayer, Position start, Position end)
	{
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		
		// Check if player is accessing same color pieces
		if(!canPlayerAccessPiece(currentPlayer, start))
		{
			return false;
		}
		Piece playerPiece = board.getPiece(startX, startY); 
		
		// Check if player is moving piece within bounds of board and that he can access the end position
		boolean boardLimitsValidation = board.isMoveWithinBounds(endX, endY, playerPiece);
		if(!boardLimitsValidation)
		{
			return false;
		}
		
		// Check if player piece can make such a movement requested.
		boolean pieceMovingFashion = playerPiece.validMoveFashion(start, end, board);
		if(!pieceMovingFashion)
		{
			return false;
		}

		// Check to see if piece is not obstructed between start and end (not including end)
		boolean pieceMovementObstruction = playerPiece.isMoveObstructed(start, end, board);
		if(!pieceMovementObstruction) //not obstructed
		{
			// Check to see if move does not result in self-check
			return !ifMoveResultsInCheck(start, end);
//			return true;
		}
		return false;
	}
	
	/***
	 * Function to get convert enum to number
	 * @param playerColor
	 * @return 1 for Black; 0 for White
	 */
	private int getPlayerIndex(color playerColor)
	{
		if(playerColor == color.BLACK)
		{
			return 1;
		}
		else
			return 0;
	}
	
	/***
	 * Main function to help execute a given move. This is after validating a given move. 
	 * @param start Starting position of piece
	 * @param end Piece moved to end position
	 */
	public void executeMove(Position start, Position end)
	{
		listOfMoves.add(new ChessBoard(board));
		int endX = end.getXPosition(), endY = end.getYPosition();
		if(board.getPiece(endX, endY) != null)
		{
			board.deletePieceFromBoard(endX, endY);
		}
		board.changePieceLocation(start, end);
	}
	
	/***
	 * Function to check if the player has access to the given piece he/she is trying to access.
	 * @param currPlayer Player object
	 * @param pieceLocation Location of piece trying to be accessed
	 * @return True if player has access to the piece.
	 */
	public boolean canPlayerAccessPiece(Player currPlayer, Position pieceLocation)
	{
		Piece currPiece = board.getPiece(pieceLocation.getXPosition(), pieceLocation.getYPosition());
		if(currPiece == null)
		{
			return false;
		}
		if(currPiece.getPieceColor() != currPlayer.getPlayerColor())
		{
			return false;
		}
		return true;

	}
	
	/**
	 * Function to undo the previous move in stack
	 * @return True if undo was succesfull. False if no previous frames present
	 */
	public boolean undo()
	{
		if(listOfMoves.size() == 0)
		{
			return false;
		}
		ChessBoard prevState = listOfMoves.pop();
		if(prevState != null)
			this.board = new ChessBoard(prevState);
		return true;
	}
		
}
