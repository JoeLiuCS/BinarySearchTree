package binarySearchTree;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class binarySearchTree<T extends Comparable<T>> {

	private TreeNode<T> root;

	/**
	 * In-order Iterator
	 * @author shuoqiaoliu
	 */
 	private class InorderIterator implements Iterator<T>{
 		private StackInterface<TreeNode<T>> nodeStack;
 		private TreeNode<T> currentNode;
 		
 		public InorderIterator(){
 			nodeStack = new LinkStack<>();
 			currentNode = root;
 		}
 		
 		public boolean hasNext(){
 			return !nodeStack.isEmpty()||(currentNode!=null);
 		}
 		
 		public T next(){
 			TreeNode<T> nextNode = null;
 			while(currentNode != null){
 				nodeStack.push(currentNode);
 				currentNode = currentNode.getLeftChild();
 			}
 			if(!nodeStack.isEmpty()){
 				nextNode = nodeStack.pop();
 				assert nextNode !=null;
 				currentNode = nextNode.getRightChild();
 			}
 			else{
 				throw new NoSuchElementException();
 			}
 			return nextNode.getData();
 		}
 		
 		public void remove(){
 			throw new UnsupportedOperationException();
 		}
 	}
 	
 	
 	public binarySearchTree(){
 		root = null;
 	}
 	
 	public binarySearchTree(T rootData){
 		root = new TreeNode<>(rootData);
 	}
 	
 	public binarySearchTree(T rootData,binarySearchTree<T> leftTree,binarySearchTree<T> rightTree){
 		privateSetTree(rootData,leftTree,rightTree);
 	}
 	
 	public Iterator<T> getInorderIterator(){
 		return new InorderIterator();
 	}
 	
 	/**
 	 * convert two tree to one
 	 * @param rootData
 	 * @param leftTree
 	 * @param rightTree
 	 */
 	private void privateSetTree(T rootData,binarySearchTree<T> leftTree,binarySearchTree<T> rightTree){
 		root = new TreeNode<>(rootData);
 		if((leftTree!=null)&&(!leftTree.isEmpty())){
 			root.setLeftChild(leftTree.root.copy());
 		}
 		if((rightTree!=null)&&(!rightTree.isEmpty())){
 			root.setRightChild(rightTree.root.copy());
 		}
 	}
 	
 	public void setTree(T rootData){
 		root = new TreeNode<>(rootData);
 	}
 	
 	public void setTree(T rootData,binarySearchTree<T> leftTree,binarySearchTree<T> rightTree){
 		privateSetTree(rootData,leftTree,rightTree);
 	}
 	
 	/**
 	 * Get the data from the root
 	 * @return data
 	 */
 	public T getRootData(){
 		if(isEmpty()){
 			throw new EmptyTreeException();
 		}
 		else{
 			return root.getData();
 		}
 	}
 	
 	public boolean isEmpty(){
 		return root == null;
 	}
 	
 	public void clear(){
 		root = null;
 	}
 	
 	protected void setRootData(T rootData){
 		root.setData(rootData);
 	}
 	
 	protected void setRootNode(TreeNode<T> rootNode){
 		root  = rootNode;
 	}
 	
 	protected TreeNode<T> getRootNode(){
 		return root;
 	}
 	public int getHeight(){
 		return root.getHeight();
 	}
 	public int getNumberOfNodes(){
 		return root.getNumberOfNode();
 	}
 	
 	public boolean contains(T entry){
 		return findEntry(root,entry)!=null;
 	}

 	/**
 	 * given the root , and search the item from this root
 	 * @param rootNode
 	 * @param entry
 	 * @return if find, return this item
 	 */
 	private T findEntry(TreeNode<T> rootNode,T entry){
 		T result = null;
 		if(rootNode != null){
 			T rootEntry = rootNode.getData();
 			if(rootNode!=null){
 	 			if(entry.equals(rootEntry)){
 	 				result = rootEntry;
 	 			}
 	 			else if(compare(entry,rootEntry)<0){
 	 				result = findEntry(rootNode.getLeftChild(),entry);
 	 			}
 	 			else{
 	 				result = findEntry(rootNode.getRightChild(),entry);
 	 			}
 	 		}
 		}
 		return result;
 	}

	public int compare(T item,T compareItem) {
		return item.compareTo(compareItem);
	}
 	/**
 	 * add a item to the tree
 	 * @param newEntry
 	 * @return
 	 */
	public T add(T newEntry){
		T result =null;
		if(this.isEmpty()){
			setRootNode(new TreeNode<>(newEntry));
			result = newEntry;
		}
		else{
			result = addEntry(getRootNode(),newEntry);
		}
		return result;
	}
	
	/**
	 * add item to the tree
	 * @param rootNode
	 * @param newEntry
	 * @return
	 */
	private T addEntry(TreeNode<T> rootNode, T newEntry){
		assert rootNode !=null;
		T result = null;

		int comparison = newEntry.compareTo(rootNode.getData());
		if(comparison<=0){                        //smaller and equal go left
			if(rootNode.hasLeftChild())
				result= addEntry(rootNode.getLeftChild(),newEntry);
			else
				rootNode.setLeftChild(new TreeNode<>(newEntry));
		}
		else{
			assert comparison>0;                    //bigger go right
			if(rootNode.hasRightChild())
				result = addEntry(rootNode.getRightChild(),newEntry);
			else
				rootNode.setRightChild(new TreeNode<>(newEntry));
		}
		return result;
	}
	
	public void inorderPrint(){
		root.inOrderTraverse();
	}
	
	public void postPrint(){
		root.postOrderTraverse();
	}
	
	public void preoderPrint(){
		root.preOrderTraverse();
	}
	
	/**
	 * find successor using in-order search
	 * @param entry
	 * @return
	 */
	public T findSuccessor(T entry){
		
		Iterator<T> find = this.getInorderIterator();
		while(find.hasNext()){
			T item = find.next();
			if(item.compareTo(entry)==0)
				break;
		}
		if(find.hasNext()){
			return find.next();
		}
		else
			throw new NoSuchElementException("Does not have next elements!!!!!");
	}
	
	/**
	 * find Presuccessor using in-order search
	 * @param entry
	 * @return data
	 */
	public T findPresuccessor(T entry){
		
		Iterator<T> firstWalker = this.getInorderIterator();
		Iterator<T> secondWalker = this.getInorderIterator();
		T result=null;
		T firstItemCheck= firstWalker.next();
		
		if(firstItemCheck.compareTo(entry)==0){
			throw new NoSuchElementException("Does not have previous elements!!!!!");
		}
		while(firstWalker.hasNext()){
			T itemFromFirstWalker = firstWalker.next();
			T itemFromSecondWlaker = secondWalker.next();
			if(itemFromFirstWalker.compareTo(entry)==0){
				result = itemFromSecondWlaker;
				break;
			}
		}
		return result;
	}
	
	public void remove(T entry){
		removeThis(root,entry);
	}
	/**
	 * find the item first , then replace it
	 * @param rootNode
	 * @param entry
	 */
	private void removeThis(TreeNode<T> rootNode,T entry){
		if(rootNode != null){
			T rootData = rootNode.getData();
			int comparation = entry.compareTo(rootData);
			if(comparation ==0){                    //find the item
				removeEntry(rootNode);
			}
			else if(comparation > 0){                //bigger go right
				removeThis(rootNode.getRightChild(),entry);
			}
			else{
				assert comparation<0;               //smaller go left
				removeThis(rootNode.getLeftChild(),entry);
			}
		}
	}
	
	/**
	 * remove the root
	 * @param rootNode
	 */
	private void removeEntry(TreeNode<T> rootNode){
		if(rootNode.hasLeftChild() && rootNode.hasRightChild()){   //two child 
			DeleteRootUseRight(rootNode);
		}
		else if(rootNode.hasLeftChild()){                       //has left child
			TreeNode<T> subParents = findMyParents(rootNode);
			if(subParents == null){                          //if it is top of the root
				DeleteRootUseLeft(rootNode);
			}
			else{
				rootNode = rootNode.getLeftChild();
				subParents.setLeftChild(rootNode);
			}
		}
		else if (rootNode.hasRightChild()){               //has right child
			TreeNode<T> subParents = findMyParents(rootNode);
			if(subParents == null){                        //if it is top of the root
				DeleteRootUseRight(rootNode);
			}
			else{
				rootNode = rootNode.getRightChild();
				subParents.setRightChild(rootNode);
			}
		}
		else{                                     //is leaf
			TreeNode<T> subParents = findMyParents(rootNode);
			if(subParents.getLeftChild()==rootNode){
				subParents.setLeftChild(null);
			}
			else{
				subParents.setRightChild(null);
			}
		}
	}
	
	/**
	 * find the largest item from the left child, then replace it to the root
	 * @param rootNode
	 */
	private void DeleteRootUseLeft(TreeNode<T> rootNode){
		TreeNode<T> LeftSubTree = rootNode.getLeftChild();
		TreeNode<T> largestNode = findLargest(LeftSubTree);
		rootNode.setData(largestNode.getData());
		rootNode.setLeftChild(removelargest(LeftSubTree));
	}
	
	/**
	 * find the smallest item from the left child, then replace it to the root
	 * @param rootNode
	 */
	private void DeleteRootUseRight(TreeNode<T> rootNode){
		TreeNode<T> rightSubTree = rootNode.getRightChild();
		TreeNode<T> smallestNode = findSmallest(rightSubTree);
		rootNode.setData(smallestNode.getData());
		rootNode.setRightChild(removeSmallest(rightSubTree));
	}
	
	/**
	 * find smallest item from give root
	 * @param rootNode
	 * @return
	 */
	private TreeNode<T> findSmallest(TreeNode<T> rootNode){
		if(rootNode.hasLeftChild()){
			rootNode = findSmallest(rootNode.getLeftChild());
		}
		return rootNode;
	}
	
	/**
	 * find largest item from give root
	 * @param rootNode
	 * @return
	 */
	private TreeNode<T> findLargest(TreeNode<T> rootNode){
		if(rootNode.hasRightChild()){
			rootNode = findLargest(rootNode.getRightChild());
		}
		return rootNode;
	}
	
	/**
	 * remove the largest from given root
	 * @param rootNode
	 * @return the root after remove
	 */
	private TreeNode<T> removelargest(TreeNode<T> rootNode){
		if(rootNode.hasRightChild()){
			TreeNode<T> RightChild = rootNode.getRightChild();
			RightChild = removelargest(RightChild);
			rootNode.setRightChild(RightChild);
		}
		else{
			rootNode = rootNode.getLeftChild();
		}
		return rootNode;
	}
	/**
	 * remove the smallest from given root
	 * @param rootNode
	 * @return the root after remove
	 */
	private TreeNode<T> removeSmallest(TreeNode<T> rootNode){
		if(rootNode.hasLeftChild()){
			TreeNode<T> leftChild = rootNode.getLeftChild();
			leftChild = removeSmallest(leftChild);
			rootNode.setLeftChild(leftChild);
		}
		else{
			rootNode = rootNode.getRightChild();
		}
		return rootNode;
	}
	
	/**
	 * find the parents from given root
	 * @param rootNode
	 * @return if find return the parents node
	 */
	public TreeNode<T> findMyParents(TreeNode<T> rootNode){
		T data = rootNode.getData();
		TreeNode<T> cursor = root;
		TreeNode<T> parent = null;
		while(cursor != null && cursor.getData() != data) {
			parent = cursor;
			if (data.compareTo(cursor.getData())<0) {
				cursor = cursor.getLeftChild();
			} else {
				cursor = cursor.getRightChild();
			}
		}
		return parent;
	}
}
