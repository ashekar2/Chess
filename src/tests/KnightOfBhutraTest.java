package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.KnightOfBhutra;

public class KnightOfBhutraTest {

	@Test
	public void moveTwoUpOneRight() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void moveTwoRightOneUp() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(6,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void moveTwoLeftOneUp() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(2,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void moveTwoLeftOneDown() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(2,3);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void moveFourRightTwoUp() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(2,2);
		Position end = new Position(6,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void moveThreeRightSixUp() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(2,1);
		Position end = new Position(5,7);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void cannotMoveDiagnol() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}
	
	@Test
	public void cannotMoveStraight() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}
	
	@Test
	public void justAnAbsurdMove() {
		KnightOfBhutra newKnight = new KnightOfBhutra(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(20,-30);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newKnight.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}
}
