package leetcode.amazon;



public class BalancedBST {

	private Node root;	
	
	class Node{
		
		public int value;
		public Node leftNode;
		public Node rightNode;
		public int size;		
	}
	
	public int size(Node n){
		
		if (n == null) return 0;
		else return n.size;
	}
	
	public void createBST(int a[]){
		
		root = createBST(root, a, 0, a.length-1);
	}
	
	private Node createBST(Node node, int a[], int min, int max){
						
		if(min>max){
			return null;
		}
		
		int mid = (max + min)/2;
		//System.out.println(min+" "+mid+" "+max);
		if(node == null)node = new Node();
		node.value = a[mid];
		node.leftNode = createBST(node.leftNode, a, min, mid-1);
		node.rightNode = createBST(node.rightNode, a, mid+1, max);	
		node.size = size(node.leftNode) + size(node.rightNode) + 1;
			
		return node;
	}
	
	public void display(){
		
		int count = 0;
		display(root, count);
	}
	
	private void display(Node node, int count){
		
//		System.out.println(node.value+";"+node.size);		
		System.out.println(node.value+ " "+count);
		if(node.leftNode != null)display(node.leftNode, count+1);
		if(node.rightNode != null)display(node.rightNode, count+1);
	}
	
	public static void main(String args[]){
		
		int a[] = {3,4,5,6,7,8,9,10,12,13};
		
		try{
			BalancedBST obj = new BalancedBST();
			obj.createBST(a);
			obj.display();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
