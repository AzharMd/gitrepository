public class Queue {

    public class Node {
        int data;
        Node next;
        
        public Node(int data) {
            this.data = data;
            next = null;
        }
    }
    
    private Node head; //remove from head
    private Node tail; //add to tail
    
    public Queue() {
        head = null;
        tail = null;
    }
    
    public void push(int data) {
        Node node = new Node(data);
        if(tail != null) {
            tail.next = node;
        }
        tail = node;
        if(head == null) {
            head = node;
        }
    }
    
    public int pop() {
        if(isEmpty()) {
            System.out.println("Queue empty");
            return -1;
        }
        int data = head.data;
        head = head.next;
        if(head == null) {
            tail = null;
        }
        
        return data;
    }
    
    public int peek() {
       if(!isEmpty()) {
           return head.data;
       }
       return -1;
    }
    
    public boolean isEmpty() {
        return head == null;
    }
    
    public void printQueue() {
        Node tmp = head;
        while(tmp != null) {
            System.out.println(""+tmp.data);
            tmp = tmp.next;
        }
    }
    
    public static void main(String[] args) {
       Queue queue = new Queue();
		queue.push(1);
		queue.push(2);
		queue.push(3);
		queue.push(4);
		queue.push(5);
		queue.pop();
		System.out.println("Peek = "+queue.peek());
		queue.printQueue();
    }
}

