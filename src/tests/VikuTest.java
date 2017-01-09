package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.Viku;

public class VikuTest {

	@Test
	public void VikuMoveTwoUpOneRight() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void VikuMoveTwoRightOneUp() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(6,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void VikuMoveTwoLeftOneUp() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(2,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void VikuMoveTwoLeftOneDown() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(2,3);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be true", canMove, true);
	}
	
	@Test
	public void VikuCannotMoveDiagnol() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}
	
	@Test
	public void justAnAbsurdMoveForKnight() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(20,-30);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should be false", canMove, false);
	}

	@Test
	public void moveForward() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveBackward() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,2);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveRight() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveLeft() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(3,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveDiagnol1() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(6,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void moveDiagnol2() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void moveDiagnol3() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(2,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void justAnAbsurdMoveForRook() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(0,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newViku.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void noObstruction1() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(5,3);
		ChessBoard board = new ChessBoard();
		boolean canMove = newViku.isMoveObstructed(start, end, board);
		assertEquals("No obstruction in path", canMove, false); //no obstruction
	}
	
	@Test
	public void obstructionPresent1() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(5,3);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(4,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newViku.isMoveObstructed(start, end, board);
		assertEquals("Viku cannot jump over pieces!", canMove, true); //obstruction present
	}
	
	@Test
	public void obstructionPresent2() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,3);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(2,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newViku.isMoveObstructed(start, end, board);
		assertEquals("Viku cannot jump over pieces!", canMove, true); //obstruction present
	}
	
	@Test
	public void obstructionPresent3() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(3,5);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(3,4);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newViku.isMoveObstructed(start, end, board);
		assertEquals("Viku cannot jump over pieces!", canMove, true); //obstruction present
	}
	
	@Test
	public void obstructionPresent4() {
		Viku newViku = new Viku(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(3,1);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(3,2);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newViku.isMoveObstructed(start, end, board);
		assertEquals("Viku cannot jump over pieces!", canMove, true); //obstruction present
	}

}
