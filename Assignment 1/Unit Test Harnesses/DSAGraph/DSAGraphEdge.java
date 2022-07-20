/*A version of this code has previously been submitted by me for Practical 6 DSA*/

class DSAGraphEdge
{
    private String label = null;
    private String from;
    private String to;
    private Object value;

    public DSAGraphEdge(String inFrom, String inTo, Object inValue)
    {
        from = inFrom;
        to = inTo;
        value = inValue;
    }

    public String getLabel()
    {
        return label;
    }

    public Object getValue()
    {
        return value;
    }

    public String getFrom()
    {
        return from;
    }

    public String getTo()
    {
        return to;
    }

    public void setValue(Object inValue)
    {
        value = inValue;
    }
    
}