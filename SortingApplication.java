/* Author -- Nafees Noor
 * CSI 310
 * This program implements the merge Sorting Algorithm
 * */
import java.util.ArrayList;
import java.util.Scanner;


class SortingApplication
{
  static void readTheInput(ArrayList<String> dummy)
  {
    Scanner sc = new Scanner(System.in);
    boolean inputDone = false;
    while( ! inputDone)
    {
      try {
       dummy.add(sc.nextLine());
     }
     catch( java.util.NoSuchElementException e)
     {
       inputDone = true;
     }
    }
    return;
  }
  static void writeTheOutput(ArrayList<String> dummy)
  {
    for(int i = 0; i < dummy.size()  ; i++)
      System.out.println(dummy.get(i));
  } 
  public static void main(String[] a)
  {
    ArrayList<String> inputs = new ArrayList<String>();
    readTheInput(inputs);
    
    SortingEngine myEngine = new SortingEngine(inputs);
    myEngine.sort();
    writeTheOutput(inputs);
  }
}