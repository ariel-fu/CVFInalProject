import java.util.ArrayList;

interface Speaker{
  public void makeSound();
}
class Pet implements Speaker{
  public Pet() {
    System.out.println("Pet created");
    this.makeSound();

    
  }
  public String toString() {
    return "Pet object" ;
  }
  public void makeSound() {
    System.out.println("No sounds allowed");
  }
}


class Dog extends Pet implements Speaker{
  public void makeSound() {
    System.out.println("woof woof");
  }
  
  public String toString() {
    return "Dog object";
  }
}


class Cat extends Pet implements Speaker{
  public void makeSound() {
    System.out.println("meow meow");
  }
  public String toString() {
    return "Cat object";
  }
}

class Human implements Speaker{
  public Human() {
    System.out.println("Human here !");
  }
  public void makeSound() {
    System.out.println("*mean words*");
    
  }
  public String toString() {
    return "Human object";
  }
  
}
public class Casting {

  public static void main(String[] args) {
    ArrayList<Speaker> test = new ArrayList<Speaker>();
    test.add(new Cat());
    test.add(new Human());
    test.add(new Dog());
    test.add(new Cat());
    for(int i=0; i<test.size(); i++) {
      if(test.get(i) instanceof Pet) {
        System.out.println("Instance of pet: " + test.get(i).toString());
        ((Pet) test.get(i)).makeSound(); // casted to a Pet but behaves as the subclass
      } else {
        System.out.println("Not an instance of pet: " + test.get(i).toString());
      }
    }

  }

}
