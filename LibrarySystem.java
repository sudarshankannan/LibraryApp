import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LibrarySystem{

    private static String PUBLIC_END_POINT = "database-endpoint";
	private static String PORT = "3306";
	private static String DB_NAME = "libdb";
	private static String USER_NAME = "username";
	private static String PASSWORD = "password";
	static Connection conn;
	static Statement stmt;
	static ResultSet result;
	public static void main(String[] args) {
		LibrarySystem.connect();
	}
	//connect
	public static void connect() {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + PUBLIC_END_POINT + ":" + PORT + "/" + DB_NAME, USER_NAME, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeQuery("select * from product");
			while (result.next()) {
				System.out.print(result.getString("name")  + " ");
				System.out.println();
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return;
	}

	//patron functions
	//login
	public int login(int id, int pin){
		String query = String.format("SELECT * WHERE (userID = %d AND pin = %d) FROM libdb.patrons", id, pin);
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			while(result.next()){
				if(result.getInt("userID") != id || result.getInt("pin") != pin){
					System.out.println("Invalid Credentials");
					return -1;
				}
				else{
					System.out.println("Valid Credentials");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	//checkout
	public int checkout(String title, String author){
		int currNum = 0;
		String query = String.format("SELECT numBooks FROM libdb.books WHERE title = %s", title);
		//check if book is available
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			while(result.next()){
				currNum = result.getInt("numBooks");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//if available, check it out
		if(currNum > 0){
			String query2 = String.format("UPDATE libdb.books SET numBooks = %d WHERE title = %s", currNum - 1);
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(query2);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	//return books
	public int returnBook(String title, String author){
		int currNum = 0;
		String query = String.format("SELECT numBooks FROM libdb.books WHERE title = %s", title);
		//get current book count
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			while(result.next()){
				currNum = result.getInt("numBooks");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query2 = String.format("UPDATE libdb.books SET numBooks = %d WHERE title = %s", currNum + 1);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	//signup
	public int signup(String first, String last, int pin){
		String query = String.format("INSERT INTO libdb.patrons (fname, lname, pin) VALUES (%s, %s, %d)", first, last, pin);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}   
		System.out.println("Signed Up");
		return 0;
	}

	//LIBRARIAN FUNCTIONS
	public int adminLogin(int id, int pin){
		String query = String.format("SELECT * WHERE (userID = %d AND pin = %d) FROM libdb.admins", id, pin);
		try {
			stmt = conn.createStatement();
			result = stmt.executeQuery(query);
			while(result.next()){
				if(result.getInt("userID") != id || result.getInt("pin") != pin){
					System.out.println("Invalid Credentials");
					return -1;
				}
				else{
					System.out.println("Valid Credentials");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return 0;
	}
	//add fines
	public int addFines(int patronID, float amount){
		String query = String.format("UPDATE libdb.patrons SET fines = %f WHERE userID = %d",amount, patronID);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}   
		System.out.println("Added Fine");
		return 0;
	}
	//add new book
	public int addNewBook(String bookname, String author){
		String query = String.format("INSERT INTO libdb.books (bookName, author) VALUES (%s, %s)", bookname, author);
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}   
		System.out.println("Added New Book");
		return 0;
	}
}