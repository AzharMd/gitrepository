public class DoublyLinkedList<E> {
    
    public class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
        
        public Node(E data, Node<E> next, Node<E> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    Node<E> head;
    Node<E> tail;
    int size;
    
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void addFirst(E data) {
        Node<E> newNode = new Node<E>(data, head, null);
        if(!isEmpty()) {
            head.prev = newNode;
        }
        head = newNode;
        if(tail == null) {
            tail = newNode;
        }
        size++;
        
    }
    
    public E getFirst() {
        return head == null ? null : head.data;
    }
    
    public void addLast(E data) {
        Node<E> newNode = new Node<E>(data, null, tail);
        if(tail != null) {
            tail.next = newNode;
        }
        tail = newNode;
        
        if(isEmpty()) {
            head = newNode;
        }
        size++;
    }
    
    public E getLast() {
        return tail == null ? null : tail.data;
    }
    
    public void addAtIndex(int index, E data) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        
        if(index == 0) {
            addFirst(data);
            return;
        }
        
        if(index == (size - 1)) {
            addLast(data);
            return;
        }
        
        Node<E> c = head;
        Node<E> p = null;
        for(int i = 0; i < index; i++) {
            p = c;
            c = c.next;
        }
        
        Node<E> newNode = new Node<E>(data, c, p);
        c.prev = newNode;
        p.next = newNode;
        size++;
    }
    
    public void printElements() {
        Node<E> tmp = head;
        while(tmp != null) {
            System.out.print("["+tmp.data+"] ");
            tmp = tmp.next;
        }
        System.out.println("");
    }
    
    public E getFromIndex(int index) {
        if(index < 0 || index > (size - 1)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> tmp = head;
        for(int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.data;
    }
    
    public E removeFirst() {
        if(isEmpty()) {
            return null;
        }
        E data = head.data;
        head = head.next;
        head.prev = null;
        size--;
        return data;
    }
    
    public E removeLast() {
        if(isEmpty()) {
            return null;
        }
        E data = tail.data;
        tail = tail.prev;
        tail.next = null;
        size--;
        return data;
    }
    
    public E removeFromIndex(int index) {
        if(index < 0 || index > (size - 1)) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        
        Node<E> c = head;
        Node<E> p = null;
        for(int i = 0; i < index; i++) {
            p = c;
            c = c.next;
        }
        
        if(p == null) {
            return removeFirst();   
        }
        
        if(c.next == null) {
            return removeLast();
        }
        
        E data = c.data;
        p.next = c.next;
        c.next.prev = p;
        size--;
        return data;
    }
    
    public void removeElement(E data) {
        if(isEmpty()) {
            System.out.println("Cannot delete, list is empty");
        }
        
        Node<E> c = head;
        Node<E> p = null;
        while(c != null && !(c.data).equals(data)) {
            p = c;
            c = c.next;
        }
        
        if(c == null) {
            System.out.println("Element not found");
            return;
        }
        
        if(p == null) {
            removeFirst();
            return;
        }
        if(c.next == null) {
            removeLast();
            return;
        }
        
        p.next = c.next;
        c.next.prev = p;
        size--;
        return;
    }
    
    public void clear() {
        head = null;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public int getSize() {
        return size;
    }
    
    public static void main(String []args){
        System.out.println("Doubly Linked List");
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<Integer>();
        dll.addFirst(10);
        dll.addLast(20);
        dll.addFirst(30);
        dll.addFirst(40);
        dll.addAtIndex(2, 50);
        dll.printElements();
        dll.removeElement(2);
        //System.out.println("Data = "+dll.removeFromIndex(-1));
        dll.printElements();

    }
}
