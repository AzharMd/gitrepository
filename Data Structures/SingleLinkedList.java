import java.lang.Object;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {

    public class Node<E> implements Comparable<E> {
        Node<E> next;
        E data;
        
        public Node(E data) {
            this.data = data;
            next = null;
        }
        
        @Override
        public int compareTo(E data) {
            return (this.data).equals(data) ? 0 : -1;
        }
    }
    
    private Node<E> head;
    private int size;
    
    public LinkedList() {
        head = null;
        size = 0;
    }
    
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }
    
    public void addFront(E data) {
        Node<E> newNode = new Node<E>(data);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    public void addLast(E data) {
        Node<E> newNode = new Node<E>(data);
        //The list is empty
        if(head == null) {
            head = newNode;
            size++;
            return;
        }
        
        Node<E> tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
        size++;
    }
    
    public void addAtIndex(int index, E data) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ". Is out of bounds for list of size: " + size);
            
        }
        
        if(head == null || index == 0) {
            addFront(data);
            return;
        }
        Node<E> newNode = new Node<E>(data);
        Node<E> tmp = head;
        for(int i = 0; i < index - 1; i++) {
            tmp = tmp.next;
        }
        newNode.next = tmp.next;
        tmp.next = newNode;
        size++;
    }
    
    public void printElements() {
        Node<E> tmp = head;
        while(tmp != null) {
            System.out.print("["+tmp.data+"] ");
            tmp = tmp.next;
        }
    }
    
    public boolean isEmpty() {
        return this.head == null;
    }
    
    public E removeFront() {
        if(isEmpty()) {
            return null;
        }
        E data = head.data;
        head = head.next;
        size--;
        return data;
    }
    
    public E removeLast() {
        if(isEmpty()) {
            return null;
        }
        Node<E> tmp = head;
        while(tmp.next.next != null) {
            tmp = tmp.next;
        }
        E data = tmp.data;
        tmp.next = null;
        size--;
        return data;
    }
    
    public E removeAtIndex(int index) {
        if(isEmpty()) {
            return null;
        }
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index: " + index + ". Is out of bounds for list of size: " + size);
        }
        
        Node<E> tmp = head;
        for(int i = 0; i < index - 1; i++) {
            tmp = tmp.next;
        }
        E data = tmp.data;
        tmp.next = tmp.next.next;
        size--;
        return data;
    }
    
    public void removeElement(E data) {
        if(isEmpty() || data == null) {
            return;
        }
        Node<E> tmp = head;
        Node<E> prev = null;
        while(tmp != null) {
            if(((Comparable<E>)data).compareTo(tmp.data) == 0) {
                if(prev == null) {
                    removeFront();
                    return;
                }
                prev.next = tmp.next;
                size--;
                return;
            }
            prev = tmp;
            tmp = tmp.next;
        }
        System.out.println("Element not found");
    }
    
    public void contains(E data) {
        Node<E> tmp = head;
        while( tmp != null) {
            if(((Comparable<E>)data).compareTo(tmp.data) == 0) {
                System.out.println("Element found = "+data);
                return;
            }
            tmp = tmp.next;
        }
        System.out.println("Element not found = "+data);
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
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.addAtIndex(0, 10);
        ll.addFront(20);
        ll.addLast(30);
        ll.addAtIndex(3, 40);
        //ll.removeLast();
        ll.printElements();
        for(Integer i : ll) {
            System.out.println(""+i);
        }
        
     }
}
