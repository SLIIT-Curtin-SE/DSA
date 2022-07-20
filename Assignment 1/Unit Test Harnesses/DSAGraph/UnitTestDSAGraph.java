class UnitTestDSAGraph
{
    public static void main(String[] args)
    {
        System.out.println("Creating a Test Graph");
        DSAGraph testGraph = new DSAGraph();
        System.out.println("Adding Nodes, A and B with weight 5 each");
        testGraph.addVertex("A", 5);
        testGraph.addVertex("B", 5);
        testGraph.displayAdjacency();
        System.out.println("Adding an edge between A and B with a weight of 9");
        testGraph.addEdges("A", "B", 9);
        testGraph.displayAdjacency();
        testGraph.displayMatrix();
        System.out.println("Adding an edge between B and A with a weight of 4");
        testGraph.addEdges("B", "A", 4);
        testGraph.displayAdjacency();
        testGraph.displayMatrix();
        System.out.println("Adding an edge between A and E (nonexistent) with a weight of 3 (Shouldn't change)");
        testGraph.addEdges("A", "E", 5);
        testGraph.displayAdjacency();
        testGraph.displayMatrix();
        System.out.println("Adding Vertice C and Edge C to A both with a weight of 3 & removing Vertex B");
        testGraph.addVertex("C", 3);
        testGraph.addEdges("A", "C", 3);
        testGraph.removeVertex("B");
        testGraph.displayAdjacency();
        testGraph.displayMatrix();
        System.out.println("Adding Edge from C to A with weight of 3 and removing edge from A to C");
        DSAGraphEdge deleteEdge = testGraph.getEdge("A", "C");
        DSAGraphVertex fromVertex = testGraph.getVertex("A");
        DSAGraphVertex toVertex = testGraph.getVertex("C");
        testGraph.removeEdge(deleteEdge, fromVertex, toVertex);
        testGraph.addEdges("C", "A", 3);
        testGraph.displayAdjacency();
        testGraph.displayMatrix();
        System.out.println("Checking if a Vertex by the Label Z exists");
        System.out.println(testGraph.hasVertex("Z"));
        System.out.println("Checking if a Vertex by the Label A exists");
        System.out.println(testGraph.hasVertex("A"));
        System.out.println("Checking if an Edge from A to C exists (we just deleted this)");
        System.out.println(testGraph.hasEdge("A", "C"));
        System.out.println("Checking if an Edge from C to A exists");
        System.out.println(testGraph.hasEdge("C", "A"));
        System.out.println("Getting the Vertex Count (Should only be A and C)");
        System.out.println(testGraph.getVertexCount());
        System.out.println("Adding Vertex's B and D, Edges AB, BC, CD and AD all with weight 4");
        testGraph.addVertex("B", 4);
        testGraph.addVertex("D", 4);
        testGraph.addEdges("A", "B", 4);
        testGraph.addEdges("B", "C", 4);
        testGraph.addEdges("C", "D", 4);
        testGraph.addEdges("A", "D", 4);
        testGraph.displayAdjacency();
        testGraph.displayMatrix();
        testGraph.printDisplayMatrix("Matrix.txt");
        testGraph.printDisplayAdjacency("Adjacency.txt");
        System.out.println("Generating all the paths from A to D and Displaying them");
        testGraph.depthFirstSearch("A", "D");
        testGraph.displayPaths();
        System.out.println("Printing all the ranked paths to a text file 'Paths.txt' ");
        testGraph.printPaths("Paths.txt");
    }
}