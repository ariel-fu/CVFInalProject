import java.util.NoSuchElementException;

public class TesterClass {

	public static void main(String[] args) {
		LinkedNode<DrawingChange> top = new LinkedNode<DrawingChange>(new DrawingChange(0, 0, ' ', '3'));
		DrawingStack x = new DrawingStack(null);
		try{
			x.peek();
		}
		catch(NoSuchElementException e) {
			System.out.println("Exception caught.");
		}
		try {
			x.push(null);
		} catch(IllegalArgumentException e) {
			System.out.println("Pushing a null object exception caught.");
		}
	}
}
