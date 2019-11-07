interface I {
  public void methodI();
}

class A {
  public void methodA() {
    System.out.println("A.methodA() called.");
  }

  public void sing() {
    System.out.println("A.sing() called");
  }

  public static <T> void printValue(T value) {
    System.out.println(value); // generic method.
  }
}

class B extends A {
  public void methodA() {
    System.out.println("B.methodA() called");
  }

  public void sing() {
    System.out.println("B.sing() called");
  }

  public static <T extends Comparable<T>> T max(T value1, T value2) {
    if(value1.compareTo(value2) >= 0) {
      return value1;
    }
    return value2;
    // generic method.
  }
}

class C extends B implements I {
  public void methodA() {
    System.out.println("C.methodA called");
  }

  public void methodI() {
    System.out.println("C.methodI() called");
  }

  public void fly() {
    System.out.println("C.fly() called");
  }
}

public class Review {
  public static void main(String[] args) {
    B b = new C(); // creates a new Object reference of C class and upcasted its reference to type
                   // B
    // note that it can be safely upcasted to B, A, I
    // C --> B --> A
    // |
    //  --> I (interface)
    // also note that B instance can only use methods in the B class and A class and
    // I interface.
    // no methods specific to the subclass can be used.
    b.methodA();
    // however, it will still behave like the subclass. Any overridden methods in
    // the C class can be used by this instance.
    C c = (C)b;
    c.methodA();
  }

}