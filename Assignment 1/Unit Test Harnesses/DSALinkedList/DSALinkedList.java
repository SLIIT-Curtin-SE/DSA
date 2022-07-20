/*A version of this code has previously been submitted by me for Practical 4 DSA*/

import java.util.*;
import java.lang.UnsupportedOperationException;

public class DSALinkedList implements Iterable
{
    private DSAListNode head;
    private DSAListNode tail;

    public DSALinkedList()
    {
        head = null;
        tail = null;
    }

    public void remove(Object inNode)
    {
        DSAListNode temp = head;
        DSAListNode prev = null;

        if (temp != null && temp.getValue() == inNode)
        {
            head = temp.getNext();
        }
        else
        {
            while (temp != null && temp.getValue() != inNode)
            {
                prev = temp;
                temp = temp.getNext();

            }

            if (temp.getValue() == inNode)
            {
                prev.setNext(temp.getNext());
            }
        }
    }

    public void insertFirst(Object newValue)
    {
        DSAListNode newNode = new DSAListNode(newValue);
        if (isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            newNode.setNext(head);
            head = newNode;
        }
    }

    public void insertLast(Object newValue)
    {
        DSAListNode currNode;
        DSAListNode newNode = new DSAListNode(newValue);
        if (isEmpty())
        {
            head = newNode;
            tail = newNode;
        }
        else
        {
            currNode = head;
            while (currNode.getNext() != null)
            {
                currNode = currNode.getNext();
            }
            tail = newNode;
            currNode.setNext(newNode);
            newNode.setPrev(currNode);
        }
    }

    public boolean isEmpty()
    {
        boolean returnVal;
        if (head == null)
        {
            returnVal = true;
        }
        else
        {
            returnVal = false;
        }
        return returnVal;
    }


    public Iterator iterator()
    {
        return new MyLinkedListIterator(this);
    }

    private class MyLinkedListIterator implements Iterator
    {
        private DSAListNode iterNext;

        public  MyLinkedListIterator(DSALinkedList theList)
        {
            iterNext = theList.head;
        }

        public boolean hasNext()
        {
            return (iterNext != null);
        }

        public Object next()
        {
            Object value;
            if (iterNext == null)
            {
                value = null;
            }
            else
            {
                value = iterNext.getValue();
                iterNext = iterNext.getNext();
            }
            return value;
        }

        public void remove()
        {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public Integer getLength()
    {
        Integer count = 0;
        DSAListNode currNode = head;

        if (currNode != null)
        {
            count = count + 1;
        
            while (currNode.getNext() != null)
            {
                count = count + 1;
                currNode = currNode.getNext();
            }
        }
        return count;
    }
}