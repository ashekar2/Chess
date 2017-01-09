package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.Game;
import boardAndLogic.Player;
import boardAndLogic.Position;
import boardAndLogic.color;

/***
 * validateMove
 * helpDetectStaleMate
 * staleMate
 * movePiece
 * isPlayerInCheckMate
 * moveKing
 * isPlayerInCheck
 * @author ajayshekar
 *
 */

public class GameTest {
	
	@Test
	public void playerCanAccessPiece() {
		Game newGame = new Game();
		Player currPlayer = new Player(color.WHITE);
		boolean canAccess = newGame.canPlayerAccessPiece(currPlayer, new Position(0,0));
		assertEquals("Player should have access to the piece", canAccess, true);
	}
	
	@Test
	public void playerCannotAccessPiece() {
		Game newGame = new Game();
		Player currPlayer = new Player(color.WHITE);
		boolean canAccess = newGame.canPlayerAccessPiece(currPlayer, new Position(7,7));
		assertEquals("Player cannot access enemy piece", canAccess, false);
	}
	
	@Test
	public void playerCannotAccessNonexistingPiece() {
		Game newGame = new Game();
		Player currPlayer = new Player(color.WHITE);
		boolean canAccess = newGame.canPlayerAccessPiece(currPlayer, new Position(4,4));
		assertEquals("Player cannot access enemy piece", canAccess, false);
	}
	
	@Test
	public void executeSimpleMove() {
		Game newGame = new Game();
		newGame.executeMove(new Position(3,1), new Position(3,2));
		assertEquals("Executing simple move to empty position", newGame.board.getPiece(3, 1), null);
		assertEquals("Executing simple move to empty position", (newGame.board.getPiece(3, 2) != null), true);
	}
	
	@Test
	public void executeMoveKillEnemy() {
		Game newGame = new Game();
		newGame.executeMove(new Position(3,1), new Position(3,6));
		assertEquals("Executing move to kill enemy piece", newGame.board.getPiece(3, 6).getPieceColor(), color.WHITE);
	}
	
	@Test
	public void rookCannotJumpOverPiece() {
		Game newGame = new Game();
		Player currPlayer = new Player(color.WHITE);
		boolean test = newGame.validateMove(currPlayer, new Position(0,0), new Position(0,2));
		assertEquals("Validate move should fail", test, false);
	}
	
	@Test
	public void pawnMoveOneStepAhead() {
		Game newGame = new Game();
		Player currPlayer = new Player(color.WHITE);
		boolean test = newGame.validateMove(currPlayer, new Position(0,1), new Position(0,2));
		assertEquals("Make pawn move valid move", test, true);
	}
	
	@Test
	public void startingBoardNotInCheckMate() {
		Game newGame = new Game();
		boolean whitePlayerInCheckMate = newGame.isPlayerInCheckMate(color.WHITE);
		assertEquals("Starting should not be in check", whitePlayerInCheckMate, false);
	}
	
	@Test
	public void startingBoardShouldNotBeInCheck() {
		Game newGame = new Game();
		boolean whitePlayerInCheck = newGame.isPlayerInCheck(color.WHITE);
		assertEquals("Should not be in check", whitePlayerInCheck, false);
	}
	
	@Test
	public void playerInCheck() {
		Game newGame = new Game();
		newGame.board.deletePieceFromBoard(3, 1);
		newGame.board.changePieceLocation(new Position(4,7), new Position(3,5));
		boolean whitePlayerInCheck = newGame.isPlayerInCheck(color.WHITE);
		assertEquals("Should be in check", whitePlayerInCheck, true);
	}

	
	@Test
	public void playerNotInCheck() {
		Game newGame = new Game();
		newGame.board.deletePieceFromBoard(3, 1);
		newGame.board.changePieceLocation(new Position(3,7), new Position(3,5));
		boolean whitePlayerInCheck = newGame.isPlayerInCheck(color.WHITE);
		assertEquals("Should not be in check", whitePlayerInCheck, false);
	}
	
	@Test
	public void testComplexCheckCondition() {
		Game newGame = new Game();
		for(int i = 0; i<8; i++)
		{
			if(i != 3 && i != 0)
			{
				newGame.board.deletePieceFromBoard(i, 0);
				newGame.board.deletePieceFromBoard(i, 7);
			}
			
			newGame.board.deletePieceFromBoard(i, 1);
			newGame.board.deletePieceFromBoard(i, 6);
		}
		newGame.board.deletePieceFromBoard(0, 7);
		newGame.executeMove(new Position(3,7), new Position(7,7));
		newGame.executeMove(new Position(3,0), new Position(7,5));
		newGame.executeMove(new Position(0,0), new Position(5,7));
		boolean playerInCheck = newGame.isPlayerInCheck(color.BLACK);
		assertEquals("Should be in check", playerInCheck, true);
	}
	
	@Test
	public void shouldBeInCheckMate() {
		Game newGame = new Game();
		for(int i = 0; i<8; i++)
		{
			if(i != 3 && i != 0)
			{
				newGame.board.deletePieceFromBoard(i, 0);
				newGame.board.deletePieceFromBoard(i, 7);
			}
			
			newGame.board.deletePieceFromBoard(i, 1);
			newGame.board.deletePieceFromBoard(i, 6);
		}
		newGame.board.deletePieceFromBoard(0, 7);
		newGame.executeMove(new Position(3,7), new Position(7,7));
		newGame.executeMove(new Position(3,0), new Position(7,5));
		newGame.executeMove(new Position(0,0), new Position(5,7));
		boolean playerInCheckMate = newGame.isPlayerInCheckMate(color.BLACK);
		assertEquals("Should be in check Mate", playerInCheckMate, true);
	}
	
	@Test
	public void startingBoardShouldNotBeInStaleMate() {
		Game newGame = new Game();
		boolean staleMate = newGame.staleMate(color.BLACK);
		assertEquals("isPlayerInStaleMate should be false", staleMate, false);
	}
}
