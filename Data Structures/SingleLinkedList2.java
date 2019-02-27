import java.lang.Object;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> implements Iterable<E> {

    public class Node<E> implements Comparable<E> {
        Node<E> next;
        E data;
        
        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
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
    
    public void addFirst(E data) {
        head = new Node<E>(data, head);
        size++;
    }
    
    public E getFirst() {
       if(head == null)
            throw new NoSuchElementException();

        return head.data;
    }
    
    public void addLast(E data) {
        if( head == null) {
            addFirst(data);
        } else {
            Node<E> tmp = head;
            while(tmp.next != null) {
                tmp = tmp.next;
            }
            tmp.next = new Node<E>(data, null);
            size++;
        }
    }
    
    public E getLast() {
        if(head == null) 
            throw new NoSuchElementException();

        Node<E> tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        return tmp.data;
    }
    
    public void addAtIndex(int index, E data) {
        if(index > size || index < 0) {
            throw new IndexOutOfBoundsException("index: " + index + ". Is out of bounds for list of size: " + size);
            
        }
        
        if(head == null || index == 0) {
            addFirst(data);
            return;
        }
        
        Node<E> tmp = head;
        for(int i = 0; i < index - 1; i++) {
            tmp = tmp.next;
        }
        Node<E> newNode = new Node<E>(data, tmp.next);
        tmp.next = newNode;
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
        if(isEmpty()) {
            return null;
        }
        
        E tmp = getFirst();
        head = head.next;
        size--;
        return tmp;
    }
    
    public E removeLast() {
        if(isEmpty()) {
            return null;
        }
        Node<E> cur = head;
        Node<E> prev = null;
        while(cur.next != null) {
            prev = cur;
            cur = cur.next;
        }
        E data = cur.data;
        cur = null;
        prev.next = null;
        size--;
        return data;
    }
    
    public E removeAtIndex(int index) {
        if(index < 0 || index > size - 1) {
            throw new IndexOutOfBoundsException("index: " + index + ". Is out of bounds for list of size: " + size);
        }
        
        if(isEmpty()) {
            throw new RuntimeException("List is Empty");
        }
      
        Node<E> cur = head;
        Node<E> prev = null;
        for(int i = 0; i < index; i++) {
            prev = cur;
            cur = cur.next;
        }
        
        //delete cur node
        E data = cur.data;
        if(prev == null) {
            head = head.next;
        } else {
            prev.next = cur.next;
        }
        size--;
        return data;
    }
    
    public void removeElement(E data) {
        if(head == null)
            throw new RuntimeException("Element not found");
        if(compare(head.data, data)) {
            head = head.next;
            return;
        }

        Node<E> cur  = head;
        Node<E> prev = null;

        while(cur != null && !compare(cur.data, data)) {
            prev = cur;
            cur = cur.next;
        }

        if(cur == null)
            throw new RuntimeException("Element not found");

        //delete cur node
        prev.next = cur.next;
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
        LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.addFirst(10);
        ll.addLast(20);
        ll.addFirst(30);
        ll.addAtIndex(3, 40);
        System.out.println(""+ll.removeLast());
        ll.printElements();
        for(Integer i : ll) {
            System.out.println(""+i);
        }
        
    }
}
