/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
import java.lang.Math;

class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {
      int pass = 0;
      int temp;
      boolean sorted;

      do
      {
        sorted = true;
        for (int ii = 0; ii < A.length-1 - pass; ii++)
        {
          if (A[ii] > A[ii+1])
          {
            temp = A[ii];
            A[ii] = A[ii + 1];
            A[ii+1] = temp;
            sorted = false;
          }
        }
        pass = pass + 1;
      } while (sorted == false);
  
    }//bubbleSort()

    // selection sort
    public static void selectionSort(int[] A)
    {
      int minIdx;
      int temp;

      for (int nn = 0; nn < A.length-1; nn++)
      {
        minIdx = nn;
        for (int jj = nn+1; jj < A.length; jj++)
        {
          if (A[jj] < A[minIdx])
          {
            minIdx = jj;
          }
        }

        temp = A[minIdx];
        A[minIdx] = A[nn];
        A[nn] = temp;
      }

      
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {
      int ii;
      int temp;

      for (int nn = 1; nn <= A.length - 1; nn++)
      {
        ii = nn;

        while (ii > 0 && A[ii-1] > A[ii])
        {
          temp = A[ii];
          A[ii] = A[ii-1];
          A[ii-1] = temp;

          ii = ii -1;
        }
      }

    }// insertionSort()

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
      System.out.println("Calling mergeSort");
      String indent = "";
      int leftIdx = 0;
      int rightIdx = A.length - 1;
      mergeSortRecurse(A, leftIdx, rightIdx, indent);
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx, String indent)
    {
      if (leftIdx < rightIdx)
      {
        indent = indent + "  ";
        int midIdx = (leftIdx + rightIdx) / 2;
        System.out.println(indent + "mergeSortRecurse(Array," + leftIdx + "," + midIdx + ")");
        mergeSortRecurse(A, leftIdx, midIdx, indent);
        int tempIdx = midIdx + 1; //just to for the printstatement
        System.out.println(indent + "mergeSortRecurse(Array," + tempIdx + "," + rightIdx + ")");
        mergeSortRecurse(A, midIdx+1, rightIdx, indent);
        System.out.println(indent + "merge(Array," + leftIdx + "," + midIdx + "," + rightIdx + ")");
        merge(A, leftIdx, midIdx, rightIdx);
      }
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
      int[] tempArr = new int[rightIdx - leftIdx + 1];
      int ii = leftIdx;
      int jj = midIdx + 1;
      int kk = 0;

      while (ii <= midIdx && jj <= rightIdx)
      {
        if (A[ii] <= A[jj])
        {
          tempArr[kk] = A[ii];
          ii = ii + 1;
        }
        else
        {
          tempArr[kk] = A[jj];
          jj = jj + 1;
        }
        kk = kk + 1;
      }

      for (int i = ii; i <= midIdx; i++)
      {
        tempArr[kk] = A[i];
        kk = kk + 1;
      }

      for (int j = jj; j <= rightIdx; j++)
      {
        tempArr[kk] = A[j];
        kk = kk + 1;
      }

      for (kk = leftIdx; kk <= rightIdx; kk++)
      {
        A[kk] = tempArr[kk-leftIdx];
      }
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
      System.out.println("Calling quickSort");
      String indent = "";
      int leftIdx = 0;
      int rightIdx = A.length -1;
      quickSortRecurse(A, leftIdx, rightIdx, indent);
    }//quickSort(), to avoid duplication, we have commented out the code, you can uncomment and run each type of quickSort
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx, String indent)
    {
      if (rightIdx > leftIdx)
      {
        int pivotIdx = leftIdx;
        indent = indent + "  ";
        int midIdx = (leftIdx + rightIdx) /2;
        // int pivotIdx = (leftIdx + midIdx + rightIdx) /3;
        // int pivotIdx = (int) (Math.random()*(rightIdx - leftIdx +1) + leftIdx);
        System.out.println(indent + "doPartitioning(Array," + leftIdx + "," + rightIdx + "," + pivotIdx + ")");
        int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);
        
        int temp1 = newPivotIdx -1;
        int temp2 = newPivotIdx + 1;

        System.out.println(indent + "quickSortRecurse(Array," + leftIdx + "," + temp1 + ")");
        quickSortRecurse(A, leftIdx, newPivotIdx-1, indent);
        System.out.println(indent + "quickSortRecurse(Array," + temp2 + "," + rightIdx + ")");
        quickSortRecurse(A, newPivotIdx+1, rightIdx, indent);
      }
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
      int pivotVal = A[pivotIdx];
      A[pivotIdx] = A[rightIdx];
      A[rightIdx] = pivotVal;

      int currIdx = leftIdx;

      for (int ii = leftIdx; ii <= rightIdx -1; ii++)
      {
        if (A[ii] < pivotVal)
        {
          int temp = A[ii];
          A[ii] = A[currIdx];
          A[currIdx] = temp;
          currIdx = currIdx + 1;
        }
      }
      int newPivotIdx = currIdx;
      A[rightIdx] = A[newPivotIdx];
      A[newPivotIdx] = pivotVal;

		return newPivotIdx;
    }//doPartitioning

  //shell sort
  public static void shellSort(int[] A)
  {
    int temp;
    for (int g = (A.length-1)/2; g >= 1; g = g/2)
    {
      for (int j = g; j < (A.length); j++)
      {
        for (int i = j -g; i >= 0; i = i-g)
        {
          if (A[i+g] < A[i])
          {
            temp = A[i+g];
            A[i+g] = A[i];
            A[i] = temp;
          }
        }
      }
    }
  }
  //shell sort

}//end Sorts calss
