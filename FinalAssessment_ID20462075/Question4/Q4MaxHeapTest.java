/**
 * DSA Final Assessment Question 4 - Q4MaxHeapTest.java
 *
 * Name : Sahas Gunasekara
 * ID   : 20462075
 *
 **/
import java.util.*;

public class Q4MaxHeapTest
{
	public static void main(String args[])
	{
		System.out.println("\n+++++ Question 4: Testing Heaps +++++\n");
		
		PriorityQueue<Integer> testHeap = new PriorityQueue<Integer>(Collections.reverseOrder()); //We use the Collections.reverseOrder() to reverse the comparator since the base is a min heap
		
        System.out.println("Adding items...");
		for (int i=0; i<10; i++)
		{
			testHeap.add(i); //Can't add both a value and a priority if using Integer, you can amend this and add an object and then redefine the comparator but that goes
			//beyond the scope of what was taught to us in DSA.
            System.out.println("Added " + "value-"+Integer.toString(i));
		}

        System.out.println();
        System.out.println("Removing items...");
		Integer temp;
		
		for (int i=0; i<10; i++)
		{
			temp = testHeap.remove();
			System.out.println(temp);
		}

		System.out.println("\n+++++ Tests Complete +++++\n");
	}
}