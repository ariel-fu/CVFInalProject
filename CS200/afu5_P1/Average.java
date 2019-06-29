public class Average {

	public static void main(String[] args) {
        // copy over the Loss class
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

        //create var for the loss which will be the original amount minus the final amout
        double freshLoss = freshBeforeLoss - freshAfterLoss;
        double subwayLoss = subwayBeforeLoss - subwayAfterLoss;
        double qqsLoss = qqsBeforeLoss - qqsAfterLoss;
        double chipotleLoss = chipotleBeforeLoss - chipotleAfterLoss;
        double epicLoss = epicBeforeLoss - epicAfterLoss;
        //add all of the losses together
        double sumOfLoss = freshLoss + subwayLoss + qqsLoss + chipotleLoss + epicLoss;

        //divide by 5 (the number of stores)
        double averageLoss = sumOfLoss/5;

        //print out the average
        System.out.println("The average loss was $" + averageLoss);

        //okay hi!
        //I had extra time doing this assignment and was thinking of different ways to do it
        // I came up with this but they don't have the same answers, is there a reason?
        //thank you!

        double before = freshBeforeLoss+ subwayBeforeLoss + qqsBeforeLoss + chipotleBeforeLoss + epicBeforeLoss;
        double after = freshAfterLoss+ subwayAfterLoss + qqsAfterLoss + chipotleAfterLoss + epicAfterLoss;
        System.out.println("The average loss was $" + (before-after)/5.0);

	}

}