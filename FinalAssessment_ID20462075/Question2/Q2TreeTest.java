/**
 * DSA Final Assessment Question 1 - Q2TreeTest.java
 *
 * Name : 
 * ID   :
 *
 **/
public class Q2TreeTest
{
	public static void main(String args[])
	{
		System.out.println("\n+++++ Question 2: Testing Trees +++++\n");
        Q2BinarySearchTree t = new Q2BinarySearchTree();
		
		System.out.println("Testing if empty");
		System.out.println(t.isEmpty());

		System.out.println("Testing if empty after insertion (Inserting value 5)");
		t.insert(5);
		System.out.println(t.isEmpty());
		System.out.println("Testing if the inserted value is defaulted to black");
		t.displayRoot();

		System.out.println("Entering values 8, 19, 1, 13, 46");
		t.insert(8);
		t.insert(19);
		t.insert(1);
		t.insert(13);
		t.insert(46);

		System.out.println("Displaying the tree! (All colors should be black)");
		t.display();

		System.out.println("Coloring the tree!");
		t.colourTree();
		t.display();
		
		System.out.println("Visual depiction of tree located in dir: Q2ColoredTree.png");	
		System.out.println("\n+++++ Tests Complete +++++\n");

	}
	
}
