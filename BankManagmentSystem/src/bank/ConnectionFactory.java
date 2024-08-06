package bank;
import java.sql.*;
public class ConnectionFactory {
	//instance variable
	Connection con;
	Statement stmt;
	
	public ConnectionFactory() {
		try {
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagment","root","Tanjirokamado#8");
			stmt=con.createStatement();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
