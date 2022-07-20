/**
 * DSA Final Assessment Question 3 - MinHeapTest
 * Name : Sahas Gunasekara
 * ID   : 20462075
 *
 **/
import java.io.*;

public class MinHeapTest //Changed the class name
{
	public static void main(String args[])
	{
		System.out.println("\n+++++ Question 3: Testing Heaps +++++\n");
		
		FA_MinHeap testHeap = new FA_MinHeap();
		
        System.out.println("Adding items...");
		try
		{
			for (int i=0; i<10; i++)
			{
				testHeap.add(i, "value-"+ Integer.toString(i));
				System.out.println("Added " + "value-"+Integer.toString(i)+" Priority: " + Integer.toString(i));
			}
			System.out.println("Trying to add one more to a full heap...");
			testHeap.add(13, "value-sahas"); //trying to add one more than the space
		}
		catch (PracExamException e) //catch the exception which will be raised in line 24
		{
			System.out.println(e); //printing the message inside the exception
		}
		System.out.println();
        System.out.println("Removing items...");
		String temp;
		
		try
		{
			for (int i=0; i<10; i++)
			{
				temp = (String)testHeap.remove();
				System.out.println(temp);
			}
			System.out.println("Trying to remove one more from an empty heap...");
			temp = (String)testHeap.remove(); //removing one more element after the last element is removed
		}
		catch (PracExamException e) //catching the exception which will be raised in line 41
		{
			System.out.println(e); //printing the message inside the exception
		}
		String data;
		System.out.println();
		System.out.println("Reading from file and adding to a priority queue");
		FA_MinHeap pQueue = new FA_MinHeap(); //assumed the priority is if its smaller higher priority since we were writing this code in the 
		//MinHeapTest file.
		try
		{
			File file = new File("Q3HeapData.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((data = br.readLine()) != null)
			{
				String[] fileValues = data.split(" ");
				System.out.println("Adding value " + fileValues[1] + " with priority " + fileValues[0]);
				pQueue.add(Integer.parseInt(fileValues[0]), fileValues[1]);
			}
		}
		catch (Exception e) //Any Exception since IO and PracExamException can occur. We use the general term.
		{
			System.out.println(e);
		}
		
		System.out.println();
		System.out.println("Removing from the Priority Queue!");
		try
		{
			for (int i=0; i<10; i++)
			{
				temp = (String)pQueue.remove();
				System.out.println(temp);
			}
			System.out.println("Trying to remove one more from an empty heap...");
			temp = (String)pQueue.remove(); //removing one more element after the last element is removed
		}
		catch (PracExamException e) //catching the exception which will be raised in line 41
		{
			System.out.println(e); //printing the message inside the exception
		}

		System.out.println("\n+++++ Tests Complete +++++\n");
	}
}