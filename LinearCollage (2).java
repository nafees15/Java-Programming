/* Author -- Nafees Noor
 * CSI 310
 * This program ask user if they want to enter picture
 * take all the pictures user wishes to enter
 * make collage of all the pictures
 * and use XX comand to exit from the program 
 * ****/
class LinearCollage
{
  private class Node
  {
    Picture data;
    Node pNext;
  }
  
  private Node pFirst;
  private Node pLast;
 
  private int nPictures; //nPictures will ALWAYS equal the nr. of images
                         //the customer thinks is CURRENTLY in his collage.
  
  private Picture clipboard;
  public LinearCollage()
  {
    pFirst = null;
    pLast = null;
    nPictures = 0;
                
  }
  public void addPictureAtEnd(Picture aPictureReference)
  {
    Node temp = new Node();
    temp.data = aPictureReference;
    temp.pNext = null;  //As the method name says, the Picture
                        //we are adding HAS NO Picture after it.
    if( pLast == null )
    {
      pLast = temp;
      pFirst = temp;
    }
    else
    {
      //THE FIRST LINE BELOW MUST BE FIRST
      pLast.pNext = temp;//THIS MUST BE DONE FIRST
      //THE ABOVE MUST BE DONE FIRST
      //
      //THE ABOVE MUST BE DONE BEFORE
      pLast = temp; //BEFORE THIS ASSIGNMENT
      //OBLITERATES!!! the original value of pLast
      //Yes, the ORDER of these two assignments
      //REALLY REALLY MATTERS!!! 
    }
    
    nPictures++;
    //After adding each Picture, we MUST increment nPictures so what we
    //plan to ALWAYS be true actually is!
  }
  public Picture makeCollage()
  {
    int collageHeight = 0;
    int collageWidth = 0;     // setting these to 0 to start from 0
    
    // Figure out height and width..YOU code!!
    // This should be done HERE, not in addPictureAtEnd, because
    // code to be added will EDIT the Picture sequence, which changes
    // what the height and width should be!
    // 
    //Draft a loop to process every image the customer THINKS is in the collage.
    //This loop is to calculate the required collageWidth and collageHeight.
    collageHeight = pFirst.data.getHeight(); // setting the collageheight to the first picture
    for( Node finger = pFirst; finger != null; finger = finger.pNext )
    {
      if(finger.data.getHeight() > collageHeight)  // Checking which picture's height the biggest
      {
        collageHeight = finger.data.getHeight();   // setting the biggest hieght of the picture to collageheight
      }
      collageWidth= collageWidth + finger.data.getWidth();  // counting all the picture's width to make the final canvas 
      System.out.println("Process Picture " + finger.data);
    }  // Looping through to get the highest height and width of each picture to make the collage canvas
    Picture retval = new Picture(collageWidth,collageHeight);  // drawing the canvas
    // compose the Pictures into retval..YOU code!!
    //Lets use a too-simple collage making loop just for testing the rest.
    //Simply apply example from the book! (What textbooks are for.)
 
    int trackHeight = 0;
    int trackWidth = 0;      // Using these to variables to make the collage of the pictures
    
    for( Node finger = pFirst; finger != null; finger = finger.pNext )
    {
      trackHeight = (retval.getHeight()- finger.data.getHeight())/2;   // Dividing the second picture to make collage of the higest picture
      System.out.println("Process Picture " + finger.data);
      finger.data.compose(retval, trackWidth, trackHeight);    // composing the picture into final canvas
      trackWidth = trackWidth + finger.data.getWidth();    // counting each picture's width so each picture comes out in collage next to one another
    }    
   
   return retval;
  }
  public void cutMiddle()  //cutMiddle doesn't return the cut picture
    //BECAUSE we made the clipboard PART OF THE LinearCollage!
  {
    System.out.println("Not implemented yet.");
     int oneBefore = 0;
    if((nPictures % 2) == 0)  //even
    {
       oneBefore = nPictures/2 -1;
    }
     else //odd
       oneBefore =  nPictures/2;
     Node finger = pFirst;
    for(int i= 1; i <oneBefore; i++)
    {
      finger = finger.pNext;
    }
     clipboard = finger.pNext.data;
     finger.pNext = finger.pNext.pNext;
     nPictures--;
   }
  
  public void pasteEnd() // pasteEnd to paste the picture in the end
  { 
    
    Node last = new Node();
     Node node = new Node();
    node.pNext = null;
   
    for(Node finger = pFirst; finger!= null; finger = finger.pNext)
    {
      last = finger;
    }
      last.pNext = node; // making the last node to point to the pasting node
      pLast = node;    // making the pLast to point to the pasted new node at the end
      node.data = clipboard; // putting the image from clipboard to the last new node
  
    nPictures++;
  } // end of pasteEnd
  
  
  public void cutEnd()
  {
    Node current = new Node();
    for ( Node finger = pFirst; finger.pNext != null; finger = finger.pNext)
    {
      current = finger;
    }
      clipboard = current.pNext.data;
      current.pNext = null;
      nPictures--;
  }
  
  public boolean isFull()
  {
    return false;
  }
  public boolean isEmpty()
  {
    return nPictures == 0;
  }
  //add others yourself..
}

