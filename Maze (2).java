/* Author -- Nafees Noor
 * CSI 310
 * Assignment 8 - Part 1
 * This program finds at least one path of maze
 * and printing out the path after get to the solution
 * */
import java.util.*;
public class Maze
{
  static Scanner sc = new Scanner(System.in);
  
  // Fields
  private Square A[][];
  private int size;
  int pathsFound;
  char M[][];
  char sar = 'A';
  int c,r;
  //width
  //height
  int sx, sy;
  //start
  //square
  int gx,gy;
  //goal
  //square
  // End of the fields
  
  class Square
  {
    int Filled;
    char Mark; 
  }
  
  Maze(int col, int row)
  {
    c= col;
    r= row;
    M = new char[c][r];
    A=new Square[c][r]; 
    for(int p =0; p <c; p++)
    {
      for(int z =0; z <r; z++)
      {
        A[p][z] = new Square();
        
      }
    } 
  }
  
  public int getSx()
  {
    return sx;
  }
  
  public int getSy()
  {
    return sy;
  }
  
  
  public static void main(String[] zzzz)
  { 
    findPath();
    
  }  
  
  public static void findPath()
  {
    System.out.println("Please enter the row and column of the maze");
    int col = sc.nextInt();
    int row = sc.nextInt();
    
    Maze maz = new Maze(col,row);
    
    System.out.println("Enter the cordinates where the maze will start");
    maz.sx = sc.nextInt();
    maz.sy = sc.nextInt();
    
    System.out.println("Now enter the cordinates the target cordinates where the maze will end");
    maz.gx = sc.nextInt();
    maz.gy = sc.nextInt();
    
    
    for(int y = 0; y < maz.r; y++)
      for(int x=0; x <maz.c; x++)
      maz.A[x][y].Filled = sc.nextInt(); //inputing the maze in the square
    
    // Initializing the M array
    for(int h =0; h < maz.r; h++)
    {
      for(int g =0; g < maz.c; g++)
      {
        
        maz.M[g][h]= maz.sar;
      }
    }    
    for(int a =0;a <maz.r; a++)
    {
      for(int b =0; b <maz.c; b++)
      {
        System.out.print(maz.A[b][a].Filled);
      }
      System.out.println();
    }     
    
    PathNode start = new PathNode(maz.sx,maz.sy,null);
    maz.printSolution(start);   
    
    maz.findDists();
  }
  
  // Finding the shortest distance
  public int extfindDists( PathNode pLast )        //method to find shortest paths and print them if found
  {
     if(pLast.x == gx && pLast.y == gy )          //test if coords of current square are the goal square
    { 
      pathsFound++;                                   //increment total number of paths found
      return pathsFound;                               //return true to calling activation record
    }
    else 
    {
      int q; //LOCAL, ACTIVATION REC. VARIABLE!! 
      PathNode pNext = new PathNode(0,0,pLast);           //create new PathNode for next square
      int Rule[][] = { {1, 0}, {0, 1}, {0,-1}, {-1,0} };  //rule array for defining surrounding squares
      for(q = 0; q < 4; q++)  //loop for each possible direction of travel
      {
        int x = pLast.x + Rule[q][0]; //set x and y values of adjacent square to test
        int y = pLast.y + Rule[q][1];
        
        if(0 <= x && x < c && 0 <= y && y < r) //the square adjacent to (pLast.x,pLast.y) according to Rule[q] is in the maze, 
        { 
          if ((A[x][y] == A[pLast.x][pLast.y])) //AND is 1 greater than current distance 
          {
            pNext.x = x;  //set coords of PathNode to next square coords
            pNext.y = y;
            int pathsFound = extfindDists(pNext); //recurse method using next square
          } 
        }
      } /*for loop finish*/ 
    } 
    return pathsFound; //return paths found ultimately to calling procedure so it is available to print 
    }
  
  // print Solution method
  boolean printSolution( PathNode pLast )       
  {
    if(pLast.x == gx && pLast.y == gy )          // Checking wheter the current square is the goal square
    { 
      pLast.printForwards();                     // then printing out the path from the start
      return true;                               
    }
    else 
    {
      boolean success;
      M[pLast.x][pLast.y] = 'V';                 // putting X as a mark to the square it just visited
      int q; //LOCAL, ACTIVATION REC. VARIABLE!! 
      
      PathNode pNext = new PathNode(0,0,pLast);           //creating new pathnode
      
      int Rule[][] = { {1, 0}, {0, 1}, {0,-1}, {-1,0} };  // All the rules to check all the side from the square
      
      for(q = 0; q < 4; q++)  //loop for each possible direction of travel
      {
        int x = pLast.x + Rule[q][0];      // checking all the sides using the rules above
        int y = pLast.y + Rule[q][1];
        
        if(0 <= x && x < c && 0 <= y && y < r) // Checking to see if the square's next is not out of bound
        { 
          if ((A[x][y].Filled == 0) && (M[x][y] != 'V')) // now checking if the next square is empty and it is not marked then it's ok to move there
          {
            pNext.x = x;  //now moving to the next square
            pNext.y = y;
            return success = printSolution(pNext);  
            
          } 
        }
      } /*for loop finish*/ 
      
    } 
    System.out.println("No solution.");
    return false; // returning false as no path found
    
  } // End of printSolution method
  
    public void findDists()
  {
    System.out.println("\nDistances maze:");
    for(int y = 0; y < r; y++)                          //loop for rows
    {
      for(int x = 0; x < c; x++)                        //loop for columns
      {
        if (A[x][y].Filled == 1)
          System.out.print("X ");                       //draw an x if user entered a 1 originally
        else if (A[x][y].Filled == 0 && !(x == sx && y == sy)&& !(M[x][y] == 'V') && !(x == gx && y == gy))
          System.out.print("U ");                       //if square is unreachable draw a U  
        else
         System.out.print(sar++ + " ");             //print distance value
      }
      System.out.print("\n");
    }
  }
  
  
} // End of the class