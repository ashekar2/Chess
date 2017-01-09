package boardAndLogic;

import javax.swing.JButton;

/**
 * Class that extens the Jbutton class to add location of the button in the grid as well.
 * @author ajayshekar
 *
 */
public class GUITile extends JButton{
	public Position currPosition;
	
	/**
	 * Default constructor that initializes the button and sets the x and y coordiantes o the button.
	 * @param x
	 * @param y
	 */
	public GUITile(int x, int y)
	{
		super();
		currPosition = new Position(x,y);
	}
	
	/**
	 * Returns the x coordinate of the button.
	 * @return X coordinate
	 */
	public int getXPosition()
	{
		return currPosition.getXPosition();
	}
	
	/**
	 * Returns the y coordinate of the button.
	 * @return Y coordinate
	 */
	public int getYPosition()
	{
		return currPosition.getYPosition();
	}
}
