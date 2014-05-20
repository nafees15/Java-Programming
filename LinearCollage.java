class LinearCollage
{
  private class Node
  {
    Picture data;
    Node pNext;
  };
  
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
    int collageWidth = 0;
    
    // Figure out height and width..YOU code!!
    // This should be done HERE, not in addPictureAtEnd, because
    // code to be added will EDIT the Picture sequence, which changes
    // what the height and width should be!
    // 
    //Draft a loop to process every image the customer THINKS is in the collage.
    //This loop is to calculate the required collageWidth and collageHeight.
    collageHeight = pFirst.data.getHeight();
    for( Node finger = pFirst; finger != null; finger = finger.pNext )
    {  
      if(finger.data.getHeight() > collageHeight)
      {
        collageHeight = finger.data.getHeight();
      }
      collageWidth= collageWidth + finger.data.getWidth();
      System.out.println("Process Picture " + finger.data);
    }
    Picture retval = new Picture(collageWidth,collageHeight);
    // compose the Pictures into retval..YOU code!!
    //Lets use a too-simple collage making loop just for testing the rest.
    //Simply apply example from the book! (What textbooks are for.)
    int i = 0;
    int trackHeight = 0;
    int trackWidth = 0;
    for( Node finger = pFirst; finger != null; finger = finger.pNext )
    {
      trackHeight = (retval.getHeight()- finger.data.getHeight())/2;
      finger.data.compose(retval, trackWidth, trackHeight);
      System.out.println("Process Picture " + finger.data);
      trackWidth = trackWidth + finger.data.getWidth();
      
      //finger.data.compose(retval, 50*i, 50*i);
      
    }    
    
    
    return retval;
  }
  public void cutMiddle()  //cutMiddle doesn't return the cut picture
    //BECAUSE we made the clipboard PART OF THE LinearCollage!
  {
    System.out.println("Not implemented yet.");
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

