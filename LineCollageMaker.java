import java.util.Scanner;
class LineCollageMaker
{
  public static void main(String a[])
  {
    LinearCollage myCollage;
    Scanner uiInput = new Scanner(System.in);
    
 
    myCollage = new LinearCollage();
    
    //FileChooser.pickMediaPath();
    boolean inputting = true;
    while( inputting ) 
    {
      System.out.println("Another picture? Type Y if so.");
      String answer = uiInput.next();
      if(answer.equals("Y"))
      {
        Picture pin = new Picture(FileChooser.pickAFile());
        myCollage.addPictureAtEnd(pin);
      }
      else
      {
        inputting = false;
      }
      
   
    }
    Picture firstToShow = myCollage.makeCollage();
    firstToShow.show();
    //YOU Code the user inteface loop and dispatch to methods
    //of myCollage here..
    boolean done = false;
    while( !done )
    {
      System.out.println("Menu...please finish me..");
      String command = uiInput.next();
      if(command.equals("XX"))
        done = true;
      else if(command.equals("CM"))
      {
        if(myCollage.isEmpty())
        {
          System.out.println("Can't cut from an empty collage.");
        }
        else
        {
          myCollage.cutMiddle();
          myCollage.makeCollage().show();
        }
      }
      else if(command.equals(""))
      {
      }
      else
        System.out.println("Unrecognized command. Try again.");
    }
    
  }
}

  
