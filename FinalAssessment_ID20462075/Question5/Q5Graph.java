/**
 * DSA Final Assessment Question 5 - Q5Graph.java                             4
 *
 * Name : Sahas Gunasekara
 * ID   : 20462075
 *
 **/
import java.util.*;

public class Q5Graph 
{
    private int maxsize;
    private int wmatrix[][];
    private String labels[];
    private int vertexCount;

    public Q5Graph() 
    {
	maxsize = 6; //changed to show it will throw exception
	wmatrix = new int[maxsize][maxsize];
	labels = new String[maxsize];
    for(int i=0; i < maxsize; i++)
    {
        for (int j=0; j< maxsize; j++)
        { 
            wmatrix[i][j] = 0;
        }
    }
    vertexCount = 0;
    }

    public void addVertex(String label) throws PracExamException
    {
        if (vertexCount == maxsize)
	{
		throw new PracExamException("No More Space Left!");
	}
	else if (!(hasVertex(label))) 
        {
            labels[vertexCount] = label;
            vertexCount++;
        }
    }

    public void addEdge(String label1, String label2, int weight)
    {
        int v1, v2;
        // for file loading, if the vertex doesnt exist, then create it.
        try
        {
            if (!hasVertex(label1))
            {
                addVertex(label1);
            }
            if (!hasVertex(label2))
            {
                addVertex(label2);
            }
        }
        catch (PracExamException e)
        {
            System.out.println(e);
        }
        
        v1 = getIndex(label1); 
        v2 = getIndex(label2);   

        wmatrix[v1][v2] = weight;
    }

    public boolean hasVertex(String label) 
    {
        boolean has = false;
        for (int i=0; i < vertexCount; i++) 
        {
           if (labels[i].equals(label))
                has = true;
        }
        return has;
    }

    public int getIndex(String label) 
    {
        int theVertex = -1;
        for (int i=0; i < vertexCount; i++) 
            {
            if (labels[i].equals(label))
                theVertex = i;
            }
	return theVertex;    
	}

    public void displayAsList() 
    {
		System.out.println("Adjacency List for graph is: ");
        for (int i=0; i < vertexCount; i++)
        {
            System.out.print(labels[i] + " - ");
            for (int j= 0; j < vertexCount; j++)
            {
                if (wmatrix[i][j] != 0)
                {
                    System.out.print(" " + labels[j] + " ");
                }
            }
            System.out.println();
        }    
    }

    public void displayAsWeightMatrix()
    {
        System.out.println("Weight matrix for graph is: ");
        System.out.print("   ");
        for (int i=0; i < vertexCount; i++)
        {
            System.out.print(" " + labels[i] + " ");
        }
        System.out.println();
        for (int i=0; i < labels.length; i++)
        {
            if (labels[i] != null)
            {
                System.out.print(labels[i] + " -");
                    if (wmatrix[i] != null)
                    {
                        for (int j = 0; j < vertexCount; j++)
                        {
                            System.out.print(" " + wmatrix[i][j] + " ");
                        }
                        System.out.println();  
                    }
            }
        }
    }

	// put your methods here
}  
