import java.util.Scanner;
class LineCollageMaker
{
 
   //OPTIONAL: declare static variables so that you can
   //code and use static methods instead of putting
   //all the user interface and dispatch code in main().
  public static void main(String a[])
  {
    LineCollage myCollage;
    Scanner uiInput = new Scanner(System.in);
    System.out.println("How many images?");
    int countdown = uiInput.nextInt();
    myCollage = new LineCollage(countdown);
    while( countdown > 0 )
    {
      Picture pin = new Picture(FileChooser.pickAFile());
      myCollage.addPictureAtEnd(pin);
      countdown--;
    }
    Picture firstToShow = myCollage.makeCollage();
    firstToShow.show();
    // Here goes the menu for copy/cut, pasting manu
    // Then check like if (ui = cm);
    //YOU Code the user inteface loop and dispatch to methods
   //of myCollage here..
  }
}

 class LineCollage
{
  private Picture myArray[]; //To student: it starts out as a null reference.
  //No field for the array length is needed because
  // the length is available with myArray.length.
  private int nPictures;
  private Picture clipboard;
  public LineCollage(int n)
  {
    myArray = new Picture[n]; //To student: The array is CONSTRUCTED here.
    nPictures = 0;
  }
  public void addPictureAtEnd(Picture aPictureReference)
  {
    myArray[nPictures] = aPictureReference;
    nPictures++;
  }
  public Picture makeCollage()
  {
    int collageHeight = 0;
    int collageWidth= 0;
  // Figure out height and width..YOU code!!
  // This should be done HERE, not in addPictureAtEnd, because 
  // code to be added will EDIT the Picture sequence, which changes
  // what the height and width should be!
   

     collageHeight = myArray[0].getHeight();
     collageWidth = myArray[0].getWidth();
    for(int i =0; i <myArray.length; i++)
    {
      if(myArray[i].getHeight() > collageHeight)
      {
        collageHeight = myArray[i].getHeight();
      }
      collageWidth += myArray[i].getWidth();
    }
   ///////////////// end of height
  

    Picture retval = new Picture(collageHeight,collageWidth);
  // compose the Pictures into retval..YOU code!!
    return retval;
  }
  //add others yourself..
}
