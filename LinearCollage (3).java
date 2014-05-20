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
    Node pPrev;
  }
  private Node pFirst;
  private Node pLast;
  
  private int nPictures; //nPictures will ALWAYS equal the nr. of images
                         //the customer thinks is CURRENTLY in his collage.
  
  private Picture clipboard;
  private Node cursor;
  
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
    temp.pPrev = null;
    if( pLast == null )
    {
      pLast = temp;
      pFirst = temp;
    }
    
    else
    { 
      temp.pPrev = pLast;
      //THE FIRST LINE BELOW MUST BE FIRST
      pLast.pNext = temp;//THIS MUST BE DONE FIRST
      //THE ABOVE MUST BE DONE FIRST  
      //THE ABOVE MUST BE DONE BEFORE
      pLast = temp; //BEFORE THIS ASSIGNMENT
      //OBLITERATES!!! the original value of pLast
      //Yes, the ORDER of these two assignments
      //REALLY REALLY MATTERS!!!   
    }
      nPictures++;
    //After adding each Picture, we MUST increment nPictures so what we
    //plan to ALWAYS be true actually is!
      
      cursor = pFirst;
     System.out.println("The cursor is now at " + cursor.data);
  }
  
  public Picture makeCollage()
  {
    int collageHeight = 0;
    int collageWidth = 0;     // setting these to 0 to start from 0
    
    collageHeight = pFirst.data.getHeight(); // setting the collageheight to the first picture
    
    for( Node finger = pFirst; finger != null; finger = finger.pNext)
    {
      if(finger.data.getHeight() > collageHeight)  // Checking which picture's height the biggest
      {
        collageHeight = finger.data.getHeight();   // setting the biggest hieght of the picture to collageheight
      }
      collageWidth= collageWidth + finger.data.getWidth();  // counting all the picture's width to make the final canvas 
    }  // Looping through to get the highest height and width of each picture to make the collage canvas
    
    Picture retval = new Picture(collageWidth,collageHeight);  // drawing the canvas
    
    int trackHeight = 0;
    int trackWidth = 0;      // Using these to variables to make the collage of the pictures
    
    for( Node finger = pFirst; finger != null; finger = finger.pNext )
    {
      trackHeight = (retval.getHeight()- finger.data.getHeight())/2;   // Dividing the second picture to make collage of the higest picture
      finger.data.compose(retval, trackWidth, trackHeight);    // composing the picture into final canvas
      trackWidth = trackWidth + finger.data.getWidth();    // counting each picture's width so each picture comes out in collage next to one another
    } 
   return retval;     
 }
  
  public void cutMiddle()  //cutMiddle doesn't return the cut picture..BECAUSE we made the clipboard PART OF THE LinearCollage!
  {
     int middle = 0;
     Node node = new Node();
     Node temp = new Node();
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
    if(clipboard == null) // checking if the user didn't cut but trying to paste something
    {
      System.out.println("No picture to paste!\nMay be you didn't cut any picture yet!");
    }
    Node temp = new Node();
   Node node = new Node();
 
   temp = pLast;     // pointing to the last picture
   temp.pNext = node; // putting a new node after the last picture, pointing to that nod
   node.pPrev = temp;  // new node previous is the last picture
   node.data = clipboard; // putting the image from clipboard to the last new node
  
   pLast = node;  // setting the new node to as plast
   node.pNext = null;
    nPictures++;
  } // end of pasteEnd
  
  
  public void cutEnd()
  {
    Node current = new Node();
    
    if(pFirst.data == pLast.data) // if statement to catch if the user is trying to cut the last only left picture
    {
      System.out.println("You can't cut this picture because it's the only picture left here\nJust drop the course");
    }
    else
    {
    clipboard= pLast.data;   // putting the picture before cutting it
      pLast = pLast.pPrev;    // pointing to the picture one before the last image
      pLast.pNext = null;  // setting the next picture to null, so the last picture will go away
      nPictures--;
    }
  }
  

  public void cutCursor()
  {
    if (cursor == null) // trying to catch if user is trying to cut when the cursor is already in the null
    {
      System.out.println("There is nothing to cut!\nPlease move through and choose one picture to cut");
    }
    
    else if(pFirst == pLast) // if user is trying to cut the only last left picture
    {
      System.out.println("NOooooo, you are about to cut the only last picture! This is lame! ");
      
    }
    else if(cursor.pPrev == null) //this if is for if the user is trying to cut the very first picture
    {
      clipboard = pFirst.data;  // putting the first picture in the clipboard
      pFirst = cursor.pNext;   // making to point the second picture and make it as first picture to cut the first picture
      pFirst.pPrev = null;    // deleting the first picture
      cursor=pFirst;         // cursor pointing at the new pasted first picture
    }
    else if (cursor.pNext ==null)  // This if statement is for if the user is trying to cut the very last picture
    {
      clipboard= cursor.data;  // putting the last picture in the clipboard
      pLast = cursor.pPrev;   // setting the pLast as one before the last image
      cursor.pPrev.pNext = null;  // making the last picture go away by pointing the one before last picture's next as null
      cursor = null;           //making the cursor to point to the null
    }
    else if (cursor == null)   //this is for if the user is trying to cut when the cursor is already in the cursor
    {
      System.out.println("There is nothing to cut!\nPlease move through and choose one picture to cut");
    }
    else
    {  // else it's cutting like normal
      Node temp = new Node();
     Node check = new Node();
     
      temp = cursor.pPrev;  // setting the temp to point one before the cursor
      check = cursor.pNext; // setting the check to point next to the cursor
      
      clipboard = temp.pNext.data;  // putting the image into the clipboard
      temp.pNext = temp.pNext.pNext; // now deleting it by point to the one after it
      check.pPrev= temp;         // making the previous to point to the check node
      cursor = temp.pNext;     // making cursor to point to the one next to the temp node
    }
    nPictures--;
  }
 
  public void pasteCursor()
  { 
    Node node = new Node();
    Node temp = new Node();
    Node point = new Node();
  
    if(clipboard == null)  // this is for if user didn't cut any image but trying to paste something
    {
      System.out.println("There's nothing to paste, perhaps you didn't cut anything yet");  
    } 
   
    else if (cursor == null) // this is for if the user is pointing to the null and trying to paste an image
    {   
      pLast.pNext = temp;   // creating a new node right after the last image
      temp.pPrev = pLast;   // making the new node pointing to the last image
      temp.data = clipboard; // pasting the image from this clipboard
     cursor = temp;    // making cursor point to the temp
      pLast = temp;    // making the last picture as temp
      temp.pNext = null;  //making the next of temp to null
       nPictures++;
    }
  
    else if(cursor.pPrev == null) // this is for user is trying to paste something at the very front 
    {
       cursor.pPrev = temp;  // making to point to the new temp node
       temp.pNext = cursor;  // pointing to the first picture
       temp.data = clipboard;  // now pasting it from the clipboard
       temp.pPrev = null;  // setting the previous of it as null
       pFirst = temp;  // setting the next of it to the first picture so now it will be as a second picture
       cursor = temp;  // making cursor point to the first new picture
       nPictures++;
    }
    
  else   // else it's creating a new node, pointing to each other and pasting the picture into the new node
  {
   temp = cursor;
   node = temp.pPrev;
   
    point.pPrev = node;  // creating new code and linking to one another
    point.pNext = temp;   // linking to one another
    
    node.pNext = point; 
    temp.pPrev = point;
    point.data = clipboard; //pasting it from the clipboard
    cursor = point;   // making the cursor to point to the point node
     nPictures++;
 }

}
 
    public void moveForward() // move forward method 
  {
      if(cursor == null)   // if the cursor is already in the null and if user is trying to move more forward
    {
      System.out.println("Can't move more forward\nIt's already in the null!");
    }
      else
      cursor = cursor.pNext;
    
      if(cursor == null)  // now checking again if the cursor is in the null now
    {
      System.out.println("The cursor is in the null");
    }
    else  // else printing out the cursor information and showing the picture where the cursor pointing at
    {
    System.out.println("The cursor is now at " + cursor.data);
    cursor.data.show();
    }
    
  }
  
  public void moveBackward()  // move backward method
  {
    if(cursor == null)  // if the cursor is already in null and if the user is trying to go back, this is for that
    {
      cursor = pLast;  // making the cursor to the last picture 
      System.out.println("The cursor is now at " + cursor.data); // printing out the cursor information
      cursor.data.show();  //showing the picture
    }
    
   else if(cursor.pPrev == null) // if the cursor is all the at the front and trying to go to the null area
    {
       System.out.println("Can't go more back, it's null there");
     
    }
    else
    {
    cursor = cursor.pPrev; // else moving the cursor backward
    if(cursor == null)
    {
      System.out.println("The cursor is in the null "); //checking and printing if the cursor is in the null
    }
    else
    {
    cursor.data.show();
    System.out.println("The cursor is now at " + cursor.data); // otherwise showing and printing out the cursor information
    }
    
    }
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