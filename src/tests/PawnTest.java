package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.Pawn;

public class PawnTest {

	@Test
	public void constructorTest() {
		Pawn newPawn = new Pawn(color.BLACK);
		assertEquals("Pawn color must be black", color.BLACK, newPawn.getPieceColor());
	}
	
	@Test
	public void pawnShouldMoveForwardWhite() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		Position start = new Position(1,2);
		Position end = new Position(1,3);
		boolean checkMove = newPawn.validMoveFashion(start, end, newBoard);
		assertEquals("moveFashion returns true", checkMove, true);
	}
	
	@Test
	public void pawnShouldNotMoveBackwardWhite() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.BLACK);
		Position start = new Position(1,6);
		Position end = new Position(1,5);
		boolean checkMove = newPawn.validMoveFashion(start, end, newBoard);
		assertEquals("moveFashion returns false", checkMove, false);
	}
	
	@Test
	public void pawnShouldMoveForwardBlack() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		Position start = new Position(1,6);
		Position end = new Position(1,5);
		boolean checkMove = newPawn.validMoveFashion(start, end, newBoard);
		assertEquals("moveFashion returns true", checkMove, true);
	}
	
	@Test
	public void pawnShouldNotMoveBackwardBlack() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.BLACK);
		Position start = new Position(1,6);
		Position end = new Position(1,7);
		boolean checkMove = newPawn.validMoveFashion(start, end, newBoard);
		assertEquals("moveFashion returns false", checkMove, false);
	}
	
	@Test
	public void pawnShouldMoveDiagnol() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		Position start = new Position(1,1);
		Position end = new Position(2,2);
		Position movePieceStart = new Position(1,6);
		Position movePieceEnd = new Position(2,2);
		newBoard.changePieceLocation(movePieceStart, movePieceEnd);
		boolean checkMove = newPawn.validMoveFashion(start, end, newBoard);
		assertEquals("moveFashion returns true", checkMove, true);
	}
	
	@Test
	public void pawnShouldNotMoveDiagnol() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		Position start = new Position(1,1);
		Position end = new Position(2,2);
		boolean checkMove = newPawn.validMoveFashion(start, end, newBoard);
		assertEquals("moveFashion returns false", checkMove, false);
	}

}
