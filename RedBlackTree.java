import java.util.*;

public class RedBlackTree {
		
		public boolean RED = true;
	    public boolean BLACK = false;
	   // private int key;
	    
	  
	public class Node{
		    
			int key;
			Node left;
			Node right;	
			Node parent;
			boolean color;
			
			//Declaring variable node with a parameter key
			public Node( int key){
				this.key = key;
				this.color = color;
				parent = null;
				left = null;
				right = null;}
			//Declaring variable node with no parameter
			public Node(){
				this.key = key;
				this.color = color;
				parent = null;
				left = null;
				right = null;}		
	}
	  	Node root;
	  	Node z;
	  	Node nil;
	  	Node y;
	  	int i=1;
	  	
	  	//Declaring a function red black tree
		public RedBlackTree() {
			
			//Assign nil as a node
			nil = new Node();
			//Assign the color nil into black
			nil.color = BLACK;
			//Assign nil to root
			root = nil;
		}
		
		private void leftRotate( Node x)
		{
			Node y = x.right;
			x.right = y.left;
			if(y.left !=nil)
				y.left.parent = x;
			y.parent = x.parent;
			if(x.parent == nil)
				root = y;
			else if (x == x.parent.left)
				x.parent.left = y;
			else
				x.parent.right = y;
			
			y.left = x;
			x.parent = y;	
		}
		
		private void rightRotate(Node x)
		{
			Node y = x.left;
			x.left = y.right;
			if(y.right !=nil)
				y.right.parent = x;
			y.parent = x.parent;
			if(x.parent == nil)
				root = y;
			else if (x == x.parent.right)
				x.parent.right = y;
			else
				x.parent.left = y;
			y.right = x;
			x.parent = y;
		}
		
		public void RBinsert(int value) {
			//Changing the value into a node called valueTree
			Node z = new Node(value);
			
			Node y = nil;
			Node x = root;
			
			while(x!=nil) {
				y = x;
				if(z.key<x.key){
					x = x.left;
				} else {
					x = x.right;
				}
			}
			
			z.parent = y;
			if (y == nil)
				root = z;
			else if (z.key < y.key)
				y.left = z;
			else
				y.right = z;
			
			z.left = nil;
			z.right = nil;
			z.color = RED;

			RBInsertFixUp(z);
					}
		
		public void RBInsertFixUp(Node z){
			//y = nil;
			//System.out.printf(" Color = " + z.parent.color + "\n");
			while(z.parent.color == RED)
			{
				if(z.parent == z.parent.parent.left){

					y = z.parent.parent.right;

					//Case 1, if y is red
					if(y.color == RED){
						z.parent.color = BLACK;
						y.color = BLACK;
						z.parent.parent.color = RED;
						z = z.parent.parent;
					}
					//Case 2, if y is black & z is a right child
					else {
						if (z == z.parent.right) {
						
							z = z.parent;
							leftRotate(z);
					}
					//Case 3, if y is black & z is a left child
						z.parent.color = BLACK;
						z.parent.parent.color = RED;
						rightRotate(z.parent.parent);
					
					}	
				} else {
					y = z.parent.parent.left;
					// Case 1, if y is red
					if(y.color == RED){
						z.parent.color = BLACK;
						y.color = BLACK;
						z.parent.parent.color = RED;
						z = z.parent.parent;
					}
					// Case 2, if y is black and z is a left child
					else {
						if (z == z.parent.left) {
					
							z = z.parent;
							rightRotate(z); 
					}
					// Case 3, if y  is black and z is a right child
					
						z.parent.color = BLACK;
						z.parent.parent.color = RED;
						leftRotate(z.parent.parent);
					}
					
				}
			}
			root.color = BLACK;
		}
		
