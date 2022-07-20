/**
 * DSA Final Assessment Question 3 - FA_MinHeap.java
 *
 * Name : Sahas Gunasekara
 * ID   : 20462075
 *
 **/
 
public class FA_MinHeap //Changed the class name to FA_MinHeap (obviously)
{

	public class HeapEntry
	{
	private int priority;
	private Object value;

	public HeapEntry(int priority, Object value)
	{
		this.priority = priority;
		this.value = value;
	}

	public int getPriority()
	{
		return priority;
	}

	public Object getValue()
	{
		return value;
	}

	}

	private HeapEntry[] heap;
	private int count;
	private int MAXSIZE = 10;
	
	
	public FA_MinHeap()
	{
		heap = new HeapEntry[MAXSIZE];
		count = 0;	
	}


	public void add(int priority, Object value) throws PracExamException 
	{ //added a throws to throw the exception
		if (count != MAXSIZE)
		{
			HeapEntry entry = new HeapEntry(priority, value);
			heap[count] = entry;
			count++;
			trickleUp(count-1);
		}
		else
		{
			throw new PracExamException("Heap is full!"); //if count is 10 this means no more space in the heap!
		}
	}

	public Object remove() throws PracExamException
	{
		Object retValue = null;
		if (count != 0)
		{	
			HeapEntry entry = heap[0];
			retValue = entry.getValue();
			heap[0] = heap[count-1];
			heap[count-1] = null;
			count--;
			trickleDown(0);
		}
		else
		{
			throw new PracExamException("Heap is empty!"); //if the count is 0 this means there are no elements in this heap!
		}	
		return retValue;
	}

	private void trickleDown(int index)
	{
	   int leftIdx = index * 2 + 1;
	   int rightIdx = leftIdx + 1;
	   int smallIdx; //renamed the variable
	   HeapEntry temp;

	   if (leftIdx < count)
		{
		smallIdx = leftIdx;			
		if (rightIdx < count)
		{
		   if (heap[leftIdx].getPriority() > heap[rightIdx].getPriority()) //if left is larger, then right is smaller
			{
				smallIdx = rightIdx;
			}
		}
		if (heap[smallIdx].getPriority() < heap[index].getPriority()) //changed the arrow head.
		{
              	temp = heap[smallIdx];
              	heap[smallIdx] = heap[index];
              	heap[index] = temp;
			trickleDown(smallIdx);
		}	
	   }
	}
	
	private void trickleUp(int index)
	{
		int parentIndex;
		HeapEntry temp;

		parentIndex = (index-1)/2;

		if (index > 0 )
		{
			if (heap[index].getPriority() < heap[parentIndex].getPriority()) //if smaller trickle up
			{
				temp = heap[parentIndex];
				heap[parentIndex] = heap[index];
				heap[index] = temp;
				trickleUp(parentIndex);
			}
		}
		
	}
}