package assignment4;

public class BST {
	class Node
	{
		int data;
		Node right;
		Node left;
	}
	private Node root;
	
	public BST()
	{
		root = new Node();
		root = null;
	}
	
	public void add(int a)
	{
		Node add = new Node();
		add.data = a;
		add.right = null;
		add.left = null;
		if(root == null)
		{
			root = add;
			return;
		}
		addRecursive(root, add);
	}
	
	private void addRecursive(Node root, Node add)
	{
		if(root.data > add.data)
		{
			if(root.left == null)
			{
				root.left = add;
				return;
			}
			addRecursive(root.left, add);
		}
		else if(root.data < add.data)
		{
			if(root.right == null)
			{
				root.right = add;
				return;
			}
			addRecursive(root.right, add);
		}
		
	}
	
	public void inOrder()
	{
		inOrderRecursive(root);
	}
	
	private void inOrderRecursive(Node root)
	{
		if(root == null)
			return;
		
		inOrderRecursive(root.left);
		System.out.print(root.data + " ");
		inOrderRecursive(root.right);
	}
	
	public void preOrder()
	{
		preOrderRecursive(root);
	}
	
	private void preOrderRecursive(Node root)
	{
		if(root == null)
			return;
		System.out.print(root.data + " ");
		preOrderRecursive(root.left);
		preOrderRecursive(root.right);
	}
	
	public void postOrder()
	{
		postOrderRecursive(root);
	}
	
	private void postOrderRecursive(Node root)
	{
		if(root == null)
			return;
		postOrderRecursive(root.left);
		postOrderRecursive(root.right);
		System.out.print(root.data + " ");
	}
	
	public boolean search(int a)
	{
		return searchRecursive(root, a);
	}
	
	private boolean searchRecursive(Node root, int a)
	{
		if(root == null)
			return false;
		else if(root.data == a)
			return true;
		else if(root.data > a)
			return searchRecursive(root.left, a);
		else if(root.data < a)
			return searchRecursive(root.right, a);
		return false;
	}
	
	public boolean delete(int a)
	{
		if(root == null)
			return false;
		else if(!searchRecursive(root, a))
		{
			return false;
		}
		Node parent = null;
		Node curr = root;
		while(curr != null)
		{
			// searching for the element to delete
			if(curr.data > a)
			{
				parent = curr;
				curr = curr.left;
				continue;
			}
			else if(curr.data < a)
			{
				parent = curr;
				curr = curr.right;
				continue;
			}
			else // curr.data == a
			{
				if(curr.left == null && curr.right == null) // leaf node
				{
					if(parent.left.data == curr.data)
					{
						parent.left = null;
						return true;
					}
					else if(parent.right.data == curr.data)
					{
						parent.right = null;
						return true;
					}
				}
				else if(curr.left != null && curr.right == null) // left child
				{
					if(parent.left.data == curr.data)
					{
						parent.left = curr.left;
						return true;
					}
					else if(parent.right.data == curr.data)
					{
						parent.right = curr.left;
						return true;
					}
				}
				else if(curr.left == null && curr.right != null) // right child
				{
					if(parent.left.data == curr.data)
					{
						parent.left = curr.right;
						return true;
					}
					else if(parent.right.data == curr.data)
					{
						parent.right = curr.right;
						return true;
					}
				}
				else // curr has two children
				{
					Node curr2 = curr.left;
					Node par2 = parent;
					while(curr2 != null)
					{
						if(curr2.right == null)
							break;
						par2 = curr2;
						curr2 = curr2.right;
					}
					curr = curr2;
					if(curr2.left != null && curr2.right == null) // left child
					{
						par2.right = curr2.left;
						return true;
					}
					else if(curr2.left == null && curr2.right != null) // right child
					{
						par2.right = curr2.right;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public int findMax()
	{
		Node temp = root;
		if(temp == null)
			return -1;
		else
		{
			while(temp != null)
			{
				if(temp.right == null)
					return temp.data;
				temp = temp.right;
			}
		}
		return -1;
	}
	
	public int findMin()
	{
		Node temp = root;
		if(temp == null)
			return -1;
		else
		{
			while(temp != null)
			{
				if(temp.left == null)
					return temp.data;
				temp = temp.left;
			}
		}
		return -1;
	}
	
	public static void main(String[] args)
	{
		BST myTree = new BST();
		myTree.add(50);
		myTree.add(30);
		myTree.add(25);
		myTree.add(75);
		myTree.add(82);
		myTree.add(28);
		myTree.add(63);
		myTree.add(70);
		myTree.inOrder();
		System.out.println();
		myTree.preOrder();
		System.out.println();
		myTree.postOrder();
		System.out.println();
		if(myTree.search(40))
			System.out.println("Value found!");
		else
			System.out.println("Value not found.");
		if(myTree.search(50))
			System.out.println("Value found!");
		else
			System.out.println("Value not found.");
		System.out.println("The smallest number is " + myTree.findMin());
		System.out.println("The largest number is " + myTree.findMax());
		if(myTree.delete(30))
			System.out.println("Deleted!");
		else
			System.out.println("Couldn't find the data.");
		myTree.inOrder();
	}
}
