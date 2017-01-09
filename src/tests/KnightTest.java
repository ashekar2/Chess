package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.Knight;

public class KnightTest {

	@Test
	public void KnightMoveTwoUpOneRight() {
		Knight newKnight = new Knight(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void KnightMoveTwoRightOneUp() {
		Knight newKnight = new Knight(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(6,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void KnightMoveTwoLeftOneUp() {
		Knight newKnight = new Knight(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(2,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void KnightMoveTwoLeftOneDown() {
		Knight newKnight = new Knight(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(2,3);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void KnightCannotMoveDiagnol() {
		Knight newKnight = new Knight(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}
	
	@Test
	public void KnightCannotMoveStraight() {
		Knight newKnight = new Knight(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}
	
	@Test
	public void justAnAbsurdMove() {
		Knight newKnight = new Knight(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(20,-30);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}

}
