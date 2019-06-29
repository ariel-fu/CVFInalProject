public class HQ {

	public static void main(String[] args) {
        //message = 0.8701298701298701, 2.155844155844156, 1.9090909090909092, 2.6493506493506493, 3.116883116883117, 3.8181818181818183
        //create vars to cast back to chars
        char one = (char) (77*0.8701298701298701);
        char two = (char)((77*2.155844155844156)/2);
        char three = (char) ((77*1.9090909090909092)/3);
        char four = (char) ((77*2.6493506493506493)/4);
        char five =(char) ((77*3.116883116883117)/5);
        char six = (char)((77*3.8181818181818183)/6);

        System.out.println(""+one+two+three+four+five+six+"");
        System.out.println("They are hiding at " + one+two+three+four+five+six + "!");
	}

}