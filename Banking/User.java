import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

public class User{
    //The first name of the user
    private String firstName;
     //The last name of the user
    private String lastName;
     //The ID number of the user
    private String uuid;
     //The MD5 hash of the user's pin
    private byte[] pinHash;
    //The list of accounts of this user
    private ArrayList<Account> accounts;

     public User(String firstName, String lastName,String pin,Bank theBank){
        
        //set user's name
        this.firstName=firstName;
        this.lastName=lastName;
        //store the pin's MD5 hash, rather thsn the original value, for
        // security reasons
        try{
            MessageDigest md=MessageDigest.getInstance("MD5");
            this.pinHash=md.digest(pin.getBytes());
        }catch(NoSuchAlgorithmException e){
            System.out.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }
        //get a new, unique universal ID for the user
        this.uuid = theBank.getNewUserUUID();
         // create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName, firstName, this.uuid );
        
    }
    
    //Add an account for the user
    //@param anAcct    the account to add
    public void addAccount(Account anAcct) {
            this.accounts.add(anAcct);
    }

    //return the user's UUID
    //@retutn the UUID
    public String getUUID(){
        return this.uuid;
    }

    //check whether a given pin matches the true User pin
    //@param apin   the pin to check
    //@rey=turn   whether the pin is valid or not
    public boolean validatePin(String apin){
            try {
                MessageDigest md=MessageDigest.getInstance("MD5");
                return MessageDigest.isEqual(md.digest(apin.getBytes()),this.pinHash);
            } catch (NoSuchAlgorithmException e) {
                
                System.out.println("error, caught NoSuchAlgorithmException");
                e.printStackTrace();
                System.exit(1);
                e.printStackTrace();
            }

            return false;
    }
    //return the user's first name
    //@retutn the first name
    public String getFirstName(){
        return this.firstName;
    }

    //print summaries for the accounts of htis user
    public void printAccountsSummary(){
        System.out.printf("\n\n%s's acocounts summary\n",this.firstName);
        for(int a=0;a<this.accounts.size();a++){
            System.out.printf("%d) %s\n", a+1,this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    // Get the number of accounts of the user
    // @return the number of accounts
    public int numAccounts(){
        return this.accounts.size();
    }

    //print transaction history for a particular account
    //@param acctId te index of the account to use
    public void printAcctTransHistory(int acctIdx){
            this.accounts.get(acctIdx).printTransHistory();
    }

    //Get the balance of a particular account
    //@param acctIdx the index of the account to use
    //@return the balance of the account
    public double getAcctBalance(int acctIdx){
        return this.accounts.get(acctIdx).getBalance();
    }

    //Get the UUID of a pearticular account
    //@param acctIdx  the index of the account to use
    // @return    the UUID of the amount
    public String getAcctUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUUID();
    }

    public void addAcctTransaction(int acctIdx,double amount,String memo){
        this.accounts.get(acctIdx).addTransaction(amount,memo);
    }
}