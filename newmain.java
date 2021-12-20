//HOW TO INSERT INTO https://alvinalexander.com/java/edu/pj/jdbc/jdbc0002/
//should be how to delete as well

//update method: https://www.tutorialspoint.com/javaexamples/jdbc_updatetable.htm
//note: books added to checkout should only store the book id in an array. it should make it easier
import java.util.Scanner; // Import the Scanner class
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//ResultSet resultSet = statement.executeQuery("select prereq_id"+" from prereq"+" where course_id = " + course);

//above is how you use a querry to get something in sql using java.

//for example. using our map to get the stock of a book with the name of name_of_book
//ResultSet resultSet = statement.executeQuery("select stock"+" from book"+" where name = " + name_of_book);
public class newmain {
	static Connection connection;
	static PreparedStatement pStmt;
	public static void main(String[] args) {
		ResultSet ret;
		String username = "";
		String password = "";
		ArrayList<String> BooksInCart = new ArrayList<String>();
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres",
					"pswd");
			Statement statement = connection.createStatement();
			// This determines what they can do. whatever they choose sets status to This
			// it makes it easier, so we can tell the user 'no' when they try to do
			// someething they cant do
			// like an unregistered user buying a book

			Scanner getInput = new Scanner(System.in);
			String Status = "";
			int totalCost = 0;//add up total cost of the book, set to 0 when purchacee.
			while(true)
			{
				System.out.println("Welcome to the X BookStore!");
				System.out.println("Are you a registered user? (Y or N) OR would you like to just track an order? (t)");
				String reg_choice = getInput.nextLine();
				// Owner is hidden so no random person tries for owner(o)
				// while loop that makes sure user has put right input
				while (!(reg_choice.toLowerCase()).equals("y") && !(reg_choice.toLowerCase()).equals("n")
						&& !(reg_choice.toLowerCase()).equals("o")&& !(reg_choice.toLowerCase()).equals("t")&& !(reg_choice.toLowerCase()).equals("q")) {
					System.out.println("ERROR: Input must be 'y' or 'n' or 'q' or  't'");
					reg_choice = getInput.nextLine();
				}
				// Y, N, O
				// if Y
				if ((reg_choice.toLowerCase()).equals("y")) {
					// set user as R and get user info
					Status = "Registered";
					System.out.println("You have chosen: Registered!");
					System.out.println("Please enter your username: ");
					username = getInput.nextLine();// get and store username
					System.out.println("Please enter your password: ");
					password = getInput.nextLine();// get and store password

				}
				// else if N
				else if (reg_choice.toLowerCase().equals("n")) {
					// ask if they would like to Registerer
					// if yes, ask for username,passord,billing and shipping info

					System.out.println("You have chosen: Unregistered!");
					System.out.println("Would you like to register?(y/n)");
					String RegisterQ = getInput.nextLine();// get and store username
					while (!(RegisterQ.toLowerCase()).equals("y") && !(RegisterQ.toLowerCase()).equals("n")) {
						System.out.println("ERROR: Input must be 'y' or 'n'");
						RegisterQ = getInput.nextLine();
					}
					if (RegisterQ.toLowerCase().equals("y")) {
						System.out.println("Please enter your username: ");
						username = getInput.nextLine();// get and store username
						System.out.println("Please enter your password: ");
						password = getInput.nextLine();// get and store password
						Status = "Registered";
						// add user to system
						registerUser(username,password);
					}
					else if (RegisterQ.toLowerCase().equals("n"))
					{
						// set status
						Status = "Unregistered";
					}

					//String newusername = getInput.nextLine();// get and store username
					//String newpassword = getInput.nextLine();// get and store username

				}
				// Else if owner
				else if ((reg_choice.toLowerCase()).equals("o")) {
					// set user as Owner and get info
					Status = "Owner";
					System.out.println("You have chosen: Owner!");
					System.out.println("Please enter your username: ");
					username = getInput.nextLine();// get and store username
					System.out.println("Please enter your password: ");
					password = getInput.nextLine();// get and store password
				}
				else if ((reg_choice.toLowerCase()).equals("t")) {
					//ask for order number
					System.out.println("You have chosen: track order!");
					System.out.println("Please enter your order number:");
					//display order. If nothing is found, quit
					return;
				}
				else if ((reg_choice.toLowerCase()).equals("q")) {
					//ask for order number
					System.out.println("You have chosen: quit");
					System.out.println("Thank you for visiting the bookstore!");
					break;
					//display order. If nothing is found, quit
					//return;
				}

				// only let users do this, not owner.
				// owner gets its own menu
				// loop starts here, re-loops when book is chosen or goes back
				if ((Status.equals("Registered")) || (Status.equals("Unregistered"))) {
					while(true)//keep asking until they want to quit
					{
						System.out.println("What would you like to do?:");
						System.out.println("(b)rowse");
						System.out.println("(p)urchace");
						System.out.println("(t)rack order");

						System.out.println("(q)uit");
						String user_Choice = getInput.nextLine();// get and store password
						while (!(user_Choice.toLowerCase()).equals("b") && !(user_Choice.toLowerCase()).equals("p")
								&& !(user_Choice.toLowerCase()).equals("q") && !(user_Choice.toLowerCase()).equals("t")) {
							System.out.println("ERROR: Input must be 'b' or 'p' or 'q' or 't'");
							user_Choice = getInput.nextLine();
						}

						if ((user_Choice.toLowerCase()).equals("b")) {
							// when the user chooses a book, it adds book to the cart.
							System.out.println("You have chosen: Browse!");
							System.out.println("How would you like to search for your book?");
							System.out.println("(n)ame");
							System.out.println("(a)uther");
							System.out.println("(g)enre");
							System.out.println("(i)sbn");
							System.out.println("(q)uit");
							String search_book = getInput.nextLine();// get and store username
							while (!(search_book.toLowerCase()).equals("n") && !(search_book.toLowerCase()).equals("a")
									&& !(search_book.toLowerCase()).equals("g")
									&& !(search_book.toLowerCase()).equals("i") && !(search_book.toLowerCase()).equals("q")) {
								System.out.println("ERROR: Input must be 'n' or 'a' or 'g' or 'i' or 'q'");
								search_book = getInput.nextLine();
							}
							if ((search_book.toLowerCase()).equals("q"))// This line of code should show up a lot
							{
								System.out.println("You have chosen to quit. Thank you.");
								return;

								// quit program, give farwell
							} else if ((search_book).equalsIgnoreCase("n")) {
								System.out.println("Please enter required info: .");
								String search_name = getInput.nextLine();// get and store username
								//////////////////////////////////////////////////////////////////

								pStmt = connection.prepareStatement(
										"select title, author, ISBN, genre from book where title = ?"
								);
								pStmt.setString(1, search_name);
								ret = pStmt.executeQuery();

								while(ret.next()) {
									String title = ret.getString(1);
									String author = ret.getString(2);
									String ISBN = ret.getString(3);
									String genre = ret.getString(4);
									System.out.println(title + " " + author + " " + ISBN + " " + genre);
								}
								//ResultSet resultSet = statement.executeQuery("select isbn"+" from book"+" where name = " + search_name);
								//System.out.println("The book you chose is: "+ resultSet);
								//System.out.println("The price of the book is: "+ statement.executeQuery("select price"+" from book"+" where name = " + search_name));

								///////////////////////////////////////////////////////////////////
								System.out.println("Would you like to add to cart?.");
								String get_search_A = getInput.nextLine();

								while (!(get_search_A).equalsIgnoreCase("y") && !(get_search_A).equalsIgnoreCase("n")) {
									System.out.println("ERROR: Input must be'y' or 'n'");
									get_search_A = getInput.nextLine();
								}
								if((get_search_A).equalsIgnoreCase("y"))
								{
									///////////////////////////////////////////////////////////////////
									pStmt = connection.prepareStatement(
											"select prices from book where title =  ?"
									);
									pStmt.setString(1, search_name);
									ret = pStmt.executeQuery();
									while(ret.next()) {
										totalCost += ret.getInt(1);
									}
									///////////////////////////////////////////////////////////////////
									//add book id to array List
									//arraylist.add(bookid);
								}
								// search book by name
							} else if ((search_book).equalsIgnoreCase("a")) {

								System.out.println("Please enter required info: .");
								String search_author = getInput.nextLine();// get and store username
								//////////////////////////////////////////////////////////////////
								pStmt = connection.prepareStatement(
										"select title, author, ISBN, genre from book where author = ?"
								);
								pStmt.setString(1, search_author);
								ret = pStmt.executeQuery();

								while(ret.next()) {
									String title = ret.getString(1);
									String author = ret.getString(2);
									String ISBN = ret.getString(3);
									String genre = ret.getString(4);
									System.out.println(title + " " + author + " " + ISBN + " " + genre);
								}
								///////////////////////////////////////////////////////////////////
								System.out.println("Would you like to add to cart?.");
								String get_search_A = getInput.nextLine();

								while (!(get_search_A.toLowerCase()).equals("y") && !(get_search_A.toLowerCase()).equals("n")) {
									System.out.println("ERROR: Input must be'y' or 'n'");
									get_search_A = getInput.nextLine();
								}
								if((get_search_A).equalsIgnoreCase("y"))
								{
									///////////////////////////////////////////////////////////////////
									//////////////////////
									pStmt = connection.prepareStatement(
											"select prices from book where author =  ?"
									);
									pStmt.setString(1, search_author);
									ret = pStmt.executeQuery();
									while(ret.next()) {
										totalCost += ret.getInt(1);
									}

									///////////////////////////////////////////////////////////////////
									//add book id to array List
									//arraylist.add(bookid);
								}
								// search book by auther name
							} else if ((search_book).equalsIgnoreCase("g")) {
								System.out.println("Please enter required info: .");
								String search_genre = getInput.nextLine();// get and store username
								//////////////////////////////////////////////////////////////////
								pStmt = connection.prepareStatement(
										"select title, author, ISBN, genre from book where genre = ?"
								);
								pStmt.setString(1, search_genre);
								ret = pStmt.executeQuery();

								while(ret.next()) {
									String title = ret.getString(1);
									String author = ret.getString(2);
									String ISBN = ret.getString(3);
									String genre = ret.getString(4);
									System.out.println(title + " " + author + " " + ISBN + " " + genre);
								}
								///////////////////////////////////////////////////////////////////
								System.out.println("Would you like to add to cart?.");
								String get_search_A = getInput.nextLine();

								while (!(get_search_A.toLowerCase()).equals("y") && !(get_search_A.toLowerCase()).equals("n")) {
									System.out.println("ERROR: Input must be'y' or 'n'");
									get_search_A = getInput.nextLine();
								}
								if((get_search_A).equalsIgnoreCase("y"))
								{
									///////////////////////////////////////////////////////////////////
									pStmt = connection.prepareStatement(
											"select prices from book where genre =  ?"
									);
									pStmt.setString(1, search_genre);
									ret = pStmt.executeQuery();
									while(ret.next()) {
										totalCost += ret.getInt(1);
										System.out.println(totalCost);
									}
									///////////////////////////////////////////////////////////////////
									//add book id to array List
									//arraylist.add(bookid);
								}
								// search book by genre
							} else if ((search_book).equalsIgnoreCase("i")) {
								System.out.println("Please enter required info: .");
								String search_isbn = getInput.nextLine();// get and store username
								//////////////////////////////////////////////////////////////////
								pStmt = connection.prepareStatement(
										"select title, author, ISBN, genre from book where ISBN = ?"
								);
								pStmt.setString(1, search_isbn);
								ret = pStmt.executeQuery();

								while(ret.next()) {
									String title = ret.getString(1);
									String author = ret.getString(2);
									String ISBN = ret.getString(3);
									String genre = ret.getString(4);
									System.out.println(title + " " + author + " " + ISBN + " " + genre);
								}
								///////////////////////////////////////////////////////////////////
								System.out.println("Would you like to add to cart?.");
								String get_search_A = getInput.nextLine();
								//book. add to end of arraylist

								while (!(get_search_A.toLowerCase()).equals("y") && !(get_search_A.toLowerCase()).equals("n")) {
									System.out.println("ERROR: Input must be'y' or 'n'");
									get_search_A = getInput.nextLine();
								}
								if((get_search_A).equalsIgnoreCase("y"))
								{
									///////////////////////////////////////////////////////////////////
									pStmt = connection.prepareStatement(
											"select prices from book where ISBN =  ?"
									);
									pStmt.setString(1, search_isbn);
									ret = pStmt.executeQuery();
									while(ret.next()) {
										totalCost += ret.getInt(1);
										System.out.println(totalCost);
									}
									///////////////////////////////////////////////////////////////////
									//add book id to array List
									//arraylist.add(bookid);
								}
								// search book by isbn
							}
							// while (!(get_search_A.toLowerCase()).equals("y") && !(get_search_A.toLowerCase()).equals("n")) {
							// 	System.out.println("ERROR: Input must be'y' or 'n'");
							// 	search_book = getInput.nextLine();
							// }
							//         if((get_search_A.toLowerCase()).equals("y"))
							//         {
							//             //add book id to array List
							//             //arraylist.add(bookid);
							//         }
						} else if ((user_Choice).equalsIgnoreCase("p")) {
							if (Status.equals("Unregistered")) {
								System.out.println("You need to be a member to sign in. The cost of your books are " + totalCost);
								//
								// give error, they can not buy if they are not registered
							} else {
								// check to see if user has chosen a book
								// if not, say error and bring up menu again
								// if so, purchace book from system
								if(totalCost == 0){
									System.out.println("Your cart is empty please select a book to purchase");
									continue;
								}
								System.out.println("You have chosen: Purchase!");
								// ask user to enter info on their billing and shipping
								System.out.println("Please enter your Card info: ");
								String bank_number = getInput.nextLine();// get and billing

								System.out.println("Please enter your address: ");
								String address = getInput.nextLine();// get and store shipping
								// ask if they would like to buy
								System.out.println("Purchase?(y/n)");
								String Purchase_book = getInput.nextLine();// get and store password
								while (!(Purchase_book).equalsIgnoreCase("y") && !(Purchase_book).equalsIgnoreCase("n")) {
									System.out.println("ERROR: Input must be 'y' or 'n'");
									Purchase_book = getInput.nextLine();
								}
								if ((Purchase_book).equalsIgnoreCase("y")) {
									// buy books
									// apply an order number. take the most recent order(if none, start at 001) and
									// ad one to it, or make a random number genre
									// tell user the order number
									while(true) {
										int order_number = random();
										pStmt=connection.prepareStatement("select order_number from check_out where order_number = ?");
										pStmt.setString(1,String.valueOf(order_number));
										ret = pStmt.executeQuery();
										if(ret.getFetchSize() == 0){

											for(int i = 0; i < BooksInCart.size();i++) {
												pStmt = connection.prepareStatement("insert into bookstore values(?,?)");
												pStmt.setString(1, String.valueOf(order_number));
												pStmt.setString(2,BooksInCart.get(i));
												pStmt.executeUpdate();
											}

											pStmt = connection.prepareStatement("insert into check_out values(?,?,?,?)");
											pStmt.setString(1,bank_number);
											pStmt.setString(2,address);
											pStmt.setString(3,username);
											pStmt.setString(4,String.valueOf(order_number));
											pStmt.executeUpdate();


											break;


										}
									}

								/*
								pStmt = connection.prepareStatement(
										"select order_number from check_out"
								);

								ret = pStmt.executeQuery();
								while(ret.next()) {
									 int num = Integer.parseInt(ret.getString(1));
									System.out.println(num);
								}
								*/

									// take one off of stock number for each book purchaced

									// if(books_sold > (statement.executeQuery("select stock"+" from book"+" where
									//name = """ + name_of_book)) + (some set amount))
									// {
									//buy books,, send request to warehouse

									// }

									// make program to check stock of other books. make sure
									// that there is a minimum threshhold for each book ID
									// if the stock is below threshhold, add books by checking the previous months
									// sales
								} else {
									// go back to menu
								}
							}
						} else if ((user_Choice.toLowerCase()).equals("t")) {
							// get order info
							System.out.println("You have chosen: Track order!");
							System.out.println("What is your order number?");
							String Track_order = getInput.nextLine();// get tracking number
							// check if order exists, and if not,
							System.out.println("Your order is:");
							// get date of ordewr(optional)
							// get store name
							// display estimated delivery(2 days)

						} else if ((user_Choice.toLowerCase()).equals("q")) {
							// say goodbye and break from loop
							System.out.println("You have chosen: Quit!");
							// break;
						}
					}
				} else// else if owner
				{
					System.out.println("Welcome back!");
					System.out.println("What would you like to do?:");
					System.out.println("(a)dd");
					System.out.println("(r)emove");
					System.out.println("(s)ales");

					System.out.println("(q)uit");
					String owner_Choice = getInput.nextLine();// get and store password
					while (!(owner_Choice.toLowerCase()).equals("a") && !(owner_Choice.toLowerCase()).equals("r")
							&& !(owner_Choice.toLowerCase()).equals("q") && !(owner_Choice.toLowerCase()).equals("s")) {
						System.out.println("ERROR: Input must be 'a' or 'r' or 's' or 'q'");
						owner_Choice = getInput.nextLine();
					}

					if ((owner_Choice.toLowerCase()).equals("s")) {
						// when the user chooses a book, it adds book to the cart.
						System.out.println("You have chosen: Sales!");
						System.out.println("What sales information would you like to know?");
						System.out.println("(s)ales vs Expendatyures");
						System.out.println("(g)enre sales");
						System.out.println("(a)uther sales");

						System.out.println("(q)uit");
						String search_book = getInput.nextLine();// get and store username
						while (!(search_book.toLowerCase()).equals("s") && !(search_book.toLowerCase()).equals("g")
								&& !(search_book.toLowerCase()).equals("a")
								&& !(search_book.toLowerCase()).equals("q")) {
							System.out.println("ERROR: Input must be'n' or 'a' or 'g' or 'i' or 'q'");
							search_book = getInput.nextLine();
						}
						if ((search_book.toLowerCase()).equals("s"))// This line of code should show up a lot
						{
							// get sales, get expences, calculate profit, display all
						} else if ((search_book.toLowerCase()).equals("g")) {
							// filter by genre, add all up
						} else if ((search_book.toLowerCase()).equals("a")) {
							// filter by auther
						} else if ((search_book.toLowerCase()).equals("q")) {
							// quit program, give farwell
						}
					} else if ((owner_Choice.toLowerCase()).equals("r")) {// get order info
						System.out.println("You have chosen: remove Booksr!");
						System.out.println("Enter the ID of the book you want to remove:");
						String add_book = getInput.nextLine();// get tracking number
						// check if order exists, and if not,
						System.out.println("Enter how many you would like to remove:");
						String num_books = getInput.nextLine();// get tracking number
						// if book is 0, add an "out of stock"
						// use try catch loop
					} else if ((owner_Choice.toLowerCase()).equals("a")) {
						// get order info
						System.out.println("You have chosen: Add Booksr!");
						System.out.println("Enter the ID of the book you want to add:");
						String add_book = getInput.nextLine();// get tracking number
						// check if order exists, and if not,
						System.out.println("Enter how many you would like to add:");
						String num_books = getInput.nextLine();// get tracking number

					} else if ((owner_Choice.toLowerCase()).equals("q")) {
						// say goodbye and break from loop
						System.out.println("You have chosen: Quit!");
						// break;
					}
				}
			}
		}
		catch (Exception sqle) {
			System.out.println("Exception: " + sqle);
		}
	}

	public static int random(){
		int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
		return randomNum;
	}

	public static void registerUser(String user, String pass) throws SQLException {

		ResultSet ret;
		Scanner getInput = new Scanner(System.in);
		while(true) {
			pStmt = connection.prepareStatement(
					"select username from person where username = ?");
			pStmt.setString(1,user);
			ret = pStmt.executeQuery();
			if(ret.getFetchSize() == 0){
				break;
			}
			else{
				System.out.println("Enter a new username, that one has been taken");
				user = getInput.nextLine();
			}
		}
		try {
			pStmt = connection.prepareStatement(
					"insert into person values(?,?)"
			);
			pStmt.setString(1, user);
			pStmt.setString(2, pass);
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
