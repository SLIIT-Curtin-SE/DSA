import java.util.Iterator;

class UnitTestDSALinkedList
{
    public static void main(String[] args)
    {
        Iterator itLinks;

        System.out.println("Creating a LinkedList");
        DSALinkedList testLinkedList = new DSALinkedList();
        System.out.println("Checking if the LinkedList we just created is empty (it should be)");
        System.out.println(testLinkedList.isEmpty());
        System.out.println("Inserting 'Sahas' to the List");
        testLinkedList.insertFirst("Sahas");
        itLinks = testLinkedList.iterator();
        System.out.println("Displaying the LinkedList");
        while (itLinks.hasNext())
        {
            System.out.print(itLinks.next());
            System.out.print(" -> ");
        }
        System.out.println();
        System.out.println("Inserting Senith to the front of the list!");
        testLinkedList.insertFirst("Senith");
        itLinks = testLinkedList.iterator();
        System.out.println("Displaying the LinkedList");
        while (itLinks.hasNext())
        {
            System.out.print(itLinks.next());
            System.out.print(" -> ");
        }
        System.out.println();
        System.out.println("Inserting Abinaya to the end of the list!");
        testLinkedList.insertLast("Abinaya");
        itLinks = testLinkedList.iterator();
        System.out.println("Displaying the LinkedList");
        while (itLinks.hasNext())
        {
            System.out.print(itLinks.next());
            System.out.print(" -> ");
        }
        System.out.println();
        System.out.println("Checking if the LinkedList is empty (it shouldnt be)");
        System.out.println(testLinkedList.isEmpty());
        System.out.println("Getting the Length of the LinkedList");
        System.out.println(testLinkedList.getLength());
        System.out.println("Removing Sahas");
        itLinks = testLinkedList.iterator();
        while (itLinks.hasNext())
        {
            String currVal = (String) itLinks.next();
            if (currVal.equals("Sahas"))
            {
                testLinkedList.remove(currVal);
            }
        }
        itLinks = testLinkedList.iterator();
        System.out.println("Displaying the LinkedList");
        while (itLinks.hasNext())
        {
            System.out.print(itLinks.next());
            System.out.print(" -> ");
        }
        System.out.println();
        System.out.println("Getting the Length of the LinkedList");
        System.out.println(testLinkedList.getLength());
    }
}