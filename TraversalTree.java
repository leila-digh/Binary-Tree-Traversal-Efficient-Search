
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TraversalTree<T> extends
BinarySearchTree<TraversalTree.Node<T>, T> {
    

	public static class Node<T> extends BSTNode<Node<T>,T> {
        public Point2D position;
	
        public Node() {
            position = new Point2D();
        }
        
        public String toString() {
            return position.toString();
        }
	}

	public TraversalTree() {
		sampleNode = new Node<T>();
		c = new DefaultComparator<T>();
	}

    protected void assignLevels() {
		assignLevels(r, 0);
	}
	
	protected void assignLevels(Node<T> u, int i) {
		if (u == null) return;
		u.position.y = i;
		assignLevels(u.left, i+1);
		assignLevels(u.right, i+1);
	}

    public void inorderDraw() {
		assignLevels();
		Node<T> node = firstInNode();
		int index = 0;
		while(node!= nil){
			node.position.x = index++;
			node = nextInNode(node);
		}
	}

    public void addAll(List<T> list) {
        for (T x : list) {
            add(x);
        }
    }

    public void printPreOrderRecursive(Node<T> u) {
        if (u == nil) {
            return;
        }
        System.out.print(u.x+", ");
        printPreOrderRecursive(u.left);
        printPreOrderRecursive(u.right);
    }

    public void printPreOrderRecursive(){
        printPreOrderRecursive(r);
        System.out.println();
    }

    public void printPreOrder(){
        Node<T> u = r;
        while (u != nil){
            System.out.print(u.x+", ");
            u = nextPreNode(u);
        }
        System.out.println();
    }

    public void printPostOrderRecursive(){
        printPostOrderRecursive(r);
        System.out.println();
    }

    public void printPostOrderRecursive(Node<T> u) {
        if (u == nil) {
            return;
        }
        printPostOrderRecursive(u.left);
        printPostOrderRecursive(u.right);
        System.out.print(u.x+", ");
    }

    public void printPostOrder(){
        Node<T> u = firstPostNode();
        while (u != nil){
            System.out.print(u.x+", ");
            u = nextPostNode(u);
        }
        System.out.println();
    }

    /**
     * Returns a list of all elements in the tree that are less than or equal to x.
     * This should be done in O(h+m) time where h is the height of the tree and m 
     * is the number of elements returned. Also note, this should work even if
     * x is not in the tree.
     * @param x
     * @return
     */
    public List<T> getLE(T x) {
        List<T> list = new ArrayList<T>();
        Node<T> curr  = firstPostNode();

        // TODO: Implement this
        if(r == nil){
            return list;
        }

        while (curr != nil){
            int comp = c.compare(x, curr.x);
            if(comp >= 0){
                list.add(curr.x);
            }
            curr = nextPostNode(curr);
        }

        return list;
    }

	
	// Here is some test code you can use. The same code is found in TraversalTreeDemo.java.
    // You probably want to run it from there.
	public static void main(String[] args) {
        TraversalTree<Integer> tree = new TraversalTree<Integer>();
        List<Integer> list = Arrays.asList(1,6,2,7,3,8,4,9,5,10);   
        Collections.shuffle(list);     
        tree.addAll(list);
       
        System.out.println(tree);

        System.out.println();
        System.out.println();      

        System.out.println("Printing Pre Order Recursive");
        tree.printPreOrderRecursive();

        System.out.println("Printing Pre Order");
        tree.printPreOrder();
        // System.exit(0);
        
        System.out.println("Printing Post Order Recursive");
        tree.printPostOrderRecursive();

        System.out.println("Printing Post Order");
        tree.printPostOrder();

        List<Integer> le = tree.getLE(5);
        System.out.println(le);
    
	}
}
