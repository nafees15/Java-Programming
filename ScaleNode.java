/* Author -- Nafees Noor
 * CSI 310
 * Assignment 7
 * This class makes the scale for the wolf
 * for the WolfAttackMovie class
 * */

public class ScaleNode extends BlueScreenNode
{
   
double scale;  

public ScaleNode(Picture p) // Constructor of this class
{
  super(p);
  this.scale = 1;
}

public double getScale()  // Getscale to set up the scale
{
  return this.scale;
}

public void setScale(double scale) // setting up the scale
{
  this.scale = scale;
}

public void drawWith(Turtle theTurtle) // draw with method
{
   Picture bg = theTurtle.getPicture();
    Picture myPict = this.getPicture().scale(scale);
    myPict.blueScreen(bg,theTurtle.getXPos(),
                      theTurtle.getYPos());
}



}