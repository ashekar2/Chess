package chess;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.border.*;

import boardAndLogic.ChessBoard;
import boardAndLogic.GUITile;
import boardAndLogic.Game;
import boardAndLogic.Player;
import boardAndLogic.Position;
import boardAndLogic.chessPiecesTypes;
import boardAndLogic.color;
import chess.allPieces.Piece;

// CITATION: http://stackoverflow.com/questions/21077322/create-a-chess-board-with-jpanel

/***
 * Class in order to generate GUI for the chess board
 * @author ajayshekar
 */

public class BoardGUI {
	private JFrame frame = new JFrame();
	private JPanel panel;
	Controller control;
	private int startX,startY, endX, endY;

	private GUITile allTiles[][]; 
	
	private JButton p1 , p2, undoButton;
	String name1, name2;
	private Color storeStart;
	private int customGame = 0;

	/***
	 * BoardGUI constructor to create the GUI
	 * @param board - board for which GUI is built
	 */
	public BoardGUI(Game chessGame , Controller control)
	{
		customGame = Integer.parseInt(JOptionPane.showInputDialog("Would you like to play custom game of chess? 1 - yes; 2 - no"));
		if(customGame == 1)
		{
			control.setUpCustomBoard();
		}
		this.control = control;
		startX = -1; startY = -1;
		endX = -1; endY = -1;
		int width = chessGame.board.getBoardWidth();
		int height = chessGame.board.getBoardHeight();
		allTiles = new GUITile[width][height];
		frame.setSize(800, 800);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Ajay's Chess Board!");
		frame.setBackground(Color.BLACK);
		
		GridLayout grid = new GridLayout(height + 1,width,0,0);
		panel = new JPanel(grid);
		panel.setBorder(new LineBorder(Color.BLACK));
		
		buildFromBoard(chessGame);
		
		JButton resetButton = new JButton();
		resetButton.setText("RESET GAME");
		resetButton.setForeground(Color.ORANGE);
		resetButton.addActionListener(resetGame());
		panel.add(resetButton);
		
		name1 = JOptionPane.showInputDialog("Please input name of player 1");
		name2 = JOptionPane.showInputDialog("Please input name of player 2");
		
		undoButton = new JButton("Undo Move");
		undoButton.setOpaque(true);
		undoButton.setBorder(null);
		undoButton.setBackground(Color.pink);
		undoButton.addActionListener(undoMove());
		panel.add(undoButton);
		
		for(int i = 0; i<1; i++)
		{
			JButton empty = new JButton();
			empty.setOpaque(true);
			empty.setBorder(null);
			panel.add(empty);
		}
		
		setPlayerButtons();
		
		for(int i = 0; i<2; i++)
		{
			JButton empty = new JButton();
			empty.setOpaque(true);
			empty.setBorder(null);
			panel.add(empty);
		}
		
		createForfeitButton();
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
	/**
	 * Helper function to create forfeit button
	 */
	private void createForfeitButton()
	{
		JButton forfeitButton = new JButton();
		forfeitButton.setText("FORFEIT");
		forfeitButton.setForeground(Color.RED);
		forfeitButton.addActionListener(forfeitGame());
		panel.add(forfeitButton);
	}
	
	
	/**
	 * Helper function to create player buttons for score and name
	 */
	private void setPlayerButtons()
	{
		p1 = new JButton();
		p1.setOpaque(true);
		p1.setBorder(null);
		p1.setText(name1 + "=0");
		p1.setBackground(Color.ORANGE);
		panel.add(p1);
		
		p2 = new JButton();
		p2.setOpaque(true);
		p2.setBorder(null);
		p2.setText(name2 + "=0");
		panel.add(p2);
	}
	
	
	/**
	 * Function to set the score of current player
	 * @param score - score of player
	 * @param playerTurn - current player
	 */
	public void setScore(int score, int playerTurn)
	{
		if(playerTurn == 2)
		{
			p2.setText(name2 + "=" + score);
		}
		else
		{
			p1.setText(name1 + "=" + score);
		}
	}
	
	/**
	 * Function to show popup for player in check.
	 */
	public void showCheck()
	{
		String name = name1;
		if(p2.getBackground() == Color.ORANGE)
		{
			name = name2;
		}
		JOptionPane.showMessageDialog(null, name + " is in Check!");
	}
	
	/**
	 * Function to show popup for player in check mate.
	 */
	public void showCheckMate()
	{
		String name = name1;
		if(p2.getBackground() == Color.ORANGE)
		{
			name = name2;
		}
		JOptionPane.showMessageDialog(null, name + " is in CheckMate! Starting Next Game.");
	}
	
	/**
	 * Action listener function that calls the resetGame function in the controller. This communicates between view and control.
	 * @return An action listener function
	 */
	private ActionListener resetGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	String inputFromUser = JOptionPane.showInputDialog("Are both players okay with resetting the game?");
            	if(inputFromUser.toLowerCase().equals("yes") || inputFromUser.toLowerCase().equals("y"))
            	{
            		
            		control.resetGame();
            	}
            }

        };
    }
	
	/**
	 * Action listener function that calls the forfeitGame function in the controller. This communicates between view and control.
	 * @return An action listener function
	 */
	private ActionListener forfeitGame() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
            	control.forfeitGame();
           	}
        };
    }
	
	/**
	 * Action listener function that calls the makeMove function in the controller. This communicates between view and control.
	 * @return An action listener function
	 */
	private ActionListener makeMove(int x, int y) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) 
            {
                Position start, end;
            	if (startX == -1 && startY == -1) 
                {
                    startX = x;
                    startY = y;
                    storeStart = allTiles[x][y].getBackground();
                    allTiles[x][y].setBackground(Color.GREEN);
                    return;
                }
                endX = x;
                endY = y;
                start = new Position(startX, startY);
                end = new Position(endX, endY);
                String status = control.makeMove(start, end);
                allTiles[startX][startY].setBackground(storeStart);
                startX = -1;
                startY = -1;
                endX = -1;
                endY = -1;
             }
        };
    }
	
	/**
	 * Action listener function that calls the undo function in the controller. This communicates between view and control.
	 * @return An action listener function
	 */
	private ActionListener undoMove() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
            	control.undoMove();
            }

        };
    }
	
