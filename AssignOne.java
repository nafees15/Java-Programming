/*** Author -- Nafees Noor
  * 02/08/2012
  * This program takes 4 pictures as input from user
  * then make a new picture collage where all inputs
  * are connected as center and input 2, 3 are flipped
  * & input 4 is flipped Horizontally
  * **/

public class AssignOne
{
  public static void main(String [] args)
  {
    /* All variable declarations */
    
    Picture pic5;   // final image 
    
    int widthpic1;
    int heightpic1;
    int widthpic2;
    int heightpic2;   // All picture width & height variables
    int widthpic3;
    int heightpic3;
    int widthpic4;
    int heightpic4;
    
   int widthNewpic1;
   int widthNewpic2;
   int widthNewpic3;
   int widthNewpic4;
   
   int heightNewpic1;
   int heightNewpic2;
   int heightNewpic3;
   int heightNewpic4;
   
    int heightNew3;
    int widthNew3;
    int sumwidth;
    int totalwidth;
    int halfwidth;
    int halfwidth2;
    int totalwidth2;
    /* all variables */
    
    int borderThickness = 20;   // Border of the final picture
    
    String filename = FileChooser.pickAFile();
    String filename2 = FileChooser.pickAFile();  // Taking 4 image as input from user
    String filename3 = FileChooser.pickAFile();
    String filename4 = FileChooser.pickAFile();
    
    Picture pic1 = new Picture(filename);
    Picture pic2 = new Picture(filename2);
    Picture pic3 = new Picture (filename3);    // Creating new pictures object based on the input
    Picture pic4 = new Picture (filename4);
     
    pic1.show();
    pic2.show();
    pic3.show();          // Showing all the images
    pic4.show();
    
    /* Getting all pictures width and heights */
    
    widthpic1 = pic1.getWidth();
    heightpic1 = pic1.getHeight();
    
    widthpic2 = pic2.getWidth();
    heightpic2 = pic2.getHeight();
    
    widthpic3 = pic3.getWidth();
    heightpic3 = pic3.getHeight();  
    
     widthpic4 = pic4.getWidth();
     heightpic4 = pic4.getHeight();
 /* Done all picture width and heights */
     
     
     int widthtemp;  // variable to create the width of the final image
     // If else statement to determine and create the width of the final image
     
     if (( widthpic2 + widthpic4) > (widthpic3))
     { /* checking whether 2nd and 4th picture is bigger than 3rd input */
       widthtemp = widthpic2 + widthpic4;
     }
     else
     {
       widthtemp = widthpic3; 
     }
     
         // total hight of the final one //
     int totalheight = ( heightpic1 + heightpic2 + heightpic3 + heightpic4);
        // total width of the final one //
     totalwidth = (widthpic1 + widthtemp); // Total width
     
     pic5 = new Picture(totalwidth, totalheight);  // Final picture
     
     
     halfwidth = totalwidth / 2;    // Dividing the total width to half to 
                                    //determine and draw the each images into the final image
     
     int countwidthpic1 = halfwidth - widthpic1/2; // Calculating the width of the first picture in the final image
     int countwidthpic4 = halfwidth - widthpic4/2; // Calculating the width of the fourth picture in the final image
     
    
    /* Flipping each images */
    pic2= pic2.flip();
    pic3 = pic3.flip();
    pic4 = horizontalFlip(pic4);
    
    /* Painting each images into the final canvas */
    pic1.compose(pic5,   countwidthpic1,  2 * borderThickness);
    pic2.compose(pic5, halfwidth - widthpic2, (2*borderThickness + heightpic1 ));  // Calculating and puting the images into the final canvas
    pic3.compose(pic5, ((halfwidth-widthpic2) + (widthpic2))  , 2 * borderThickness + heightpic1 );
    
    
    /* below figuring out which middle picture (picture 3 or picture 2) 
     height is bigger than the other one then counting the biggest one height picture
     to determine the height to draw the 4rth picture
     */
    int checkHeight;
    if( heightpic3 > heightpic2)
    {
      checkHeight = heightpic3;
    }
    else
      checkHeight = heightpic2;
    pic4.compose(pic5, countwidthpic4 , 2* borderThickness + heightpic1 + checkHeight );
    
    //// Done painting all the pictures into the final image //////
    
    pic5.show();  // At the end showing the final image on the screen as output
    
  }   // End of the main method
  
     /* Below method is for flipping the fourth image */
     public static  Picture horizontalFlip(Picture x) 
   {
     Pixel currPixel = null;
     Pixel targetPixel = null;
     Picture target = new Picture(x.getWidth(),x.getHeight());
    
    for (int srcY = 0, trgY = x.getHeight()-1; 
         srcY < x.getHeight();
         srcY++, trgY--) 
    {
        for (int srcX = 0, trgX = 0; 
           srcX < x.getWidth();
           srcX++, trgX++) 
    {    
        // get the current pixel
        currPixel = x.getPixel(srcX,srcY);
        targetPixel = target.getPixel(trgX,trgY);
        
        // copy the color of currPixel into target
        targetPixel.setColor(currPixel.getColor());
      }
    }
    return target;
  }
   ///// Done with the method of flipping the fourth image ////

     
}  // End of the class
