package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.Bishop;

public class BishopTest {

	@Test
	public void BishopMoveForward() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should not be able to move", canMove, false);
	}
	
	@Test
	public void BishopMoveBackward() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,2);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should not be able to move", canMove, false);
	}
	
	@Test
	public void BishopMoveRight() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should not be able to move", canMove, false);
	}
	
	@Test
	public void BishopMoveLeft() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(3,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should not be able to move", canMove, false);
	}
	
	@Test
	public void BishopMoveDiagnol1() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(6,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should be able to move", canMove, true);
	}
	
	@Test
	public void BishopMoveDiagnol2() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should be able to move", canMove, true);
	}
	
	@Test
	public void BishopMoveDiagnol3() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(2,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should be able to move", canMove, true);
	}
	
	@Test
	public void BishopMoveIllegal1() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should not be able to move", canMove, false);
	}
	
	@Test
	public void BishopMoveIllegal2() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(0,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newBishop.validMoveFashion(start, end, notRelevant);
		assertEquals("Should not be able to move", canMove, false);
	}
	
	@Test
	public void BishopNoDiagnolObstruction1() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(2,2);
		Position end = new Position(5,5);
		ChessBoard board = new ChessBoard();
		boolean canMove = newBishop.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, false); //no obstruction
	}
	
	@Test
	public void BishopDiagnolObstructionPresent1() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(6,6);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(3,7);
		Position moveE = new Position(4,4);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newBishop.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, true); //obstruction present
	}
	
	@Test
	public void BishopDiagnolObstructionPresent2() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,1);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,6);
		Position moveE = new Position(2,2);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newBishop.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, true); //obstruction present
	}
	
	@Test
	public void BishopDiagnolObstructionPresent3() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(0,5);
		Position end = new Position(3,2);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(1,7);
		Position moveE = new Position(2,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newBishop.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, true); //obstruction present
	}
	
	@Test
	public void BishopDiagnolObstructionPresent4() {
		Bishop newBishop = new Bishop(color.WHITE);
		Position start = new Position(3,2);
		Position end = new Position(0,5);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(1,7);
		Position moveE = new Position(2,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newBishop.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, true); //obstruction present
	}

}
