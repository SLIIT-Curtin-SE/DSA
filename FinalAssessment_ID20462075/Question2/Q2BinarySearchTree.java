/**
 * DSA Final Assessment Question 2 - Q2BinarySearchTree.java
 *
 * Name : 
 * ID   :
 *
 **/

public class Q2BinarySearchTree {   
	// Inner class Q2TreeNode
	private class Q2TreeNode {
		public int value;
		public String color;
		public Q2TreeNode left;
		public Q2TreeNode right;
		
		public Q2TreeNode(int inVal)
		{
			color = "black";
			value = inVal;
			left = null;
			right = null;
		}
		// Getters for all the attributes of the Q2TreeNode
		public String getColor()
		{
			return color;
		}

		public int getValue()
		{
			return value;
		}

		public Q2TreeNode getLeft()
		{
			return left;
		}

		public Q2TreeNode getRight()
		{
			return right;
		}
		//Since we are only setting the color after, we create one set method.
		public void setColour(String inColour)
		{
			color = inColour;
		}

	}
	// End Inner class
	// Class Q2BinarySearchTree
	private Q2TreeNode root;

	public void displayRoot()
	{
		System.out.println("Root color: " + root.getColor());
		System.out.println("Root value: " + root.getValue());
	}
	
	public Q2BinarySearchTree()
	{
		root = null;
	}
	
	public void insert(int val)
	{
		if (isEmpty())
		{
			root = new Q2TreeNode(val);
		}
		else
		{
			root = insertRec(val, root);
		}
	}

	public Boolean isEmpty()
	{
		return root == null;
	}

	private Q2TreeNode insertRec(int inVal, Q2TreeNode cur)
	{
		if (cur == null)
		{
			cur = new Q2TreeNode(inVal);
		}
		else
		{
			if (inVal < cur.value)
			{
				cur.left = insertRec(inVal, cur.left);
			}
			else	
			{
				cur.right = insertRec(inVal, cur.right);
			}
		}
		return cur;
	}
	//simple display function for the tree which displays the tree in order.
	public void display()
	{
		System.out.println("Displaying inorder!");
		displayinOrder(root);
	}

	private void displayinOrder(Q2TreeNode currNode)
	{
		if (currNode != null)
		{
			displayinOrder(currNode.getLeft());
			System.out.println(currNode.getValue() + " : " + currNode.getColor());
			displayinOrder(currNode.getRight());
		}
	}

	public void colourTree()
	{
		int height = 1; //root is assumed to be at height 1
		recColorTree(root, height);
	}
	//recursive function that traverses the tree in order, setting the colors.
	private void recColorTree(Q2TreeNode currNode, int height)
	{
		if (currNode != null)
		{
			recColorTree(currNode.getLeft(), height + 1);
			if (currNode.getLeft() != null && currNode.getRight() != null)
			{
				currNode.setColour("black");
			}
			else if (currNode.getLeft() == null && currNode.getRight() == null)
			{
				if (height > 2)
				{
					currNode.setColour("red");
				}
				else
				{
					currNode.setColour("orange");
				}
			}
			else //if both aren't null or both are null once must be null and the other must not be.
			{
				currNode.setColour("brown");
			}
			recColorTree(currNode.getRight(), height + 1);
		}
	}
}
