package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.King;

public class KingTest {

	@Test
	public void KingMoveFrontShouldMoveFront() {
		King newKing = new King(color.BLACK);
		Position start = new Position(4,4);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKing.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion returns true", canMove, true);
	}
	
	@Test
	public void KingShouldMoveDiagnol() {
		King newKing = new King(color.BLACK);
		Position start = new Position(4,4);
		Position end = new Position(5,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKing.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion returns true", canMove, true);
	}
	
	@Test
	public void KingShouldMoveBackDiagnol() {
		King newKing = new King(color.BLACK);
		Position start = new Position(4,4);
		Position end = new Position(3,3);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKing.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion returns true", canMove, true);
	}
	
	@Test
	public void KingShouldNotJumpTwoAhead() {
		King newKing = new King(color.BLACK);
		Position start = new Position(4,4);
		Position end = new Position(4,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKing.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion returns false", canMove, false);
	}
	
	@Test
	public void KingCannotStayInSameSpot() {
		King newKing = new King(color.BLACK);
		Position start = new Position(4,4);
		Position end = new Position(4,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKing.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion returns false", canMove, false);
	}

}
