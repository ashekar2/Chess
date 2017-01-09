package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.Rook;

public class RookTest {

	@Test
	public void moveForward() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveBackward() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,2);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveRight() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveLeft() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(3,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void moveDiagnol1() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(6,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void moveDiagnol2() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void moveDiagnol3() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(2,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void cannotMoveLikeKnight() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void justAnAbsurdMove() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(0,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newRook.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void noObstruction1() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(5,3);
		ChessBoard board = new ChessBoard();
		boolean canMove = newRook.isMoveObstructed(start, end, board);
		assertEquals("No obstruction in path", canMove, false); //no obstruction
	}
	
	@Test
	public void obstructionPresent1() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(5,3);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(4,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newRook.isMoveObstructed(start, end, board);
		assertEquals("Rook cannot jump over pieces!", canMove, true); //obstruction present
	}
	
	@Test
	public void obstructionPresent2() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,3);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(2,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newRook.isMoveObstructed(start, end, board);
		assertEquals("Rook cannot jump over pieces!", canMove, true); //obstruction present
	}
	
	@Test
	public void obstructionPresent3() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(3,5);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(3,4);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newRook.isMoveObstructed(start, end, board);
		assertEquals("Rook cannot jump over pieces!", canMove, true); //obstruction present
	}
	
	@Test
	public void obstructionPresent4() {
		Rook newRook = new Rook(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(3,1);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(3,2);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newRook.isMoveObstructed(start, end, board);
		assertEquals("Rook cannot jump over pieces!", canMove, true); //obstruction present
	}

}
