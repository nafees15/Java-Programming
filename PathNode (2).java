public class PathNode 
{
  int x; int y; PathNode prev;
  PathNode(int px,int py,PathNode pprev)
  { 
    x = px; y = py; prev = pprev; 
  }
  void printForwards()
  { 
    if(prev!=null) 
    {
      prev.printForwards();
      System.out.print(", ");
    }
    printOne();
  }
  public void printOne()
  { System.out.print("("+x+","+y+")"); 
  }
  
}