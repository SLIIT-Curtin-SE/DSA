/*A version of this code has previously been submitted by me for Practical 6 DSA*/

import java.util.Iterator;
import java.io.*;

class DSAGraph
{
    private DSALinkedList vertices;
    private DSALinkedList edges;
    private Object value = null;
    private DSALinkedList paths;
    private Path[] rankedPaths;
    
    public DSAGraph()
    {
        vertices = new DSALinkedList();
        edges = new DSALinkedList();
        paths = new DSALinkedList();
    }

    public DSAGraph(DSAGraph copyGraph)
    {
        vertices = copyGraph.getVertices();
        edges = copyGraph.getEdges();
        paths = copyGraph.getPaths();
    }

    public void clearPaths()
    {
        paths = new DSALinkedList();
    }

    public DSALinkedList getVertices()
    {
        return vertices;
    }

    public DSALinkedList getEdges()
    {
        return edges;
    }

    public DSALinkedList getPaths()
    {
        return paths;
    }

    public Path[] getRankedPaths()
    {
        return rankedPaths;
    }

    public void addVertex(String inLabel, Object inValue)
    {
        if (hasVertex(inLabel) == false)
        {
            DSAGraphVertex vertex = new DSAGraphVertex(inLabel, inValue);
            vertices.insertLast(vertex);
        }
    }

    private void addEdge(String inFrom, String inTo, Object inValue)
    {
        if (hasEdge(inFrom, inTo) == false)
        {
            DSAGraphEdge edge = new DSAGraphEdge(inFrom, inTo, inValue);
            edges.insertLast(edge);
        }
    }

    public void addVertex(String inLabel)
    {
        if (hasVertex(inLabel) == false)
        {
            DSAGraphVertex vertex = new DSAGraphVertex(inLabel);
            vertices.insertLast(vertex);
        }
    }

    public boolean hasVertex(String inLabel)
    {
        boolean found = false;
        DSAGraphVertex tempVertice;
        Iterator itVertices = vertices.iterator();
        while (itVertices.hasNext())
        {
            tempVertice= (DSAGraphVertex) itVertices.next();
            if (tempVertice.getLabel().equals(inLabel))
            {
                found = true;
            }
        }
        return found;
    }

    public boolean hasEdge(String inFrom, String inTo)
    {
        boolean found = false;
        DSAGraphEdge tempEdge;
        Iterator itEdges = edges.iterator();
        while (itEdges.hasNext())
        {
            tempEdge= (DSAGraphEdge) itEdges.next();
            if (tempEdge.getFrom().equals(inFrom))
            {
                if (tempEdge.getTo().equals(inTo))
                {
                    found = true;
                }
            }
        }
        return found;
    }

    public Integer getVertexCount()
    {
        Integer count = vertices.getLength();
        return count;
    }

    public void addEdges(String inLabel1, String inLabel2, Object inValue)
    {
        if (getVertex(inLabel1) != null && getVertex(inLabel2) != null)
        {
            addEdge(inLabel1, inLabel2, inValue);
            getVertex(inLabel1).addLink(getVertex(inLabel2));
        } 
    }

    public DSAGraphVertex getVertex(String inLabel)
    {
        DSAGraphVertex returnVertice = null;
        DSAGraphVertex tempVertice;
        Iterator itVertices = vertices.iterator();
        while(itVertices.hasNext())
        {
            tempVertice = (DSAGraphVertex) itVertices.next();
            if (tempVertice.getLabel().equals(inLabel))
            {
                returnVertice = tempVertice;
            }
        }
        return returnVertice;
    }

    public void removeVertex(String inLabel)
    {
        DSAGraphEdge deleteEdge;
        DSAGraphVertex tempVertice;

        DSAGraphVertex deleteVertice = getVertex(inLabel);
        vertices.remove(deleteVertice);

        Iterator itVertices = vertices.iterator();
        while(itVertices.hasNext())
        {
            tempVertice = (DSAGraphVertex) itVertices.next();
            deleteEdge = getEdge(tempVertice.getLabel(), inLabel);
            if (deleteEdge != null)
            {
                removeEdge(deleteEdge, tempVertice, deleteVertice);
            }
            deleteEdge = getEdge(inLabel, tempVertice.getLabel());
            if (deleteEdge != null)
            {
                removeEdge(deleteEdge, tempVertice, deleteVertice);
            }
        }
    }

