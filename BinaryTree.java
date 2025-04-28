
import java.util.LinkedList;
import java.util.Queue;


public class BinaryTree<Node extends BinaryTreeNode<Node>> {
	/**
	 * Used to make a mini-factory
	 */
	public Node sampleNode;

	/**
	 * The root of this tree
	 */
	public Node r;

	/**
	 * This tree's null node
	 */
	public Node nil;

	/**
	 * Create a new instance of this class
	 *
	 * @param isampleNode
	 */
	public BinaryTree(Node nil) {
		sampleNode = nil;
		this.nil = null;
	}

	/**
	 * Create a new instance of this class
	 *
	 * @warning child must set sampleNode before anything that
	 * might make calls to newNode()
	 */
	public BinaryTree() {
	}

	/**
	 * Allocate a new node for use in this tree
	 *
	 * @return
	 */
	@SuppressWarnings({"unchecked"})
	public Node newNode() {
		try {
			Node u = (Node) sampleNode.getClass().getDeclaredConstructor().newInstance();
			u.parent = u.left = u.right = nil;
			return u;
		} catch (Exception e) {
			return null;
		}
	}

	public int depth(Node u) {
		int d = 0;
		while (u != r) {
			u = u.parent;
			d++;
		}
		return d;
	}

	/**
	 * Compute the size (number of nodes) of this tree
	 *
	 * @return the number of nodes in this tree
	 */
	public int size() {
		return size(r);
	}

	/**
	 * @return the size of the subtree rooted at u
	 */
	public int size(Node u) {
		if (u == nil)
			return 0;
		return 1 + size(u.left) + size(u.right);
	}

	public int height() {
		return height(r);
	}

	/**
	 * @return the size of the subtree rooted at u
	 */
	public int height(Node u) {
		if (u == nil)
			return -1;
		return 1 + Math.max(height(u.left), height(u.right));
	}


	/**
	 * @return
	 */
	public boolean isEmpty() {
		return r == nil;
	}

	/**
	 * Make this tree into the empty tree
	 */
	public void clear() {
		r = nil;
	}

	public void traverse(Node u) {
		if (u == nil) return;
		traverse(u.left);
		traverse(u.right);
	}

	public void traverse2() {
		Node u = r, prev = nil, next;
		while (u != nil) {
			if (prev == u.parent) {
				if (u.left != nil) next = u.left;
				else if (u.right != nil) next = u.right;
				else next = u.parent;
			} else if (prev == u.left) {
				if (u.right != nil) next = u.right;
				else next = u.parent;
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
	}


	public int size2() {
		Node u = r, prev = nil, next;
		int n = 0;
		while (u != nil) {
			if (prev == u.parent) {
				n++;
				if (u.left != nil) next = u.left;
				else if (u.right != nil) next = u.right;
				else next = u.parent;
			} else if (prev == u.left) {
				if (u.right != nil) next = u.right;
				else next = u.parent;
			} else {
				next = u.parent;
			}
			prev = u;
			u = next;
		}
		return n;
	}


	public void bfTraverse() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(r);
		while (!q.isEmpty()) {
			Node u = q.remove();
			if (u.left != nil) q.add(u.left);
			if (u.right != nil) q.add(u.right);
		}
	}

	/**
	 * Find the first node in an in-order traversal
	 *
	 * @return
	 */
	public Node firstInNode() {
		Node w = r;
		if (w == nil) return nil;
		while (w.left != nil)
			w = w.left;
		return w;
	}

	/**
	 * Find the node that follows u in an in-order traversal
	 *
	 * @param w
	 * @return
	 */
	public Node nextInNode(Node w) {
		if (w.right != nil) {
			w = w.right;
			while (w.left != nil)
				w = w.left;
		} else {
			while (w.parent != nil && w.parent.left != w)
				w = w.parent;
			w = w.parent;
		}
		return w;
	}

	/**
	 * Find the node that follows u in an pre-order traversal,
	 * where the traversal starts at the root, then goes to the left child,
	 * then the right child
	 *
	 * @param w
	 * @return
	 */
	public Node nextPreNode(Node w) {
		// TODO: Implement this method.
		// NOTE: This method does not have a matching firstPreNode method, since the
		// first node in a pre-order traversal is the root of the (sub)tree, which
		// is easy enough to find.

		if (w.left != nil) { //if left node is not null, next node in preorder traversal is the left node
			return w.left;
		} else if (w.right != nil) { //if right node is not null, next node in preorder traversal is the right node
			return w.right;
		} else { //go up until the parent node and go to the right node from there (if it's not the param)
			Node curr = w;
			while (curr.parent != null) {
				if (curr.parent.right != nil && curr.parent.right != curr) {
					return curr.parent.right;
				}
				//update curr to the parent
				curr = curr.parent;
			}
		}
		return nil;
	}

	/**
	 * Find the first node in a post-order traversal of the tree.
	 * You may consider a similar function that takes a node as an argument.
	 * This could come in handy later.
	 *
	 * @return
	 */
	public Node firstPostNode() {
		// TODO: Implement this method.
		if (r == nil) {
			return nil;
		}

		Node w = r;
		while (w.left != nil || w.right != null) {
			if (w.left != nil) {
				w = w.left;
			} else {
				w = w.right;
			}

		}
		return w;
	}

	/**
	 * Find the node that follows u in an post-order traversal,
	 * that is, left-child, right-child, root. Node w has persumably
	 * been traversed, so you should start looking at w.parent.
	 *
	 * @param w
	 * @return
	 */
	public Node nextPostNode(Node w) {
		// TODO: Implement this method.
		if (w == nil) {
			return nil;
		}

		if (w.parent == nil) {
			return nil;
		}

		//if it is the root node
		if (w == r) {
			return nil;
		}
		//if it is a right child, parent node is next
		if (w == w.parent.right || w.parent.right == nil) { //|| (w == w.parent.left && w.right == nil)){
			return w.parent;
		}

		Node curr = w.parent.right;
		while (curr.left != null || curr.right != null) {
			if (curr.left != null) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}
			return curr;
		}
		//if left child
//		if(w == w.parent.left){
//			if(w.right == nil){ //if there is no corresponding right node then
//				return w.parent;
//			}
//			else{
//				Node curr = r;
//				while (curr.left != null){
//					curr = curr.left;
//				}
//				if(curr.right != null){
//					return curr.right;
//				}else{
//					return curr;
//				}
////				return firstPostNode();
//				//if leftmost and
//			}
//		}
//
//		return nil;
//	}
	
}


//	Since the root is the last node in postorder traversal, its successor is NULL.
//		In the case of the current node being the right child, the parent node is a successor.

//		In case of current node being left child, then

//		If the right sibling is absent, the successor is a parent node
//		If the right sibling exist then either the node or its leftmost child is successor.