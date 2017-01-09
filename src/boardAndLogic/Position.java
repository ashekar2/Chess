package boardAndLogic;

/**
 * Class that defines the coordinates for any location on the chess board.
 * @author ajayshekar
 *
 */
public class Position {
	private int x,y;	
	
	/**
	 * Constructor for creating new Position Object
	 * @param newX - x position
	 * @param newY - y position
	 */
	public Position(int newX, int newY){
		x = newX;
		y = newY;
	}
	
	public Position(Position copy)
	{
		x = copy.getXPosition();
		y = copy.getYPosition();
	}
	
	/**
	 * Function to change the x,y values to newX,newY
	 * @param newX - new value of x coord
	 * @param newY - new value of y coord
	 */
	public void setPosition(int newX, int newY){
		x = newX;
		y = newY;
	}
	
	/**
	 * Function to get the x position
	 * @return X position
	 */
	public int getXPosition(){
		return x;
	}
	
	/**
	 * Function to get the y position
	 * @return Y position
	 */
	public int getYPosition(){
		return y;
	}
};