package binarySearchTree;

public class TreeNode<T> {
	private T data;
	private TreeNode<T> leftChild;
	private TreeNode<T> rightChild;
	
	public TreeNode(){
	    this(null);
	}
	
	public TreeNode (T data) {
		this.data = data;
	}

	public TreeNode (T data, TreeNode<T> leftChild, TreeNode<T> rightChild) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}
	
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public TreeNode<T> getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(TreeNode<T> leftChild) {
		this.leftChild = leftChild;
	}

	public TreeNode<T> getRightChild() {
		return rightChild;
	}

	public void setRightChild(TreeNode<T> rightChild) {
		this.rightChild = rightChild;
	}

	public boolean isLeaf() {
		return this.leftChild == null && this.rightChild == null;
	}
	
	public boolean hasLeftChild(){
		return leftChild != null;
	}

	public boolean hasRightChild(){
		return rightChild != null;
	}
	
	
	public void preOrderTraverse() {
		preorderTraverse(this);
	}

	private void preorderTraverse(TreeNode<T> root) {
		if (root != null) {
			System.out.print(root.getData() + " ");
			preorderTraverse(root.getLeftChild());
			preorderTraverse(root.getRightChild());
		}
	}
	
	public void inOrderTraverse() {
		inorderTraverse(this);
	}

	private void inorderTraverse(TreeNode<T> root) {
		if (root != null) {
			inorderTraverse(root.getLeftChild());
			System.out.print(root.getData() + " " );
			inorderTraverse(root.getRightChild());
		}
	}
	
	public void postOrderTraverse() {
		postorderTraverse(this);
	}

	private void postorderTraverse(TreeNode<T> root) {
		if (root != null) {
			postorderTraverse(root.getLeftChild());
			postorderTraverse(root.getRightChild());
			System.out.print(root.getData() + " ");
		}
	}
	
	/**
	 * Copy everything from the root
	 * @return root
	 */
	public TreeNode<T> copy(){
 		TreeNode<T> newRoot = new TreeNode<T> (data);
 		if(leftChild != null){
 			newRoot.setLeftChild(leftChild.copy());
 		}
 		if(rightChild !=null){
 			newRoot.setRightChild(rightChild.copy());
 		}
 		return newRoot;
 	}
	
	/**
	 * Give Height of the root
	 * @param node
	 * @return HEIGHT
	 */
	public int getHeight(){
 		return getHeight(this);
 	}
	
	/**
	 * Give Height of the root
	 * @param node
	 * @return HEIGHT
	 */
 	private int getHeight(TreeNode<T> node){
 		int height = 0;
 		if(node !=null){
 			height = 1 + Math.max(getHeight(node.getLeftChild()), getHeight(node.getRightChild()));
 		}
 		return height;
 	}
 	
 	/** 
 	 * Give the total of the nodes
 	 * @return number of the nodes
 	 */
 	public int getNumberOfNode(){
 		int left = 0;
 		int right = 0;
 		if(leftChild != null){
 			left = leftChild.getNumberOfNode();
 		}
 		if(rightChild != null){
 			right = rightChild.getNumberOfNode();
 		}
 		return 1 + left + right ;
 	}

}
