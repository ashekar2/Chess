package boardAndLogic;
/***
 * Player class.
 * @author ajayshekar
 *
 */
public class Player {
	private color playerColor;
	private boolean playerInCheck;
	private int score;
	private String name;
	
	/**
	 * Constructor to create player object
	 * @param playerColor Color of player
	 */
	public Player(color playerColor)
	{
		this.score = 0;
		this.playerColor = playerColor;
		this.playerInCheck = false;
	}
	
	/**
	 * Function to set the name of current player
	 * @param name Player's new name
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Function to get the current players name
	 * @return Name of current Player
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Function to return whether the player is in check or not.
	 * @return Returns true if player is in check. False if not.
	 */
	public boolean returnCheckStatus()
	{
		return this.playerInCheck;
	}
	
	/**
	 * Function to set the current player check value to true.
	 */
	public void setPlayerCheckTrue()
	{
		this.playerInCheck = true;
	}
	
	/**
	 * Function to set the current player check value to false.
	 */
	public void setPlayerCheckFalse()
	{
		this.playerInCheck = false;
	}
	
	/**
	 * Function to get the color of the current player.
	 * @return Current player color.
	 */
	public color getPlayerColor()
	{
		return playerColor;
	}
	
	/**
	 * Function to get score of player.
	 * @return score as int
	 */
	public int getScore()
	{
		return this.score;
	}
	
	/**
	 * Function to increment score of current player.
	 */
	public void incrementScore()
	{
		this.score = this.score + 1;
	}
	
}
