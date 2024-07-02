import java.util.ArrayList;
import java.util.Random;
public class Bank {
    
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts; 
    // create a new Bank object with empty lists of users and accounts
    // @param name the of the
    public Bank(String name){
        this.name=name;
        this.users=new ArrayList<User>();
        this.accounts=new ArrayList<Account>();   
    }
    
    //Generate a new universally unique ID for a user
    //@return  the uuid
    public String getNewUserUUID() {

        //inits
        String uuid;
        Random rng=new Random();
        int len=6;
        boolean nonUnique;
        
        //continuing looping until we get a unique Id
        do {
            
            //generate the number
            uuid="";
            for(int c=0;c<len;c++){
                uuid +=((Integer)rng.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique=false;
            for(User u:this.users){
                if(uuid.compareTo(u.getUUID())==0){
                    nonUnique=true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
		 
    }

     //Generate a new universally unique ID for an account
    //@return  the uuid
    public String getNewAccountUUID() {
        
        //inits
        String uuid;
        Random rng=new Random();
        int len=10;
        boolean nonUnique;
        
        //continuing looping until we get a unique Id
        do {
            
            //generate the number
            uuid="";
            for(int c=0;c<len;c++){
                uuid +=((Integer)rng.nextInt(10)).toString();
            }

            //check to make sure it's unique
            nonUnique=false;
            for(Account a:this.accounts){
                if(uuid.compareTo(a.getUUID())==0){
                    nonUnique=true;
                    break;
                }
            }

        } while (nonUnique);

        return uuid;
    }


    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
    
   
    //create a new user of the bank
    //@param firstName   the User's firstName
    //@param lastName   the User's lastName
    //@param pin   the User's pin
    //@return   the new user object
    public User addUser(String firstName,String lastName,String pin){

        //cerate  a new user object and add it to our list
        User newUser=new User(firstName, lastName, pin,this);
        this.users.add(newUser);

        //create a savings account for the user and add to user and Bank accounts lists
        Account newAccount=new Account("Savings", newUser, this);
        //add to holder and bank lists
		newUser.addAccount(newAccount);
		newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    //get the user object associated with aparticular userId and pin
    public User userLogin(String userID, String pin){

        //search trough list of users
        for(User u:this.users){

            //check user Id is correct
            if(u.getUUID().compareTo(userID)==0 && u.validatePin(pin)){
                return u;
            }
        }

        //if we haven't found the user or have an incorrect pin
        return null;

    }

    //  Get the name of the bank
    // @return the name of the bank

    public String getName(){
        return this.name;
    }
}
