/*A version of this code has previously been submitted by me for Practical 6 DSA*/

import java.util.Iterator;

class DSAGraphVertex
{
    private String label;
    private Object value = null;
    private DSALinkedList links;
    private boolean visited;
    private String vCode;

    public DSAGraphVertex(String inLabel, Object inValue, String inVCode)
    {
        label = inLabel;
        value = inValue;
        links = new DSALinkedList();
        visited = false;
        vCode = inVCode;
    }

    public DSAGraphVertex(String inLabel)
    {
        label = inLabel;
        links = new DSALinkedList();
        visited = false;
    }

    public void addLink(DSAGraphVertex inVertex)
    {  
       links.insertLast(inVertex);
    }

    public void deleteLink(Object inVertex)
    {
        Iterator itLinks = links.iterator();
        while (itLinks.hasNext())
        {
            if (itLinks.next() == inVertex)
            {
                links.remove(inVertex);
            }
        }
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String inLabel)
    {
        label = inLabel;
    }

    public Object getValue()
    {
        return value;
    }

    public DSALinkedList getAdjacent()
    {
        return links;
    }

    public void setVisited()
    {
        visited = true;
    }

    public void clearVisited()
    {
        visited = false;
    }

    public boolean getVisited()
    {
        return visited;
    }

    public void setVCode(String inVCode)
    {
        vCode = inVCode;
    }

    public String getVCode()
    {
        return vCode;
    }


}