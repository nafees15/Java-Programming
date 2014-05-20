import java.util.ArrayList;
class SortingEngine
{
  private ArrayList<String> A;
  private ArrayList<String> scratch;
  private ArrayList <String> testLeft;
  private ArrayList <String> testRight;
  public SortingEngine(ArrayList<String> target)
  {
    setA(target);
  }
  public void setA(ArrayList<String> newTarget)
  {
    A = newTarget;
    scratch = new ArrayList<String>(newTarget.size());
    for(int i = 0; i < A.size(); i++)
    {
      scratch.add(null);
    } 
  }
  
  public void sort()
  {
    CatThing(0,A.size()-1);
    
  }

  private void CatThing(int leftIndex, int rightIndex)
  {  // it's sorting after dividing the middle, recursivly sorting both side then calling marge
    if(leftIndex == rightIndex)
      return ; // A subarray of 1 element is already sorted!
    else
    { 
      int mid = (leftIndex + rightIndex)/2;
      
      System.out.println("it's divided");

      CatThing(leftIndex, mid);
      CatThing(mid +1, rightIndex);
      Merge(leftIndex, mid, mid+1, rightIndex);
      
      //Code to call merge() to merge the results:
      //Merge(leftIndex, /*YOU FIGURE OUT*/, 
      //      /*YOU FIGURE OUT*/, rightIndex);
    }
  }
  private void Merge(int left1, int right1, int left2, int right2)
  {
    System.out.println("Entered in the merge method");
    //MERGE DOES THE MERGE OPERATION IN THE SHARED ArrayList A
    //and uses the shared ArrayList scratch for scratch space.
    
    //To test if the string referred to by A.get(i) should be
    // placed before the string referred to A.get(j) is:
  
  /*   if(A.get(left1).compareTo(A.get(right1)) < 0 )
     { // The first string should be before the second 
       System.out.println("It entered in the for loops if ");
       
     }
    else
     { //The second string should be before the first 
      System.out.println("It entered in the else");
     }  */
    // YOU MUST CAREFULLY WRITE LOOP AND DECISION MAKING
    // CODE!!
    }
    }
    
    int ScrPoint = left1;                                           
    int leftIndex = left1, rightIndex = right2;                     
    while (left1 <= right1 || left2 <= right2)                      
    {

           
      if (left1 == (right1 + 1))                                    
      {
        scratch.set(ScrPoint,A.get(left2));                         
        ScrPoint++;                                                 
        left2++;                                                    
      }
      else if (left2 == (right2 + 1))                               
      {
        scratch.set(ScrPoint,A.get(left1));                         
        ScrPoint++;                                                 
        left1++;                                                    
      }
      else                                                          
      {
        if((A.get(left1).compareTo(A.get(left2)))<=0)                
        {
          scratch.set(ScrPoint,A.get(left1));                       
          ScrPoint++;                                               
          left1++;                                                  
        }
        else                                                       
        {
          scratch.set(ScrPoint,A.get(left2));                     
          ScrPoint++;                                         
          left2++;                           
        }
      }
    }
    
    
    for (int i = leftIndex; i <= rightIndex; i++)                  
    {
      A.set(i,scratch.get(i));                                      
    
    } 

  }   
}
    
