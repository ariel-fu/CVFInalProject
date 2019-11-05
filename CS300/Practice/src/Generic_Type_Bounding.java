class Car extends Vehicle {
	public Car() {
		System.out.println("NEW CAR OBJECT CREATEd");
	}
}

class Vehicle{
	public Vehicle() {
		System.out.println("VEHICLE OBJECT CREATED");
	}
}
public class Generic_Type_Bounding<T extends Vehicle> {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Generic_Type_Bounding<Car> x = new Generic_Type_Bounding<Car>();
		
	}

}
