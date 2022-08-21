import java.util.ArrayList;
import java.util.Iterator;



public class For_Each_Loop<T> implements Iterable<T> {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(2);
		list.add(5);
		list.add(9);
		list.add(45);
		list.add("new object");
		list.add('e');
		list.add(true);
		int i=0;
		for(Object o : list) {
			System.out.println(list.get(i));
			i++;
		}
	}

	@Override
	public Iterator<T> iterator() {
		return null;
		
	}

}
