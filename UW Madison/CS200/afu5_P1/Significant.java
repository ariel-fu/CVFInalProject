public class Significant {

	public static void main(String[] args) {
        //copy over loss class
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
  
          //create var for the loss which will be the original amount minus the final amout
          double freshLoss = freshBeforeLoss - freshAfterLoss;
          double subwayLoss = subwayBeforeLoss - subwayAfterLoss;
          double qqsLoss = qqsBeforeLoss - qqsAfterLoss;
          double chipotleLoss = chipotleBeforeLoss - chipotleAfterLoss;
          double epicLoss = epicBeforeLoss - epicAfterLoss;
  
        //create var that is loss / original
        double freshPercent = (freshLoss/freshBeforeLoss)*100;
        double subwayPercent = (subwayLoss/subwayBeforeLoss)*100;
        double qqsPercent = (qqsLoss / qqsBeforeLoss)*100;
        double chipotlePercent = (chipotleLoss/ chipotleBeforeLoss)*100;
        double epicPercent = (epicLoss/ epicBeforeLoss)*100;

    /*
        //create booleans if percentage var is greater than 43.21876
        boolean freshLossSignificant = (freshPercent*100) > 43.21876;
        boolean subwayLossSignificant = (subwayPercent*100) > 43.21876;
        boolean qqsLossSignificant = (qqsPercent*100) > 43.21876;
        boolean chipotleLossSignificant = (chipotlePercent*100) > 43.21876;
        boolean epicLossSignificant = (epicPercent*100) > 43.21876;
    */
    
       //print out percentage loss of each store
        System.out.println("Fresh lost " + freshPercent + "% of their money" );
        System.out.println("Subway lost " + subwayPercent + "% of their money" );
        System.out.println("QQ's lost " + qqsPercent + "% of their money" );
        System.out.println("Chipotle lost " + chipotlePercent + "% of their money" );
        System.out.println("Epic lost " + epicPercent + "% of their money" );

        //print out stores that lost a significant amount.
        System.out.println("");
        //then everything is less cramped :))
        System.out.println("Stores that lost a significant amount: Fresh, Subway, and Chipotle");
	}

}