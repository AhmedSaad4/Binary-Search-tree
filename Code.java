import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Code {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		BST tree=new BST();
		try {
		 File file = new File("D:\\data.txt"); 
			    Scanner ss = new Scanner(file); 
			  
			    while (ss.hasNextLine()) { 
			    	tree.root=tree.insert(tree.root , ss.nextLine()); 
			  } 
			    ss.close();
		}
		catch(FileNotFoundException e) {
			System.out.println("Error");
		      e.printStackTrace();
		}

		
		while (true) {
			System.out.println();
			System.out.println("============================================================");
			System.out.println("1) Insert a word");
			System.out.println("2) Delete a word");
			System.out.println("3) Search for a word");
			System.out.println("4) Print the words in in-order traversal");
			System.out.println("5) Print the words in post-order traversal");
			System.out.println("6) Print the words in pre-order traversal");
			System.out.println("7) Print the words in BFS traversal");
			System.out.println("8) Print the tree hight");
			System.out.println("9) Print the number of words in the tree");
			System.out.println("0) Exit the program");
			System.out.println();
			System.out.print("Enter the number of the operation you want to do: ");
			int x=sc.nextInt();
			
			System.out.println();
			
			if (x==0) {
				System.out.println("The progeam has been terminated.");
				break;
			}
			else if(x==1) {
				System.out.print("Enter the word to be inserted: ");
				String s=sc.next();
				if(!tree.search(tree.root , s)) {
				tree.root=tree.insert(tree.root , s);
				System.out.println("the word has successfully been inserted into the tree!");
				}
				else {
					System.out.println("The word already exists in the tree.");
				}
			}
			else if(x==2) {
				System.out.print("Enter the word to be deleted: ");
				String s=sc.next();
				if(tree.search(tree.root, s)) {
				tree.root=tree.delete(tree.root , s);
				tree.counter--;
				System.out.println("the word has successfully been deleted from the tree.");
				}
				else {
					System.out.println("The word doesn't exist in the tree.");
				}
			}
			else if(x==3) {
				System.out.print("Enter the word to be searched for: ");
				String s=sc.next();
				if(tree.search(tree.root,s)) {
					System.out.println("The word "+s+" was found in the tree.");
				}
				else {
					System.out.println("The word was not found.");
					tree.printSuggetions(s);
				}
			}
			else if(x==4) {
				tree.inorderTraversal(tree.root);
				System.out.println();
			}
			else if(x==5) {
				tree.postorderTraversal(tree.root);
				System.out.println();
			}
			else if(x==6) {
				tree.preorderTraversal(tree.root);
				System.out.println();
			}
			else if(x==7) {
				tree.BFSTraversal();
				System.out.println();
			}
			else if(x==8) {
				System.out.println("The tree hight is: "+tree.treeHeight(tree.root));
			}
			else if(x==9) {
				System.out.println("The tree contains "+tree.counter+" words in it.");
			}
			else {
				System.out.println("Wrong input, try again.");
			}

		}
		sc.close();
		
	}
}

class Node{
	String data;
	Node left;
	Node right;
	
	public Node(String data) {
		this.data=data;
		left=null;
		right=null;
	}
}

class BST{
	 Node root; 
	 int counter=0;
	 
	 public Node insert(Node node , String data) {
		 if(node == null) {
			 counter++;
			 return node=new Node(data);
		 }
		 if(data.compareToIgnoreCase(node.data)<0) {
			 node.left=insert(node.left,data);
		 }
		 else if (data.compareToIgnoreCase(node.data)>0) {
			 node.right=insert(node.right,data);
		 }
		 return node;
	 }
	 
	 public boolean search(Node node , String data) {
		 if (node == null) {
			 return false;
		 }
		 else if (data.compareToIgnoreCase(node.data)==0) {
			 return true;
		 }
		 else if(data.compareToIgnoreCase(node.data)<0) {
			 return search(node.left , data);
		 }
		 else if (data.compareToIgnoreCase(node.data)>0){
			 return search(node.right , data);
		 }
		 return false;
		 
	 }
	 
	 public Node delete(Node node , String data) {
		 if (node == null) {
			 return null;
		 }
		 else if (data.compareToIgnoreCase(node.data)<0) {
			 node.left=delete(node.left , data);
		 }
		 else if (data.compareToIgnoreCase(node.data)>0) {
			 node.right=delete(node.right , data);
		 }
		 else  {
			 if (node.left==null && node.right==null) {
				 return null;
			 }
			 else if (node.left!=null && node.right!=null) {
				 String successor=getSuccessor(node);
				 node.data=successor;
				 node.right=delete(node.right , successor);
			 }
			 else {
				 if (node.left!=null) {
					 return node.left;
				 }
				 return node.right;
			 }
		 }
		 return node;
	 }
	 
	public void preorderTraversal(Node node) {
		if(node==null) {
			return;
		}
		System.out.print(node.data + " ");
		preorderTraversal(node.left);
		preorderTraversal(node.right);
	}
		
	public void inorderTraversal(Node node) {
		if(node==null) {
			return;
		}
		preorderTraversal(node.left);
		System.out.print(node.data + " ");
		preorderTraversal(node.right);
	}
		
	public void postorderTraversal(Node node) {
		if(node==null) {
			return;
		}
		preorderTraversal(node.left);
		preorderTraversal(node.right);
		System.out.print(node.data + " ");
	}
	 
	 public void BFSTraversal() {
		 Queue<Node> q=new LinkedList<Node>();
		 q.add(root);
		 
		 while (!q.isEmpty()) {
			 Node temp=q.remove();
			 System.out.print(temp.data + " ");
			 if(temp.left!=null) {
				 q.add(temp.left);
			 }
			 if(temp.right!=null) {
				 q.add(temp.right);
			 }	 
		 }
	 }
	 
	 public String getSuccessor(Node node) {
		 node=node.right;
		 if(node==null) {
			 return "";
		 }
		 while (node.left!=null) {
			 node = node.left;
		 }
		 return node.data; 
	 }
	 
	 public String getPredccessor(Node node) {
		 node=node.left;
		 if(node==null) {
			 return "";
		 }
		 while (node.right!=null) {
			 node = node.right;
		 }
		 return node.data; 
	 }
	 
	 public int treeHeight(Node node) {
		  if (node == null) return 0;
		  return 1 + Math.max(treeHeight(node.left), treeHeight(node.right));
		}
	 
	 public Node Suggetions(String data) {
		 Node temp=root;
		 Node temp1=root;
		 Node temp2=root;
		 while (temp != null) {
			 if (data.compareToIgnoreCase(temp.data)<0) {
				 	temp1=temp;
				 	temp=temp.left;
				 	if(temp.right==null && temp.left==null) {
				 		return temp1;
				 	}
			 }
		 
			 if (data.compareToIgnoreCase(temp.data)>0) {
				 	temp2=temp;
				 	temp=temp.right;
				 	if(temp.right==null && temp.left==null) {
				 		return temp2;
				 	}
			 }
		 }
		return temp;
		 

	 }
	 
	 public void printSuggetions(String data) {
			 Node x=Suggetions(data);
			 System.out.println("do you mean: "+x.data+" "+getSuccessor(x)+" "+getPredccessor(x)+" ?");
		 
	 }
	
}







