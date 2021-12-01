//notes for the ER diagram
//Owner needs a username and passowrd if we want the owner to access everything through the store

import java.util.Scanner;  // Import the Scanner class

public class Main
{
	public static void main(String[] args) {
	    Scanner getInput = new Scanner(System.in);
	    String Status = "";//This determines what they can do. whatever they choose sets status to This
	    //it makes it easier, so we can tell the user 'no' when they try to do someething they cant do
	    //like an unregistered user buying a book
		System.out.println("Welcome to the X BookStore!");
		System.out.println("Are you a registered user? (Y or N)");
		String reg_choice = getInput.nextLine();
		//Owner is hidden so no random person tries for owner(o)
		//while loop that makes sure user has put right input
		while (!(reg_choice.toLowerCase()).equals ("y") && !(reg_choice.toLowerCase()).equals ("n")&& !(reg_choice.toLowerCase()).equals ("o"))
		{
            System.out.println("ERROR: Input must be 'y' or 'n'");
            reg_choice = getInput.nextLine(); 
        }
		//Y, N, O
		//if Y
		if((reg_choice.toLowerCase()).equals("y"))
		{
		//set user as R and get user info
			Status = "Registered";
			System.out.println("You have chosen: Registered!");
			System.out.println("Please enter your username: ");
			String username = getInput.nextLine();//get and store username
			System.out.println("Please enter your password: ");
			String password = getInput.nextLine();//get and store password

		}
		//else if N
		else if((reg_choice.toLowerCase()).equals("n"))
		{
		    //ask if they would like to Registerer
		    //if yes, ask for username,passord,billing and shipping info
		    
		    
		    
		    
		//set user as U
			Status = "Unregistered";
			System.out.println("You have chosen: Unregistered!");
		}
		//Else if owner
		else if((reg_choice.toLowerCase()).equals("o"))
		{
		//set user as Owner and get info
			Status = "Owner";
			System.out.println("You have chosen: Owner!");
			System.out.println("Please enter your username: ");
			String username = getInput.nextLine();//get and store username
			System.out.println("Please enter your password: ");
			String password = getInput.nextLine();//get and store password
		}
		
		//only let users do this, not owner.
		//owner gets its own menu
		//loop starts here, re-loops when book is chosen or goes back
		if((Status.equals("Registered")) || (Status.equals("Unregistered")))
    	{
    		System.out.println("What would you like to do?:");
    		System.out.println("(b)rowse");
    		System.out.println("(p)urchace");
    		System.out.println("(q)uit");
    		String user_Choice = getInput.nextLine();//get and store password
    		while (!(user_Choice.toLowerCase()).equals ("b") && !(user_Choice.toLowerCase()).equals ("p")&& !(user_Choice.toLowerCase()).equals ("q"))
    		{
                System.out.println("ERROR: Input must be 'b' or 'p' or 'q");
                user_Choice = getInput.nextLine(); 
            }
            
            if((user_Choice.toLowerCase()).equals("b"))
        	{
        		//when the user chooses a book, it fills the "book chosen" slot. Because you can only do one at a time, if a user chooses another book, it deletes the other book.
        		System.out.println("You have chosen: Browse!");
        		System.out.println("How would you like to search for your book?");
        		System.out.println("(n)ame");
        		System.out.println("(a)uther");
        		System.out.println("(g)enre");
        		System.out.println("(i)sbn");
        		System.out.println("(q)uit");
        		String search_book = getInput.nextLine();//get and store username
        		while (!(search_book.toLowerCase()).equals ("n") && !(search_book.toLowerCase()).equals ("a")&& !(search_book.toLowerCase()).equals ("g")
        		&& !(search_book.toLowerCase()).equals ("i")&& !(search_book.toLowerCase()).equals ("q"))
        		{
                    System.out.println("ERROR: Input must be'n' or 'a' or 'g' or 'i' or 'q'");
                    search_book = getInput.nextLine(); 
                }
                if((search_book.toLowerCase()).equals ("q"))//This line of code should show up a lot
                {
                    //quit program, give farwell
                }
                else if((search_book.toLowerCase()).equals ("n"))
                {
                    //search book by name
                }
                else if((search_book.toLowerCase()).equals ("a"))
                {
                    //search book by auther name
                }
                else if((search_book.toLowerCase()).equals ("g"))
                {
                    //search book by genre
                }
                else if((search_book.toLowerCase()).equals ("i"))
                {
                    //search book by isbn
                }
        	}
    		else if((user_Choice.toLowerCase()).equals("p"))
    		{
    		    //check to see if user has chosen a book 
    		    //if not, say error and bring up menu again
    		    //if so, purchace book from system
    		      System.out.println("You have chosen: Purchace!");
    		      //ask user to enter info on their billing and shipping
    		      System.out.println("Please enter your Card info: ");
	              String bank_number = getInput.nextLine();//get and billing
	              System.out.println("Please enter your adress: ");
	              String adress = getInput.nextLine();//get and store shipping
    		}
    		else if((user_Choice.toLowerCase()).equals("q"))
    		{
    		    //say goodbye and break from loop
    		    System.out.println("You have chosen: Quit!");
    		    //break;
    		}
    		
    	}
		else//else if owner
		{
		    
		}
		//if Browse
		//when this is set to browse
		//ask to search for book. ask what they would like to search(name, page, ect)
		//if info is found, show books that can be applicable to this
		//allow user to choose from count, 1 2 3
		//show book inf0
		//ask user if they want it in checkout basket
		//if yes, add and loop back
		//if no, loop back
	}
}