    public void removeEdge(DSAGraphEdge deleteEdge, DSAGraphVertex fromVertex, DSAGraphVertex toVertex)
    {
        edges.remove(deleteEdge);
        fromVertex.deleteLink(toVertex);
    }

    public DSAGraphEdge getEdge(String inLabel1, String inLabel2)
    {
        DSAGraphEdge returnEdge = null;
        DSAGraphEdge tempEdge;
        Iterator itEdges = edges.iterator();
        while(itEdges.hasNext())
        {
            tempEdge = (DSAGraphEdge) itEdges.next();
            if (tempEdge.getFrom().equals(inLabel1))
            {
                if (tempEdge.getTo().equals(inLabel2))
                {
                    returnEdge = tempEdge;
                }
            }
        }
        return returnEdge;
    }

    private DSALinkedList getAdjacent(String inLabel)
    {
        DSALinkedList returnList = null;
        Iterator itVertices = vertices.iterator();
        DSAGraphVertex tempVertice;
        while (itVertices.hasNext())
        {
            tempVertice = (DSAGraphVertex) itVertices.next();
            if (tempVertice.getLabel().equals(inLabel))
            {
                returnList = tempVertice.getAdjacent();
            }
        }
        return returnList;
    }
    public void displayMatrix()
    {
        DSAGraphVertex tempVertex = null;
        DSAGraphVertex colVertex = null;
        System.out.println("-----------Displaying Weighted Matrix------------");
        Iterator itVertices = vertices.iterator();
        System.out.print("| ");
        while (itVertices.hasNext())
        {
            tempVertex = (DSAGraphVertex) itVertices.next();
            System.out.print("|" + tempVertex.getLabel());
        }
        System.out.println("|");

        Iterator itVertice = vertices.iterator();
        while (itVertice.hasNext())
        {
            tempVertex = (DSAGraphVertex) itVertice.next();
            System.out.print("|"+ tempVertex.getLabel());

            Iterator colVertices = vertices.iterator();
            while (colVertices.hasNext())
            {
                colVertex = (DSAGraphVertex) colVertices.next();
                if (colVertex.getLabel().equals(tempVertex.getLabel()))
                {
                    System.out.print("| ");
                }
                else
                {
                    DSAGraphEdge connection = getEdge(tempVertex.getLabel(), colVertex.getLabel());
                    if (connection == null)
                    {
                        System.out.print("| ");
                    }
                    else
                    {
                        System.out.print("|" + connection.getValue());
                    }
                }
            }
            System.out.println("|");
        }
    }   

