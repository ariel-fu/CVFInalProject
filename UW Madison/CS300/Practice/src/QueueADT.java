import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

public class QueueADT {
  public void enqueue(int x) {
    
  }
  public static void main(String[] args) {
//    // TODO Auto-generated method stub
//    Queue queue = new Queue();
//    queue.enqueue(10);
//    queue.enqueue(20);
//    queue.enqueue(30);
//    queue.enqueue(40);
//    // while loop:
//    while (!queue.isEmpty()) {
//      Integer front = queue.dequeue();
//    }

    Stack<Integer> stack = new Stack(); // creates an empty stack
    stack.push(1);
    stack.push(2);
    stack.push(3); 
    Iterator<Integer> iterator = stack.iterator(); // returns an iterator over the stack starting from its top.
    System.out.print(iterator.next());

//    Stack<String> x = new Stack();
//    x.push("10");
//    x.push("20");
//    while (!x.isEmpty()) {
//      System.out.println(x.peek());
//    }
  }

}
