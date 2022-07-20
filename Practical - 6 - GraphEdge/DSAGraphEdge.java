class DSAGraphEdge
{
    DSAGraphVertex from;
    DSAGraphVertex to;
    String label;
    Object value = null;
    boolean directed;

    public DSAGraphEdge(DSAGraphVertex fromVertex, DSAGraphVertex toVertex, String inLabel, Object inValue, boolean inDirected)
    {
        from = fromVertex;
        to = toVertex;
        label = inLabel;
        value = inValue;
        directed = inDirected;
    }

    public DSAGraphEdge(DSAGraphVertex fromVertex, DSAGraphVertex toVertex, String inLabel, boolean inDirected)
    {
        from = fromVertex;
        to = toVertex;
        label = inLabel;
        directed = inDirected;
    }

    public String getLabel()
    {
        return label;
    }

    public Object getValue()
    {
        return value;
    }

    public DSAGraphVertex getFrom()
    {
        return from;
    }

    public DSAGraphVertex getTo()
    {
        return to;
    }

    public boolean isDirected()
    {
        return directed;
    }

    public String toString()
    {
        String export = "This the edge from the Vertex " + from.getLabel() + " to Vertex " + to.getLabel();
    }
}