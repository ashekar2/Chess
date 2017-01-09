package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.Queen;

public class QueenTest {

	@Test
	public void QueenMoveForward() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void QueenMoveForwardToEnemyKing() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(4,0);
		Position end = new Position(3,7);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, false);
	}
	
	@Test
	public void QueenMoveBackward() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(4,2);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void QueenMoveRight() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(5,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void QueenMoveLeft() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(4,4);
		Position end = new Position(3,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void QueenMoveDiagnol1() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(6,6);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void QueenMoveDiagnol2() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void QueenMoveDiagnol3() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(2,4);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return true", canMove, true);
	}
	
	@Test
	public void cannotMoveLikeKnight() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(4,5);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void absureMove() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(0,1);
		ChessBoard notRelevant = new ChessBoard();
		boolean canMove = newQueen.validMoveFashion(start, end, notRelevant);
		assertEquals("moveFashion should return false", canMove, false);
	}
	
	@Test
	public void QueenNoLinearObstruction() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(4,3);
		ChessBoard board = new ChessBoard();
		boolean canMove = newQueen.isMoveObstructed(start, end, board);
		assertEquals("isMoveObstructed returns false", canMove, false); //no obstruction
	}
	
	@Test
	public void QueenLinearObstructionPresent() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(5,3);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,7);
		Position moveE = new Position(4,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newQueen.isMoveObstructed(start, end, board);
		assertEquals("isMoveObstructed returns true", canMove, true); //obstruction present
	}
	
	@Test
	public void QueenNoDiagnolObstruction() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(2,2);
		Position end = new Position(5,5);
		ChessBoard board = new ChessBoard();
		boolean canMove = newQueen.isMoveObstructed(start, end, board);
		assertEquals("isMoveObstructed returns false", canMove, false); //no obstruction
	}
	
	@Test
	public void QueenDiagnol1ObstructionPresent() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(6,6);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(3,7);
		Position moveE = new Position(4,4);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newQueen.isMoveObstructed(start, end, board);
		assertEquals("isMoveObstructed returns true", canMove, true); //obstruction present
	}
	
	@Test
	public void QueenDiagnol2ObstructionPresent() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,3);
		Position end = new Position(1,1);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(0,6);
		Position moveE = new Position(2,2);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newQueen.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, true); //obstruction present
	}
	
	@Test
	public void QueenDiagnol3ObstructionPresent() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(0,5);
		Position end = new Position(3,2);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(1,7);
		Position moveE = new Position(2,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newQueen.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, true); //obstruction present
	}
	
	@Test
	public void QueenDiagnol4ObstructionPresent() {
		Queen newQueen = new Queen(color.WHITE);
		Position start = new Position(3,2);
		Position end = new Position(0,5);
		ChessBoard board = new ChessBoard();
		Position moveS = new Position(1,7);
		Position moveE = new Position(2,3);
		board.changePieceLocation(moveS, moveE);
		boolean canMove = newQueen.isMoveObstructed(start, end, board);
		assertEquals("Should be able to move", canMove, true); //obstruction present
	}
	
}
