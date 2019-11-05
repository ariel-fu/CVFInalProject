class Base {
	public Base() {
		System.out.println("B");
	}

	public Base(int x) {
		System.out.println("x: " + x);
	}

	public void print() {
		System.out.println("Base object");
	}
}

public class Class_Heirarchy extends Base {

	public Class_Heirarchy() {
		System.out.println("C");
	}

	public Class_Heirarchy(int a) {
		super(a);
		System.out.println("D");
	}

	public void print() {
		System.out.println("Class_Heirarchy object");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Base b = new Class_Heirarchy(8);
		System.out.println(b instanceof Object);
		System.out.println(b instanceof Base);
		System.out.println(b instanceof Class_Heirarchy);
		System.out.println("--------------------");
		b = new Base();
		b.print();
		if (b instanceof Class_Heirarchy) {
			b = (Class_Heirarchy) b;
			System.out.println("downcasted to Class_Heirarchy");
		}
		b.print();
		System.out.println("Upcasted !");

		b = (Base) b;

		b.print();
		System.out.println("--------------------");
		Base h = new Class_Heirarchy();
		System.out.println(h instanceof Base);
		System.out.println(h instanceof Class_Heirarchy);
		h = (Base) h;
		System.out.println("Up casted to base?");
		h.print(); // Base object?
		// even though it is upcasted to a Base object, it will still behave like a
		// Class_Heirarchy object.

	}

}