/**
 * Function that sets the active player to currentPlayer and change the tile background accordingly.
 * @param currPlayer
 */
	public void setActive(int currPlayer)
	{
		if(currPlayer == 1)
		{
			p1.setBackground(Color.ORANGE);
    		p2.setBackground(Color.WHITE);
		}
		else
		{
			p2.setBackground(Color.ORANGE);
    		p1.setBackground(Color.WHITE);
		}
	}
	
	/**
	 * Function to close the window.
	 */
	public void closeFrame()
	{
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}
	
	/**
	 * Function to reset all pieces on the chess board according to the board specified in the Game.
	 * @param chessGame gui built according to the game.
	 */
	public void resetPieces(Game chessGame)
	{
		for(int i = 0 ; i< chessGame.board.getBoardHeight() ; i++)
		{
			for(int j = 0 ;j < chessGame.board.getBoardWidth(); j++)
			{
				Piece currPiece = chessGame.board.getPiece(i, j);
				Icon currIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				if(currPiece != null)
				{
					currIcon = getIcon(currPiece);
				}
				allTiles[i][j].setIcon(currIcon);
				allTiles[i][j].setOpaque(true);
				allTiles[i][j].setBorder(null);
			}
		}
	}
	
	/**
	 * Function that builds the gui from the board in chessGame
	 * @param chessGame - baord that gui is build on.
	 */
	public void buildFromBoard(Game chessGame)
	{
		int flag = 1;
		ChessBoard currBoard = chessGame.board;
		
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j<8; j++)
			{
				Icon currIcon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
				Piece currPiece = currBoard.getPiece(j,7-i);
				if(currPiece != null)
				{
					currIcon = getIcon(currPiece);
				}
				GUITile boardTile = initializeGUITile(currIcon, j, 7-i, flag);
				allTiles[j][7-i] = boardTile;
				flag = Math.abs(flag - 1);
				boardTile.addActionListener(makeMove(j,7-i));
				panel.add(boardTile);
			}
			flag = Math.abs(flag - 1);
		}
	}
	
	/**
	 * Function to help intialize the tiles of the chess pieces and their appropriate icons.
	 * @param currIcon Icon of the tile
	 * @param i x location of the tile
	 * @param j y location of the tile
	 * @param flag flag to decide black or white
	 * @return GUITile object
	 */
	private GUITile initializeGUITile(Icon currIcon, int i, int j, int flag)
	{
		GUITile boardTile = new GUITile(i, j);
		boardTile.setIcon(currIcon);
		boardTile.setOpaque(true);
		boardTile.setBorder(null);
		if(flag == 0)
		{
			boardTile.setBackground(Color.LIGHT_GRAY);
			boardTile.setForeground(Color.LIGHT_GRAY);
			flag = 1;
		}
		else
		{
			boardTile.setBackground(Color.WHITE);
			boardTile.setForeground(Color.WHITE);
			flag = 0;
		}
		return boardTile;
	}
	
	/**
	 * Function the change the icon in the gui from start to end
	 * @param start Start position
	 * @param end End position
	 */
	public void changePieceLocation(Position start, Position end)
	{
		
		int startX = start.getXPosition(), startY = start.getYPosition();
		int endX = end.getXPosition(), endY = end.getYPosition();
		Icon startIcon = allTiles[startX][startY].getIcon();
		allTiles[endX][endY].setIcon(startIcon);
		allTiles[endX][endY].setOpaque(true);
		allTiles[endX][endY].setBorder(null);
		allTiles[startX][startY].setIcon(new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB)));
		allTiles[startX][startY].setOpaque(true);
		allTiles[startX][startY].setBorder(null);
 	}
	
	/**
	 * Function to set the board tile to the new icon
	 * @param x x position of the tile
	 * @param y y position of the tile
	 * @param currPiece Piece on the tile
	 */
	public void setBoardTile(int x, int y, Piece currPiece)
	{
		Icon pieceIcon = getIcon(currPiece);
		allTiles[x][y].setIcon(pieceIcon);
	}
	
	/**
	 * Function to convert Piece to Icon
	 * @param currPiece Current Piece
	 * @return Icon of the piece
	 */
	private Icon getIcon(Piece currPiece)
	{
		//KING, ROOK, BISHOP, QUEEN, KNIGHT, PAWN, BHUTRA, VIKU;
		if(currPiece == null)
		{
			return new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		}
		
		String imgLocation = "../images/";
		chessPiecesTypes type = currPiece.getPieceType();
		switch(type) 
		{
			case KING:
				imgLocation += "king";
				break;
			case ROOK:
				imgLocation += "rook";
				break;
			case BISHOP:
				imgLocation += "bishop";
				break;
			case QUEEN:
				imgLocation += "queen";
				break;
			case KNIGHT:
				imgLocation += "knight";
				break;
			case PAWN:
				imgLocation += "pawn";
				break;
			case VIKU:
				imgLocation += "viku";
				break;
			case BHUTRA:
				imgLocation += "knightOfBhutra";
				break;
			default:
				break;
		}
		
		if(currPiece.getPieceColor() == color.BLACK)
		{
			imgLocation += "_black.png";
		}
		else
		{
			imgLocation += "_white.png";
		}
		Icon icon = null;
		try {
			Image img = ImageIO.read(getClass().getResource(imgLocation));
			icon = new ImageIcon(img);
		} catch (IOException ex) {}
		
		return icon;
	}
	
}
