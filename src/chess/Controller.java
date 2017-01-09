package chess;

/**
 * Class that behaves as the Controller in the MVC paradigm
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import boardAndLogic.Game;
import boardAndLogic.Player;
import boardAndLogic.Position;
import boardAndLogic.color;

public class Controller {
	private Game chessGame;
	private BoardGUI view;
	private Player[] chessPlayers = {new Player(color.WHITE), new Player(color.BLACK)};
	private Player currPlayer;
	
	/**
	 * Default constructor builds game, gui and sets the view.
	 */
	public Controller ()
	{
		chessGame = new Game();
		view = new BoardGUI(chessGame, this);
		currPlayer = chessPlayers[0];
		view.setActive(getPlayerIndex());
	}
	
	/**
	 * Function to reset the game. Score and pieces reset. Used by Actionlistener items in gui.
	 */
	public void resetGame()
	{
		chessGame = new Game();
		view.resetPieces(chessGame);
		chessPlayers[0] = new Player(color.WHITE);
		chessPlayers[1] = new Player(color.BLACK);
		view.setScore(0 , 1);
		view.setScore(0 , 2);
	}
	
	/**
	 * Function to start the game for the first time.
	 */
	public void startGame()
	{
		new Controller();
		view.setActive(getPlayerIndex());
	}
	
	/**
	 * Helper function to convert the current Player to 0 or 1. 
	 * @return
	 */
	private int getPlayerIndex()
	{
		if(currPlayer.getPlayerColor() == color.WHITE)
		{
			return 1;
		}
		return 2;
	}
	
	/**
	 * Function to forfeit the game. Score and pieces reset. Used by Actionlistener items in gui.
	 */
	public void forfeitGame()
	{
		chessGame = new Game();
		view.resetPieces(chessGame);
		changePlayerTurn();
		view.setActive(getPlayerIndex());
		currPlayer.incrementScore();
		view.setScore(currPlayer.getScore(), getPlayerIndex());
	}
	
	/**
	 * Function that is called in order to create a custom board with custom pieces.
	 */
	public void setUpCustomBoard()
	{
		chessGame.board.createCustomGame();
	}
	
	/**
	 * Function that is used to communicate the move from view to model.
	 * @param start - start position of piece
	 * @param end - end position of piece
	 * @return - string that conveys whether the move was succesfull.
	 */
	public String makeMove(Position start, Position end)
	{
		System.out.println(start.getXPosition() + " " + start.getYPosition() + " " + end.getXPosition() + " "+ end.getYPosition());
		boolean validMove = chessGame.validateMove(currPlayer, start, end);
		if(!validMove)
		{
			return "NV";
		}
		view.changePieceLocation(start , end);
		chessGame.executeMove(start, end);
		
		changePlayerTurn();
		view.setActive(getPlayerIndex());
		if(chessGame.isPlayerInCheck(currPlayer.getPlayerColor()))
		{
			view.showCheck();
			if(chessGame.isPlayerInCheckMate(currPlayer.getPlayerColor()))
			{
				view.showCheckMate();
				currPlayer.incrementScore();
				view.setScore(currPlayer.getScore(), getPlayerIndex());
				chessGame = new Game();
				view.resetPieces(chessGame);
			}
		}
		return "";
	}
	
	/**
	 * Function to undo a move.
	 */
	public void undoMove()
	{
		boolean isPossible = chessGame.undo();
		if(isPossible)
		{
			view.resetPieces(chessGame);
			changePlayerTurn();
			view.setActive(getPlayerIndex());
		}
	}
	
	/**
	 * Helper function to change the current players turn.
	 */
	private void changePlayerTurn()
	{
		if(currPlayer.getPlayerColor() == color.WHITE)
		{
			currPlayer = chessPlayers[1];
		}
		else
		{
			currPlayer = chessPlayers[0];
		}
	}
	
	/**
	 * Function to get the current player
	 * @return current Player
	 */
	public Player getCurrentPlayer()
	{
		return currPlayer;
	}
	
	public static void main(String[] args)
	{
		new Controller();
	}
}
