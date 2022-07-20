/**
 * DSA Final Assessment Question 5 - GraphTest.java
 *
 * Name : Sahas Gunasekara
 * ID   : 20462075
 *
 **/

import java.io.*;

public class Q5GraphTest
{
	public static void main(String args[])
	{
			System.out.println("\n+++++ Question 5: Testing Graphs +++++\n");

			// put your code here
			Q5Graph g = new Q5Graph();
			try
			{
				g.addVertex("A");
				g.addVertex("B");
				g.addVertex("C");
				g.addVertex("D");
				g.addVertex("E");
				g.addVertex("F");
				System.out.println();
				System.out.println("Adding one more than the max size!");
				g.addVertex("H");
			}
			catch (PracExamException e)
			{
				System.out.println(e);
			}
			
			g.addEdge("A", "B", 3);
			g.addEdge("A", "C", 4);
			g.addEdge("A", "D", 5);
			g.addEdge("D", "B", 6);
			g.addEdge("D", "C", 7);
			System.out.println();
			System.out.println();
			System.out.println("Testing weighted Matrix!");
			g.displayAsWeightMatrix();
			System.out.println();
			System.out.println();
			System.out.println("Testing the adjacency list!");
			g.displayAsList();

			System.out.println();
			System.out.println("Loading from the Q5GraphDataFile!");
			loadFile("Q5GraphData.txt");
			
			System.out.println("\n+++++ Tests Complete +++++\n");
	}

	public static void loadFile(String fileName)
	{
		Q5Graph loadedGraph = new Q5Graph();
		String data;
		try
		{
			File file = new File(fileName);
			BufferedReader br = new BufferedReader(new FileReader(file));

			while ((data = br.readLine()) != null)
			{
				String[] fileValues = data.split(" ");
				loadedGraph.addEdge(fileValues[0], fileValues[1], Integer.parseInt(fileValues[2]));
			}
		}
		catch (Exception e) //Any Exception since IO and PracExamException can occur. We use the general term.
		{
			System.out.println(e);
		}
		System.out.println();
		System.out.println("Testing the adjacency list!");
		loadedGraph.displayAsList();
		System.out.println();
		System.out.println();
		System.out.println("Testing weighted Matrix!");
		loadedGraph.displayAsWeightMatrix();
	}
	
}
