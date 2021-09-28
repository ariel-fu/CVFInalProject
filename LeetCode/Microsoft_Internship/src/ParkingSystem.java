
public class ParkingSystem {
	private final int SMALL = 3;
	private final int MEDIUM = 2;
	private final int BIG = 1;

	private int big;
	private int medium;
	private int small;

	public ParkingSystem() {
		this.big = 0;
		this.medium = 0;
		this.small = 0;
	}
	
	public ParkingSystem(int big, int medium, int small) {
		this.big = big;
		this.medium = medium;
		this.small = small;
	}


	public boolean addCar(int carType) {
		if (carType == SMALL) {
			// remove a spot because we are "parking" a car there
			small -= 1;
			// if there are no more spots left, return false
			if (small < 0) {
				return false;
			}
			// otherwise, the car has been successfully parked
			return true;
		} else if (carType == MEDIUM) {
			medium -= 1;
			if (medium < 0) {
				return false;
			} else {
				return true;
			}
		} else if (carType == BIG) {
			big -= 1;
			if (big < 0) {
				return false;
			} else {
				return true;
			}
		} else {
			// invalid type of car type
			return true;
		}
	}
}
