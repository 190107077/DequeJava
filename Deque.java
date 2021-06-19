import java.util.*;
public class Deque<Item> implements java.lang.Iterable<Item> {

	private Node<Item> head;
	private Node<Item> tail;
	private int size;
	
	// construct an empty deque
	public Deque(){
		this.size = 0;
	}
	
	// is the deque empty?
	public boolean isEmpty() {
		if(size == 0) {
			return true;
		} 

		return false;
	}
	
	// return the number of items on the deque
	public int size() {
		return this.size;
	}
	// add the item to the front
	public void addFirst(Item item) throws IllegalArgumentException{
		if (item == null) {
        throw new IllegalArgumentException("argument in this method can not be empty");
    }
		Node<Item> newNode = new Node<>(item);

		if(tail == null) {
			head = newNode;
			tail = newNode;		
		} else {
			head.prev = newNode;
			newNode.next = head;
			head = newNode;
		}
		size++;
	}
	
	// add the item to the back
	public void addLast(Item item) throws IllegalArgumentException{
		if (item == null) {
        throw new IllegalArgumentException("argument in this method can not be empty");
    }
		Node<Item> newNode = new Node<>(item);

		if (tail == null) {
			head = newNode;
			tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = tail.next;
		}

		size++;

	}
	 //remove and return the item from the front
	public Item removeFirst() {
		if (size == 0) return null;
		
		Node<Item> node = head;
		
		if(size == 1) {
			head = null;
			tail = null;
		} else {
			head = head.next;
			head.prev = null;
		}
		size--;

		return node.element;
	}
	// remove and return the item from the back
	public Item removeLast() {
		
		if (size == 0) return null;

		Node<Item> current = head;
		
		if (size == 1) {
			Node<Item> temp = head;
			head = null;
			tail = null;
			size--;
			return temp.element;
		} else {

			Node<Item> newTail = tail.prev;
			tail = newTail;
			tail.next = null;
			size--;
			return tail.element;
		}

	}
	// return an iterator over items in order from front to back

	@Override
	public Iterator<Item> iterator(){
		return new dequeIterator();
	}

	private class dequeIterator implements java.util.Iterator<Item> {
		private Node<Item> current = head; // Current index
		
		@Override
		public boolean hasNext() {
			return current != null;
		}
		
		@Override
		public Item next() throws NoSuchElementException{
			if(current.next == null ) throw new java.util.NoSuchElementException("*No more items to return*");
			Item temp = current.element;
			current = current.next;
			return temp;
		}
		
		@Override
		public void remove() {
		
		}
	}

	@Override
	public String toString() throws java.util.NoSuchElementException{
		String result = "[";

		Node<Item> current = head;

		if(current == null) throw new java.util.NoSuchElementException("Deque is empty");

		while(current.next != null) {
			result += current.element + ", ";
			current = current.next;
		}	

		result += current.element + "]";

		return result;
	} 
	// unit testing (required)
	public static void main(String[] args){
		
		Deque<Integer> list = new Deque<>();

		try{
		list.addFirst(2);
		System.out.println(list);
		list.addFirst(3);
		System.out.println(list);
		list.addLast(4);
		System.out.println(list);
		list.addLast(5);
		System.out.println(list);
		list.addLast(6);
		System.out.println(list);
		list.removeFirst();
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		list.removeLast();
		System.out.println(list);
		list.addFirst(8);
		System.out.println(list);
		

		
		for(Integer e: list){
			System.out.print(e + " ");
		}
		

	}catch(NoSuchElementException e){
		System.out.println(e.getMessage());
	} catch(IllegalArgumentException ex){
		System.out.println(ex.getMessage());
	}
		
	}

	private class Node<Item> {
		Item element;
		Node<Item> prev;
		Node<Item> next;

		public Node(Item element) {
			this.element = element;
		}
	}
}