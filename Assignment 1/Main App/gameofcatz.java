import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

public class gameofcatz
{
    public static void main(String[] args)
    {
        String start = null;
        String finish = null;
        DSAGraph initializedGraph;

        if (args.length == 0)
        {
            System.out.println("------Providing usage information!-------");
            System.out.println("add argument '-i' for interactive testing environment");
            System.out.println("add argument '-s infile savefile' where savefile is the file you want to load");
        }
        else if (args[0].equals("-i"))
        {
            System.out.println("---------------Going to interactive mode!---------------");
            menu();
        }
        else if (args[0].equals("-s") && args.length == 3)
        {
            System.out.println("----------------Going to simulation mode!---------------");
            initializedGraph = initializeInput(args[1]);
            start = getStart(args[1]);
            finish = getFinish(args[1]);
            
            if (initializedGraph != null && start != null && finish != null && initializedGraph.hasVertex(start) && initializedGraph.hasVertex(finish))
            {
                initializedGraph.depthFirstSearch(start, finish);
                Path[] paths = initializedGraph.getRankedPaths();
                if (paths.length != 0)
                {
                    initializedGraph.printPaths(args[2]);
                    System.out.println("Data loaded from " + args[1] + " & the paths are generated to " + args[2]);
                }
            }
            else
            {
                System.out.println("The infile seems to be corrupted!");
            }  
        }
        else
        {
            System.out.println("Wrong combination of usage, call 'java gameofcatz' to get usage information");
        }
    }

    private static DSAHashTable getVerticeCodes(String fileName)
    {
        DSAHashTable verticeCodes = new DSAHashTable(5);
        String data;
        try
        {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((data = br.readLine()) != null)
            {
                String[] splitData = data.split(" ");
                if (splitData[0].equals("Ncode"))
                {
                    DSAHashEntry getCode = verticeCodes.get(splitData[1]);
                    if (getCode == null)
                    {
                        verticeCodes.put(splitData[1], Integer.parseInt(splitData[2]));
                    } 
                }
            }
        } catch (Exception e)
        {
            System.out.println("No Vertice Codes found, infile possibly doesn't exist"); 
        }

        return verticeCodes;
    }

