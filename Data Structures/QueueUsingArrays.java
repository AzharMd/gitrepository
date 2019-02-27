public class Queue {

    int index;
    int capacity;
    int[] queue;
    
    
    public Queue(int capacity) {
        this.capacity = capacity;
        queue = new int[capacity];
        this.index = 0;
    }
    
    public void push(int data) {
        if(!isFull()) {
            queue[index] = data;
            index++;
        } else {
            System.out.println("Queue is full");
        }
    }
    
    public int pop() {
        if(isEmpty()) {
            System.out.println("Queue empty");
            return -1;
        }
        int data = queue[0];
        for(int i = 0; i < index - 1; i++){
			queue[i] = queue[i + 1];
		}
		this.index--;
        
        return data;
    }
    
    public int peek() {
       if(!isEmpty()) {
           return queue[0];
       }
       return -1;
    }
    
    public boolean isEmpty() {
        return index == 0;
    }
    
    public boolean isFull() {
        return index == capacity;
    }
    
    public void printQueue() {
        for(int i = 0; i < index; i++) {
            System.out.println(""+queue[i]);
        }
    }
    
    public static void main(String[] args) {
       Queue queue = new Queue(5);
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


