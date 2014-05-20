/* Author -- Nafees Noor
 * CSI 310
 * This program implements the merge Sorting Algorithm
 * */

import java.util.ArrayList;
class SortingEngine
{
  private ArrayList<String> A;
  private ArrayList<String> scratch;

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
    for(int i =0; i <A.size(); i++)
      scratch.set(i, A.get(i));
   
    CatThing(0,A.size()-1); // Calling the catThing
  }

  private void CatThing(int leftIndex, int rightIndex)
  {  // it's sorting after dividing the middle, recursivly sorting both side then calling marge
    if(leftIndex == rightIndex)
      return ; // A subarray of 1 element is already sorted!
    else
    { 
      int mid = (leftIndex + rightIndex)/2;

      CatThing(leftIndex, mid);
      CatThing(mid +1, rightIndex);
      Merge(leftIndex, mid, mid+1, rightIndex);
      
      //Code to call merge() to merge the results:
      //Merge(leftIndex, /*YOU FIGURE OUT*/, 
      //      /*YOU FIGURE OUT*/, rightIndex);
    }
  }
  private void Merge(int left1, int right1, int left2, int right2)
  { // merge sort where the actual sort works
    int i= left1;
    int k = left1;
    int j = left2;
    // A to scratch
    for(int a = left1; a <= right2; a++)
    {
      scratch.set(a, A.get(a)); // putting all the numbers into the scratch array list
    }
    
    while( i <= right1 && j < right2) // Comparing and putting the sorted number in the list
    {
      if(scratch.get(i).compareTo(scratch.get(j)) < 0)
      { // if the number less than the second number then put the lowest value in the list
        A.set(k, scratch.get(i));
        i++;
        k++;
      }

     else
     {
       A.set(k, scratch.get(j));
       j++;
       k++;
       
     }  
   }
    // These while loops to copy over the rest of the value in the list
    while(i <= right1) 
    {
      A.set(k, scratch.get(i));
      i++;
      k++;
    }
    // These while loops to copy over the rest of the value in the list
    while(j <= right2)
    {
      A.set(k, scratch.get(j));
      j++;
      k++;
   }

  }   
}
    
