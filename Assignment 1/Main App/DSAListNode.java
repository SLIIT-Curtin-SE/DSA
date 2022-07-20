/*A version of this code has previously been submitted by me for Practical 4 DSA*/
class DSAListNode
{
    private Object value;
    private DSAListNode next;
    private DSAListNode prev;

    public DSAListNode(Object inValue)
    {
        value = inValue;
        next = null;
        prev = null;
    }

    public Object getValue()
    {
        return value;
    }

    public void setValue(Object inValue)
    {
        value = inValue;
    }

    public DSAListNode getNext()
    {
        return next;
    }

    public void setNext(DSAListNode inNext)
    {
        next = inNext;
    }

    public DSAListNode getPrev()
    {
        return prev;
    }

    public void setPrev(DSAListNode inPrev)
    {
        prev = inPrev;
    }
}

