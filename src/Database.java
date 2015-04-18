import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
	
	
	public static Connection getConnection()
	{
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ims","root","");
			return con;
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static boolean authenticate(String username, String password)
	{
		Connection con;
		try
		{
			con = getConnection();
			String auth ="SELECT  `username` , `password` FROM `admin` WHERE `username` = ? AND `password` = ?";
			PreparedStatement ps = con.prepareStatement(auth);
			ps.setString(1, username);
			ps.setString(2, password);
			return ps.executeQuery().next();
		}
		catch(Exception e)
		{
			
			System.out.println(e.getMessage() + " " + username + " " + password);
			return false;
		}
		
	}
	
	public static void close(Connection con)
	{
		try
		{
			con.close();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
