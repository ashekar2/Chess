package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import boardAndLogic.ChessBoard;
import boardAndLogic.Position;
import boardAndLogic.color;
import chess.allPieces.Pawn;

public class ChessBoardTest {
	
	@Test
	public void testNumberOfPieces() {
		ChessBoard newBoard = new ChessBoard();
		assertEquals("checking number of black pieces", 16, newBoard.getNumberOfPieces(color.BLACK));
	}
	
	@Test
	public void testGetKingPosition() {
		ChessBoard newBoard = new ChessBoard();
		Position correctPosition = new Position(3,7);
		assertEquals("checking black king position x", correctPosition.getXPosition(), newBoard.getKingPosition(color.BLACK).getXPosition());
		assertEquals("checking black king position y", correctPosition.getYPosition(), newBoard.getKingPosition(color.BLACK).getYPosition());
	}
	
	@Test
	public void moveToSameColorPiece() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		boolean check = newBoard.isMoveWithinBounds(4,1, newPawn);
		assertEquals("Cannot Move to position with same color piece", check, false);
	}
	
	@Test
	public void moveToOutOfBoard() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		boolean check = newBoard.isMoveWithinBounds(40,1, newPawn);
		assertEquals("Outside board!", check, false);
	}
	
	@Test
	public void moveWhitePieceToBlackPiece() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		boolean check = newBoard.isMoveWithinBounds(0,6, newPawn);
		assertEquals("verify move possible", check, true);
	}
	
	@Test
	public void moveWhitePieceToWhitePiece() {
		ChessBoard newBoard = new ChessBoard();
		Pawn newPawn = new Pawn(color.WHITE);
		boolean check = newBoard.isMoveWithinBounds(0,1, newPawn);
		assertEquals("verify move not possible", check, false);
	}
	
	@Test
	public void deletePieceTest() {
		ChessBoard newBoard = new ChessBoard();
		newBoard.deletePieceFromBoard(0, 0);
		newBoard.deletePieceFromBoard(0, 1);
		newBoard.deletePieceFromBoard(1, 0);
		newBoard.deletePieceFromBoard(2, 0);
		newBoard.deletePieceFromBoard(3, 0);
		newBoard.deletePieceFromBoard(7, 7);
		newBoard.deletePieceFromBoard(6, 7);
		int size = newBoard.getAllPiecesWithColor(color.BLACK).size();
		assertEquals("verify number of pieces is correct", size, 14);
	}
}
