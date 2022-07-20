/*A version of this code has previously been submitted by me for Practical 1 DSA*/
class Sort 
{
    public static Path[] bubbleSort(Path[] inArr)
        {
          int pass = 0;
          Path temp;
          boolean sorted;
    
          do
          {
            sorted = true;
            for (int ii = 0; ii < inArr.length-1 - pass; ii++)
            {
              if (inArr[ii].getWeight() > inArr[ii+1].getWeight())
              {
                temp = inArr[ii];
                inArr[ii] = inArr[ii + 1];
                inArr[ii+1] = temp;
                sorted = false;
              }
            }
            pass = pass + 1;
          } while (sorted == false);

          return inArr;
        }
}   