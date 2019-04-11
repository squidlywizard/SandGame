package Sand;
import java.awt.*;
import java.util.*;
//import Sand.SandDisplay;


public class SandLab
{
  //Step 4,6
  //add constants for particle types here
  public static final int EMPTY = 0;
  public static final int METAL = 1;
  public static final int SAND = 2;
  public static final int WATER = 3;
  
  //do not add any more fields below
  private int[][] grid;
  private SandDisplay display;
  
  
  /**
   * Constructor for SandLab
   * @param numRows The number of rows to start with
   * @param numCols The number of columns to start with;
   */
  public SandLab(int numRows, int numCols)
  {
    String[] names;
    // Change this value to add more buttons
    //Step 4,6
    names = new String[4];
    // Each value needs a name for the button
    names[EMPTY] = "Empty";
    names[METAL] = "Metal";
    names[SAND]= "sand";
    names[WATER]= "water";
    
    //1. Add code to initialize the data member grid with same dimensions
    this.grid = new int[numRows][numCols];
    
    display = new SandDisplay("Falling Sand", numRows, numCols, names);
  }
  
  //called when the user clicks on a location using the given tool
  private void locationClicked(int row, int col, int tool)
  {
    //2. Assign the values associated with the parameters to the grid
   grid[row][col] = tool;
  }

  //copies each element of grid into the display
  public void updateDisplay()
  {
      //Step 3
	  for(int I = 0; I<grid[0].length;I++)
	  {
		  for(int O = 0; O<grid.length; O++)
	  {
		 if(grid[I][O] == 0)
		 {
			 display.setColor(I, O, Color.BLACK);
		 }
		 else if(grid[I][O] == 1)
		 {
			 display.setColor(I, O, Color.GRAY);
		 }
		 else if(grid[I][O] == 2)
		 {
			 display.setColor(I, O, Color.ORANGE);
		 }
		 else
		 {
			 display.setColor(I, O, Color.BLUE);
		 }
	  }
		  
	  }
   //Hint - use a nested for loop
    
  }

  //Step 5,7
  //called repeatedly.
  //causes one random particle in grid to maybe do something.
  public void step()
  {
    //Remember, you need to access both row and column to specify a spot in the array
    //The scalar refers to how big the value could be
    int someRandomRow = (int) (Math.random() * grid[0].length) ;
    //int waterRandomRow = (int) (Math.random() *grid[0].length);
    
//    if(someRandomRow > 18)
//    {
//    	someRandomRow--;
//    }
    int someRandomCol = (int) (Math.random() * grid.length);
    int waterDirection = (int) (Math.random() * 2);
    if(someRandomCol == 19 && waterDirection== 1)
    {waterDirection--;
    	
    }
    if(someRandomCol == 0 && waterDirection == 0)
    {
    	waterDirection++;
    }
    
    if(!(someRandomRow == 19) && grid[someRandomRow][someRandomCol]== 2 && grid[someRandomRow + 1][someRandomCol] == 0 )
    {
    	
    	grid[someRandomRow][someRandomCol] = 0;
    	grid[someRandomRow + 1][someRandomCol]=2;
    	
    	
    }
    if(!(someRandomRow == 19) &&grid[someRandomRow][someRandomCol]== 3 && grid[someRandomRow + 1][someRandomCol] == 0  )
    {
    	
    	grid[someRandomRow][someRandomCol] = 0;
    	grid[someRandomRow + 1][someRandomCol]=3;
    	
    	
    }
    
    else if(grid[someRandomRow][someRandomCol]==3 && waterDirection == 0 && grid[someRandomRow][someRandomCol -1]== 0)
    {
   	
    	grid[someRandomRow][someRandomCol] = 0;
    	grid[someRandomRow][someRandomCol -1] =3;
    }
    else if(grid[someRandomRow][someRandomCol]==3 && waterDirection == 1 && grid[someRandomRow][someRandomCol +1]== 0 )
    {
   	
    	grid[someRandomRow][someRandomCol] = 0;
    	grid[someRandomRow][someRandomCol +1] =3;
    }
    //remember that you need to watch for the edges of the array
    
    
  }
  
  //do not modify this method!
  public void run()
  {
    while (true) // infinite loop
    {
      for (int i = 0; i < display.getSpeed(); i++)
      {
        step();
      }
      updateDisplay();
      display.repaint();
      display.pause(1);  //wait for redrawing and for mouse
      int[] mouseLoc = display.getMouseLocation();
      if (mouseLoc != null)  //test if mouse clicked
      {
        locationClicked(mouseLoc[0], mouseLoc[1], display.getTool());
      }
    }
  }
}
