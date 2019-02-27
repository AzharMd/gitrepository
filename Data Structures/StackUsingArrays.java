public class Stack {
    
    int[] stack;
    int top;
    int size;

    public Stack(int size) {
        this.stack = new int[size];
        this.top = -1;
        this.size = size;
    }
    
    public void push(int data) {
        if(!isFull()) {
            top++;
            stack[top] = data;
        } else {
            System.out.println("Stack full");
        }
    }
    
    public int pop() {
        if(!isEmpty()) {
            int data = stack[top];
            top--;
            return data;
        } else {
            System.out.println("Stack is empty");
            return -1;
        }
    }
    
    public int peek() {
        if(!isEmpty()) {
            return stack[top];
        }
        return -1;
    }
    
    public boolean isFull() {
        return top == (size - 1);
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
    
    public void printStack() {
        if(top == -1) {
            System.out.println("Stack is empty");
            return;
        }
        int i = top;
        while(i >= 0) {
            System.out.println(""+stack[i]);
            --i;
        } 
    }
    
    public static void main(String[] args) {
        Stack stack = new Stack(10);
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
