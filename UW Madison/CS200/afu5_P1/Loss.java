public class Loss {

	public static void main(String[] args) {
        //create var for the original amount of money each store had before the robbery
        double freshBeforeLoss=10000.00;
        double subwayBeforeLoss = 2000.00;
        double qqsBeforeLoss = 827.69;
        double chipotleBeforeLoss = 188203.98;

        double epicBeforeLoss = 2000000.00;

        //create var for the final amount of money that each store had
        double freshAfterLoss = 4376.02;
        double subwayAfterLoss = 2.99;
        double qqsAfterLoss = 543.21;
        double chipotleAfterLoss = 25767.03;
        double epicAfterLoss = 1999999.99;

        //create var for the loss which will be the original amount minus the final amount
        double freshLoss = freshBeforeLoss - freshAfterLoss;
        double subwayLoss = subwayBeforeLoss - subwayAfterLoss;
        double qqsLoss = qqsBeforeLoss - qqsAfterLoss;
        double chipotleLoss = chipotleBeforeLoss - chipotleAfterLoss;
        double epicLoss = epicBeforeLoss - epicAfterLoss;

        //print out the loss
        System.out.println("Fresh lost $" + freshLoss );
        System.out.println("Subway lost $" + subwayLoss);
        System.out.println("QQ's lost $" + qqsLoss);
        System.out.println("Chipotle lost $" + chipotleLoss);
        System.out.println("Epic lost $" + epicLoss);

	}

}