    private static DSAHashTable getEdgeCodes(String fileName)
    {
        DSAHashTable edgeCodes = new DSAHashTable(5);
        String data;
        try
        {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((data = br.readLine()) != null)
            {
                String[] splitData = data.split(" ");
                if (splitData[0].equals("Ecode"))
                {
                    DSAHashEntry getCode = edgeCodes.get(splitData[1]);
                    if (getCode == null)
                    {
                        edgeCodes.put(splitData[1], Integer.parseInt(splitData[2]));
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println("No Edge Codes found, infile possibly doesn't exist"); 
        }
        return edgeCodes;
    }

    private static String getStart(String fileName)
    {
        String data;
        String returnStart = null;
        try
        {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((data = br.readLine()) != null)
            {
                String[] splitData = data.split(" ");
                if (splitData[0].equals("Start"))
                {
                    returnStart = splitData[1];
                }
            }
        } catch (Exception e)
        {
            System.out.println("No Start found, infile possibly doesn't exist"); 
        }

        return returnStart;
    }

    private static String getFinish(String fileName)
    {
        String data;
        String returnFinish = null;
        try
        {
            File file = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((data = br.readLine()) != null)
            {
                String[] splitData = data.split(" ");
                if (splitData[0].equals("Target"))
                {
                    returnFinish = splitData[1];
                }
            }
        } catch (Exception e)
        {
            System.out.println("No Target found, infile possibly doesn't exist"); 
        }

        return returnFinish;
    }

    private static DSAGraph initializeInput(String inFile)
    {
        DSAGraph returnGraph = null;
        DSAGraph initialGraph = new DSAGraph();
        String data;
    
        DSAHashTable edgeCodes = getEdgeCodes(inFile);
        DSAHashTable verticeCodes = getVerticeCodes(inFile);
        try
        {
            File file = new File(inFile);
            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((data = br.readLine()) != null)
            {
                String[] splitData = data.split(" ");
    
                if (splitData[0].equals("Node")) 
                {
                    DSAHashEntry foundHash = verticeCodes.get(splitData[2]);
                    if (foundHash != null)
                    {
                        initialGraph.addVertex(splitData[1], foundHash.getValue(), splitData[2]);
                    }
                    else
                    {
                        System.out.println("No Such Vertice Code for " + splitData[2]);
                    }
                }
                else if (splitData[0].equals("Edge"))
                {
                    DSAHashEntry foundHash = edgeCodes.get(splitData[3]);
                    if (foundHash != null)
                    {
                        initialGraph.addEdges(splitData[1], splitData[2] ,foundHash.getValue(), splitData[3]);
                    }
                    else
                    {
                        System.out.println("No Such Edge Code for " + splitData[3]);
                    }
                }
            }
            returnGraph = initialGraph;

        } catch (Exception e)
        {
            System.out.println("No Nodes or Edges found, infile possibly doesn't exist"); 
        }

        return returnGraph;
    }

    public static void menu()
    {
        DSAHashTable verticeCodes = new DSAHashTable(5);
        String from, to;
        DSAHashTable edgeCodes = new DSAHashTable(5);
        Scanner sc = new Scanner(System.in);
        String outputFile = null;
        boolean vaildInput = false;
        String choice = null;
        String confirmation = null;
        DSAGraph loadedGraph = new DSAGraph();
        System.out.println("  ^  ^  \n( 0..0 )  meow!\n");

        do 
        {
            System.out.println("-----------Interactive Mode! Main Menu!------------");
            System.out.println("Please enter the number for whatever respective option you want ");
            System.out.println("(1) Load input file");
            System.out.println("(2) Node Operations (find, insert, delete, update)");
            System.out.println("(3) Edge Operations (find, add, remove, update)");
            System.out.println("(4) Edge Code tweaks");
            System.out.println("(5) Node Code tweaks");
            System.out.println("(6) Display graph");
            System.out.println("(7) Display world");
            System.out.println("(8) Display routes");
            System.out.println("(9) Save Network");
            System.out.println("(10) Exit");
            System.out.print("Your choice: ");

            try
            {
                choice = sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Please enter a valid input");
            }

            switch(choice)
            {
                case "1": 
                    System.out.print("Please enter an input file: ");
                    outputFile = sc.nextLine();
                    if (!outputFile.equals(""))
                    {
                        loadedGraph = initializeInput(outputFile);
                        if (loadedGraph != null)
                        {
                            verticeCodes = getVerticeCodes(outputFile);
                            edgeCodes = getEdgeCodes(outputFile);
                            System.out.println("Data loaded from " + outputFile);
                        }
                    }
                    break;

                case "2": 
                    loadedGraph = nodeSubMenu(loadedGraph, verticeCodes, edgeCodes);
                    break;
                    
                case "3":
                    loadedGraph = edgeSubMenu(loadedGraph, verticeCodes, edgeCodes);
                    break;

                case "4": 
                    DSAHashTable oldEdgeCodes = new DSAHashTable(edgeCodes);
                    edgeCodes = edgeCodeMenu(loadedGraph, edgeCodes);
                    loadedGraph = regenerateEdgeGraph(loadedGraph, edgeCodes, oldEdgeCodes);
                    break;

                case "5":
                    DSAHashTable oldVerticeCodes = new DSAHashTable(verticeCodes);
                    verticeCodes = verticeCodeMenu(loadedGraph, verticeCodes);
                    loadedGraph = regenerateNodeGraph(loadedGraph, verticeCodes, oldVerticeCodes);
                    break;

                case "6":
                    loadedGraph.displayMatrix();
                    System.out.print("Do you want to save? (Y: Yes): ");
                    confirmation = sc.nextLine();
                    if (confirmation.equals("Y"))
                    {
                        System.out.print("File Name to Save?: ");
                        outputFile = sc.nextLine();
                        if (!outputFile.equals(""))
                        {
                            loadedGraph.printDisplayMatrix(outputFile);
                        }
                    }
                    break;

                case "7":
                    loadedGraph.displayAdjacency();
                    System.out.println("Number of Unique Vertices: " + verticeCodes.getCount());
                    System.out.println("Number of Unique Edges: " + edgeCodes.getCount());
                    System.out.print("Do you want to save? (Y: Yes): ");
                    confirmation = sc.nextLine();
                    if (confirmation.equals("Y"))
                    {
                        System.out.print("File Name to Save?: ");
                        outputFile = sc.nextLine();
                        if (!outputFile.equals(""))
                        {
                            loadedGraph.printDisplayAdjacency(outputFile);
                        }
                    }
                    break;

                case "8":
                    System.out.print("Starting position: ");
                    from = sc.nextLine();
                    System.out.print("Destination: ");
                    to = sc.nextLine();
                    if (!from.equals("") && !to.equals(""))
                    {
                        loadedGraph.depthFirstSearch(from, to);
                        loadedGraph.displayPaths();
                        System.out.print("Do you want to save? (Y: Yes): ");
                        confirmation = sc.nextLine();
                        if (confirmation.equals("Y"))
                        {
                            System.out.print("File Name to Save?: ");
                            outputFile = sc.nextLine();
                            if (!outputFile.equals(""))
                            {
                                loadedGraph.printPaths(outputFile);
                            }
                        }
                    }
                    break;

                case "9":
                    System.out.print("Starting position: ");
                    from = sc.nextLine();
                    System.out.print("Destination: ");
                    to = sc.nextLine();
                    System.out.print("Do you want to save? (Y: Yes): ");
                    confirmation = sc.nextLine();
                    if (confirmation.equals("Y"))
                    {
                        System.out.print("File Name to Save?: ");
                        outputFile = sc.nextLine();
                        if (!from.equals("") && !to.equals("") && !outputFile.equals(""))
                        {
                            saveNetwork(from, to, outputFile, loadedGraph, verticeCodes, edgeCodes);
                        }
                    }
                    break;

                case "10":
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Enter a valid choice please");
            }

        } while (!choice.equals("10"));
    }

    private static DSAGraph nodeSubMenu(DSAGraph loadedGraph, DSAHashTable verticeCodes, DSAHashTable edgeCodes)
    {
    
        String input;
        String updateInput;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println("-----------Node Submenu------------");
            System.out.println("1. Delete Node");
            System.out.println("2. Update Node");
            System.out.println("3. Insert Node");
            System.out.println("4. Display All Nodes");
            System.out.println("5. Display All Node Codes");
            System.out.println("6. Find Node");
            System.out.println("7. Exit Node Sub Menu");
            System.out.print("What would you like to do?: ");
            input = sc.nextLine();

            switch(input)
            {
                case "1":
                    if (loadedGraph.getVertices().getLength() != 0)
                    {
                        System.out.print("What would you like to delete?: ");
                        updateInput = sc.nextLine();
                        if (!updateInput.equals(""))
                        {
                            if (loadedGraph.hasVertex(updateInput))
                            {
                                loadedGraph.removeVertex(updateInput);
                            }
                            else
                            {
                                System.out.println("No such Node!");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Nothing to delete!");
                    }
                    break;

                case "2":
                    if (loadedGraph.getVertices().getLength() != 0)
                    {
                        System.out.print("What node would you like to update?: ");
                        updateInput = sc.nextLine();
                        if (!updateInput.equals(""))
                        {
                            if (loadedGraph.hasVertex(updateInput))
                            {
                                DSAGraphVertex updateVertex = loadedGraph.getVertex(updateInput);
                                System.out.print("What name would you like to give this new node?: ");
                                updateInput = sc.nextLine();
                                
                                updateVertex.setLabel(updateInput);
                            }
                            else
                            {
                                System.out.println("No such Node!");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Nothing to delete!");
                    }
                    break;

                case "3":
                    if (verticeCodes.getCount() != 0)
                    {
                        System.out.print("New name for this node?: ");
                        updateInput = sc.nextLine();
                        if (!updateInput.equals(""))
                        {
                            if (loadedGraph.hasVertex(updateInput))
                            {
                                System.out.println("A Node with this label already exists!");
                            }
                            else
                            {
                                System.out.println("NodeCodes:Weight");
                                verticeCodes.export();
                                System.out.print("New weight code for this node?: ");
                                input = sc.nextLine();
                                if (!updateInput.equals(""))
                                {
                                    DSAHashEntry returnEdgeCode = verticeCodes.get(input);

                                    if (returnEdgeCode != null)
                                    {
                                        loadedGraph.addVertex(updateInput, returnEdgeCode.getValue(), input);
                                    }
                                    else
                                    {
                                        System.out.println("No such Node Code");
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Add a Vertice Code First");
                    }
                    break;
                
                case "4":
                    System.out.println("----------Displaying all Nodes!------------");
                    System.out.println("Node Label : Node Value");
                    Iterator itVertices = loadedGraph.getVertices().iterator();
                    while (itVertices.hasNext())
                    {
                        DSAGraphVertex itVertex = (DSAGraphVertex) itVertices.next();
                        System.out.println(itVertex.getLabel() + " : " + itVertex.getValue());
                    }
                    break;

                case "5":
                    System.out.println("Node Codes : Weight");
                    verticeCodes.export();
                    break;

                case "6":
                    System.out.print("What is the Node you want to search for?: ");
                    input = sc.nextLine();
                    DSAGraphVertex returnVertice = loadedGraph.getVertex(input);
                    if (returnVertice != null)
                    {
                        System.out.println(returnVertice.getLabel() + ":" + returnVertice.getValue());
                    }
                    break;

                case "7":
                    System.out.println("Exiting to the main menu");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (!input.equals("7"));

        return loadedGraph;
    }

    private static DSAGraph edgeSubMenu(DSAGraph loadedGraph, DSAHashTable verticeCodes, DSAHashTable edgeCodes)
    {
        String updateInput, input;
        String fromInput, toInput;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println("-----------Edge Submenu------------");
            System.out.println("1. Delete Edge");
            System.out.println("2. Update Edge");
            System.out.println("3. Insert Edge");
            System.out.println("4. Display All Edges");
            System.out.println("5. Display All Edge Codes");
            System.out.println("6. Find Edge");
            System.out.println("7. Exit Edge Sub Menu");
            System.out.print("What would you like to do?: ");
            input = sc.nextLine();

            switch(input)
            {
                
                case "1":
                    if (loadedGraph.getEdges().getLength() != 0)
                    {
                        System.out.print("What is the 'from' of the edge you want to delete?: ");
                        fromInput = sc.nextLine();
                        System.out.print("What is the 'to' of the edge you want to delete?: ");
                        toInput = sc.nextLine();
                        if (!fromInput.equals("") && !toInput.equals(""))
                        {
                            if (loadedGraph.hasEdge(fromInput, toInput))
                            {
                                DSAGraphVertex fromVertex = loadedGraph.getVertex(fromInput);
                                DSAGraphVertex toVertex = loadedGraph.getVertex(toInput);
                                DSAGraphEdge deleteEdge = loadedGraph.getEdge(fromInput, toInput);
                                loadedGraph.removeEdge(deleteEdge, fromVertex, toVertex);
                            }
                            else
                            {
                                System.out.println("No such Edge!");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Nothing to delete!");
                    }
                    break;

                case "2":
                    if (loadedGraph.getEdges().getLength() != 0)
                    {
                        System.out.print("What is the 'from' of the edge you want to update?: ");
                        fromInput = sc.nextLine();
                        System.out.print("What is the 'to' of the edge you want to update?: ");
                        toInput = sc.nextLine();
                        if (!fromInput.equals("") && !toInput.equals(""))
                        {
                            if (loadedGraph.hasEdge(fromInput, toInput))
                            {
                                DSAGraphEdge updateEdge = loadedGraph.getEdge(fromInput, toInput);
                                System.out.println("EdgeCodes:Weight");
                                edgeCodes.export();
                                System.out.print("New weight code for this Edge?: ");
                                input = sc.nextLine();
                                DSAHashEntry returnEdgeCode = edgeCodes.get(input);

                                if (returnEdgeCode != null)
                                {
                                    DSAGraphVertex fromVertex = loadedGraph.getVertex(fromInput);
                                    DSAGraphVertex toVertex = loadedGraph.getVertex(toInput);
                                    DSAGraphEdge deleteEdge = loadedGraph.getEdge(fromInput, toInput);
                                    loadedGraph.removeEdge(updateEdge, fromVertex, toVertex);
                                    loadedGraph.addEdges(fromInput, toInput, returnEdgeCode.getValue(), input);
                                }
                                else
                                {
                                    System.out.println("No such Edge Code");
                                }
                            }
                            else
                            {
                                System.out.println("No such Edge!");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Nothing to update!");
                    }
                    break;

                case "3":
                    if (edgeCodes.getCount() != 0)
                    {
                        System.out.print("What is the 'from' of the edge you want to add?: ");
                        fromInput = sc.nextLine();
                        System.out.print("What is the 'to' of the edge you want to add?: ");
                        toInput = sc.nextLine();
                        if (!fromInput.equals("") && !toInput.equals(""))
                        {
                            if (loadedGraph.hasEdge(fromInput, toInput))
                            {
                                System.out.println("A Edge with this label already exists!");
                            }
                            else
                            {
                                System.out.println("EdgeCodes:Weight");
                                edgeCodes.export();
                                System.out.print("New weight code for this Edge?: ");
                                input = sc.nextLine();
                                if (!input.equals(""))
                                {    
                                    DSAHashEntry returnEdgeCode = edgeCodes.get(input);

                                    if (returnEdgeCode != null)
                                    {
                                        loadedGraph.addEdges(fromInput, toInput, returnEdgeCode.getValue(), input);
                                    }
                                    else
                                    {
                                        System.out.println("No such Edge Code");
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        System.out.println("Add an edge code first");
                    }
                    break;
                
                case "4":
                    System.out.println("----------Displaying all Edges!------------");
                    System.out.println("Edge From -> Edge To : Edge value");
                    Iterator itEdges = loadedGraph.getEdges().iterator();
                    while (itEdges.hasNext())
                    {
                        DSAGraphEdge itEdge = (DSAGraphEdge) itEdges.next();
                        System.out.println(itEdge.getFrom() + " -> " + itEdge.getTo() + " : " + itEdge.getValue());
                    }
                    break;

                case "5":
                    System.out.println("EdgeCodes:Weight");
                    edgeCodes.export();
                    break;

                case "6":
                    System.out.print("What is the 'from' of the edge you want to find?: ");
                    fromInput = sc.nextLine();
                    System.out.print("What is the 'to' of the edge you want to find?: ");
                    toInput = sc.nextLine();
                    if (!fromInput.equals("") && !toInput.equals(""))
                    {
                        DSAGraphEdge returnEdge = loadedGraph.getEdge(fromInput, toInput);
                        if (returnEdge != null)
                        {
                            System.out.println("Edge From -> Edge To : Edge value");
                            System.out.println(returnEdge.getFrom() + " -> " + returnEdge.getTo() + " : " + returnEdge.getValue());
                        }
                        else
                        {
                            System.out.println("No such Edge!");
                        }
                    }
                    break;

                case "7":
                    System.out.println("Exiting to the main menu");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (!input.equals("7"));

        return loadedGraph;
    }

    private static DSAHashTable edgeCodeMenu(DSAGraph loadedGraph, DSAHashTable edgeCodes)
    {
        String input, updateInput;
        DSAHashEntry deleteEdge;
        Integer edgeWeight;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println("-----------Edge Code Tweaking Submenu------------");
            System.out.println("1. Add Edge Code Parameter");
            System.out.println("2. Delete Edge Code Parameter");
            System.out.println("3. Update Edge Code Parameter");
            System.out.println("4. Display Edge Codes");
            System.out.println("5. Exit");
            System.out.print("What is your choice?: ");
            input = sc.nextLine();

            switch(input)
            {
                case "1":
                    System.out.print("Please enter the Char for the Edge Code: ");
                    updateInput = sc.nextLine();
                    if (!updateInput.equals(""))
                    {
                        if (edgeCodes.get(updateInput) == null)
                        {
                            System.out.print("Please enter the weight for the Edge Code: ");
                            try
                            {
                                edgeWeight = sc.nextInt();
                                edgeCodes.put(updateInput, edgeWeight);
                                sc.nextLine();
                            } catch (InputMismatchException e)
                            {
                                System.out.println("Please input an integer!");
                            }
                        }
                        else
                        {
                            System.out.println("Use a different label since this already exists!");
                        }
                    }
                    break;

                case "2":
                    boolean foundElement = false;
                    System.out.print("Please enter the Char for the Edge Code to be deleted: ");
                    updateInput = sc.nextLine();
                    if (!updateInput.equals(""))
                    {
                        deleteEdge = edgeCodes.get(updateInput);
                        if (deleteEdge != null)
                        {
                            Iterator itEdges = loadedGraph.getEdges().iterator();
                            while (itEdges.hasNext())
                            {
                                DSAGraphEdge currEdge = (DSAGraphEdge) itEdges.next();
                                if ((Integer) currEdge.getValue() == deleteEdge.getValue())
                                {
                                    foundElement = true;
                                }
                            }
                            if (foundElement == true)
                            {
                                System.out.println("There are Edges in this graph with this edge code, therefore will not delete");
                            }
                            else
                            {
                                edgeCodes.remove(updateInput);
                            }
                        }
                        else
                        {
                            System.out.println("No such Edge Code exists!");
                        }
                    }
                    break;

                case "3":
                    System.out.println("EdgeCode:Weight");
                    edgeCodes.export();
                    System.out.print("Please enter the Char for the Edge Code to be update: ");
                    updateInput = sc.nextLine();
                    if (!updateInput.equals(""))
                    {
                        deleteEdge = edgeCodes.get(updateInput);
                        if (deleteEdge != null)
                        {
                            System.out.print("Please enter a new integer weight for the Edge Code: ");
                            try
                            {
                                edgeWeight = sc.nextInt();
                                edgeCodes.remove(updateInput);
                                edgeCodes.put(updateInput, edgeWeight);
                                sc.nextLine();
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Please enter an integer!");
                            }
                        }
                        else
                        {
                            System.out.println("No such Edge Code exists!");
                        }
                    }
                    break;

                case "4":
                    System.out.println("EdgeCode:Weight");
                    edgeCodes.export();
                    break;

                case "5":
                    System.out.println("Exiting Edge Code Tweaking menu!");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;

            }

        } while (!input.equals("5"));

        return edgeCodes;
    }

    private static DSAHashTable verticeCodeMenu(DSAGraph loadedGraph, DSAHashTable verticeCodes)
    {
        String input, updateInput;
        DSAHashEntry deleteVerticeCode;
        Integer verticeWeight;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println("-----------Vertice Code Tweaking Submenu------------");
            System.out.println("1. Add Vertice Code Parameter");
            System.out.println("2. Delete Vertice Code Parameter");
            System.out.println("3. Update Vertice Code Parameter");
            System.out.println("4. Display Vertice Codes");
            System.out.println("5. Exit");
            System.out.print("What is your choice?: ");
            input = sc.nextLine();

            switch(input)
            {
                case "1":
                    System.out.print("Please enter the Char for the Edge Code: ");
                    updateInput = sc.nextLine();
                    if (!updateInput.equals(""))
                    {
                        if (verticeCodes.get(updateInput) == null)
                        {
                            System.out.print("Please enter the weight for the Edge Code: ");
                            try
                            {
                                verticeWeight = sc.nextInt();
                                verticeCodes.put(updateInput, verticeWeight);
                                sc.nextLine();
                            } catch (InputMismatchException e)
                            {
                                System.out.println("Please input an integer!");
                            }
                        }
                        else
                        {
                            System.out.println("Use a different label since this already exists!");
                        }
                    }
                    break;

                case "2":
                    boolean foundElement = false;
                    System.out.print("Please enter the Char for the Vertice Code to be deleted: ");
                    updateInput = sc.nextLine();
                    if (!updateInput.equals(""))
                    {
                        deleteVerticeCode = verticeCodes.get(updateInput);
                        if (deleteVerticeCode != null)
                        {
                            Iterator itVertices = loadedGraph.getVertices().iterator();
                            while (itVertices.hasNext())
                            {
                                DSAGraphVertex currVertex = (DSAGraphVertex) itVertices.next();
                                if ((Integer) currVertex.getValue() == deleteVerticeCode.getValue())
                                {
                                    foundElement = true;
                                }
                            }
                            if (foundElement == true)
                            {
                                System.out.println("There are Edges in this graph with this edge code, therefore will not delete");
                            }
                            else
                            {
                                verticeCodes.remove(updateInput);
                            }
                        }
                        else
                        {
                            System.out.println("No such Edge Code exists!");
                        }
                    }
                    break;

                case "3":
                    System.out.print("Please enter the Char for the Edge Code to be update: ");
                    updateInput = sc.nextLine();
                    if (!updateInput.equals(""))
                    {
                        deleteVerticeCode = verticeCodes.get(updateInput);
                        if (deleteVerticeCode != null)
                        {
                            System.out.print("Please enter a new integer weight for the Edge Code: ");
                            try
                            {
                                verticeWeight = sc.nextInt();
                                verticeCodes.remove(updateInput);
                                verticeCodes.put(updateInput, verticeWeight);
                                sc.nextLine();
                            }
                            catch (InputMismatchException e)
                            {
                                System.out.println("Please enter an integer!");
                            }
                        }
                        else
                        {
                            System.out.println("No such Edge Code exists!");
                        }
                    }
                    break;

                case "4":
                    System.out.println("EdgeCode:Weight");
                    verticeCodes.export();
                    break;

                case "5":
                    System.out.println("Exiting Edge Code Tweaking menu!");
                    break;

                default:
                    System.out.println("Invalid choice");
                    break;

            }

        } while (!input.equals("5"));

        return verticeCodes;
    }

    private static DSAGraph regenerateNodeGraph(DSAGraph loadedGraph, DSAHashTable verticeCodes, DSAHashTable oldVerticeCodes)
    {
        DSAGraph returnGraph = new DSAGraph();
        DSAHashEntry[] rawVerticeCodes = verticeCodes.getArray();
        DSAHashEntry[] rawOldVerticeCodes = oldVerticeCodes.getArray();
        System.out.println("Node Parameters have changed, regenerating the graph.");

        Iterator itVertices = loadedGraph.getVertices().iterator();
        while (itVertices.hasNext())
        {
            DSAGraphVertex tempVertice = (DSAGraphVertex) itVertices.next();
            Integer currValue =  verticeCodes.get(tempVertice.getVCode()).getValue();
            returnGraph.addVertex(tempVertice.getLabel(), currValue, tempVertice.getVCode());
            
        }

        Iterator itEdges = loadedGraph.getEdges().iterator();
        while (itEdges.hasNext())
        {
            DSAGraphEdge tempEdge = (DSAGraphEdge) itEdges.next();
            returnGraph.addEdges(tempEdge.getFrom(), tempEdge.getTo(), tempEdge.getValue(), tempEdge.getECode());
        }
        
        return returnGraph;
    }

    private static DSAGraph regenerateEdgeGraph(DSAGraph loadedGraph, DSAHashTable edgeCodes, DSAHashTable oldEdgeCodes)
    {
        DSAGraph returnGraph = new DSAGraph();
        System.out.println("Edge Parameters have changed, regenerating the graph.");

        Iterator itVertices = loadedGraph.getVertices().iterator();
        while (itVertices.hasNext())
        {
            DSAGraphVertex tempVertex = (DSAGraphVertex) itVertices.next();
            returnGraph.addVertex(tempVertex.getLabel(), tempVertex.getValue(), tempVertex.getVCode());
        }

        Iterator itEdges = loadedGraph.getEdges().iterator();
        while (itEdges.hasNext())
        {
            DSAGraphEdge tempEdge = (DSAGraphEdge) itEdges.next();
            Integer currValue =  edgeCodes.get(tempEdge.getECode()).getValue();
            returnGraph.addEdges(tempEdge.getFrom(), tempEdge.getTo(), currValue, tempEdge.getECode());
        }        
        
        return returnGraph;
    }

    private static void saveNetwork(String from, String to, String outputFile, DSAGraph loadedGraph, DSAHashTable verticeCodes,
    DSAHashTable edgeCodes)
    {
        try
        {
            FileWriter writer = new FileWriter(outputFile);
            BufferedWriter bw = new BufferedWriter(writer);

            String data = "";
            data = "# Node label code\n";
            bw.write(data);
            data = "# Edge label label code\n";
            bw.write(data);
            data = "# Ncode code weight\n";
            bw.write(data);
            data = "# Ecode code weight\n";
            bw.write(data);
            data = "# Start label\n";
            bw.write(data);
            data = "# Target label\n";
            bw.write(data);
            
            DSAHashEntry[] codes = verticeCodes.getArray();

            for (int x = 0; x < codes.length; x++)
            {
                if (codes[x].getKey() != "")
                {
                    data = "Ncode " + codes[x].getKey() + " " + codes[x].getValue() + "\n";
                    bw.write(data);
                }
            }

            Iterator itVertices = loadedGraph.getVertices().iterator();
            while (itVertices.hasNext())
            {
                DSAGraphVertex currVertex = (DSAGraphVertex) itVertices.next();
                for (int x = 0; x < codes.length; x++)
                {
                    if (codes[x].getValue() == (Integer) currVertex.getValue())
                    {
                        data = "Node " + currVertex.getLabel() + " " + codes[x].getKey() + "\n";
                        bw.write(data);
                    }
                }
            }

            codes = edgeCodes.getArray();

            for (int x = 0; x < codes.length; x++)
            {
                if (codes[x].getKey() != "")
                {
                    data = "Ecode " + codes[x].getKey() + " " + codes[x].getValue() + "\n";
                    bw.write(data);
                }
            }

            Iterator itEdges = loadedGraph.getEdges().iterator();
            while (itEdges.hasNext())
            {
                DSAGraphEdge currEdge = (DSAGraphEdge) itEdges.next();
                for (int x = 0; x < codes.length; x++)
                {
                    if (codes[x].getValue() == (Integer) currEdge.getValue())
                    {
                        data = "Edge " + currEdge.getFrom() + " " + currEdge.getTo() + " " + codes[x].getKey() + "\n";
                        bw.write(data);
                    }
                }
            }

            data = "Start " + from + "\n";
            bw.write(data);
            data = "Target " + to;
            bw.write(data);
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception when printing the paths!");
        }
    }

}