    public void displayAdjacency()
    {
        System.out.println("-------------Displaying Adjacency!-------------");
        Iterator itVertices = vertices.iterator();
        DSAGraphVertex tempVertice;
        DSAGraphVertex tempLink;
        while (itVertices.hasNext())
        {
            tempVertice = (DSAGraphVertex) itVertices.next();
            System.out.print("| " +  tempVertice.getLabel());
            Iterator itLinks = tempVertice.getAdjacent().iterator();
            System.out.print(" | ");
            while (itLinks.hasNext())
            {
                tempLink = (DSAGraphVertex) itLinks.next();
                System.out.print(tempLink.getLabel());
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    public void depthFirstSearch(String inSource, String inDestination)
    {
        DSAGraphVertex currVertice;
        DSAGraphVertex startVertex = getVertex(inSource);
        DSAGraphVertex endVertex = getVertex(inDestination);
        String result = startVertex.getLabel();

        if (startVertex != null && endVertex != null)
        {         
            dfsUtil(startVertex, endVertex , result);
            Iterator itVertices = vertices.iterator();

            while (itVertices.hasNext())
            {
                currVertice = (DSAGraphVertex) itVertices.next();
                currVertice.clearVisited();
            }

            if (paths.getLength() == 0)
            {
                System.out.println("No possible paths found!");
            }
            else
            {
                rankedPaths = sortPaths();
                clearPaths();
            }
            
        }
    }

    public void displayPaths()
    {
        for (int x = 0; x < rankedPaths.length; x++)
        {
            System.out.println(rankedPaths[x].getName() + " : " + rankedPaths[x].getWeight());
        }
    }

    private void dfsUtil(DSAGraphVertex inSource, DSAGraphVertex inDestination, String result)
    {
        DSAGraphVertex currVertice;
        int length;
        Iterator itVertex = inSource.getAdjacent().iterator();
        
        if (inSource.getLabel().equals(inDestination.getLabel()))
        {
            paths.insertLast(result);
            return;
        }

        inSource.setVisited();
        
        while (itVertex.hasNext())
        {
            currVertice = (DSAGraphVertex) itVertex.next();
            if (!currVertice.getVisited())
            {
                length = currVertice.getLabel().length();
                result = result + "+" + currVertice.getLabel();
                dfsUtil(currVertice, inDestination, result);
                result = result.substring(0, result.length() -1 -length);
            } 
        }

        inSource.clearVisited();
    }

    public void printDisplayMatrix(String fileName)
    {
        DSAGraphVertex tempVertex = null;
        DSAGraphVertex colVertex = null;
        Iterator itVertices = vertices.iterator();
        
        try
        {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);

            String data = "";
            data = data + "| ";
            while (itVertices.hasNext())
            {
                tempVertex = (DSAGraphVertex) itVertices.next();
                data = data + "|" + tempVertex.getLabel();
            }
            data = data + "|\n";
            bw.write(data);
            data = "";
            Iterator itVertice = vertices.iterator();
            while (itVertice.hasNext())
            {
                tempVertex = (DSAGraphVertex) itVertice.next();
                data = data + "|"+ tempVertex.getLabel();

                Iterator colVertices = vertices.iterator();
                while (colVertices.hasNext())
                {
                    colVertex = (DSAGraphVertex) colVertices.next();
                    if (colVertex.getLabel().equals(tempVertex.getLabel()))
                    {
                        data = data + "| ";
                    }
                    else
                    {
                        DSAGraphEdge connection = getEdge(tempVertex.getLabel(), colVertex.getLabel());
                        if (connection == null)
                        {
                            data = data + "| ";
                        }
                        else
                        {
                            data = data + "|" + connection.getValue();
                        }
                    }
                }
                data = data + "|\n";
                bw.write(data);
                data = "";
            }
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception when printing the paths!");
        }
    }

    public void printDisplayAdjacency(String fileName)
    {
        try
        {
            FileWriter writer = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(writer);

            Iterator itVertices = vertices.iterator();
            DSAGraphVertex tempVertice;
            DSAGraphVertex tempLink;
            String data = "";

            while (itVertices.hasNext())
            {
                tempVertice = (DSAGraphVertex) itVertices.next();
                data = data + "| " +  tempVertice.getLabel();
                Iterator itLinks = tempVertice.getAdjacent().iterator();
                data = data + " | ";
                while (itLinks.hasNext())
                {
                    tempLink = (DSAGraphVertex) itLinks.next();
                    data = data + tempLink.getLabel();
                    data = data + " ";
                }
                data = data + "\n";
                bw.write(data);
                data = "";
            }
            bw.close();
        }        
        catch (IOException e)
        {
            System.out.println("Exception when printing the paths!");
        }
    }

    private Path[] sortPaths()
    {
        Integer weight;
        String vertexLabel;
        String fromLabel, toLabel;
        Path[] arrPaths;
        Integer count = paths.getLength();
        Integer arrInd = 0;

        arrPaths = new Path[count];

        Iterator itPaths = paths.iterator();

        while(itPaths.hasNext())
        {
            String pathName = (String) itPaths.next();
            weight = 0;
            String[] splitVertex = pathName.split("\\+");
            for (int x = 0; x < splitVertex.length; x++)
            {
                vertexLabel = splitVertex[x];
                weight = weight + (Integer) getVertex(vertexLabel).getValue();
            }

            for (int x = 0; x < splitVertex.length -1; x++)
            {
                fromLabel = splitVertex[x];
                toLabel = splitVertex[x+1];
                weight = weight + (Integer) getEdge(fromLabel, toLabel).getValue();
            }
            arrPaths[arrInd] = new Path(pathName.replace("+", "->"), weight);
            arrInd = arrInd + 1;
        }

        arrPaths = Sort.bubbleSort(arrPaths);

        return arrPaths;
    }

    public void printPaths(String saveFile)
    {
        printPaths(rankedPaths, saveFile);
    }

    private void printPaths(Path[] rankedPaths, String saveFile)
    {
        if (rankedPaths != null)
        {
            try
            {
                FileWriter writer = new FileWriter(saveFile);
                BufferedWriter bw = new BufferedWriter(writer);

                for (int x = 0; x < rankedPaths.length; x++)
                {
                    String data = rankedPaths[x].getName() + " : " + rankedPaths[x].getWeight() + "\n";
                    bw.write(data);
                }
                bw.close();
            }
            catch (IOException e)
            {
                System.out.println("Exception when printing the paths!");
            }
        }
    }
}