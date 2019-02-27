public class StackLL {

    public class Node {
        int data;
        Node next;
        
        public Node(int data) {
            this.data = data;
        }
    }
    
    Node head;
    
    public StackLL() {
       head = null;
    }
    
    public void push(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }
    
    public int pop() {
        if(isEmpty()) {
            System.out.println("Stack empty");
            return -1;
        }
        int data = head.data;
        head = head.next;
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
    
    public void printStack() {
        Node tmp = head;
        while(tmp != null) {
            System.out.println(""+tmp.data);
            tmp = tmp.next;
        }
    }
    
    public static void main(String[] args) {
        StackLL stack = new StackLL();
        stack.pop();
        System.out.println("=================");
        stack.push(10);
        stack.push(30);
        stack.push(50);
        stack.push(40);
        stack.printStack();        
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println("Peek = "+stack.peek());
        stack.printStack();        
    }
}
