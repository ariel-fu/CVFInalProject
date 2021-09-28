class Plant {
  public Plant() {

  }

  public void grow() {
    System.out.print("growing");
  }

}

public class flower extends Plant {
  public static void main(String[] args) {
    flower x = new flower();
    x.grow();
  }
}
