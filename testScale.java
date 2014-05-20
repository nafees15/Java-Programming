public class ScaleNode extends BlueScreenNode
{
   
double scale;  
public ScaleNode(Picture p)
{
  super(p);
  this.scale = 1;
}


public double getScale()
{
  return this.scale;
}

public void setScale(double scale)
{
  this.scale = scale;
}

public void drawWith(Turtle theTurtle)
{
   Picture bg = theTurtle.getPicture().scale(scale);
    Picture myPict = this.getPicture();
    myPict.blueScreen(bg,theTurtle.getXPos(),
                      theTurtle.getYPos());
}



}