		//Function to get a node based on the inorder BST
		public void inOrder(Node root, HashMap<Integer, String> ScoreandColor,HashMap<Integer, String> PageScoreandURL,HashMap<Integer,Integer> PageRankandPageScore) {
			if(root !=  nil) {
				String redOrBlack;
				inOrder(root.left,ScoreandColor,PageScoreandURL,PageRankandPageScore);
				
				//To output the page rank and the page score
				System.out.printf("\nPage rank = " + i++ + "\tScore Page = " + root.key);

				//TO assign for string 
				if(root.color)
					redOrBlack = "Red";
				else
					redOrBlack = "Black";
				
				//Output the color of each score page
				System.out.printf("\tColor = " + redOrBlack );
				//System.out.printf("\tURL = " + PageScoreandURL.get(root.key) );

				//Using hashmap to contain the page rank and page score
				PageRankandPageScore.put(i, root.key);

				//Using hashmap to contain the score and the color
				ScoreandColor.put(root.key, redOrBlack);
				inOrder(root.right,ScoreandColor,PageScoreandURL,PageRankandPageScore);
			}
		}
		
		//Original color function of Y
		public boolean originalColor(Node y)
		{
			boolean orgColor;
			orgColor = y.color;
			return orgColor;
		}
		
		//Function transplant for the red black tree delete
		public void transplant(Node root, Node u, Node v)
		{	
			if(u.parent == nil)
				root = v;
			else if (u == u.parent.left)
				u.parent.left = v;
			else 
				u.parent.right = v;
				v.parent = u.parent;
		}
		
		//To get the minimum number in the BST
		public Node minimum(Node x)
		{
			while(x.left != nil)
				x = x.left;
			return x;
		}
		
		public void RBdelete(Node tree, int data)
		{
			Node x;
			z = new Node(data);
			
			Node y = z;
			boolean ori = originalColor(y);
			
			if(z.left == nil) {
				x = z.right;
				transplant(tree,z,z.right);
			}
			else if(z.right == nil)
			{
				x = z.left;
				transplant(tree, z, z.left);
			}
			else {
				y = minimum(z.right);
				ori = originalColor(y);
				x = y.right;
				
				if(y.parent == z)
					x.parent = y;
				else
				{
					transplant(tree,y,y.right);
					y.right = z.right;
					y.right.parent = y;
				}
				transplant(tree,z,y);
				y.left = z.left;
				y.left.parent = y;
				y.color = z.color;		
			}
			
		if(ori==BLACK)
			RBDeleteFixup(tree,x);
		}
		
		public void RBDeleteFixup(Node tree, Node x)
		{
			Node w;
			while(x!=root && x.color == BLACK)
			{
				//for the right tree
				if(x==x.parent.left)
				{
					w = x.parent.right;
					if(w.color == RED)
					{//case 1
						w.color = BLACK;
						x.parent.color = RED;
						leftRotate(x.parent);
						w = x.parent.right;
					}
					if(w.left.color == BLACK && w.right.color ==BLACK)
					{//case 2
						w.color = RED;
						x = x.parent;
					}
					else if(w.right.color == BLACK)
					{//case 3
						w.left.color = BLACK;
						w.color = RED;
						rightRotate(w);
						w = x.parent.right;
						
					}
					//case 4
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.right.color = BLACK;
					leftRotate(x.parent);
					x = root;
				} else {
					//for the left tree
					w = x.parent.right;
					if(w.color == RED)
					{//case 1
						w.color = BLACK;
						x.parent.color = RED;
						leftRotate(x.parent);
						w = x.parent.right;
					}
					if(w.right.color == BLACK && w.left.color ==BLACK)
					{//case 2
						w.color = RED;
						x = x.parent;
					}
					else if(w.left.color == BLACK)
					{//case 3
						w.right.color = BLACK;
						w.color = RED;
						rightRotate(w);
						w = x.parent.left;
						
					}
					//case 4
					w.color = x.parent.color;
					x.parent.color = BLACK;
					w.left.color = BLACK;
					leftRotate(x.parent);
					x = root;
				}
			}
			x.color = BLACK;
		}
		
}
