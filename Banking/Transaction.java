import java.util.Date;

public class Transaction {
    // The amount of this transaction
    private double amount;
    // The time and date of this transaction
    private Date timestamp;
    // A memo for this transaction
    private String memo;
    // The amount in which the transaction was performed
    private Account inAccount;
    
    //create a new transaction
    //@param  amount   the amount transacted
    //@param inAccount   the account the belongs to
    public Transaction(double amount,Account iAccount){

        this.amount=amount;
        this.inAccount=inAccount;
        this.timestamp=new Date();
        this.memo="";

    }
    
     //create a new transaction
    //@param  amount   the amount transacted
    //@param  memo     the memo for the transaction
    //@param inAccount   the account the belongs to
    public Transaction(double amount,String memo, Account iAccount){

        //call the two-arg constructor
        this(amount, iAccount);

        //set the memo
        this.memo=memo;

    }

    //Get the aount of the transaction
    //@return the amount  
    public double getAmount(){
        return this.amount;
    }

    //Get a string summarizing the transaction
    //@return the summary string
    public String getSummaryLine(){
        if(amount>=0){
            return String.format("%s : $%.02f : %s",this.timestamp.toString(),
                        this.amount,this.memo);
        }
        else{

            return String.format("%s : $(%.02f) : %s",this.timestamp.toString(),
                        this.amount,this.memo);
        }
    }
}
