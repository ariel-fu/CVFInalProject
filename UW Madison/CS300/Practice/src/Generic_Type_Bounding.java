class Cup{
  public Cup() {
    System.out.println("CUP object created");
  }
  public Cup(Object object) {
    System.out.println("cup type");
  }
  public void pour() {
    System.out.println("<-----");
  }
}
class Liquid extends Cup{
  public Liquid() {
    System.out.println("liquid type");
  }
  public void pour() {
    System.out.println("------>");
  }
}
public class Generic_Type_Bounding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cup c = new Liquid();
		c.pour();
	}

}
