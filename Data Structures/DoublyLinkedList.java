import java.lang.Object;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<E> implements Iterable<E> {

    public class Node<E> implements Comparable<E> {
        Node<E> next;
        Node<E> prev;
        E data;
        
        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        
        @Override
        public int compareTo(E data) {
            return (this.data).equals(data) ? 0 : -1;
        }
    }
    
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    
    public void addFirst(E data) {
        Node<E> newNode = new Node(data, head, null);
		if(head != null ) {
		    head.prev = newNode;
		}
		head = newNode;
		if(tail == null) { 
		    tail = newNode;
		}
		size++;
		System.out.println("adding: "+data);
    }
    
    public E getFirst() {
        if(head == null)
            throw new NoSuchElementException();

        return head.data;
    }
    
    public void addLast(E data) {
       Node<E> newNode = new Node(data, null, tail);
        if(tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        if(head == null) { 
            head = newNode;
        }
        size++;
        System.out.println("adding: "+data);
    }
    
    public E getLast() {
        if(head == null) 
            throw new NoSuchElementException();

        return tail.data;
    }
    
    public void addAtIndex(int index, E data) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ". Is out of bounds for list of size: " + size);
        }
        
        if(head == null || index == 0) {
            addFirst(data);
            return;
        }
        
        if(index == size) {
            addLast(data);
            return;
        }
        
        Node<E> cur = head;
        for (int k = 0; k < index - 1; k++) {
            cur = cur.next;
        }
            
        Node<E> newNode = new Node<E>(data, cur.next, cur.next.prev);
        cur.next.prev = newNode;
        cur.next = newNode;
        size++;
    }
    
    public E getFromIndex(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();

        Node<E> tmp = head;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        if(tmp == null) throw new IndexOutOfBoundsException();

        return tmp.data;
    }
    
   
    public E removeFirst() {
        if(head == null) 
            throw new NoSuchElementException();
            
        Node<E> tmp = head;
        head = head.next;
        head.prev = null;
        size--;
        System.out.println("deleted: "+tmp.data);
        return tmp.data;
    }
    
    public E removeLast() {
        if(head == null) 
            throw new NoSuchElementException();
            
        Node<E> tmp = tail;
        tail = tail.prev;
        tail.next = null;
        size--;
        System.out.println("deleted: "+tmp.data);
        return tmp.data;
    }
    
    public E removeAtIndex(int index) {
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index: " + index + ". Is out of bounds for list of size: " + size);
        }
        
        if(isEmpty()) {
            throw new RuntimeException("List is Empty");
        }
      
        Node<E> current = head;
        Node<E> previous = null;
        for(int i = 0; i < index; i++) {
            previous = current;
            current = current.next;
        }
        
        //node to be removed is first node
        if(previous == null) {
            removeFirst();
        //node to be removed is last node
        } else if(current.next == null) {
            removeLast();
        } else {
            E data = current.data;
            previous.next = current.next;
            current.next.prev = previous;
            size--;
            return data;
        }
        return null;
    }
    
    public void removeElement(E data) {
        if(head == null)
            throw new RuntimeException("Element not found");
        if(compare(head.data, data)) {
            removeFirst();
            return;
        }

        Node<E> current  = head;
        Node<E> previous = null;

        while(current != null && !compare(current.data, data)) {
            previous = current;
            current = current.next;
        }

        if(current == null)
            throw new RuntimeException("Element not found");

        //delete cur node
        if(current.next == null) {
            removeLast();
            return;
        } else {
            previous.next = current.next;
            current.next.prev = previous;
            size--;
        }
    }
    
    public void iterateForward(){
         
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.data);
            tmp = tmp.next;
        }
    }
     
    public void iterateBackward(){
         
        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null){
            System.out.println(tmp.data);
            tmp = tmp.prev;
        }
    }
    
    public void printElements() {
        Node<E> tmp = head;
        while(tmp != null) {
            System.out.print("["+tmp.data+"] ");
            tmp = tmp.next;
        }
    }
    
    private boolean compare(E data1, E data2) {
        return ((Comparable<E>)data1).compareTo(data2) == 0;
    }
    
    public void clear() {
        head = null;
    }
    
    public boolean isEmpty() {
        return this.head == null;
    }
    
    public boolean contains(E data) {
        for(E tmp : this) {
            if(compare(data, tmp)) {
                return true;
            }
        }
        return false;
    }
    
    public int getSize() {
        return size;
    }
    
    public String toString() {
        StringBuffer result = new StringBuffer();
        for(Object x : this)
      	    result.append(x + " ");

        return result.toString();
    }
    
    public class LinkedListIterator implements Iterator<E> {
        private Node<E> index;
        
        public LinkedListIterator() {
            index = head;
        }
        
        public boolean hasNext() {
            return index != null;
        }
        
        public E next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            E val = index.data;
            index = index.next;
            return val;
        }
        
        public void remove() { 
            throw new UnsupportedOperationException(); 
        }
    }
    
    public static void main(String []args){
        System.out.println("Linked List");
        DoubleLinkedList<Integer> ll = new DoubleLinkedList<Integer>();
        ll.addFirst(10);
        ll.addLast(20);
        ll.addFirst(30);
        ll.addAtIndex(3, 40);
        ll.removeElement(40);
        System.out.println(""+ll.getSize());
        ll.printElements();
        for(Integer i : ll) {
            System.out.println(""+i);
        }
        
    }
}
