package binarySearchTree;
import java.util.EmptyStackException;

public class LinkStack<T> implements StackInterface<T> {
	
	private Node<T> topNode;

	@SuppressWarnings("hiding")
	private class Node<T>{
		private T data;
		private Node<T> next;
		
		private Node (T dataPortion){
			this(dataPortion,null);
		}
		private Node(T dataPortion, Node<T> nextNode){
			data = dataPortion;
			next = nextNode;
		}
		
		public T getData(){
			return data;
		}
		
		@SuppressWarnings("unused")
		public void setData(T newData){
			data = newData;
		}
		
		public Node<T> getNextNode(){
			return next;
		}
		
		@SuppressWarnings("unused")
		public void setNextNode(Node<T> nextNode){
			next = nextNode;
		}
	}
	
	public LinkStack(){
		topNode = null;
	}

	@Override
	public void push(T newEntry) {
		// TODO Auto-generated method stub
		Node<T> newNode = new Node<T>(newEntry,topNode);
		topNode = newNode;
	}

	@Override
	public T pop() {
		T top = peek();
		assert topNode!=null;
		topNode = topNode.getNextNode();
		return top;
	}

	@Override
	public T peek() {
		if(isEmpty()){
			throw new EmptyStackException();
		}
		else{
			return topNode.getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return topNode == null;
	}

	@Override
	public void clear() { 
		topNode = null;
	}
}
