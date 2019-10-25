
public class Test {
  /**
   * Helper method to display the contents of a list of mega blocks
   * 
   * @param list a reference to a LinkedListMegaBlock object
   * @throws NullPointerException if list is null
   */
  private static void display(LinkedListMegaBlock list) {
    // display list content
    System.out.println("list content: " + list.toString());
    // display information about the size of this list and the its blocks' color
    // counts
    System.out.println("Size: " + list.size() + ", redCount: " + list.getRedCount()
            + ", yellowCount: " + list.getYellowCount() + ", blueCount: " + list.getBlueCount());
    System.out.println();
  }

  /**
   * Driver method to show how to use the implemented classes in P06 Mega Blocks
   * Builder
   * 
   * @param args input arguments
   */
  public static void main(String[] args) {
    // Create a new empty LinkedListMegaBlocks list
    LinkedListMegaBlock list = new LinkedListMegaBlock();
    list.addYellow(0, new MegaBlock(Color.YELLOW, 'A'));
    list.addRed(new MegaBlock(Color.RED, 'B'));
    list.addYellow(list.size(), new MegaBlock(Color.YELLOW, 'C'));
    list.addYellow(2, new MegaBlock(Color.YELLOW, 'D'));
    list.addBlue(new MegaBlock(Color.BLUE, 'E'));
    
  }

}
